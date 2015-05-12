package clear.ui.panel;
import javax.swing.*;

import clear.ui.logic.FriendData;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class FriendPanel extends JFrame{

	private static final long serialVersionUID = 1L;
	JTextField cmd = new JTextField();
	JTextArea area = new JTextArea();
	JButton button = new JButton("添加");
	JButton button3 = new JButton("删除");
	
	FriendData data = new FriendData();
	//public static AddFriendClient client = new AddFriendClient();
	
	Socket socket;
	PrintStream output ;
	BufferedReader input ;
	String message="";
	
	public FriendPanel(){
		initFrame();
	}
	
	private void initFrame(){
		
		Container content = getContentPane();
		
		content.setLayout(null);
		
		//输出数据流
		cmd.setBounds(40,10,45,30);
		button.setBounds(90,10,70,23);
		
		button3.setBounds(285,10,70,23);
		area.setBounds(40,70,300,160);
		content.add(cmd) ;
		content.add(button) ;
		
		content.add(button3) ;
		content.add(area);
		button.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				output.println("操作 添加好友申请 "+cmd.getText()+" "+Player.getUserName());
				output.flush();
			}
			
		});
		
		button3.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				output.println("操作 删除好友请求 "+cmd.getText()+" "+Player.getUserName()) ;
				output.flush();
			}
			
		});
		
		setTitle("模拟客户端-添加好友界面") ;
		setSize(400,300);
		setLocation(200,100);
		setVisible(true);
		connect();
		new recThread().start();
		this.addWindowListener(new WindowAdapter() { //响应关闭窗口事件
	         public void windowClosing(WindowEvent e) {
	             disconnect();
	             System.exit(0);
	         }
	     });
	}
	
	public static void main(String[] args){
		new FriendPanel().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void connect(){
		try {
			socket=new Socket("192.168.102.1",10000);
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println("修改 key "+Player.getUserName()) ;
			output.flush();
			
		} catch (UnknownHostException e3) {
			
		} catch (IOException e3) {
			
		}
	}

	class recThread extends Thread{
		public void run(){
			while(true){
				try {
					while((message = input.readLine())!=null){
						String[] temp = message.split(" ") ;
						if(temp.length == 2){
							if(temp[1].equals("已添加您为好友")){
								JOptionPane.showMessageDialog(null, 
										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
							}else if(temp[1].equals("邀请您为好友")){
								int i = JOptionPane.showConfirmDialog(null, 
										   temp[0]+temp[1],"消息",JOptionPane.YES_NO_OPTION);
								if(i == JOptionPane.YES_OPTION){
									output.println("操作 添加好友请求 "+temp[0]
											+" "+Player.getUserName()) ;
									output.flush();
								}else if( i== JOptionPane.NO_OPTION){
									output.println("操作 拒绝添加好友 "+temp[0]
											+" "+Player.getUserName()) ;
									output.flush();
								}
								
							}else if(temp[1].equals("拒绝添加您为好友")){
								JOptionPane.showMessageDialog(null, 
										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
								
							}else if(temp[1].equals("已将您拉黑")){
								JOptionPane.showMessageDialog(null, 
										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
								
							}
						}
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void disconnect() { //窗口关闭时关闭客户端，输入，输出
        try {
        	output.println("验证 注销请求 "+Player.getUserName()) ;
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            
        }
    }
}

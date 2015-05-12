package clear.ui.network;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clear.ui.component.DialogPanel;

public class SignInClient {
	public static Socket socket;
	public static PrintStream output ;
	public static BufferedReader input ;
	String message="";
	
	public SignInClient(){
		
	}
	//参数要改。。
	public String connect(String userName , String userPw ,String userGender ,String userHead
			){
		
		Socket socket;
		try{
			//创建Socket类对象，指定端口号为8998
			socket=new Socket("127.0.0.1",10000);
			//创建输入、输出流对象
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//输出数据流
			output.println("验证 注册请求 "+userName+" "+userPw+" "+userGender
					+" "+userHead);
			output.flush();
			
			try{
			   message=(String)input.readLine();
			   
			}catch(IOException e){
//				   JOptionPane.showMessageDialog(null,
//						   "无法获取服务器信息！","Error",JOptionPane.INFORMATION_MESSAGE);
				TipsDialog td = new TipsDialog("无法获取服务器信息");
			}
			output.close();
			input.close();
			socket.close();
			}catch(EOFException eof)
			{
//				JOptionPane.showMessageDialog(null,
//						   "服务器中断！","Error",JOptionPane.INFORMATION_MESSAGE);
				TipsDialog td  = new TipsDialog("服务器中断");

			}catch(IOException e)
			{
				e.printStackTrace();
			}
		
		return message ;
	}
	private class TipsDialog extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel cancle;
		public TipsDialog(String msgs){
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/warning.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			msg = new JLabel(msgs);
			msg.setFont(new Font("幼圆",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// 设置组件大小
			close.setSize(36, 36);
			yes.setSize(78, 32);
			msg.setSize(300,18);
			// 设置组件位置
			close.setLocation(353, 7);
			yes.setLocation(253,147);
			msg.setLocation(155,93);
			// 设置组件监听
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// 添加组件
			backPanel.add(close);
			backPanel.add(yes);
			backPanel.add(msg);
			getContentPane().add(backPanel);
			setUndecorated(true);
			setLocation(927/2, 661/2);
			setSize(backPanel.getWidth(),backPanel.getHeight());
			System.out.println(backPanel.getWidth());
			setVisible(true);
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Object source = arg0.getSource();
			if (source == yes){
				dispose();
			}else if (source == close){
				dispose();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}

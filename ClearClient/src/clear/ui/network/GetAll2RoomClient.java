package clear.ui.network;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clear.ui.component.DialogPanel;
import clear.ui.panel.IPAddress;
import clear.ui.panel.Player;

public class GetAll2RoomClient {
	public static Socket socket;
	public static PrintStream output ;
	public static BufferedReader input ;
	String message="";
	ArrayList<String> messages = new ArrayList<String>();
	public GetAll2RoomClient(){
		
	}
	//参数要改。。
	public String connect(){
		
		Socket socket;
		try{
			new IPAddress();
			//创建Socket类对象，指定端口号为8998
			socket=new Socket(IPAddress.getIP(),10000);
			//创建输入、输出流对象
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//输出数据流
			output.println("修改 key "+Player.getUserName()) ;
			output.flush();
			output.println("操作 显示所有房间2 "+Player.getUserName());
			output.flush();
			message = input.readLine();
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		TipsDialog td = new TipsDialog("服务器中断");
		}
			
		//new revThread().start();
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

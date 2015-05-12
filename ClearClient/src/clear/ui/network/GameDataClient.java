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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clear.ui.component.DialogPanel;
import clear.ui.panel.IPAddress;
import clear.ui.panel.Player;

public class GameDataClient {
	
	public static Socket socket;
	public static PrintStream output ;
	public static BufferedReader input ;
	String message="";
	public GameDataClient(){
		
	}
	//����Ҫ�ġ���
	public String connect(String data){
		
		Socket socket;
		try{
			new IPAddress();
			//����Socket�����ָ���˿ں�Ϊ8998
			socket=new Socket(IPAddress.getIP(),10000);
			//�������롢���������
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//���������
			output.println("�޸� key "+Player.getUserName()) ;
			output.flush();
			output.println("���� ��ȡ�û����� "+data);
			output.flush();
			
		    message=(String)input.readLine();
		
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			TipsDialog td = new TipsDialog("�������ж�");
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
			msg.setFont(new Font("��Բ",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// ���������С
			close.setSize(36, 36);
			yes.setSize(78, 32);
			msg.setSize(300,18);
			// �������λ��
			close.setLocation(353, 7);
			yes.setLocation(253,147);
			msg.setLocation(155,93);
			// �����������
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// �������
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
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
	//����Ҫ�ġ���
	public String connect(String userName , String userPw ,String userGender ,String userHead
			){
		
		Socket socket;
		try{
			//����Socket�����ָ���˿ں�Ϊ8998
			socket=new Socket("127.0.0.1",10000);
			//�������롢���������
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//���������
			output.println("��֤ ע������ "+userName+" "+userPw+" "+userGender
					+" "+userHead);
			output.flush();
			
			try{
			   message=(String)input.readLine();
			   
			}catch(IOException e){
//				   JOptionPane.showMessageDialog(null,
//						   "�޷���ȡ��������Ϣ��","Error",JOptionPane.INFORMATION_MESSAGE);
				TipsDialog td = new TipsDialog("�޷���ȡ��������Ϣ");
			}
			output.close();
			input.close();
			socket.close();
			}catch(EOFException eof)
			{
//				JOptionPane.showMessageDialog(null,
//						   "�������жϣ�","Error",JOptionPane.INFORMATION_MESSAGE);
				TipsDialog td  = new TipsDialog("�������ж�");

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
			// ������
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

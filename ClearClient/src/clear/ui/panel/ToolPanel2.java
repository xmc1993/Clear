package clear.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clear.ui.data.HomeMsg;
import clear.ui.logic.RoomDataService;
import clear.ui.logic.RoomDataServiceInterface;
import clear.ui.network.GetAllRoom1Client;
import clear.ui.test.TestDrive;

public class ToolPanel2 extends JPanel implements MouseListener {
	ImageIcon imageIcon;
	int width;
	int hight;
	JLabel tool1;
	JLabel tool2;
	JLabel tool3;
	JPanel start;
	JPanel back;
	JLabel paymoney;
	JLabel leftmoney;
	int initemoney;
	int spend;
	Font font;
	boolean t1 = false;
	boolean t2 = false;
	boolean t3 = false;
	JLabel tips;
	Socket socket;
	PrintStream output ;
	BufferedReader input ;
	String message="";
	String roomMaster ;
	String roomName ;
	String tag;
	int tag2 ;
	JFrame bootFrame;
	private JLabel minButton;
	private JLabel quitButton;
	public ToolPanel2(String roomName ,String roomMaster,int initemoney,String path
			,String tag , int tag2,JFrame boot) {
		bootFrame = boot;
		this.tag = tag ;
		this.tag2 = tag2;
		spend = 0;
		this.roomName = roomName ;
		this.roomMaster = roomMaster ;
		this.initemoney = initemoney;
		imageIcon = new ImageIcon(path);
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		tips = new JLabel();
		font = new Font("Dialog", 1, 15);
		tool1 = new JLabel();
		tool2 = new JLabel();
		tool3 = new JLabel();
		paymoney = new JLabel();
		leftmoney = new JLabel("" + initemoney);
		paymoney.setFont(font);
		leftmoney.setFont(font);
		paymoney.setForeground(Color.black);
		leftmoney.setForeground(Color.black);
		back = new JPanel();
		start = new JPanel();
		back.setVisible(true);
		start.setVisible(true);
		back.setOpaque(false);
		start.setOpaque(false);
		
		tips.setSize(435, 37);
		back.setSize(111, 47);
		start.setSize(179, 59);
		tool3.setSize(137, 223);
		tool2.setSize(137, 223);
		tool1.setSize(137, 223);
		paymoney.setSize(77, 35);
		leftmoney.setSize(77, 35);

		tips.setLocation(154, 455);
		back.setLocation(83, 578);
		start.setLocation(382, 566);
		tool1.setLocation(155, 193);
		tool2.setLocation(397, 193);
		tool3.setLocation(639, 193);
		paymoney.setLocation(708, 455);
		leftmoney.setLocation(799, 586);

		tool1.addMouseListener(this);
		tool2.addMouseListener(this);
		tool3.addMouseListener(this);
		back.addMouseListener(this);
		start.addMouseListener(this);
		setLayout(null);
		add(tips);
		add(tool1);
		add(tool2);
		add(tool3);
		add(paymoney);
		add(leftmoney);
		add(back);
		add(start);
		connect();
		new recThread().start();
	}
	public void connect(){
		try {
			socket=new Socket(IPAddress.getIP(),10000);
			output=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println("修改 key "+Player.getUserName()) ;
			output.flush();
			
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
						}else{
							if(temp[0].equals("系统消息:")){
								if(temp[2].equals("邀请您加入他/她的")){
									int i = JOptionPane.showConfirmDialog(null, 
											   temp[1]+temp[2]+temp[3]+temp[4],
											   "消息",JOptionPane.YES_NO_OPTION);
									if(i == JOptionPane.YES_OPTION){
										output.println("操作 同意加入房间 "+temp[3]+" "+temp[1]
												+" "+Player.getUserName()) ;
										output.flush();
									}else if( i== JOptionPane.NO_OPTION){
										output.println("操作 拒绝加入房间 "+temp[1]
												+" "+Player.getUserName()) ;
										output.flush();
									}
								}else if(temp[2].equals("同意加入您的房间")
										||temp[2].equals("拒绝加入您的房间")||
									temp[2].equals("允许您加入TA的房间")||
									temp[2].equals("拒绝您加入ta的房间")||
									temp[2].equals("已经离开您的房间")
									||temp[2].equals("进入了房间")
									||temp[2].equals("说:")){
									if(temp.length == 3){
										 JOptionPane.showMessageDialog(null, 
												 (temp[1]+temp[2]),"登录失败",JOptionPane.INFORMATION_MESSAGE); ;
									}else{
										JOptionPane.showMessageDialog(null, 
												 (temp[1]+" 说: "+temp[3]),"",JOptionPane.INFORMATION_MESSAGE); ;
										
									}
									
								}else if(temp[2].equals("已经准备就绪")){
									JOptionPane.showMessageDialog(null, 
											 (temp[1]+" "+temp[3]),"",JOptionPane.INFORMATION_MESSAGE); ;

								}else if(temp[2].equals("申请加入您的")){
									int i = JOptionPane.showConfirmDialog(null, 
											   temp[1]+temp[2],"消息",JOptionPane.YES_NO_OPTION);
									
									if(i == JOptionPane.YES_OPTION){
										output.println("操作 允许加入房间 "+temp[3]+" "+temp[1]
												+" "+Player.getUserName()) ;
										output.flush();
									}else if( i== JOptionPane.NO_OPTION){
										output.println("操作 不许加入房间 "+temp[1]
												+" "+Player.getUserName()) ;
										output.flush();
									}
								}
								if(temp[1].equals("您已被")){
									JOptionPane.showMessageDialog(null, 
											  temp[1]+temp[2]+temp[3],"消息",JOptionPane.INFORMATION_MESSAGE);
								}
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
	public void paintComponent(Graphics g) {
		Image image = imageIcon.getImage();
		g.drawImage(image, 0, 0, this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == tool1) {
			if (!t1) {
				if(Integer.parseInt(leftmoney.getText())<60){
					
				}else{
					tool1.setIcon(new ImageIcon("src/images/toolpanel/tool2.png"));
					spend = spend + 60;
					t1 = true;
				}
				
			} else {
				tool1.setIcon(null);
				spend = spend -60;
				t1 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t2.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool1");
		} else if (source == tool2) {
			if(!t2){
				if(Integer.parseInt(leftmoney.getText())<480){
					
				}else{
					tool2.setIcon(new ImageIcon("src/images/toolpanel/tool1.png"));
					spend = spend + 480;
					t2 = true;
				}
			
			}else{
				tool2.setIcon(null);
				spend = spend - 480;
				t2 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t1.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool2");
			
		} else if (source == tool3) {
			if(!t3){
				if(Integer.parseInt(leftmoney.getText())<880){
					
				}else{
					tool3.setIcon(new ImageIcon("src/images/toolpanel/tool3.png"));
					spend = spend + 880;
					t3 = true;
				}
		
			}else{
				tool3.setIcon(null);
				spend = spend-880;
				t3 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t3.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool3");
		}else if(source == back){
			removeAll();
			ModelPanel model =new ModelPanel(bootFrame);
			model.setSize(927, 661);
			model.setLocation(0, 0);
			add(model);
			repaint();
		}else if(source == start){
			if(tag2 == 0){
				GamePanel2 gp = new GamePanel2(roomMaster,roomName,Integer.parseInt(tag),bootFrame);
				
				removeAll();
				gp.setSize(927, 661);
				gp.setLocation(0, 0);
				add(gp);
				repaint();
			}else{
				GamePanel3 gp = new GamePanel3(roomMaster,roomName,Integer.parseInt(tag),bootFrame);
				removeAll();
				gp.setSize(927, 661);
				gp.setLocation(0, 0);
				add(gp);
				repaint();
			}
			
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}
			
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

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

//	public static void main(String[] args) {
//		TestDrive td = new TestDrive();
//		td.test(new ToolPanel(0,"src/images/toolpanel/toolpanel.png"));
//	}
}

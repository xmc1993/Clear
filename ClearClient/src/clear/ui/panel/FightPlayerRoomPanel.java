package clear.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clear.ui.component.DialogPanel;
import clear.ui.component.MyButton;
import clear.ui.component.MyPanel;
import clear.ui.logic.RoomDataService;
import clear.ui.logic.RoomDataServiceInterface;
import clear.ui.panel.CoopHosterRoomPanel.FriendsDialog;
import clear.ui.panel.FightHosterRoomPanel.recThread;
import enupackage.ButtonType;
import enupackage.PanelType;

public class FightPlayerRoomPanel extends JPanel implements MouseListener{

	private Image backGround;
	private Image addImage;
	private Image readyImage;
	private Image waitImage;
	private Image hosterImage;
	private Image playerImage;
	private Image f_playerImage;
	private Image f_waitImage;
	private Image f_addImage;
	private Image headImage[];
	private JLabel readyLabel;
	private JLabel backLabel;
	private JPanel panel[];
	private int state[];
	private int head[];
	private boolean ready[];
	private String name[];
	private String level[];
	private String gold[];
	private Location loc[];
	JFrame bootframe;
	boolean isunlock = true;
	JTextArea content;
	JTextField msg;
	JLabel send;
	Socket socket;
	PrintStream output ;
	BufferedReader input ;
	String message="";String roomMaster ;
	String roomName ;
	private JLabel minButton;
	private JLabel quitButton;
	boolean hasSomeOne = false ;
	JFrame bootFrame;
	int index = 0 ;
	String name2[] ;int head2[];String level2[];String gold2[] ;
	/*说明name level gold 均为4位数组 包含四个玩家的信息 没有达到四个 可以为""*/
	/*ready为 3为代表非房主的 1、2、3个玩家 的ready信息 例如房主新建房间时 均为false*/
	/*state代表非房主的另外三个房间的状态  0 代表 加号状态 1 代表等待状态 2 代表 有人状态*/
	public FightPlayerRoomPanel(String roomName ,String roomMaster ,String name[],int head[],String level[],
			String gold[],boolean ready[],int state[], 
			String name2[] , int head2[],String level2[],String gold2[],JFrame boot){
		
		bootframe = boot;
		this.name2 = name2;
		this.head2 = head2 ;
		this.level2 = level2;
		this.gold2 = gold2 ;
		this.name=name;this.roomName = roomName ;
		this.roomMaster = roomMaster ;
		this.head=head;
		this.level=level;
		this.gold=gold;
		this.ready=ready;
		this.state=state;
		
		for(int i:this.state){
			System.out.println(i);
		}
		setLayout(null);
		init();
		
		repaint();
		
	}
	
	private void init(){
		initImage();
		
		if(name[1].equals(Player.getUserName())){
			index = 1;
		}
		if(name2[0].equals(Player.getUserName())){
			index = 2;
		}else if(name2[1].equals(Player.getUserName())){
			index = 3;
		}
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		send = new JLabel(new ImageIcon("src/images/readypanel/send.png"));
		send.setSize(84, 51);
		send.setLocation(795, 538);
		send.addMouseListener(this);
		msg = new JTextField();
		msg.setOpaque(false);
		msg.setBorder(null);
		content = new JTextArea();
		content.setOpaque(false);
		content.setBorder(null);
		msg.setSize(163, 36);
		msg.setFont(new Font("Dialog", 1, 25));
		content.setSize(265, 386);
		content.setFont(new Font("Dialog", 1, 25));
		content.setEditable(false);
		msg.setLocation(623, 549);
		content.setLocation(611, 140);
		readyLabel=new JLabel(new ImageIcon("src/images/CoopRoom/ready_0.png"));
		readyLabel.setBounds(340, 508, 134, 116);
		readyLabel.addMouseListener(this);
		backLabel=new JLabel();
		backLabel.setBounds(87, 578,103,48);
		backLabel.addMouseListener(this);
		add(backLabel);
		add(readyLabel);
		panel=new JPanel[3];
		for(int i=0;i<3;i++){
			panel[i]=new JPanel();
			panel[i].setOpaque(false);
			panel[i].addMouseListener(this);
			panel[i].setSize(242,178);
		}
		panel[0].setLocation(297,65);
		panel[1].setLocation(61, 238);
		panel[2].setLocation(297,238);
		add(panel[0]);
		add(panel[1]);
		add(panel[2]);
		add(msg);
		add(content);
		add(send);
		loc=new Location[4];
		loc[0]=new Location(61,65);
		loc[1]=new Location(297,65);
//340, 486   340-297=43 486-65=423
		loc[2]=new Location(61,238);
		loc[3]=new Location(297,238);
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
			
		} catch (UnknownHostException e3) {
			
		} catch (IOException e3) {
			
		}
	}
	class recThread extends Thread{
		public void run(){
			while(true){
				try {
					while((message = input.readLine())!=null){
System.out.println(message);
						String[] temp = message.split(" ") ;
						if(temp.length == 2){
							if(temp[1].equals("已添加您为好友")){
//								JOptionPane.showMessageDialog(null, 
//										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
								TipsDialog td = new TipsDialog(temp[0]+temp[1]);
								
							}else if(temp[1].equals("邀请您为好友")){
//								int i = JOptionPane.showConfirmDialog(null, 
//										   temp[0]+temp[1],"消息",JOptionPane.YES_NO_OPTION);
//								if(i == JOptionPane.YES_OPTION){
//									output.println("操作 添加好友请求 "+temp[0]
//											+" "+Player.getUserName()) ;
//									output.flush();
//									
//								}else if( i== JOptionPane.NO_OPTION){
//									output.println("操作 拒绝添加好友 "+temp[0]
//											+" "+Player.getUserName()) ;
//									output.flush();
//								}
								AskDialog1 ad = new AskDialog1(message);
								
							}else if(temp[1].equals("拒绝添加您为好友")){
//								JOptionPane.showMessageDialog(null, 
//										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
								TipsDialog td = new TipsDialog(temp[0]+temp[1]);
								
							}else if(temp[1].equals("已将您拉黑")){
//								JOptionPane.showMessageDialog(null, 
//										   temp[0]+temp[1],"消息",JOptionPane.INFORMATION_MESSAGE);
								TipsDialog td = new TipsDialog(temp[0]+temp[1]);
							}
						}else{
							if(temp[0].equals("系统消息:")){
								if(temp[2].equals("拒绝您加入ta的房间")){

//									 JOptionPane.showMessageDialog(null, 
//											   message,"登录失败",JOptionPane.INFORMATION_MESSAGE);
									TipsDialog td = new TipsDialog(message);
								}else if(temp[2].equals("允许您加入TA的房间")){
									index = Integer.parseInt(temp[3]) ;
									state[index-1] = 2 ;
									head[index] =Player.getHead() ;
									level[index] =Player.getLvl()+"";
									gold[index] =Player.getGold()+"" ;
									repaint();
								}else if(temp[2].equals("已经准备就绪")){
									 int x = Integer.parseInt(temp[3]) ;
									 ready[x] = true ;
									 repaint();
								}else if(temp[2].equals("发出协作邀请")){
//									int i = JOptionPane.showConfirmDialog(null, 
//											   temp[1]+temp[2],"消息",JOptionPane.YES_NO_OPTION);
//									
//									if(i == JOptionPane.YES_OPTION){
//										
//										index = Integer.parseInt(temp[5]) ;//!!!!
//										state[index-1] = 2 ;
//										head[index] =Player.getHead() ;
//										level[index] =Player.getLvl()+"";
//										gold[index] =Player.getGold()+"" ;
//										repaint();
//										hasSomeOne = true ;
//										output.println("操作 同意加入房间2 0 "+index+" "+temp[3]+" "+temp[1]
//												+" "+Player.getUserName()+" "+Player.getHead()+" "+
//												Player.getLvl()+" "+Player.getGold()) ;
//										output.flush();
//										
//									}else if( i== JOptionPane.NO_OPTION){
//										output.println("操作 拒绝加入房间2 "+temp[1]
//												+" "+Player.getUserName()) ;
//										output.flush();
//									}
									AskDialog3 ad = new AskDialog3(message);
								}else if(temp[2].equals("发出对战邀请")){
//									int i = JOptionPane.showConfirmDialog(null, 
//											   temp[1]+temp[2],"消息",JOptionPane.YES_NO_OPTION);
//									if(i == JOptionPane.YES_OPTION){
//										index = Integer.parseInt(temp[5]) ;//!!!!
//										state[index-1] = 2 ;
//										head[index] =Player.getHead() ;
//										level[index] =Player.getLvl()+"";
//										gold[index] =Player.getGold()+"" ;
//										repaint();
//										hasSomeOne = true ;
//										output.println("操作 同意加入房间2 1 "+index+" "+temp[3]+" "+temp[1]
//												+" "+Player.getUserName()+" "+Player.getHead()+" "+
//												Player.getLvl()+" "+Player.getGold()) ;
//										output.flush();
//										
//										
//									
//									}else if( i== JOptionPane.NO_OPTION){
//										output.println("操作 拒绝加入房间2 "+temp[1]
//												+" "+Player.getUserName()) ;
//										output.flush();
//									}
									AskDialog4 ad = new AskDialog4(message);
								}
								if(temp[1].equals("您已被")){
//									JOptionPane.showMessageDialog(null, 
//											  temp[1]+temp[2]+temp[3],"消息",JOptionPane.INFORMATION_MESSAGE);
									TipsDialog td = new TipsDialog( temp[1]+temp[2]+temp[3]);
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
	
	private void change1(String message,int type) throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			output.println("操作 添加好友请求 "+temp[0]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type ==1){
			output.println("操作 拒绝添加好友 "+temp[0]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change3(String message,int type)throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			index = Integer.parseInt(temp[5]) ;//!!!!
			state[index-1] = 2 ;
			head[index] =Player.getHead() ;
			level[index] =Player.getLvl()+"";
			gold[index] =Player.getGold()+"" ;
			repaint();
			hasSomeOne = true ;
			output.println("操作 同意加入房间2 0 "+index+" "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()+" "+Player.getHead()+" "+
					Player.getLvl()+" "+Player.getGold()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 拒绝加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change4(String message,int type)throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			index = Integer.parseInt(temp[5]) ;//!!!!
			state[index-1] = 2 ;
			head[index] =Player.getHead() ;
			level[index] =Player.getLvl()+"";
			gold[index] =Player.getGold()+"" ;
			repaint();
			hasSomeOne = true ;
			output.println("操作 同意加入房间2 1 "+index+" "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()+" "+Player.getHead()+" "+
					Player.getLvl()+" "+Player.getGold()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 拒绝加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	
	
	private void initImage(){
		backGround=new ImageIcon("src/images/CoopRoom/backGround.png").getImage();
		readyImage=new ImageIcon("src/images/CoopRoom/ok.png").getImage();
		waitImage=new ImageIcon("src/images/CoopRoom/wait.png").getImage();
		hosterImage=new ImageIcon("src/images/CoopRoom/hoster.png").getImage();
		playerImage=new ImageIcon("src/images/CoopRoom/player.png").getImage();
		addImage=new ImageIcon("src/images/CoopRoom/add.png").getImage();
		f_playerImage=new ImageIcon("src/images/CoopRoom/f_player.png").getImage();
		f_addImage=new ImageIcon("src/images/CoopRoom/f_add.png").getImage();
		f_waitImage =new ImageIcon("src/images/CoopRoom/f_wait.png").getImage();
		headImage=new Image[6];
		for(int i=0;i<6;i++){
			headImage[i]=new ImageIcon("src/images/chatHead/head"+i+".jpg").getImage();
		}
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(backGround,0,0,927,661,this);
		g2d.drawImage(hosterImage,61,65,242,178,this);
		g2d.drawImage(headImage[head[0]],loc[0].x+40,loc[0].y+21,67,48,this);
		g2d.setFont(new Font("幼圆",1,24));
		g2d.setColor(Color.white);
		g2d.drawString(name[0],loc[0].x+119,loc[0].y+54);
		g2d.drawString(level[0], loc[0].x+97, loc[0].y+109);
		g2d.drawString(gold[0], loc[0].x+97, loc[0].y+154);

		switch(state[0]){
		case 0:    
			
			g2d.drawImage(addImage,loc[1].x,loc[1].y,242,178,this);
			
			break;
		case 1:
			
			g2d.drawImage(f_waitImage, loc[1].x,loc[1].y,242,178,this);
			
			break;
		case 2:
			
			g2d.drawImage(f_playerImage,loc[1].x,loc[1].y,242,178,this);
			
			g2d.drawImage(headImage[head[1]],loc[1].x+40,loc[1].y+21,67,48,this);
			g2d.drawString(name[1],loc[1].x+119,loc[1].y+54);
			g2d.drawString(level[1], loc[1].x+97, loc[1].y+109);
			g2d.drawString(gold[1], loc[1].x+97, loc[1].y+154);
			break;
			
			
		}
		switch(state[1]){
		case 0:    
			
			g2d.drawImage(f_addImage,loc[2].x,loc[2].y,242,178,this);
			
			break;
		case 1:
			
				g2d.drawImage(f_waitImage, loc[2].x,loc[2].y,242,178,this);
			
			break;
		case 2:
			
				g2d.drawImage(f_playerImage,loc[2].x,loc[2].y,242,178,this);
			
			g2d.drawImage(headImage[head2[0]],loc[2].x+40,loc[2].y+21,67,48,this);
			g2d.drawString(name2[0],loc[2].x+119,loc[2].y+54);
			g2d.drawString(level2[0], loc[2].x+97, loc[2].y+109);
			g2d.drawString(gold2[0], loc[2].x+97, loc[2].y+154);
			break;
			
			
		}switch(state[2]){
		case 0:    
			
			g2d.drawImage(f_addImage,loc[3].x,loc[3].y,242,178,this);
			
			break;
		case 1:
			
				g2d.drawImage(f_waitImage, loc[3].x,loc[3].y,242,178,this);
			
			break;
		case 2:
			
				g2d.drawImage(f_playerImage,loc[3].x,loc[3].y,242,178,this);
			
			g2d.drawImage(headImage[head[1]],loc[3].x+40,loc[2].y+21,67,48,this);
			g2d.drawString(name2[1],loc[3].x+119,loc[3].y+54);
			g2d.drawString(level2[1], loc[3].x+97, loc[3].y+109);
			g2d.drawString(gold2[1], loc[3].x+97, loc[3].y+154);
			break;
			
		}
			for(int j = 0 ;j<4 ; j++){
				if(ready[j]){
					g2d.drawImage(readyImage,loc[j].x, loc[j].y, 62,64,this);
				}
			}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source =e.getSource();
		if(isunlock){
		if(source==readyLabel){
			removeAll();
			
			ToolPanel2 tp = new ToolPanel2(roomName,
					roomMaster,Player.getGold(),"src/images/toolpanel/toolpanel2.png",index+"" ,1,bootframe);
			tp.setSize(927, 661);
			tp.setLocation(0, 0);
			add(tp);
			repaint();
			repaint();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source==panel[0]){
			if(state[0]==0){
				output.println("操作 加入房间申请2 0 "+roomName+" "+roomMaster+" "+Player.getUserName()
						+" "+Player.getHead()+" "+Player.getLvl()+" "+Player.getGold());
				output.flush();
				state[0] = 1;
				repaint();
			}else if(state[0]==1){
				return;
			}else if(state[0]==2){
				return;
			}
			
		}else if(source==panel[1]){
			if(state[1]==0){
				output.println("操作 加入房间申请2 1 "+roomName+" "+roomMaster+" "+Player.getUserName()
						+" "+Player.getHead()+" "+Player.getLvl()+" "+Player.getGold());
				output.flush();
				
				state[1] = 1;
				repaint();
			}else if(state[1]==1){
				return;
			}else if(state[1]==2){
				return;
			}
		}else if(source==panel[2]){
			if(state[2]==0){
				output.println("操作 加入房间申请2 2 "+roomName+" "+roomMaster+" "+Player.getUserName()
						+" "+Player.getHead()+" "+Player.getLvl()+" "+Player.getGold());
				output.flush();
				
				state[2] = 1;
				repaint();
			}else if(state[2]==1){
				return;
			}else if(state[2]==2){
				return;
			}
		}else if(source==backLabel){   //返回上一个界面 
			removeAll();
			RoomDataServiceInterface data = new RoomDataService();
			
			ArrayList<String> homeInfos = data.room2Info() ;

			FightGameHall hm =new FightGameHall(homeInfos,1,bootFrame);
			removeAll();
			hm.setSize(927, 661);
			hm.setLocation(0, 0);
			add(hm);
			repaint();
		}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class Location{
		public int x;
		public int y;
		public Location(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	private void lock(){
		isunlock = false;
	}
	private void unlock(){
		isunlock = true;
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
	private class AskDialog1 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog1(String msg0){
			tempMsg = msg0;
			String[] temp = msg0.split(" ");
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/ask.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			no = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/refuse.png"));
			msg = new JLabel(temp[0]+temp[1]);
			msg.setFont(new Font("幼圆",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// 设置组件大小
			close.setSize(36, 36);
			yes.setSize(78, 32);
			no.setSize(78, 32);
			msg.setSize(300,18);
			// 设置组件位置
			close.setLocation(353, 7);
			yes.setLocation(151,157);
			no.setLocation(271, 157);
			msg.setLocation(155,93);
			// 设置组件监听
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// 添加组件
			backPanel.add(close);
			backPanel.add(yes);
			backPanel.add(msg);
			backPanel.add(no);
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
				try {
					change1(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change1(tempMsg,1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class AskDialog3 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog3(String msg0){
			tempMsg = msg0;
			String[] temp = msg0.split(" ");
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/ask.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			no = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/refuse.png"));
			msg = new JLabel(temp[1]+temp[2]);
			msg.setFont(new Font("幼圆",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// 设置组件大小
			close.setSize(36, 36);
			yes.setSize(78, 32);
			no.setSize(78, 32);
			msg.setSize(300,18);
			// 设置组件位置
			close.setLocation(353, 7);
			yes.setLocation(151,157);
			no.setLocation(271, 157);
			msg.setLocation(155,93);
			// 设置组件监听
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// 添加组件
			backPanel.add(close);
			backPanel.add(yes);
			backPanel.add(msg);
			backPanel.add(no);
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
				try {
					change3(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change3(tempMsg,1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	private class AskDialog4 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog4(String msg0){
			tempMsg = msg0;
			String[] temp = msg0.split(" ");
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/ask.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			no = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/refuse.png"));
			msg = new JLabel(temp[1]+temp[2]);
			msg.setFont(new Font("幼圆",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// 设置组件大小
			close.setSize(36, 36);
			yes.setSize(78, 32);
			no.setSize(78, 32);
			msg.setSize(300,18);
			// 设置组件位置
			close.setLocation(353, 7);
			yes.setLocation(151,157);
			no.setLocation(271, 157);
			msg.setLocation(155,93);
			// 设置组件监听
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// 添加组件
			backPanel.add(close);
			backPanel.add(yes);
			backPanel.add(msg);
			backPanel.add(no);
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
				try {
					change4(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change4(tempMsg,1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}

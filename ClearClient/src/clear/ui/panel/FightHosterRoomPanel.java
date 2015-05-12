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
import clear.ui.panel.CoopHosterRoomPanel.recThread;
import enupackage.ButtonType;
import enupackage.PanelType;

public class FightHosterRoomPanel extends JPanel implements MouseListener{
	private Image backGround;
	private Image addImage;
	private Image readyImage;
	private Image waitImage;
	private Image hosterImage;
	private Image playerImage;
	private Image headImage[];
	private Image f_waitImage;
	private Image f_addImage;
	private Image f_playerImage;
	private JLabel readyLabel;
	private JLabel backLabel;
	private JPanel panel[];
	private JPanel d_panel[];
	private JLabel minButton;
	private JLabel quitButton;
	private int state[];
	private int head[];
	private boolean ready[];
	private String name[];
	private String level[];
	private String gold[];
	private Location loc[];
	boolean isunlock = true;
	JTextArea content;
	JTextField msg;
	JLabel send;
	
	String roomMaster ;
	String roomName ;
	
	Socket socket;
	PrintStream output ;
	BufferedReader input ;
	String message="";
	
	boolean hasSb = false;
	boolean hasSb2 = false ;
	
	String name2[] ;int head2[];String level2[];String gold2[] ;
	JFrame bootFrame;
	/*说明name level gold 均为4位数组 包含四个玩家的信息 没有达到四个 可以为""*/
	/*ready为 3为代表非房主的 1、2、3个玩家 的ready信息 例如房主新建房间时 均为false*/
	/*state代表非房主的另外三个房间的状态  0 代表 加号状态 1 代表等待状态 2 代表 有人状态*/
	public FightHosterRoomPanel(String roomName ,String roomMaster ,String name[],int head[],String level[],String gold[],boolean ready[],int state[], 
			String name2[] , int head2[],String level2[],String gold2[],JFrame boot){
		bootFrame = boot;
		this.name2 = name2;
		this.head2 = head2 ;
		this.level2 = level2;
		this.gold2 = gold2 ;
		this.name=name;
		this.roomName = roomName ;
		this.roomMaster = roomMaster ;
		this.head=head;
		this.level=level;
		this.gold=gold;
		this.ready=ready;
		this.state=state;
		
		setLayout(null);
		init();
for(String s : name2){
	System.out.println("name2:"+s);
}
for(int i : head2){
	System.out.println("head2:"+i);
}
for(String i : level2){
	System.out.println("level2:"+i);
}
for(String i : gold2){
	System.out.println("gold2"+i);
}
for(int i : state){
	System.out.println("state"+i);
}
	}
	
	private void init(){
		initImage();
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
		d_panel=new JPanel[3];
		for(int i=0;i<3;i++){
			panel[i]=new JPanel();
			d_panel[i]=new JPanel();
			panel[i].setOpaque(false);
			d_panel[i].setOpaque(false);
			panel[i].addMouseListener(this);
			d_panel[i].addMouseListener(this);
			panel[i].setSize(242,178);
			d_panel[i].setSize(55,55);
		}		
		panel[0].setLocation(297,65);
		panel[1].setLocation(61, 238);
		panel[2].setLocation(297,238);
		d_panel[0].setLocation(490, 200);
		d_panel[1].setLocation(254,373);
		d_panel[2].setLocation(490,373);
		add(d_panel[0]);
		add(d_panel[1]);
		add(d_panel[2]);
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
								TipsDialog td = new TipsDialog(temp[0]+temp[1]);
								
							}else if(temp[1].equals("邀请您为好友")){
							AskDialog1 ad = new AskDialog1(message);
							}else if(temp[1].equals("拒绝添加您为好友")){
								TipsDialog td = new TipsDialog(temp[0]+temp[1]);
								
							}else if(temp[1].equals("已将您拉黑")){
								TipsDialog td =new TipsDialog(temp[0]+temp[1]);
								
							}
						}else{
							if(temp[0].equals("系统消息:")){
								if(temp[2].equals("拒绝加入您的房间")||
									temp[2].equals("已经离开您的房间")
									
									){

									TipsDialog td = new TipsDialog(message);
								}else if(temp[2].equals("已经准备就绪")){
									 int x = Integer.parseInt(temp[3]) ;
									 ready[x] = true ;
									 repaint();
								}else if(temp[2].equals("同意加入您的房间")){
System.out.println(message);
									int index = Integer.parseInt(temp[3]) ;
									state[index-1] = 2 ;
									head[index] = Integer.parseInt(temp[4]) ;
									level[index] =(temp[5]) ;
									gold[index] =(temp[6]) ;
									repaint();
								}else if(temp[2].equals("在线好友")){
									ArrayList<String> f_name = new ArrayList<String>();
									ArrayList<Integer> f_state = new ArrayList<Integer>();
									ArrayList<Integer> f_head = new ArrayList<Integer>();
									ArrayList<String> f_exp = new ArrayList<String>();
									f_name.add("没有好友");
									f_state.add(-1) ;
									f_head.add(0);
									f_exp.add("0") ;
									ArrayList<String> lists = new ArrayList<String> ();
									for(String s:temp[3].split(";")){
										lists.add(s);
									}
									if(lists.get(0).equals("空")){
										TipsDialog td =new TipsDialog("啊哦，好友都不在。。");
									}else{
										ArrayList<String> nameOnline = new ArrayList<String>();
										ArrayList<Integer> headOnline = new ArrayList<Integer>();
										ArrayList<Integer> lvlOnline = new ArrayList<Integer>();
										ArrayList<String> goldOnline = new ArrayList<String>();
										
										ArrayList<String> nameList = FriendsData.getNames();
										ArrayList<Integer> headList = FriendsData.getHeadList();
										ArrayList<Integer> lvlList =  FriendsData.getLvlList() ;
										ArrayList<String> goldList =  FriendsData.getGoldList() ;
										for(String s : lists){
											nameOnline.add(s.split("_")[1]);
										}
										int[] indexes = new int[nameOnline.size()];
										for(int i = 0 ; i< nameList.size() ; i++){
											for( int  j = i ; j<nameOnline.size() ; j++){
												if(nameOnline.get(j).equals(nameList.get(i))){
													indexes[j] = i ;
												}
											}
										}
										for(int i = 0 ; i<indexes.length ; i++){
											headOnline.add(headList.get(indexes[i]));
											lvlOnline.add(lvlList.get(indexes[i]));
											goldOnline.add(goldList.get(indexes[i]));
										}
										FriendsDialog fd = new FriendsDialog(nameOnline, headOnline, 
												lvlOnline,goldOnline,Integer.parseInt(temp[4]));
										fd.setLocation(297, 65);
										lock();
										fd.setAlwaysOnTop(true);
									}
									
								}else if(temp[2].equals("发出协作申请")){
								AskDialog3 ad = new AskDialog3(message);
								}else if(temp[2].equals("发出对战申请")){
									AskDialog4 ad = new AskDialog4(message);
								}else if(temp[2].equals("发出协作邀请")){
									AskDialog5 ad = new AskDialog5(message);
								}else if(temp[2].equals("发出对战邀请")){
									AskDialog6 ad = new AskDialog6(message);
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
	
	
	private void change1(String message ,int type) throws IOException{
		String[] temp =message.split(" ");
		if(type == 0){
			output.println("操作 添加好友请求 "+temp[0]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type ==1 ){
			output.println("操作 拒绝添加好友 "+temp[0]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change2(String message,int type) throws IOException{
		
	}
	private void change3(String message,int type) throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			int index = Integer.parseInt(temp[3]);
			state[index-1] = 2 ;
			head[index] =Player.getHead() ;
			level[index] =Player.getLvl()+"";
			gold[index] =Player.getGold()+"" ;
			repaint();
			output.println("操作 允许加入房间2 0 "+index+" "+temp[4]+" "+temp[1]+" "+Player.getUserName()) ;
			output.flush();
		}else if(type ==1){
			output.println("操作 不许加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change4(String message,int type) throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			int index = Integer.parseInt(temp[3]);
			state[index-1] = 2 ;
			head[index] =Player.getHead() ;
			level[index] =Player.getLvl()+"";
			gold[index] =Player.getGold()+"" ;
			repaint();
			output.println("操作 允许加入房间2 1 "+index+" "+temp[4]+" "+temp[1]+" "+Player.getUserName()) ;
			output.flush();
		}else if(type ==1){
			output.println("操作 不许加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change5(String message,int type) throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			output.println("操作 同意加入房间2 0 "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 拒绝加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change6(String message,int type) throws IOException{
		String[] temp = message.split(" ");
		if(type == 0){
			output.println("操作 同意加入房间2 1 "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 拒绝加入房间2 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	
	private void initImage(){
		backGround=new ImageIcon("src/images/CoopRoom/background.png").getImage();
		readyImage=new ImageIcon("src/images/CoopRoom/ok.png").getImage();
		waitImage=new ImageIcon("src/images/CoopRoom/wait.png").getImage();
		hosterImage=new ImageIcon("src/images/CoopRoom/hoster.png").getImage();
		playerImage=new ImageIcon("src/images/CoopRoom/h_player.png").getImage();
		addImage=new ImageIcon("src/images/CoopRoom/add.png").getImage();
		f_playerImage=new ImageIcon("src/images/CoopRoom/fh_player.png").getImage();
		f_addImage=new ImageIcon("src/images/CoopRoom/f_add.png").getImage();
		f_waitImage=new ImageIcon("src/images/CoopRoom/f_wait.png").getImage();
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
		g2d.setFont(new Font("微软雅黑",0,24));
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
					roomMaster,Player.getGold(),"src/images/toolpanel/toolpanel2.png",0+"" ,1,bootFrame);
			tp.setSize(927, 661);
			tp.setLocation(0, 0);
			add(tp);
		
			repaint();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source==panel[0]){
			
			if(state[0]==0){
				if(state[0]==0){
					output.println("数据 显示在线好友请求2 "+Player.getUserName()+" "+0);
					output.flush();
				}else if(state[0]==1){
					return;
				}else if(state[0]==2){
					return;
				}
				
			}else if(state[0]==1){
				return;
			}else if(state[0]==2){
				return;
			}
			
		}else if(source==panel[1]){
			System.out.println("邀请好友游戏");
			if(state[1]==0){
				output.println("数据 显示在线好友请求2 "+Player.getUserName()+" "+1);
				output.flush();
			}else if(state[1]==1){
				return;
			}else if(state[1]==2){
				return;
			}
		}else if(source==panel[2]){
			System.out.println("邀请好友游戏");
			if(state[2]==0){
				output.println("数据 显示在线好友请求2 "+Player.getUserName()+" "+2);
				output.flush();
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
		}else if(source==d_panel[0]){
			if(state[0]!=2){
				return;
			}else{
				
				System.out.println("删除好友");
				/*进行踢人的一些操作*/
			}
			repaint();
		}else if(source==d_panel[1]){
			if(state[1]!=2){
				return;
			}else{
				/*进行踢人的一些操作*/
				System.out.println("删除好友");
			}
			repaint();
		}else if(source==d_panel[2]){
			if(state[2]!=2){
				return;
			}else{
				/*进行踢人的一些操作*/
				System.out.println("删除好友");
			}
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
	class FriendsDialog extends JFrame implements MouseListener {
		JPanel background;
		ArrayList<JPanel> panelList;
		ArrayList<FriendInfoPanel> infolist;
		JPanel bootpanel;
		JPanel invite1;
		JPanel invite2;
		JPanel invite3;
		CardLayout card;
		MyButton nextButton;
		MyButton lastButton;
		int pages = 0;
		int panelnum;
		int infonum;
		
		int tag = 0 ;
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		ArrayList<Integer> headList = new ArrayList<Integer>(); 
		ArrayList<Integer> lvlList= new ArrayList<Integer>(); 
		ArrayList<String> goldList= new ArrayList<String>(); 
		
		public FriendsDialog(ArrayList<String> nameList,
				ArrayList<Integer> headList, ArrayList<Integer> lvlList,
				ArrayList<String> goldList , int tag) {
			
			this.tag = tag ;
			this.headList = headList;
			this.nameList = nameList;
			this.lvlList = lvlList;
			this.goldList = goldList;
			
			background = new MyPanel(PanelType.Dialog);
			invite1 = new JPanel();
			invite2 = new JPanel();
			invite3 = new JPanel();
			invite1.setSize(61, 75);
			invite2.setSize(61, 75);
			invite3.setSize(61, 75);
			invite1.setLocation(530, 7);
			invite2.setLocation(530, 7 + 75);
			invite3.setLocation(530, 7 + 75 * 2);
			invite1.setOpaque(false);
			invite1.setVisible(true);
			invite2.setOpaque(false);
			invite3.setOpaque(false);
			invite1.addMouseListener(this);
			invite2.addMouseListener(this);
			invite3.addMouseListener(this);

			Dimension buttonSize = new Dimension(63, 45);
			Dimension thispaneSize = new Dimension(690, 290);
			Dimension infoPaneSize = new Dimension(515, 79);// 515
			Dimension panelSize = new Dimension(515, 237);

			panelnum = (nameList.size() / 3) + 1;
			infonum = nameList.size();

			bootpanel = new JPanel();
			panelList = new ArrayList<JPanel>();
			infolist = new ArrayList<FriendInfoPanel>();

			for (int i = 0; i < panelnum; i++) {
				JPanel aPanel = new MyPanel(PanelType.FriendsBack);
				aPanel.setLayout(null);
				aPanel.setLocation(0, 0);
				aPanel.setSize(panelSize);
				panelList.add(aPanel);
			}

			for (int i = 0; i < infonum; i++) {
				FriendInfoPanel friendInfo = new FriendInfoPanel(
						nameList.get(i), headList.get(i),i %3, lvlList.get(i),
						goldList.get(i));
				friendInfo.setLocation(0, infoPaneSize.height * (i % 3));
				friendInfo.setSize(infoPaneSize);
				infolist.add(friendInfo);
				
			}

			int j = 0;
			for (int i = 0; i < infonum;) {
				JPanel tem = panelList.get(j);
				tem.add(infolist.get(i));
				panelList.get(j).add(infolist.get(i));
				
				i++;
				if (i % 3 == 0) {
					j++;
				}
			}

			bootpanel = new MyPanel(PanelType.homepane);
			bootpanel.setSize(panelSize);
			bootpanel.setLocation(80, 4);
			card = new CardLayout();
			bootpanel.setLayout(card);

			for (int i = 0; i < panelnum; i++) {
				bootpanel.add(panelList.get(i));
			}
			background.setLayout(null);
			background.add(bootpanel);
			nextButton = new MyButton(ButtonType.nextButton);
			nextButton.setSize(buttonSize);
			nextButton.setLocation(590, 242);
			nextButton.addMouseListener(this);
			lastButton = new MyButton(ButtonType.lastButton);
			lastButton.setSize(buttonSize);
			lastButton.setLocation(84, 242);
			lastButton.addMouseListener(this);
			background.add(invite1);
			background.add(lastButton);
			background.add(nextButton);

			getContentPane().add(invite1);
			getContentPane().add(invite2);
			getContentPane().add(invite3);
			getContentPane().add(background, -1);

			setSize(690 + 16, 290 + 38);
			setLocation(0, 0);
			setVisible(true);
		}
		
		

		@Override
		public void mouseClicked(MouseEvent e) {
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

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();

			if (source == lastButton) {
				pages--;
				if (pages < 0) {
					pages = 0 - pages;
				}
				if (pages >= panelnum) {
					pages = 0;
				}
				card.previous(bootpanel);
			} else if (source == nextButton) {
				pages++;
				if (pages < 0) {
					pages = 0 - pages;
				}
				if (pages >= panelnum) {
					pages = 0;
				}
				card.next(bootpanel);
			} else if (source == invite1) {
				if (((pages + 1) == panelnum) && (panelnum * 3 - infonum) >= 3) {
System.out.println("Here1");

					state[tag] = 1 ;repaint();
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
					
				} else {
					
					state[tag] = 1 ;
					repaint();
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
					unlock();
					dispose();
				
				}
			} else if (source == invite2) {
				if (((pages + 1) == panelnum) && (panelnum * 3 - infonum) >= 2) {
					state[tag] = 1 ;repaint();
					
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3+1)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
				
					
				} else {
					state[tag] = 1 ;
					repaint();
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3+1)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
					unlock();
					 dispose();
				
				}
			} else if (source == invite3) {
				if (((pages + 1) == panelnum) && (panelnum * 3 - infonum) >= 1) {
					state[tag] = 1 ;
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3+2)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
					repaint();
				} else {
					state[tag] = 1 ;
					repaint();
					output.println("操作 邀请加入房间2 "+roomName + " "+nameList.get(pages*3+2)+" "+Player.getUserName()+" "+(tag+1));
					output.flush();
					unlock();
					 dispose();
				
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

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
	private class AskDialog2 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog2(String msg0){
			tempMsg = msg0;
			String[] temp = msg0.split(" ");
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/ask.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			no = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/refuse.png"));
			msg = new JLabel(temp[1]+temp[2]+temp[3]+temp[4]);
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
					change2(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change2(tempMsg,1);
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
	private class AskDialog5 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog5(String msg0){
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
					change5(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change5(tempMsg,1);
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

	private class AskDialog6 extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskDialog6(String msg0){
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
					change6(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change6(tempMsg,1);
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

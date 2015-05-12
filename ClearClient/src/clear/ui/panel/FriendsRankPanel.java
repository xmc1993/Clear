package clear.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clear.ui.component.DialogPanel;
import clear.ui.data.RankData;
import clear.ui.network.AddFriendClient;
import clear.ui.network.SearchFriendClient;
import clear.ui.panel.FriendPanel.recThread;
import clear.ui.test.TestDrive;
import enupackage.MouseType;

public class FriendsRankPanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	ImageIcon imageIcon;
	
	private int count=0;
	Image add;
	Image right;
	
	String friendNum = "" ;
	String friendName = "" ;
	
	String friendHead = "" ;
	String friendMax = "" ;
	String friendLvl = "";
	
	String friendGold = "";
	String friendExp = "" ;
	
	int width;
	int hight;
	MouseType type = MouseType.EXIT;
	int position;
	FriendsPanel friendsPanel;
	JPanel rankPanel;
	JLabel back;
	JLabel start;
	JLabel help;
	CardLayout card;
	CardLayout addCard;
	JPanel down;
	JPanel up;
	JPanel addPanel;
	JPanel cardPanel;
	String name="";
	
	AddFriendPanel afp;
	Socket socket;
	PrintStream output ;
	BufferedReader input ;
	String message="";
	ArrayList<String> nameList;
	ArrayList<Integer> lvlList;
	
	ArrayList<Integer> headList;
	ArrayList<String> gold;
	RankData rankData ;
	//
	JFrame bootFrame;
	private JLabel minButton;
	private JLabel quitButton;
	public FriendsRankPanel(ArrayList<String> nameList,ArrayList<Integer> headList,
			ArrayList<Integer> lvlList,
			ArrayList<String> gold,RankData rankData,JFrame boot){
		bootFrame = boot;
		this.nameList = nameList;
		this.lvlList = lvlList;
		this.headList = headList;
		this.gold =gold ;
		imageIcon = new ImageIcon("src/images/friendsrank.jpg");
		//创建组件
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		down = new JPanel();
		up = new JPanel();
		addPanel=new JPanel();
		addPanel.setOpaque(false);
		addPanel.setVisible(true);
		addPanel.setBounds(844,92,26,295);
		addPanel.addMouseListener(this);
		add(addPanel);
		card = new CardLayout();
		addCard=new CardLayout();
		rankPanel = new JPanel();
		back = new JLabel(new ImageIcon("src/images/friendsRankPanel/back.png"));
		back.addMouseListener(this);
		start = new JLabel(new ImageIcon("src/images/friendsRankPanel/start.png"));
		start.addMouseListener(this);
		help = new JLabel(new ImageIcon("src/images/friendsRankPanel/help.png"));
		help.addMouseListener(this);
		
		add=new ImageIcon("src/images/friendsRankPanel/add.png").getImage();
		right=new ImageIcon("src/images/friendsRankPanel/right.png").getImage();
		
		friendsPanel = new FriendsPanel(nameList, headList,lvlList,gold);
		
		ArrayList<String> info=rankData.getInfo();
		GameRecordPanel grp=new GameRecordPanel(info.get(0),info.get(1),info.get(2),info.get(3));
		ScorePerGamePanel spg = new ScorePerGamePanel(rankData.getList());
		GameCountPanel gcp = new GameCountPanel(rankData.getGameCount());
		AverageScorePanel asp = new AverageScorePanel(rankData.getAverageCount());
		
		cardPanel=new JPanel();
		rankPanel.setOpaque(false);
		cardPanel.setLayout(addCard);	
		SearchFriendPanel sfp=new SearchFriendPanel();
		friendsPanel.setSize(690,290);
		
		cardPanel.setBounds(153,92,690,290);
		cardPanel.add(friendsPanel);
		cardPanel.add(sfp);
		//cardPanel.add(afp);
		add(cardPanel);
		
		
//		rankPanel = new RankPanel(rankData);
		//设置组件的某些性质
		down.setOpaque(false);
		up.setOpaque(false);
		down.addMouseListener(this);
		up.addMouseListener(this);
		quitButton.addMouseListener(this);
		rankPanel.setOpaque(false);
		rankPanel.setLayout(card);
		rankPanel.add(grp);
		rankPanel.add(asp);
		rankPanel.add(gcp);
		rankPanel.add(spg);
		
		//设置各个组建的位置
		down.setLocation(239, 545);
		up.setLocation(740, 547);
		back.setLocation(76, 598);
		start.setLocation(393,592);
		help.setLocation(756, 592);
//		friendsPanel.setLocation(153,92);
		rankPanel.setLocation(214, 383);
		quitButton.setLocation(844, 2);
		//各组件设置大小
		quitButton.setSize(54, 72);
		down.setSize(48, 30);
		up.setSize(48, 30);
		rankPanel.setSize(630,160);
		back.setSize(121, 50);
		start.setSize(150, 54);
		help.setSize(150, 54);
		//添加组建
		setLayout(null);
		add(back);
		add(start);
		add(help);
//		add(friendsPanel);
		add(rankPanel);
		add(down);
		add(up);
		add(quitButton);
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		setVisible(true);
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
	public ArrayList<String> analysis(ArrayList<String> lists,int num){
		ArrayList<String> result = new ArrayList<String>();
		for(String s : lists){
			String[] temps = s.split(" ") ;
			result.add(temps[num]) ;
		}
		return result;
	}
	
	public ArrayList<Integer> analysis2(ArrayList<String> lists,int num){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String s : lists){
			String[] temps = s.split(" ") ;
			result.add(Integer.parseInt(temps[num])) ;
		}
		return result;
	}
	
	class recThread extends Thread{
		public void run(){
			while(true){
				try {
					while((message = input.readLine())!=null){
System.out.println(message);
						String[] temp = message.split(" ") ;
						
						if(temp.length==5){
							if(temp[1].equals("已添加您为好友")){
								OffLine offline = new OffLine(temp[0]+"已添加您为好友");
								if(nameList.get(0).equals("暂无好友")){
									nameList.set(0,temp[0]);
									lvlList.set(0,Integer.parseInt(temp[2]));
									headList.set(0,Integer.parseInt(temp[4]));
									gold.set(0,temp[3]);
								}else{
									nameList.add(temp[0]);
									lvlList.add(Integer.parseInt(temp[2]));
									headList.add(Integer.parseInt(temp[4]));
									gold.add(temp[3]);
								}
								
								cardPanel.remove(friendsPanel);
								friendsPanel = new FriendsPanel(nameList, lvlList, headList,gold);
								friendsPanel.setSize(690,290);
								
								cardPanel.add(friendsPanel);
								repaint();
							}else if(temp[1].equals("邀请您为好友")){

								InviteDialog invite = new InviteDialog(message);
							}else if(temp[1].equals("拒绝添加您为好友")){
								OffLine of = new OffLine("拒绝添加您为好友");
								
							}else if(temp[1].equals("已将您拉黑")){
								OffLine of = new OffLine("已将您拉黑");
								
							}else{

								OffLine off = new OffLine(message);
							}
						}else if(temp.length>=5){
								friendNum = temp[0];
								friendName = temp[1];
								friendHead = temp[2];
								friendMax =temp[3];
								friendLvl = temp[4] ;
								friendGold = temp[5] ;
								afp=new AddFriendPanel(friendName,Integer.parseInt(friendHead),Integer.parseInt(friendLvl),Integer.parseInt(friendMax));
								cardPanel.add(afp);
								addCard.next(cardPanel);
								count++;
						
						}else{

							OffLine off = new OffLine(message);
						}
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void change2(String msg1,int type) throws IOException{
		String[] temp = msg1.split(" ");
		if(type == 0){
			output.println("操作 同意加入房间 "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 拒绝加入房间 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change(String msg1,int type) throws IOException {
		String[] temp = msg1.split(" ");
		if(type == 0){
			//头像 等级 金币
System.out.println("好友信息："+temp[2]+" "+temp[3]+" "+temp[4]);
		output.println("操作 添加好友请求 "+temp[0]
				+" "+Player.getUserName()+" "+Player.getHead()+" "+Player.getLvl()+" "+
				Player.getGold()+" "+temp[2]+" "+temp[3]+" "+temp[4] ) ;
		output.flush();
		
		if(nameList.get(0).equals("暂无好友")){
			nameList.set(0,temp[0]);
			lvlList.set(0,Integer.parseInt(temp[2]));
			headList.set(0,Integer.parseInt(temp[4]));
			gold.set(0,temp[3]);
		}else{
			nameList.add(temp[0]);
			lvlList.add(Integer.parseInt(temp[2]));
			headList.add(Integer.parseInt(temp[4]));
			gold.add(temp[3]);
		}
		cardPanel.remove(friendsPanel);

		friendsPanel = new FriendsPanel(nameList, lvlList, headList,gold);
		friendsPanel.setSize(690,290);
		cardPanel.add(friendsPanel);
		repaint();
		}else if(type == 1){
			output.println("操作 拒绝添加好友 "+temp[0]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	private void change3(String msg1,int type) throws IOException{
		String[] temp = msg1.split(" ");
		if(type == 0){
			output.println("操作 同意加入房间 "+temp[3]+" "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}else if(type == 1){
			output.println("操作 不许加入房间 "+temp[1]
					+" "+Player.getUserName()) ;
			output.flush();
		}
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return hight;
	}
	public void paintComponent(Graphics g){
		Image image = imageIcon.getImage();
		g.drawImage(image,0,0,this);
		if(count==0){
			g.drawImage(add,844,92,26,295,this);
		}else if(count!=0){
			g.drawImage(right,844,92,26,295,this);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == back){
			removeAll();
			SignPanel login = new SignPanel(bootFrame);
			login.setSize(927, 661);
			login.setLocation(0, 0);
			add(login);
			repaint();
		}else if(source == help){
			HelpPanel hp = new HelpPanel();
			hp.setSize(927, 661);
			hp.setLocation(0, 0);
			removeAll();
			add(hp);
			repaint();
		}else if(source == start){
			removeAll();
			ModelPanel model = new ModelPanel(bootFrame);
			model.setSize(927, 661);
			model.setLocation(0, 0);
			add(model);
			repaint();
		}else if(source == down){
			card.previous(rankPanel);
		}else if(source == up){
			card.next(rankPanel);
		}else if(source==addPanel){
			addCard.next(cardPanel);
			if(count==0){
				count++;
			}else if(count==1){
				count--;
			}else if(count==2){
				count=0;
				cardPanel.remove(afp);
			}
			repaint();
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}
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
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*内部类一查找好友的Panel*/
	public class SearchFriendPanel extends JPanel implements MouseListener{
		private BufferedImage backGround;
		private JPanel search;
		private JTextField field;
		
		public SearchFriendPanel(){
			setSize(690,290);
			setLayout(null);
			init();
		}
		
		private void init(){
			field =new JTextField();
			field.setOpaque(false);
			field.setVisible(true);
			field.setBounds(168, 138, 358, 45);
			field.setFont(new Font("幼圆", 0, 24));
			field.setForeground(new Color(186,181,181));
			add(field);
			search=new JPanel();
			search.setOpaque(false);
			search.setVisible(true);
			search.addMouseListener(this);
			search.setBounds(525, 138, 81, 45);
			add(search);
			initImage();
		}
		private void initImage(){
			try {
				backGround = ImageIO.read(new FileInputStream("src/images/friendsRankPanel/search.png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		protected void paintComponent(Graphics g){
			Graphics2D g2d=(Graphics2D) g;
			g2d.drawImage(backGround,0,0,690,290,this);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			output.println("操作 搜索好友请求 "+Player.getUserName()+" "+field.getText()) ;
			output.flush();
			
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
	}
    
	public class AddFriendPanel extends JPanel implements MouseListener{
		private BufferedImage backGround;
		private BufferedImage addImage;
		private JPanel addPanel;
	
		private String name;
		private int head;
		private int level;
		private int highScore;
		private ImageIcon headImage;
		private JLabel headLabel;
		
		public AddFriendPanel (String name,int head,int level,int highScore){
			this.name=name;
			this.head=head;
			this.level=level;
			this.highScore=highScore;
			
			setSize(690,290);
			setLayout(null);
			init();
		}
		private void init(){
			addPanel=new JPanel();
			addPanel.addMouseListener(this);
			addPanel.setOpaque(false);
			addPanel.setVisible(true);
			addPanel.setBounds(433,184,136,52);
			add(addPanel);
			headLabel=new JLabel(new ImageIcon("src/images/chatHead/dataHead"+head+".jpg"));
			headLabel.setBounds(114, 50, 150, 150);
			add(headLabel);
			initImage();
		}
		private void initImage(){
			try {
				backGround = ImageIO.read(new FileInputStream("src/images/friendsRankPanel/addFriend.png"));
				addImage= ImageIO.read(new FileInputStream("src/images/friendsRankPanel/addImage.png"));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		protected void paintComponent(Graphics g){
			Graphics2D g2d=(Graphics2D) g;
			g2d.drawImage(backGround,0,0,690,290,this);
			g2d.drawImage(addImage,433,184,136,52,this);
			g2d.setFont(new Font("微软雅黑",1,24));
			g2d.setColor(Color.white);
			g2d.drawString(name,307,79);
			g2d.drawString(String.valueOf(level), 340, 124);
			g2d.drawString(String.valueOf(highScore), 420, 170);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==addPanel){
				output.println("操作 添加好友申请 "+friendName +" "+Player.getUserName()+" "+Player.getHead()+" "+Player.getLvl()+" "+
						Player.getGold());
				output.flush();
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
		
}
	private class OffLine extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel cancle;
		public OffLine(String msgs){
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
	private class InviteDialog extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public InviteDialog(String msg0){
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
					change(tempMsg,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}else if (source == close){
				dispose();
			}else if(source == no){
				try {
					change(tempMsg,1);
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
	private class EnterRoomDialog extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public EnterRoomDialog(String msg0){
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
	private class AskEnterRoomDialog extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel no;
		JLabel cancle;
		String tempMsg;
		public AskEnterRoomDialog(String msg0){
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
		}
	} 
}

package clear.ui.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import clear.ui.component.DialogPanel;
import clear.ui.component.MyJDialog;
import clear.ui.data.RankData;
import clear.ui.logic.FriendData;
import clear.ui.logic.GameData;
import clear.ui.logic.LoginData;
import clear.ui.panel.LoginPanel.SetPanel;
import clear.ui.panel.OverPanel.MyListener;

public class SignPanel extends JPanel implements MouseListener{
	private Image backGround;
	private Image starLeft;
	private Image vistorImage;
	private Image setImage;
	private Image starRight;
	private Image inputImage;
	private ImageIcon loginImage;
	private ImageIcon regImage;
	private JLabel loginLabel;
	private JLabel regLabel;
	private JPanel setPanel;
	private JPanel vistorPanel;
	private JTextField nameField;
	private JPasswordField passwordField;
	private Timer timer;
	private JLabel minButton;
	private JLabel quitButton;
	private float upAlpha=1.0f;
	private float downAlpha=1.0f;
	private int count=0;
	int head = 0 ;
	JFrame bootFrame;
	public SignPanel(JFrame boot){
		bootFrame = boot;
		setLayout(null);
		init();
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
		loginLabel=new JLabel(loginImage);
		regLabel=new JLabel(regImage);
		setPanel=new JPanel();
		vistorPanel=new JPanel();
		nameField=new JTextField();
		passwordField=new JPasswordField();
		loginLabel.setBounds(300, 430,110,53);
		regLabel.setBounds(500, 430,110,53);
		setPanel.setBounds(770, 177,162,47);
		vistorPanel.setBounds(770, 107, 162,47);
		nameField.setBounds(300, 260, 316,53);
		passwordField.setBounds(300, 340, 316,53);
		loginLabel.addMouseListener(this);
		regLabel.addMouseListener(this);
		vistorPanel.addMouseListener(this);
		setPanel.addMouseListener(this);
		setPanel.setOpaque(false);
		vistorPanel.setOpaque(false);
		nameField.setOpaque(false);
		passwordField.setOpaque(false);
		passwordField.setEchoChar('*');
		nameField.setFont(new Font("幼圆", 1, 24));
		passwordField.setFont(new Font("幼圆", 1, 24));
		nameField.setForeground(new Color(186,181,181));
		passwordField.setForeground(new Color(186,181,181));
		nameField.setBorder(null);
		passwordField.setBorder(null);
		add(loginLabel);
		add(regLabel);
		add(setPanel);
		add(vistorPanel);
		add(nameField);
		add(passwordField);
		setSize(927,661);
		timer=new Timer(200,new MyListener());
		timer.setInitialDelay(0);
		timer.start();
	}
	
	private void initImage(){
		backGround=new ImageIcon("src/images/Sign/backGround.png").getImage();
		starLeft=new ImageIcon("src/images/Sign/starLeft.png").getImage();
		starRight=new ImageIcon("src/images/Sign/starRight.png").getImage();
		vistorImage=new ImageIcon("src/images/Sign/vistor.png").getImage();
		setImage=new ImageIcon("src/images/Sign/set.png").getImage();
		loginImage=new ImageIcon("src/images/Sign/login.png");
		regImage=new ImageIcon("src/images/Sign/register.png");
		inputImage=new ImageIcon("src/images/Sign/input.png").getImage();
	}

	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(backGround, 0, 0, 927,661,this);
		g2d.drawImage(setImage,770, 177,162,47,this);
		g2d.drawImage(vistorImage,770, 107, 162,47,this);
		g2d.drawImage(inputImage,300, 260, 316,53,this);
		g2d.drawImage(inputImage,300, 340, 316,53,this);
		/*闪烁图*/
		AlphaComposite newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, upAlpha);
		g2d.setComposite(newComposite);
		g2d.drawImage(starLeft, 0, 0, 927,661,this);   //上面的星星图
		newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, downAlpha); 
		g2d.setComposite(newComposite);
		g2d.drawImage(starRight, 0, 0, 927,661,this);	
		newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f);
		g2d.setComposite(newComposite);  //下面的星星图
		
		
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source=e.getSource();
		if(source==loginLabel){
			String id = nameField.getText();
			String ps = new String(passwordField.getPassword());

			if(id.equals("")){
				MyJDialog mydialog = new MyJDialog(5);
			}else{
				if(ps.equals("")){
					MyJDialog myJDialog = new MyJDialog(1);
				}else{
System.out.println(IPAddress.getIP());
					String result = new LoginData().check(id, ps, 0) ;
System.out.println(result);
					String[] temp = result.split("_");
					if(temp[0].equals("Success")){
						//登录成功时的动作
						
						Player.setHead(Integer.parseInt(result.split(";")[0].split("_")[3]));
						Player.setLvl(Integer.parseInt(result.split(";")[0].split("_")[4]));
						Player.setGold(Integer.parseInt(result.split(";")[0].split("_")[5])+100);
						Player.setExp(Integer.parseInt(result.split(";")[0].split("_")[6]));
						Player.setMax(Integer.parseInt(result.split(";")[0].split("_")[7]));
System.out.println(Player.getExp()+" "+Player.getLvl()+" "+Player.getHead()+" "+Player.getGold()+" "+Player.getMax());				

						double[] f_avg = new double[7];
						int[] f_game_day = new int[7];
						ArrayList<String> f_score_game = new ArrayList<String>();
						ArrayList<String> f_info = new ArrayList<String>();
						ArrayList<String> f_name = new ArrayList<String>();
						ArrayList<Integer> f_state = new ArrayList<Integer>();
						ArrayList<Integer> f_head = new ArrayList<Integer>();
						ArrayList<String> f_exp = new ArrayList<String>();
						
						for(int i = 0 ;i <7 ; i++){
							f_avg[i] = 0;
							f_game_day[i] = 0 ;
							f_score_game.add("0") ;
							f_info.add("0") ;
						}
						f_name.add("暂无好友");
						f_state.add(-1) ;
						f_head.add(0);
						f_exp.add("0") ;
						
						Player.setUserName(id);
						FriendData data = new FriendData();
						ArrayList<String> lists = data.getFriends(id) ;
for(String s : lists){
	System.out.println("friend : "+s);
}
						GameData data2 = new GameData(Player.getUserName());
						String x = data2.getData() ;

						if(lists.get(0).equals("")||lists.get(0).split(" ")[0].equals("null")){

							RankData rankData = new RankData(f_avg,f_game_day,f_score_game,f_info);
							FriendsRankPanel friendsRankPanel= 
									new FriendsRankPanel(f_name, 
											f_state, f_head, f_exp,rankData,bootFrame);
							friendsRankPanel.setSize(927, 661);
							friendsRankPanel.setLocation(0, 0);
							removeAll();
							add(friendsRankPanel);
							FriendsData.setNames(f_name);
							FriendsData.setHeadList(f_state);
							FriendsData.setLvlList(f_head);
							FriendsData.setGoldList(f_exp);
						}else{
							RankData rankData=  new RankData(data2.getAvgPerDay(),
								data2.getGamesPerDay(),data2.getScorePerGame(),data2.getRecords());
							FriendsRankPanel friendsRankPanel= 
								new FriendsRankPanel(analysis(lists,1), 
										analysis2(lists,2), analysis2(lists,3), analysis(lists,4),rankData,bootFrame);
							friendsRankPanel.setSize(927, 661);
							friendsRankPanel.setLocation(0, 0);
							removeAll();
							add(friendsRankPanel);
							
							FriendsData.setNames(analysis(lists,1));
							FriendsData.setHeadList(analysis2(lists,2));
							FriendsData.setLvlList(analysis2(lists,3));
							FriendsData.setGoldList(analysis(lists,4));
						}
						
						repaint();
					}else if(temp[0].equals("DataNotExist")){
						MyJDialog myJDialog = new MyJDialog(2);
						 nameField.setText("");
					}else if(temp[0].equals("DataUnmatch")){
						MyJDialog myJDialog = new MyJDialog(4);
						 passwordField.setText("");
					}else if(temp[0].equals("DataExist")){
						TipsDialog td = new TipsDialog("你已登录");
						 passwordField.setText("");
					}else if(temp[0].equals("OverTime")){
						MyJDialog myJDialog = new MyJDialog(3);
							passwordField.setText("");
					}else{
						nameField.setText("");
						passwordField.setText("");
					}
				}
			}
		}else if(source==regLabel){
			removeAll();
			ResPanel rp=new ResPanel(bootFrame);
			rp.setBounds(0, 0, 927, 661);
			add(rp);
			repaint();
		}else if(source==vistorPanel){
			removeAll();
			GamePanel game = new GamePanel(true);
			game.setSize(927, 661);
  		    game.setLocation(0, 0);
	
			add(game);
			repaint();
		}else if(source==setPanel){
			removeAll();
			//调用方法获得musiclist在此处就简单点自己创建了
			ArrayList<String> musiceList = new ArrayList<String>();
			for(int i = 0;i<10;i++){
				musiceList.add(""+i);
			}
			SetPanel setpanel = new SetPanel(musiceList);
			setpanel.setSize(927, 661);
			setpanel.setLocation(0, 0);
		
			add(setpanel);
			repaint();
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
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
	
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (count % 3) {
			case 0:
				upAlpha = 1.0f;
				downAlpha=0.6f;
				break;
			case 1:
				upAlpha = 0.75f;
				downAlpha=0.75f;
				break;
			case 2:
				upAlpha = 0.6f;
				downAlpha=1.0f;
				break;
			}
			count++;
			repaint();

		}
	}
	class SetPanel extends JPanel implements MouseListener {
		private JPanel leftPanel;
		private JPanel rightPanel;
		private JButton okButton;
		private JLabel headLabel;
		ImageIcon okImage;
		ImageIcon imageIcon;
		ImageIcon headImage[];
		Image leftImage;
		Image rightImage;
		int width;
		int hight;
		JLabel OnOff;
		JLabel musicname;
		JPanel up;
		JPanel down;
		JPanel back;
		JTextField ip1;
		JTextField ip2;
		JTextField ip3;
		JTextField ip4;
		ArrayList<String> musiclist;
		int num;
		int len;
		boolean open = false;
		
		
		String ip ;

		public SetPanel(ArrayList<String> musiclist) {
			this.musiclist = musiclist;
			
			len = musiclist.size();
			imageIcon = new ImageIcon("src/images/setpanel/setclose.png");
			okImage=new ImageIcon("src/images/setpanel/okImage.png");
			OnOff = new JLabel(new ImageIcon("src/images/setpanel/open.png"));
			leftImage=new ImageIcon("src/images/setpanel/leftImage.png").getImage();
			rightImage=new ImageIcon("src/images/setpanel/rightImage.png").getImage();
			headImage=new ImageIcon[6];
			for(int i=0;i<6;i++){
				headImage[i]=new ImageIcon("src/images/chatHead/squareHead"+i+".jpg");
			}
			headLabel=new JLabel(headImage[head]);
			headLabel.setBounds(387,416, 115, 115);
			add(headLabel);
			okButton=new JButton(okImage);
			musicname = new JLabel(musiclist.get(0));
			leftPanel=new JPanel();
			rightPanel=new JPanel();
			up = new JPanel();
			down = new JPanel();
			back = new JPanel();
			ip1 = new JTextField();
			ip2 = new JTextField();
			ip3 = new JTextField();
			ip4 = new JTextField();
			ip1.setBackground(new Color(90, 95, 95));
			ip1.setBorder(null);
			ip2.setBackground(new Color(90, 95, 95));
			ip2.setBorder(null);
			ip3.setBackground(new Color(90, 95, 95));
			ip3.setBorder(null);
			ip4.setBackground(new Color(90, 95, 95));
			ip4.setBorder(null);

			back.setVisible(true);
			back.setOpaque(false);
			up.setVisible(true);
			up.setOpaque(false);
			down.setVisible(true);
			down.setOpaque(false);
			// 设置组件大小
			OnOff.setSize(104, 37);
			back.setSize(111, 47);
			ip1.setSize(59, 34);
			ip2.setSize(59, 34);
			ip3.setSize(59, 34);
			ip4.setSize(59, 34);
			up.setSize(32, 36);
			down.setSize(32, 36);
			musicname.setSize(279, 36);
			// 设置组件位置
			OnOff.setLocation(310, 219);
			back.setLocation(83, 578);
			ip1.setLocation(311, 287);
			ip2.setLocation(407, 287);
			ip3.setLocation(503, 287);
			ip4.setLocation(600, 287);
			up.setLocation(310, 352);
			down.setLocation(628, 355);
			musicname.setLocation(346, 353);
			
			leftPanel.setOpaque(false);
			rightPanel.setOpaque(false);
			leftPanel.addMouseListener(this);
			rightPanel.addMouseListener(this);
			leftPanel.setBounds(352,418, 32, 115);
			rightPanel.setBounds(502,418, 32, 115);
			okButton.setBounds(720, 572, 129, 59);
			okButton.addMouseListener(this);
			add(okButton);
			add(leftPanel);
			add(rightPanel);
			
			// 添加监听
			OnOff.addMouseListener(this);
			back.addMouseListener(this);
			up.addMouseListener(this);
			down.addMouseListener(this);
			// 添加组件
			setLayout(null);
			add(OnOff);
			add(musicname);
			add(up);
			add(down);
			add(back);
			add(ip1);
			add(ip2);
			add(ip3);
			add(ip4);

			width = imageIcon.getIconWidth();
			hight = imageIcon.getIconHeight();
			System.out.println(width);
			System.out.println(hight);

		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return hight;
		}

		public void paintComponent(Graphics g) {
			Image image = imageIcon.getImage();
			g.drawImage(image, 0, 0, this);
			g.drawImage(leftImage,352,418, 32, 115,this);
			g.drawImage(rightImage,502,418, 32, 115,this);
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
			if (source == OnOff) {
				if (!open) {
					OnOff.setIcon(new ImageIcon("src/images/setpanel/open.png"));
					open = true;
				} else {
					OnOff.setIcon(new ImageIcon("src/images/setpanel/close.png"));
					open = false;
				}
				// 卉神处理音乐开环
				System.out.println("OnOff");
			} else if (source == up) {
				num--;
				int a = num % len;
				if(a<0){
					a = -a;
				}
				musicname.setText(musiclist.get(a));
				musicname.repaint();
				
			} else if (source == down) {
				num++;
				int a = num % len;
				if(a<0){
					a = -a;
				}
				musicname.setText(musiclist.get(a));
				musicname.repaint();
				
			} else if (source == back) {
				ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText()
						+ "." + ip4.getText();
				
				removeAll();
				SignPanel login = new SignPanel(bootFrame);
				login.setSize(927, 661);
				login.setLocation(0, 0);
				add(login);
				repaint();
			}else if(source==okButton){
				ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText()
						+ "." + ip4.getText();
				IPAddress.setIP(ip);
				Player.setHead(head);

				removeAll();
				SignPanel login = new SignPanel(bootFrame);
				login.setSize(927, 661);
				login.setLocation(0, 0);
				add(login);
				repaint();
			}else if(source==leftPanel){
				head--;
				if(head<3){
					head=(head+3)%3;
				}else{
					head=(head+6)%6;
				}
				headLabel.setIcon(headImage[head]);
			}else if(source==rightPanel){
				head++;
				if(head<=3){
					head=head%3;
				}else{
					head=(head-3)%3+3;
				}
				headLabel.setIcon(headImage[head]);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
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
}

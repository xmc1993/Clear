package clear.ui.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import clear.ui.network.OverClient;

public class OverPanel extends JPanel{
	private int score;
	private int level;
	private int percent;
	private int money;
	private boolean levelUp;
	private boolean newRecord;
	private BufferedImage backGround;
	private BufferedImage starUp;
	private BufferedImage starDown;
	private BufferedImage upImage,recordImage;
	private BufferedImage backImage,againImage;
	private Timer timer;
	private int count=0;
	private float upAlpha=1.0f;
	private float downAlpha=1.0f;
	private JPanel backButton;
	private JPanel againButton;
	int lianji ;
	JFrame bootFrame;
	private JLabel minButton;
	private JLabel quitButton;
	public OverPanel(int lianji ,int score,int level,int percent,int money,boolean levelUp,boolean newRecord,JFrame boot){
		bootFrame = boot;
		this.lianji = lianji ;
		this.score=score;
		this.level=level;
		this.percent=percent;
		this.money=money;
		this.levelUp=levelUp;
		this.newRecord=newRecord;
		setLayout(null);
		init();
		initImage();
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(new BackListener());
		quitButton.addMouseListener(new BackListener());
		add(quitButton);
		add(minButton);
		backButton=new JPanel();
		backButton.setOpaque(false);
		backButton.setVisible(true);
		backButton.setBounds(66,565,121,50);
		backButton.addMouseListener(new BackListener());
		add(backButton);
		againButton=new JPanel();
		againButton.setOpaque(false);
		againButton.setVisible(true);
		againButton.setBounds(672,568,176,44);
		againButton.addMouseListener(new BackListener());
		add(againButton);
	}
	private void init(){
		setSize(927,661);
		timer=new Timer(200,new MyListener());
		timer.setInitialDelay(0);
		timer.start();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		String data = date+";"+score+";"+lianji+";"+ score/100+";"+score/200;
		OverClient client = new OverClient();
		client.connect(data) ;
		
	}
	private void initImage(){
		try {
			backGround=ImageIO.read(new FileInputStream("src/images/over/over.png"));
			starUp=ImageIO.read(new FileInputStream("src/images/over/starUp.png"));
			starDown=ImageIO.read(new FileInputStream("src/images/over/starDown.png"));
			upImage=ImageIO.read(new FileInputStream("src/images/over/levelUp.png"));
			recordImage=ImageIO.read(new FileInputStream("src/images/over/newrecord.png"));
			backImage=ImageIO.read(new FileInputStream("src/images/over/back.png"));
			againImage=ImageIO.read(new FileInputStream("src/images/over/again.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(backGround, 0, 0, 927, 661,this);  //画背景
		g2d.drawImage(againImage,564,506,390,174,this);
		g2d.drawImage(backImage,66,565,121,50,this);
		/*闪烁图*/
		AlphaComposite newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, upAlpha);
		g2d.setComposite(newComposite);
		g2d.drawImage(starUp, 0, 0, 927,566,this);   //上面的星星图
		newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, downAlpha); 
		g2d.setComposite(newComposite);
		g2d.drawImage(starDown, 0, 192, 927,468,this);	
		newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f);
		g2d.setComposite(newComposite);  //下面的星星图
		/*添加游戏信息*/
		g2d.setFont(new Font("Hobo Std", 1, 60));
		g2d.setColor(new Color(158,101,27));
		g2d.drawString(String.valueOf(score),370, 445);
		
		g2d.setColor(new Color(148,97,38));
		g2d.setFont(new Font("Broadway BT", 1, 30));
		g2d.drawString(String.valueOf(money),811, 84);
		g2d.setFont(new Font("Hobo Std", 1, 40));
		g2d.drawString(String.valueOf(level),800, 166);
		g2d.setFont(new Font("Broadway BT", 1, 20));
		g2d.drawString(String.valueOf(percent)+"%",865,166);
		
		/*如果打破记录和升级*/
		if(levelUp){
			g2d.drawImage(upImage, 471,239,217,78,this);
		}
		if(newRecord){
			g2d.drawImage(recordImage, 246,239,217, 78, this);
		}
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
	class BackListener implements MouseListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("返回");

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==backButton){
				Player.setExp(Player.getExp()+score/200);
				Player.setGold(Player.getGold()+money);
				Player.setLvl(level);
				removeAll();
				
				ToolPanel tp = new ToolPanel(Player.getGold(),"src/images/toolpanel/toolpanel.png",bootFrame);
				tp.setSize(927, 661);
				tp.setLocation(0, 0);
				add(tp);
				repaint();
			}else if(e.getSource()==againButton){
				Player.setExp(Player.getExp()+score/200);
				Player.setGold(Player.getGold()+money);
				Player.setLvl(level);
				removeAll();
				
				ToolPanel tp = new ToolPanel(Player.getGold(),"src/images/toolpanel/toolpanel.png",bootFrame);
				tp.setSize(927, 661);
				tp.setLocation(0, 0);
				add(tp);
				repaint();
			}else if(e.getSource() == quitButton){
				bootFrame.dispose();
			}else if(e.getSource() == minButton){
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
	}
	
}

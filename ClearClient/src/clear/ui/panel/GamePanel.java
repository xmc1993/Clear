package clear.ui.panel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import clear.bean.*;
import clear.map.*;
import clear.ui.component.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*Panel的大小 927*661*/

public class GamePanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	/* 游戏池的行数和列数 */
	private final int NUM_ROW = 9;
	private final int NUM_COL = 9;

	private final int NUM_PATTERN = 16;

	final static int CYCLE_TIME = 100;
	final static int SINGLE_TIME = 30;
	static int count = 0;

	private final static int perTime = 25;

	private MapServiceInterface map;
	/* 得到图标的边长 */
	private int edge_len = Pattern.getPatternEdge_Len();
	/* 游戏池与x,y边的距离 */
	private final int VER_EDGE = 60;
	private final int HOR_EDGE = 60;
	/* 鼠标点击处的坐标 */
	private int xClicked;
	private int yClicked;
	private int xAbs = -1;
	private int yAbs = -1;
	/* 鼠标所在处的坐标 */
	private int xFocus;
	private int yFocus;
	/* 游戏有效点击区域的最大x、y坐标 */
	private int HOR_DIS = HOR_EDGE + edge_len * NUM_ROW;
	private int VER_DIS = VER_EDGE + edge_len * NUM_COL;

	private BufferedImage backImage;
	private BufferedImage tipImage;
	private BufferedImage pattern[];
	private BufferedImage overImage;
	private BufferedImage superImage;
	private BufferedImage twinkleImage;
	private BufferedImage propImage[];
	/* 存放所有的pattern */
	int maxCombo ;
	boolean hasSuper = false ;
	
	private ArrayList<Pattern> pat_list;

	private Graphics2D g2d;

	private float alpha = 1.0f;
	private float superAlpha=1.0f;

	private Timer timer = null;
	private Timer comboTimer;
	private Timer superTimer;
	private Timer tipTimer;
	private Timer inSuperTimer;
	private Timer overTimer;

	private boolean isGameOver=true;

	private JLabel scoreLabel;
	private JProgressBar bar;

	private JLabel timeLabel;
	private JLabel timeBar; 
	private JLabel superLabel;

	private ImageIcon icon;
	private ImageIcon superIcon[];

	private int combo_num;

	private boolean isInSuper;
	private boolean inTip;
	
	private boolean CProp;
	private boolean DProp;
	private boolean EProp;
	
	private int hintTime=3000;
	private int comboTime=1000;
	JFrame bootFrame;
	
	boolean isVistor = false ;
	
	public GamePanel(boolean isVisitor) {
		this.isVistor = isVisitor ;
		init();
		initImage();
	}
	public GamePanel(boolean CProp,boolean DProp,boolean EProp,JFrame bootFrame){
		this.bootFrame = bootFrame ;
		this.CProp=CProp;
		this.DProp=DProp;
		this.EProp=EProp;
		init();
		initImage();
	}

	public void init() {
		setSize(927, 661);
		addMouseListener(this);
		setLayout(null);
		map = new Map();
		if(DProp){
			map.D_prop();
		}
		if(CProp){
			comboTime=2000;
		}
		if(EProp){
			hintTime=2000;
		}
		isGameOver = false;
		pattern = new BufferedImage[NUM_PATTERN];
		propImage=new BufferedImage[4];
		pat_list = new ArrayList<Pattern>();
		initPat_list();
		scoreLabel = new JLabel("0");
		scoreLabel.setFont(new Font("Hobo Std", 1, 60));
		scoreLabel.setBounds(650, 89, 260, 96);
		scoreLabel.setForeground(Color.blue);
		add(scoreLabel);
		timeLabel = new JLabel("60s");
		timeLabel.setFont(new Font("黑体", 1, 48));
		timeLabel.setForeground(Color.gray);
		timeLabel.setBounds(780, 235, 134, 68);
		add(timeLabel);
		icon = new ImageIcon(new ImageIcon("src/images/game/bar.png").getImage());
		timeBar = new JLabel();
		timeBar.setIcon(icon);
		timeBar.setBounds(60, 568, 480, 30);
		add(timeBar);
		overTimer=new Timer(60000,new OverListener());
		overTimer.setInitialDelay(60000);
		overTimer.start();
		new ProgressThread(timeBar, timeLabel, this).start();
		combo_num = 0;
		superIcon=new ImageIcon[5];
		superLabel=new JLabel();
		superLabel.setBounds(629,370,235,29);
		add(superLabel);
		timer = new Timer(hintTime, this);
		timer.setInitialDelay(hintTime);
		timer.start();
		comboTimer = new Timer(comboTime, new ComboListener());    //这是什么哦
		comboTimer.setInitialDelay(comboTime);
		comboTimer.start();
		superTimer = new Timer(5000, new SuperListener());
		superTimer.setInitialDelay(5000);
		tipTimer = new Timer(200, new TipListener());
		tipTimer.setInitialDelay(0);
		inSuperTimer= new Timer(200, new InSuperListener());
		inSuperTimer.setInitialDelay(0);

	}

	private void initPat_list() {
		/* 初始化所有的网格图案 */
		Pattern tempPattern;
		for (int i = 0; i < NUM_ROW; i++) {
			for (int j = 0; j < NUM_COL; j++) {
				tempPattern = new Pattern();
				tempPattern.setImage(map.returnMapValue(j, i));
				tempPattern.setX_coor(HOR_EDGE + i * edge_len);
				tempPattern.setY_coor(VER_EDGE + j * edge_len);
				pat_list.add(tempPattern);
			}
		}
	}

	public void updatePat_list() {
		Pattern tempPattern;
		for (int i = 0; i < NUM_ROW; i++) {
			for (int j = 0; j < NUM_COL; j++) {
				tempPattern = new Pattern();
				tempPattern.setImage(map.returnMapValue(j, i));
				tempPattern.setX_coor(HOR_EDGE + i * edge_len);
				tempPattern.setY_coor(VER_EDGE + j * edge_len);
				pat_list.set(i * NUM_ROW + j, tempPattern);
			}
		}
	}

	/**
	 * 初始化图片资源
	 */
	public void initImage() {
		for(int a=0;a<4;a++){
			superIcon[a]=new ImageIcon(new ImageIcon("src/images/game/super_"+a+".png").getImage());
		}
		superLabel.setIcon(superIcon[0]);
		for (int i = 0; i < NUM_PATTERN; i++) {
			String url = "src/images/game/pattern_0" + (i + 1) + ".png";
			try {
				pattern[i] = ImageIO.read(new FileInputStream(url));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			backImage = ImageIO.read(new FileInputStream(
					"src/images/game/backGround.png"));
			tipImage = ImageIO.read(new FileInputStream("src/images/game/tip.png"));
			overImage=ImageIO.read(new FileInputStream("src/images/game/end.png"));
			superImage=ImageIO.read(new FileInputStream("src/images/game/super.png"));
			twinkleImage=ImageIO.read(new FileInputStream("src/images/game/twinkle.png"));
			propImage[0]=ImageIO.read(new FileInputStream("src/images/game/prop.png"));
			propImage[1]=ImageIO.read(new FileInputStream("src/images/game/propUp.png"));
			propImage[2]=ImageIO.read(new FileInputStream("src/images/game/propQuick.png"));
			propImage[3]=ImageIO.read(new FileInputStream("src/images/game/propSuper.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {

		g2d = (Graphics2D) g;
		g2d.drawImage(backImage, 0, 0, 927, 661, this);
		g2d.drawImage(propImage[0],645,535,85,108,this);
		g2d.drawImage(propImage[0],741,535,85,108,this);
		g2d.drawImage(propImage[0],837,535,85,108,this);
		if(CProp){
			g2d.drawImage(propImage[3],657,548,60,77,this);
		}
		if(DProp){
			g2d.drawImage(propImage[1],753,548,60,77,this);
		}
		if(EProp){
			g2d.drawImage(propImage[2],849,548,60,77,this);
		}
		
		
		if(isInSuper){
			AlphaComposite newComposite = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, superAlpha);
			g2d.setComposite(newComposite);
			g2d.getRenderingHints();
			g2d.drawImage(superImage, 650, 420, 204,64,this);
			g2d.drawImage(twinkleImage, 0, 11, 610,581,this);
		}
		if (isInSuper) {
			AlphaComposite newComposite = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 1.0f);
			g2d.setComposite(newComposite);
		}
		for (Pattern p : pat_list) {
			g2d.drawImage(pattern[p.getImage()], p.getX_coor(), p.getY_coor(),
					edge_len, edge_len, this);
		}
		
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_ROUND));
		if (xAbs != -1) { // 画标记线 .
			int d1 = 12;
			int d2 = edge_len - d1;
			g2d.drawLine(xAbs * edge_len + HOR_EDGE,
					yAbs * edge_len + VER_EDGE, (xAbs + 1) * edge_len
							+ HOR_EDGE - d2, yAbs * edge_len + VER_EDGE);
			g2d.drawLine(xAbs * edge_len + HOR_EDGE + d2, yAbs * edge_len
					+ VER_EDGE, (xAbs + 1) * edge_len + HOR_EDGE, yAbs
					* edge_len + VER_EDGE);
			g2d.drawLine(xAbs * edge_len + HOR_EDGE, (yAbs + 1) * edge_len
					+ VER_EDGE, (xAbs + 1) * edge_len + HOR_EDGE - d2,
					(yAbs + 1) * edge_len + VER_EDGE);
			g2d.drawLine(xAbs * edge_len + HOR_EDGE + d2, (yAbs + 1) * edge_len
					+ VER_EDGE, (xAbs + 1) * edge_len + HOR_EDGE, (yAbs + 1)
					* edge_len + VER_EDGE);
			g2d.drawLine((xAbs + 1) * edge_len + HOR_EDGE, yAbs * edge_len
					+ VER_EDGE, (xAbs + 1) * edge_len + HOR_EDGE, (yAbs + 1)
					* edge_len + VER_EDGE - d2);
			g2d.drawLine((xAbs + 1) * edge_len + HOR_EDGE, yAbs * edge_len
					+ VER_EDGE + d2, (xAbs + 1) * edge_len + HOR_EDGE,
					(yAbs + 1) * edge_len + VER_EDGE);
			g2d.drawLine(xAbs * edge_len + HOR_EDGE, (yAbs + 1) * edge_len
					+ VER_EDGE, xAbs * edge_len + HOR_EDGE, yAbs * edge_len
					+ VER_EDGE + d2);
			g2d.drawLine(xAbs * edge_len + HOR_EDGE, (yAbs + 1) * edge_len
					+ VER_EDGE - d2, xAbs * edge_len + HOR_EDGE, yAbs
					* edge_len + VER_EDGE);
		}

		if (inTip) {
			AlphaComposite newComposite = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(newComposite);
			ArrayList<Integer> list = map.hint();
			int index1 = list.get(0) + list.get(1) * NUM_ROW;
			int index2 = list.get(2) + list.get(3) * NUM_ROW;
			g2d.drawImage(tipImage, pat_list.get(index1).getX_coor(), pat_list
					.get(index1).getY_coor(), edge_len, edge_len, this);
			g2d.drawImage(tipImage, pat_list.get(index2).getX_coor(), pat_list
					.get(index2).getY_coor(), edge_len, edge_len, this);

		}

		AlphaComposite newComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f);
		g2d.setComposite(newComposite);
		//if(isGameOver){
			//g2d.drawImage(overImage,0,0,927,661,this);

	}

	private void swap(int type) {
		int item_0 = xAbs * NUM_ROW + yAbs; // 交换的两个图案之一的序号 .
		String info = map.getLastClicked();
		String[] tmp = info.split(";");
		int x = Integer.parseInt(tmp[0]);
		int y = Integer.parseInt(tmp[1]);
		int item_1 = x * NUM_ROW + y; // 交换的两个图案之二的序号 .
		Pattern pat_0;
		Pattern pat_1;
		if (type == 1) {
			pat_0 = pat_list.get(item_0);
			pat_1 = pat_list.get(item_1);
		} else {
			pat_1 = pat_list.get(item_0);
			pat_0 = pat_list.get(item_1);
		}
		int xDis = xAbs - x;
		int yDis = yAbs - y;
		if (xDis != 0) {
			for (int i = 0; i < 6; i++) {
				pat_0.setX_coor(pat_0.getX_coor() - xDis * edge_len / 6);
				pat_1.setX_coor(pat_1.getX_coor() + xDis * edge_len / 6);
				SwingUtilities.invokeLater(new Runnable() { // 将repaint方法加入到EDT队列中去
							public void run() {
								repaint();
							}
						});
				try {
					Thread.sleep(perTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			for (int j = 0; j < 5; j++) {
				pat_0.setY_coor(pat_0.getY_coor() - yDis * edge_len / 6);
				pat_1.setY_coor(pat_1.getY_coor() + yDis * edge_len / 6);
				SwingUtilities.invokeLater(new Runnable() { // 将repaint方法加入到EDT队列中去
							public void run() {
								repaint();
							}
						});
				try {
					Thread.sleep(perTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void continueClear() {

		map.clearAllMap(); // 消除整个地图 .
		updatePat_list(); // 更新图标列表 .
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				repaint();

			}
		});
		try {
			Thread.sleep(30); // 刷新中间界面 .
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.moveMap(); // 下落
		map.updateMap(); // 更新图标列表
		while (!map.isNotDead()) { // 当为死局的时候不断刷新地图 .
			map.updateMap(); // 刷新map
		}
		updatePat_list();
		fallDown();

	}

	public void setIsGameOver(boolean boo) {
		isGameOver = boo;
	}

	private void afterClear() {
		updatePat_list();
		SwingUtilities.invokeLater(new Runnable() { // 将repaint方法加入到EDT队列中去
					public void run() {
						repaint();
					}
				});
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.moveMap(); // 下落
		map.updateMap();
		while (!map.isNotDead()) { // 当为死局的时候不断刷新地图 .
			map.updateMap(); // 刷新界面 .
		}
		updatePat_list();
		fallDown();

		while (map.haveDis()) { // 当 出现连续消除的时候 .
			continueClear();
		}
	}

	private void fallDown() {
		for (int i = 0; i < NUM_ROW; i++) {
			for (int j = 0; j < NUM_COL; j++) {
				pat_list.get(i * NUM_ROW + j).setY_coor(
						pat_list.get(i * NUM_ROW + j).getY_coor()
								- map.getFallDis(j, i));
			}
		}
		SwingUtilities.invokeLater(new Runnable() { // 将repaint方法加入到EDT队列中去
					public void run() {
						repaint();

					}
				});
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int k = 0; k < 6; k++) {
			for (int i = 0; i < NUM_ROW; i++) {
				for (int j = 0; j < NUM_COL; j++) {
					pat_list.get(i * NUM_ROW + j).setY_coor(
							pat_list.get(i * NUM_ROW + j).getY_coor()
									+ map.getFallDis(j, i) / 6);
				}
				SwingUtilities.invokeLater(new Runnable() { // 将repaint方法加入到EDT队列中去
							public void run() {
								repaint();

							}
						});
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		map.initFallDisMap();
	}

	private void over() {
		timer.stop();
		comboTimer.stop();
		tipTimer.stop();
		overTimer.stop();

	}

	private Cursor getMyCursor() {
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("src/images/").getImage(),
				new Point(10, 20), "stick");
		return cursor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//comboTimer.stop();
		inTip = true;
		tipTimer.start();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (timeBar.getBounds().width <= 0) {			
			isGameOver=true;
			repaint();
			over();
			return;
		}

		/* 提示功能 */
		timer.restart();
		if (inTip) {
			inTip = false;
			tipTimer.stop();
		}

		xFocus = e.getX();
		yFocus = e.getY();

		Runnable runnable = new Runnable() {
			public void run() {
				if (((HOR_EDGE <= xFocus) && (xFocus <= HOR_DIS))
						&& ((VER_EDGE <= yFocus) && (yFocus <= VER_DIS))) {
					xAbs = (xFocus - HOR_EDGE) / edge_len;// 横坐标
					yAbs = (yFocus - VER_EDGE) / edge_len;// 纵坐标
					int image = pat_list.get(xAbs * NUM_ROW + yAbs).getImage();

					if (image == 15) {// 如果点击的是道具
						map.B_prop(yAbs, xAbs);
						afterClear();
					} else if (((image > 7) && (image < 15))
							&& (!map.needSwap(yAbs, xAbs))){
						map.A_prop(yAbs, xAbs);
						afterClear();
					} else { // 如果点击的不是道具
						boolean needSwap = map.needSwap(yAbs, xAbs);
						if (needSwap) {
							swap(1);
						}
						boolean swap = map.swap(yAbs, xAbs);
						if (needSwap) { // 如果是需要交换的情况
							if (!swap) {
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								swap(2);
							} else {
								comboTimer.restart();
								combo_num++;
								if (combo_num >= 4) {
									combo_num=4;
									isInSuper = true;
									hasSuper = true ;
									inSuperTimer.start();
									map.superMode(true);
									superTimer.restart();// 刷新super模式的时间
								} 
								superLabel.setIcon(superIcon[combo_num]);
								afterClear();
							}
						} else {
							repaint();
						}

					}

				}
System.out.println("score:"+map.getScore());
				scoreLabel.setText(Integer.toString(map.getScore()));
			}
		};
		new Thread(runnable).start();
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

	class TimeListener implements ActionListener {     //这个是什么哦
		int time = 0;

		public void actionPerformed(ActionEvent e) {
			time++;
			bar.setValue(bar.getValue() - 1);
			bar.setString(bar.getValue() + "s");
			if (time > 60) {
				timer.stop();
				map.superMode(false);
				isGameOver = true;
			}
		}

	}

	
	class ComboListener implements ActionListener {      
		public void actionPerformed(ActionEvent e) {
			
			if (combo_num > 0) {
				maxCombo = combo_num ;
				combo_num--;
				superLabel.setIcon(superIcon[combo_num]);
			}

		}
	}

	class SuperListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			isInSuper = false;
			inSuperTimer.stop();
			superTimer.stop();
		}
	}

	class TipListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (count % 3) {
			case 0:
				alpha = 1.0f;
				break;
			case 1:
				alpha = 0.75f;
				break;
			case 2:
				alpha = 0.6f;
				break;
			}
			count++;
			repaint();

		}
	}
	class InSuperListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (count % 3) {
			case 0:
				superAlpha = 1.0f;
				break;
			case 1:
				superAlpha = 0.75f;
				break;
			case 2:
				superAlpha = 0.6f;
				break;
			}
			count++;
			repaint();

		}
	}
	class OverListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			removeAll();
			if(isVistor){
				SignPanel op=new SignPanel(bootFrame);
				op.setBounds(0, 0, 927, 661);
				add(op);
				over();
				repaint();
			}else{
				int score = Integer.parseInt(scoreLabel.getText()) ;
				int oldLevel = Player.getLvl() ;
				int oldExp = Player.getExp() ;
				int newExp = oldExp*200+score ;
				int newLvl = new ExpToLvl().trans(newExp);
				boolean lvlUp = false ;
				if(newLvl>oldLevel){
					lvlUp = true ;
				}
				boolean newRecord = false ;
				if(score>Player.getMax()){
					newRecord = true ;
				}
				
				int nextExp = new LvlToExp().getExp(newLvl+1) ;
				int percent = 0 ;
				
				percent = newExp/nextExp ; 

				
				int lianji = 0 ;
				if(hasSuper){
					lianji = 4 ;
				}else{
					lianji = maxCombo ;
				}
				
				int gold = score/100;
				OverPanel op=new OverPanel(lianji ,score,newLvl,percent,gold,lvlUp,newRecord,bootFrame);
				op.setBounds(0, 0, 927, 661);
				add(op);
				over();
				repaint();
			}
			

		}
	}

}

package clear.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScorePerGamePanel extends JPanel{
	private ArrayList<String> list=new ArrayList<String>();
	private BufferedImage backGround;
	private BufferedImage lineImage;
	private BufferedImage upImage;
	private BufferedImage downImage;
	private BufferedImage gameImage;
	private int ptr=0;
	private int lineNum=4;
	private int col;
	private JPanel upPanel;
	private JPanel downPanel;
	
	public ScorePerGamePanel(ArrayList<String> list){
		this.list=list;
		col=list.size();
		setSize(630,160);
		setLayout(null);
		init();
		upPanel=new JPanel();
		upPanel.setOpaque(false);
		upPanel.setVisible(true);
		upPanel.setBounds(579,27,37,21);
		upPanel.addMouseListener(new MyListener());
		add(upPanel);
		downPanel=new JPanel();
		downPanel.setOpaque(false);
		downPanel.setVisible(true);
		downPanel.setBounds(579, 131, 37,21);
		downPanel.addMouseListener(new MyListener());
		add(downPanel);
	}
	public ScorePerGamePanel(){
		setSize(692,192);
		init();
	}
	public void init(){
		initImage();
	}
	public void initImage(){
		try {
			backGround= ImageIO.read(new FileInputStream("src/images/chart/chart_2.png"));
			lineImage = ImageIO.read(new FileInputStream("src/images/chart/line.png"));
			upImage= ImageIO.read(new FileInputStream("src/images/chart/up.png"));
			downImage= ImageIO.read(new FileInputStream("src/images/chart/down.png"));
			gameImage= ImageIO.read(new FileInputStream("src/images/chart/Game.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(backGround,0,0,630,160,this);
		
		g2d.setFont(new Font("Hobo Std", 1, 14));
		
		int base=48;
		int dis=32;
		int num = 10001;
		
			if(col<=lineNum){   //如果数据数小于显示的行数
				int a=0;
				for(String s:list){
					if(s.equals("0")){
						
					}else{
						g2d.drawImage(gameImage,300,52,77,18,this);
						String[] tmp=s.split(" ");
						g2d.setColor(new Color(69,113,134));
						g2d.drawString(num+"",148,base+dis*a);
						g2d.setColor(new Color(118,69,134));
						g2d.drawString(tmp[0],238,base+dis*a);
						g2d.setColor(new Color(208,91,129));
						g2d.drawString(tmp[1],455,base+dis*a);
						a++;
						num++;
					}
					
				}
			}else{     //如果数据数大于显示的行数
				for(int j=0;j<lineNum;j++){
					
					g2d.drawImage(gameImage,40,33+dis*j,77,18,this);
					String s=list.get((ptr+j)%col);
					if(s.equals("0")){
						
					}else{
						String[] tmp=s.split(" ");
						g2d.setColor(new Color(69,113,134));
						g2d.drawString(num+"",148,base+dis*j);
						g2d.setColor(new Color(118,69,134));
						g2d.drawString(tmp[0],238,base+dis*j);
						g2d.setColor(new Color(208,91,129));
						g2d.drawString(tmp[1],455,base+dis*j);
						num++;
					}
					
				}
			}
		
		
	}
	
	public class MyListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(col<=lineNum){
				return;
			}
			if(e.getSource()==upPanel){
				ptr--;
				ptr=(ptr+lineNum)%lineNum;
				
			}else if(e.getSource()==downPanel){
				ptr++;
				ptr=ptr%lineNum;
			}
			repaint();
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

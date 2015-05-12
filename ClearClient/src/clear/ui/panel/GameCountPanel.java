package clear.ui.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import clear.ui.test.TestDrive;

public class GameCountPanel extends JPanel{
	private int count[];
	private int day[];
	private float x_dis=43;
	private float y_dis=120;
	private float total_dis=83;
	private float ver=10;
	private float hor=91;
	private BufferedImage chartImage;
	
	public GameCountPanel(int[] count){
		setSize(630,160);
		this.count=new int[7];
		int len = count.length;
		int i = 0 ;
		int c = 0 ;
		
		for( i=7-len;i<7;i++){
			this.count[i]=count[c];
			c++;
		}
		for(int j = 0 ; j <7-len; j++){
	
			this.count[j] = 0 ;
		}
		
		init();
	}
	
	public static void main(String[] args){
		TestDrive td= new TestDrive();
		int[] count = {4,5,6,7,8,9};
	
		td.test(new GameCountPanel(count));
	}
	
	private void init(){
	
		int max=getMax();
		if(max!=0){
			ver=total_dis/max;
		}
		try {
			chartImage = ImageIO.read(new FileInputStream("src/images/chart/chart_0.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public int getMax(){
		int max=count[0];
		for(int i:count){
			if(i>max){
				max=i;
			}
		}
		return max;
	}
	
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(chartImage, 0, 0, 630, 160, this);
		g2d.setFont(new Font("ºÚÌå", 1, 10));
		g2d.setColor(new Color(95,22,108));
		g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_ROUND));
		g2d.setColor(new Color(95,22,108));
		int i;
		for(i=0;i<6;i++){
			g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND));
			g2d.drawLine((int)(x_dis+i*hor), (int)(y_dis-count[i]*ver), (int)(x_dis+(i+1)*hor),(int)( y_dis-count[i+1]*ver));
			
			g2d.setStroke(new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,
					3.5f,new float[]{15,10,},0f));
			g2d.drawLine((int)(x_dis),(int)(y_dis-count[i]*ver),(int)(x_dis+i*hor),(int)(y_dis-count[i]*ver));
			g2d.drawString(String.valueOf(count[i]),15, y_dis-count[i]*ver+2);
			
		}
		g2d.drawLine((int)(x_dis),(int)(y_dis-count[i]*ver),(int)(x_dis+i*hor),(int)(y_dis-count[i]*ver));
		g2d.drawString(String.valueOf(count[i]),15, y_dis-count[i]*ver+2);
	}
}

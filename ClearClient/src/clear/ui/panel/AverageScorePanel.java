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

/*
 * 仅仅返回十天内局数的曲线
 */
public class AverageScorePanel extends JPanel{
	private double count[];
	private int day[];
	private float x_dis=43;
	private float y_dis=120;
	private float total_dis=83;
	private double ver=10;
	private float hor=91;
	private BufferedImage chartImage;
	
	public AverageScorePanel(double[] count){
		setSize(630,160);
		this.count=new double[7];
		int len = count.length;
		int i = 0 ;
		int c = 0;
		for( i=7-len;i<7;i++){
			this.count[i]=count[c];
			c++;
		}
		for(int j = 0 ; j <7-len; j++){
	
			this.count[j] = 0 ;
		}
		init();
	}
	
	public AverageScorePanel(){
		init();
	}
	private void init(){
		double max=getMax(count);
		if(max!=0){
		ver=total_dis/max;
		}
		try {
			chartImage = ImageIO.read(new FileInputStream("src/images/chart/chart_1.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public double getMax(double[] count2){
		double max=count2[0];
		for(double i:count2){
			if(i>max){
				max=i;
			}
		}
		return max;
	}
	
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(chartImage, 0, 0, 630, 160, this);
		g2d.setFont(new Font("黑体", 1, 10));
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
			g2d.drawString(String.valueOf(count[i]),10, (int) (y_dis-count[i]*ver+2));
		}
		g2d.drawLine((int)(x_dis),(int)(y_dis-count[i]*ver),(int)(x_dis+i*hor),(int)(y_dis-count[i]*ver));
		g2d.drawString(String.valueOf(count[i]),10, (int) (y_dis-count[i]*ver+2));
	}
}

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

public class GameRecordPanel extends JPanel{
	private BufferedImage backGround;
	private String gameCount;
	private String aveScore;
	private String higestCombo;
	private String higestScore;
	
	public  GameRecordPanel(String gameCount,String aveScore,String higestCombo,String higestScore){
		this.gameCount=gameCount;
		this.aveScore=aveScore;
		this.higestCombo=higestCombo;
		this.higestScore=higestScore;
		init();
	}
	public void init(){
		initImage();
		setSize(630,160);
	}
	public void initImage(){
		try {
			backGround= ImageIO.read(new FileInputStream("src/images/chart/chart_3.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(backGround, 0, 0, 630, 160, this);
		g2d.setFont(new Font("·ÂËÎ", 1, 18));
		g2d.setColor(new Color(250,198,193));
		g2d.drawString(gameCount, 206, 75);
		g2d.drawString(aveScore, 432, 75);
		g2d.drawString(higestCombo, 206, 119);
		g2d.drawString(higestScore, 432, 119);
	}
	
	public static void main(String[] args){
		TestDrive td= new TestDrive();
		td.test(new GameRecordPanel("40", "45", "34", "34"));
	}
	
}
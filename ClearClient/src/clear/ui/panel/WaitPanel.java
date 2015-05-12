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
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WaitPanel extends JPanel{
	private BufferedImage backGround;
	private Image peopleImage;
	private Timer timer;
	JFrame bootFrame;
	public WaitPanel(JFrame boot){
		bootFrame = boot;
		setSize(927,661);
		setLayout(null);
		init();
		initImage();
		timer=new Timer(3000,new MyListener());
		timer.setInitialDelay(1500);
		timer.start();
	}
	private void init(){
		setSize(927,661);
	}
	private void initImage(){
		peopleImage=new ImageIcon("src/images/wait/people.gif").getImage();
		try {
			backGround=ImageIO.read(new FileInputStream("src/images/wait/backGround.png"));
			//peopleImage=ImageIO.read(new FileInputStream("src/images/wait/people.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(backGround, 0, 0, 927, 661,this);  //»­±³¾°
		g2d.drawImage(peopleImage,423,323,60,34,this);

	}
	
//	public static void main(String[] args){
//		JFrame jf=new JFrame();
//		WaitPanel wp=new WaitPanel();
//		wp.setBounds(0, 0, 927, 661);
//		jf.setLayout(null);
//		jf.add(wp);
//		jf.setSize(943,699);
//		jf.setVisible(true);
//	}
	
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			removeAll();
			SignPanel sp =new SignPanel(bootFrame);
			sp.setBounds(0, 0, 927, 661);
			add(sp);
			repaint();
			timer.stop();
		}
	}
	
	
}

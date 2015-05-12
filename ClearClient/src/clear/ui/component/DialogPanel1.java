package clear.ui.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DialogPanel1 extends JPanel{
	ImageIcon imageIcon;
	int width;
	int hight;
	public DialogPanel1(){
		imageIcon = new ImageIcon("src/images/Dialog/donmatch.png");
		
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		System.out.println(width);
		System.out.println(hight);
		setVisible(true);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return hight;
	}
	/**
	 * 重写panel的绘图方法，改变panel的背景
	 */
	public void paintComponent(Graphics g){
		Image image = imageIcon.getImage();
		g.drawImage(image,0,0,this);
	}
}

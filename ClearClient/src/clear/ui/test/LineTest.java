package clear.ui.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LineTest extends JPanel{
	JLabel l = new JLabel("asd");
	Image image = null;
	ImageIcon imageIcon = null;
	public LineTest(){
		add(l);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		imageIcon = new ImageIcon("src/images/headman.jpg");
		image = imageIcon.getImage();
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		g.drawImage(image, 0, 0, this);
		Graphics2D g2d  = (Graphics2D) g;
		for (int i = 0;i<10;i++){
			Line2D line = new Line2D.Float(i, i, 100*i, 100*i+1);
			g2d.draw(line);
		}
		
	}
	public static void main(String [] args){
		TestDrive t = new TestDrive();
		t.test(new LineTest());
		t.repaint();
	}
}

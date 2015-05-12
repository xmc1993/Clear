package clear.ui.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import enupackage.PanelType;

public class HeadPicture extends JPanel{
	int width;
	int hight;
	Image image = null;
	ImageIcon imageIcon = null;
	String pictureName;
	public HeadPicture(String name){
		pictureName = name;	
	}
	public void paintComponent(Graphics g) {
		imageIcon = new ImageIcon("src/images/headpicture/"+pictureName+".jpg");
		image = imageIcon.getImage();
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		g.drawImage(image, 0, 0, this);
	}
}

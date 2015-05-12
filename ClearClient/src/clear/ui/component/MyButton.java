package clear.ui.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import clear.ui.test.TestDrive;
import enupackage.ButtonType;
import enupackage.MouseType;

public class MyButton extends JPanel {
	int width;
	int hight;
	Image image = null;
	ImageIcon imageIcon = null;
	ButtonType type;
	MouseType mousestate = MouseType.OUT;

	//
	public MyButton(ButtonType type) {
		this.type = type;
	}

	//
	public void paintComponent(Graphics g) {
		if (mousestate == MouseType.OUT) {
			if (type == ButtonType.loginButton) {
				imageIcon = new ImageIcon("src/images/loginbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.signInButton) {
				imageIcon = new ImageIcon("src/images/signinbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.minButton) {
				imageIcon = new ImageIcon("src/images/minbutton.png");
				image = imageIcon.getImage();
				System.out.print("created");
			} else if (type == ButtonType.stopButton) {
				imageIcon = new ImageIcon("src/images/stopbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.quitButton) {
				imageIcon = new ImageIcon("src/images/quitbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.visitButton) {
				imageIcon = new ImageIcon("src/images/visitbutton.png");
				image = imageIcon.getImage();
			}else if(type == ButtonType.inviteButton0){
//				imageIcon = new ImageIcon("src/images/invitebutton_0.jpg");
				imageIcon = new ImageIcon("src/images/friendsRankPanel/invitebutton_0.jpg");
				image = imageIcon.getImage();
				System.out.println(imageIcon.getIconHeight());
			}else if(type == ButtonType.inviteButton1){
//				imageIcon = new ImageIcon("src/images/invitebutton1.jpg");
				imageIcon = new ImageIcon("src/images/friendsRankPanel/invitebutton_1.jpg");
				System.out.println(imageIcon.getIconHeight());
				image = imageIcon.getImage();
			}else if(type == ButtonType.inviteButton2){
//				imageIcon = new ImageIcon("src/images/invitebutton2.jpg");
				imageIcon = new ImageIcon("src/images/friendsRankPanel/invitebutton_2.jpg");
				System.out.println(imageIcon.getIconHeight());
				image = imageIcon.getImage();
			}else if(type == ButtonType.nextButton){
				imageIcon = new ImageIcon("src/images/nextbutton.jpg");
				image = imageIcon.getImage();
			}else if(type == ButtonType.nextButton01){
				imageIcon = new ImageIcon("src/images/datanext.jpg");
				image = imageIcon.getImage();
			}else if(type == ButtonType.lastButton01){
				imageIcon = new ImageIcon("src/images/datalast.jpg");
				image = imageIcon.getImage();
			}else if (type == ButtonType.lastButton){
				imageIcon = new ImageIcon("src/images/lastbutton.jpg");
				image = imageIcon.getImage();
			}
		}else if (mousestate == MouseType.ENTER){
			if (type == ButtonType.loginButton) {
				imageIcon = new ImageIcon("src/images/loginbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.signInButton) {
				imageIcon = new ImageIcon("src/images/signinbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.minButton) {
				imageIcon = new ImageIcon("src/images/minbutton1.jpg");
				image = imageIcon.getImage();
				System.out.print("created");
			} else if (type == ButtonType.stopButton) {
				imageIcon = new ImageIcon("src/images/stopbutton1.jpg");
				image = imageIcon.getImage();
			} else if (type == ButtonType.quitButton) {
				imageIcon = new ImageIcon("src/images/quitbutton.png");
				image = imageIcon.getImage();
			} else if (type == ButtonType.visitButton) {
				imageIcon = new ImageIcon("src/images/visitbutton.png");
				image = imageIcon.getImage();
			}
		}
		System.out.println(imageIcon.getIconWidth());
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		System.out.println("painted");
		g.drawImage(image, 0, 0, this);

	}
	public void changeState(MouseType type) {
		mousestate = type;
	}

	//
	public static void main(String[] args) {
		JPanel panel = new MyButton(ButtonType.inviteButton0);
		TestDrive t = new TestDrive();
		t.test(panel);
	}
}

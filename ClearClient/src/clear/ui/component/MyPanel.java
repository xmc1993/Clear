package clear.ui.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import clear.ui.test.TestDrive;
import enupackage.ButtonType;
import enupackage.MouseType;
import enupackage.PanelType;

public class MyPanel extends JPanel{
	int width;
	int hight;
	Image image = null;
	ImageIcon imageIcon = null;
	PanelType type;

	//
	public MyPanel(PanelType type) {
		this.type = type;
	}

	//
	public void paintComponent(Graphics g) {
			if (type == PanelType.HeadPicture0) {
//				imageIcon = new ImageIcon("src/images/headman.jpg");
				imageIcon = new ImageIcon("src/images/friendsRankPanel/headman.jpg");
				image = imageIcon.getImage();
			}else if(type == PanelType.HeadPicture1){
//				imageIcon = new ImageIcon("src/images/headwoman.jpg");
				imageIcon = new ImageIcon("src/images/friendsRankPanel/headman.jpg");
				image = imageIcon.getImage();
			}else if(type == PanelType.NULL){
//				imageIcon = new ImageIcon("src/images/headwoman.jpg");
				imageIcon = new ImageIcon("src/images/chatHead/Í·Ïñ¿ò.png");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head0){
				imageIcon=new ImageIcon("src/images/chatHead/head0.jpg");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head1){
				imageIcon=new ImageIcon("src/images/chatHead/head1.jpg");
				image = imageIcon.getImage();
			}else if(type == PanelType.Dialog){
				imageIcon = new ImageIcon("src/images/Dialog/background01.png");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head2){
				imageIcon=new ImageIcon("src/images/chatHead/head2.jpg");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head3){
				imageIcon=new ImageIcon("src/images/chatHead/head3.jpg");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head4){
				imageIcon=new ImageIcon("src/images/chatHead/head4.jpg");
				image = imageIcon.getImage();
			}else if(type==PanelType.Head5){
				imageIcon=new ImageIcon("src/images/chatHead/head5.jpg");
				image = imageIcon.getImage();
			}
			else if (type == PanelType.Help) {
				imageIcon = new ImageIcon("src/images/help.png");
				image = imageIcon.getImage();
			} else if (type == PanelType.Login) {
				imageIcon = new ImageIcon("src/images/login.jpg");
				image = imageIcon.getImage();
				System.out.print("created");
			} else if (type == PanelType.Model) {
				imageIcon = new ImageIcon("src/images/model.png");
				image = imageIcon.getImage();
			} else if (type == PanelType.Rank) {
				imageIcon = new ImageIcon("src/images/rank.png");
				image = imageIcon.getImage();
			} else if(type == PanelType.FriendsBack){
				imageIcon = new ImageIcon("src/images/friendsRankPanel/bootpanel.jpg");
				image = imageIcon.getImage();
			}else if(type ==PanelType.tableBack){
				imageIcon = new ImageIcon("src/images/tableback.jpg");
				image = imageIcon.getImage();
			}else if(type == PanelType.datapanel){
				imageIcon = new ImageIcon("src/images/friendsRankPanel/bootpanel.jpg");
				image = imageIcon.getImage();
			}else if(type == PanelType.homepane){
				imageIcon = new ImageIcon("src/images/friendsRankPanel/bootpanel.jpg");
				image = imageIcon.getImage();
			}
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		g.drawImage(image, 0, 0, this);
		
	}
	public static void main(String[] args){
		MyPanel mypane = new MyPanel(PanelType.Login);
		mypane.setSize(927, 661);
		TestDrive td = new TestDrive();
		td.test(mypane);
	}
}

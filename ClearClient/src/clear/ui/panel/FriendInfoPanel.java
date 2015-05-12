package clear.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clear.ui.component.MyButton;
import clear.ui.component.MyPanel;
import clear.ui.test.TestDrive;
import enupackage.ButtonType;
import enupackage.MouseType;
import enupackage.PanelType;

public class FriendInfoPanel extends JPanel implements MouseListener {
	ImageIcon imageIcon;
	JPanel headPicture;
	JLabel nameLabel;
	JLabel lvlLabel;
	JLabel goldLabel;
	MyButton inviteButton;
	int width;
	int hight;
	int num;

	public FriendInfoPanel(String name, int head, int num, int lvl ,String gold) {
System.out.println("name :"+name+" "+head+" "+num+" "+lvl+" "+gold);
		this.num = num;
		if (num == 0) {
			imageIcon = new ImageIcon("src/images/friendsRankPanel/item_0.jpg");// 背景图片665*101
																// (191,86)
		} else if (num == 1) {
			imageIcon = new ImageIcon("src/images/friendsRankPanel/item_1.jpg");
		} else if (num == 2) {
			imageIcon = new ImageIcon("src/images/friendsRankPanel/item_2.jpg");
		}
		
		switch(head){
			case -1:
				headPicture=new MyPanel(PanelType.NULL);
				break;
			case 0:
				headPicture=new MyPanel(PanelType.Head0);
				break;
			case 1:
				headPicture=new MyPanel(PanelType.Head1);
				break;
			case 2:
				headPicture=new MyPanel(PanelType.Head2);
				break;
			case 3:
				headPicture=new MyPanel(PanelType.Head3);
				break;
			case 4:
				headPicture=new MyPanel(PanelType.Head4);
				break;
			case 5:
				headPicture=new MyPanel(PanelType.Head5);
				break;
			default:
				break;
		}
		
		nameLabel = new JLabel(name);
		lvlLabel = new JLabel(lvl+" 级");
		goldLabel = new JLabel(gold+" 金币");
		if (num == 0) {
			inviteButton = new MyButton(ButtonType.inviteButton0);
		} else if (num == 1) {
			inviteButton = new MyButton(ButtonType.inviteButton1);
		} else if (num == 2) {
			inviteButton = new MyButton(ButtonType.inviteButton2);
		}
		// 设置label中的字体样式
		nameLabel.setFont(new java.awt.Font("Hobo Std", 1, 18));
		nameLabel.setForeground(Color.blue);
		lvlLabel.setFont(new java.awt.Font("Hobo Std", 1, 18));
		lvlLabel.setForeground(Color.blue);
		goldLabel.setFont(new java.awt.Font("Hobo Std", 1, 18));
		goldLabel.setForeground(Color.blue);
		// 设置各个组建的位置
//		headPicture.setLocation(0, 22);//00
		headPicture.setLocation(2, 29);//00
		nameLabel.setLocation(127, 29);
		lvlLabel.setLocation(237, 29);
		goldLabel.setLocation(347, 29);
//		inviteButton.setLocation(578, 0);
		inviteButton.setLocation(448, 27);
		// 各组件设置大小
//		headPicture.setSize(87, 101);
		headPicture.setSize(61, 49);
		nameLabel.setSize(134, 39);
		lvlLabel.setSize(224, 39);
		goldLabel.setSize(224, 39);
//		inviteButton.setSize(87, 101);
		inviteButton.setSize(61,49);
		// 设置监听
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		inviteButton.addMouseListener(this);
		inviteButton.setBorder(null);
		setLayout(null);
		add(headPicture);
		add(nameLabel);
		add(lvlLabel);
		add(goldLabel);
		add(inviteButton);
		setVisible(true);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}

	public void paintComponent(Graphics g) {
		Image image = imageIcon.getImage();
		g.drawImage(image, 0, 0, this);
		System.out.println("=====" + width);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == inviteButton) {
			inviteButton.changeState(MouseType.ENTER);
			inviteButton.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == inviteButton) {
			inviteButton.changeState(MouseType.OUT);
			inviteButton.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		TestDrive td = new TestDrive();
		td.test(new FriendInfoPanel("徐阳东", 1, 2, 1,"100"));
	}
}

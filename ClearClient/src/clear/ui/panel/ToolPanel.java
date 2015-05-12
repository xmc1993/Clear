package clear.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clear.ui.data.HomeMsg;
import clear.ui.logic.RoomDataService;
import clear.ui.logic.RoomDataServiceInterface;
import clear.ui.network.GetAllRoom1Client;
import clear.ui.test.TestDrive;

public class ToolPanel extends JPanel implements MouseListener {
	ImageIcon imageIcon;
	int width;
	int hight;
	JLabel tool1;
	JLabel tool2;
	JLabel tool3;
	JPanel start;
	JPanel back;
	JLabel paymoney;
	JLabel leftmoney;
	int initemoney;
	int spend;
	Font font;
	boolean t1 = false;
	boolean t2 = false;
	boolean t3 = false;
	JLabel tips;
	JFrame bootFrame;
	private JLabel minButton;
	private JLabel quitButton;
	public ToolPanel(int initemoney,String path,JFrame boot) {
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		bootFrame = boot;
		spend = 0;
		this.initemoney = initemoney;
		imageIcon = new ImageIcon(path);
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		tips = new JLabel();
		font = new Font("Dialog", 1, 15);
		tool1 = new JLabel();
		tool2 = new JLabel();
		tool3 = new JLabel();
		paymoney = new JLabel();
		leftmoney = new JLabel("" + initemoney);
		paymoney.setFont(font);
		leftmoney.setFont(font);
		paymoney.setForeground(Color.black);
		leftmoney.setForeground(Color.black);
		back = new JPanel();
		start = new JPanel();
		back.setVisible(true);
		start.setVisible(true);
		back.setOpaque(false);
		start.setOpaque(false);
		
		tips.setSize(435, 37);
		back.setSize(111, 47);
		start.setSize(179, 59);
		tool3.setSize(137, 223);
		tool2.setSize(137, 223);
		tool1.setSize(137, 223);
		paymoney.setSize(77, 35);
		leftmoney.setSize(77, 35);

		tips.setLocation(154, 455);
		back.setLocation(83, 578);
		start.setLocation(382, 566);
		tool1.setLocation(155, 193);
		tool2.setLocation(397, 193);
		tool3.setLocation(639, 193);
		paymoney.setLocation(708, 455);
		leftmoney.setLocation(799, 586);

		tool1.addMouseListener(this);
		tool2.addMouseListener(this);
		tool3.addMouseListener(this);
		back.addMouseListener(this);
		start.addMouseListener(this);
		setLayout(null);
		add(tips);
		add(tool1);
		add(tool2);
		add(tool3);
		add(paymoney);
		add(leftmoney);
		add(back);
		add(start);
	}

	public void paintComponent(Graphics g) {
		Image image = imageIcon.getImage();
		g.drawImage(image, 0, 0, this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == tool1) {
			
			if (!t1) {
				if(Integer.parseInt(leftmoney.getText())<60){
					
				}else{
					tool1.setIcon(new ImageIcon("src/images/toolpanel/tool2.png"));
					spend = spend + 60;
					t1 = true;
				}
				
			} else {
				tool1.setIcon(null);
				spend = spend -60;
				t1 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t2.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool1");
		} else if (source == tool2) {
			if(!t2){
				if(Integer.parseInt(leftmoney.getText())<480){
					
				}else{
					tool2.setIcon(new ImageIcon("src/images/toolpanel/tool1.png"));
					spend = spend + 480;
					t2 = true;
				}
			
			}else{
				tool2.setIcon(null);
				spend = spend - 480;
				t2 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t1.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool2");
			
		} else if (source == tool3) {
			if(!t3){
				if(Integer.parseInt(leftmoney.getText())<880){
					
				}else{
					tool3.setIcon(new ImageIcon("src/images/toolpanel/tool3.png"));
					spend = spend + 880;
					t3 = true;
				}
		
			}else{
				tool3.setIcon(null);
				spend = spend-880;
				t3 = false;
			}
			tips.setIcon(new ImageIcon("src/images/toolpanel/t3.png"));
			paymoney.setText("" + spend);
			leftmoney.setText("" + (initemoney - spend));
			paymoney.setFont(font);
			leftmoney.setFont(font);
			paymoney.setForeground(Color.black);
			leftmoney.setForeground(Color.black);
			repaint();
			System.out.println("tool3");
		}else if(source == back){
			removeAll();
			ModelPanel model =new ModelPanel(bootFrame);
			model.setSize(927, 661);
			model.setLocation(0, 0);
			add(model);
			repaint();
		}else if(source == start){
			
			//调用方法得到房间的信息列表，我自己模拟的啊，卉神直接删除得了
			
			GamePanel hm =new GamePanel(t1,t2,t3,bootFrame);
			removeAll();
			hm.setSize(927, 661);
			hm.setLocation(0, 0);
			add(hm);
			repaint();
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

//	public static void main(String[] args) {
//		TestDrive td = new TestDrive();
//		td.test(new ToolPanel(0,"src/images/toolpanel/toolpanel2.png"));
//	}
}

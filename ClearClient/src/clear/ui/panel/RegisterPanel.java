package clear.ui.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clear.ui.component.MyJTextField;
import clear.ui.component.MyPSField;
import clear.ui.test.TestDrive;

public class RegisterPanel extends JPanel implements MouseListener {
	ImageIcon imageIcon;
	int width;
	int hight;
	JTextField idfield;
	JTextField psfield1;
	JTextField psfield2;
	JPanel back;
	JPanel register;
	JLabel hanzi;
	JLabel meizi;
	JLabel quitButton;
	JFrame bootFrame;

	public RegisterPanel(JFrame boot) {
		bootFrame = boot;
		imageIcon = new ImageIcon("src/images/registerpanel/registerpanel.png");

		idfield = new MyJTextField();
		psfield1 = new MyPSField();
		psfield2 = new MyPSField();
		quitButton = new JLabel();
		back = new JPanel();
		back.setVisible(true);
		back.setOpaque(false);
		register = new JPanel();
		register.setVisible(true);
		register.setOpaque(false);
		hanzi = new JLabel(new ImageIcon("src/images/registerpanel/hanzi0.jpg"));
		meizi = new JLabel(new ImageIcon("src/images/registerpanel/meizi0.jpg"));
		// she zhi wei zhi
		quitButton.setLocation(844, 2);
		hanzi.setLocation(296, 398);// 296
		meizi.setLocation(440, 398);
		idfield.setLocation(224, 258);
		idfield.setBorder(null);
		psfield1.setLocation(223, 305);
		psfield1.setBorder(null);
		psfield2.setLocation(223, 354);
		psfield2.setBorder(null);
		back.setLocation(45, 445);
		register.setLocation(206, 445);
		// she zhi da xiao
		quitButton.setSize(54, 72);
		hanzi.setSize(64, 34);
		meizi.setSize(64, 34);
		idfield.setSize(264, 23);
		psfield1.setSize(264, 23);
		psfield2.setSize(264, 23);
		back.setSize(123, 52);
		register.setSize(123, 52);
		// tian jia jian ting
		hanzi.addMouseListener(this);
		meizi.addMouseListener(this);
		back.addMouseListener(this);
		register.addMouseListener(this);
		quitButton.addMouseListener(this);
		setLayout(null);
		add(idfield);
		add(psfield1);
		add(psfield2);
		add(hanzi);
		add(meizi);
		add(back);
		add(register);
		add(quitButton);
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();

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
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == back) {
			removeAll();
			LoginPanel login = new LoginPanel(bootFrame);
			login.setSize(927, 661);
			login.setLocation(0, 0);
			add(login);
			repaint();
			System.out.println("back");
		} else if (source == register) {
			System.out.println("register");
		} else if (source == hanzi) {
			meizi.setIcon(new ImageIcon("src/images/registerpanel/meizi0.jpg"));
			hanzi.setIcon(new ImageIcon("src/images/registerpanel/hanzi.jpg"));
			meizi.repaint();
			hanzi.repaint();
			System.out.println("hanzi");
		} else if (source == meizi) {
			meizi.setIcon(new ImageIcon("src/images/registerpanel/meizi.jpg"));
			hanzi.setIcon(new ImageIcon("src/images/registerpanel/hanzi0.jpg"));
			meizi.repaint();
			hanzi.repaint();
			System.out.println("meizi");
		}else if(source == quitButton){
			bootFrame.dispose();
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
		Object source = arg0.getSource();
		if (source == hanzi) {
			meizi.setIcon(new ImageIcon("src/images/registerpanel/meizi0.jpg"));
			hanzi.setIcon(new ImageIcon("src/images/registerpanel/hanzi.jpg"));
			meizi.repaint();
			hanzi.repaint();
			System.out.println("hanzi");
		} else if (source == meizi) {
			meizi.setIcon(new ImageIcon("src/images/registerpanel/meizi.jpg"));
			hanzi.setIcon(new ImageIcon("src/images/registerpanel/hanzi0.jpg"));
			meizi.repaint();
			hanzi.repaint();
			System.out.println("meizi");
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	// public static void main(String[] args){
	// TestDrive td = new TestDrive();
	// td.test(new RegisterPanel());
	// }
}

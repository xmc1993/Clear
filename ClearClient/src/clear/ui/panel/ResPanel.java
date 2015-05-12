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
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import clear.ui.network.SignInClient;
import clear.ui.panel.SignPanel.MyListener;

public class ResPanel extends JPanel implements MouseListener{
	private Image backGround;
	
	private Image rightImage;
	
	private ImageIcon regImage;
	private ImageIcon backImage;
	
	private JLabel regLabel;
	private JLabel backLabel;
	private JPanel boyPanel;
	private JPanel girlPanel;
	
	private JTextField nameField;
	private JPasswordField passwordField;
	private JPasswordField sec_passwordField;
	
	private boolean isBoy=true;
	JFrame bootFrame;
	private JLabel minButton;
	private JLabel quitButton;
	public ResPanel(JFrame boot){
		bootFrame = boot;
		setLayout(null);
		init();
	}
/*back 295*455  ok 590*455  sexLeft 402*412 sexright 559*412*/
/*left 408*404 right 570*404*/
/* ‰»Î”√ªß√˚ 393 271  ‰»Î√‹¬Î 393 323‘Ÿ¥Œ ‰»Î 397 375*/
/*input 318 40*  ok back 100 45 sex 27 26*/	
	private void init(){
		initImage();
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		regLabel=new JLabel(regImage);
		backLabel=new JLabel(backImage);
		boyPanel=new JPanel();
		girlPanel=new JPanel();
		nameField=new JTextField();
		passwordField=new JPasswordField();
		sec_passwordField=new JPasswordField();
		regLabel.setBounds(590, 455,100,45);
		backLabel.setBounds(295, 455, 100, 45);
		boyPanel.setBounds(408, 404, 27, 26);
		girlPanel.setBounds(570, 405, 27,26);
		nameField.setBounds(373, 239, 318,40);
		passwordField.setBounds(373, 291, 318,40);
		sec_passwordField.setBounds(373, 343, 318,40);
		regLabel.addMouseListener(this);
		girlPanel.addMouseListener(this);
		boyPanel.addMouseListener(this);
		backLabel.addMouseListener(this);
		boyPanel.setOpaque(false);
		girlPanel.setOpaque(false);
		nameField.setOpaque(false);
		passwordField.setOpaque(false);
		passwordField.setEchoChar('*');
		sec_passwordField.setEchoChar('*');
		sec_passwordField.setOpaque(false);
		nameField.setFont(new Font("”◊‘≤", 1, 24));
		passwordField.setFont(new Font("”◊‘≤", 1, 24));
		sec_passwordField.setFont(new Font("”◊‘≤", 1, 24));
		nameField.setForeground(new Color(186,181,181));
		passwordField.setForeground(new Color(186,181,181));
		sec_passwordField.setForeground(new Color(186,181,181));
		nameField.setBorder(null);
		passwordField.setBorder(null);
		sec_passwordField.setBorder(null);
		add(regLabel);
		add(backLabel);
		add(boyPanel);
		add(girlPanel);
		add(nameField);
		add(passwordField);
		add(sec_passwordField);
		setSize(927,661);
	}
	
	private void initImage(){
		backGround=new ImageIcon("src/images/register/backGround.png").getImage();
		regImage=new ImageIcon("src/images/register/ok.png");
		backImage=new ImageIcon("src/images/register/back.png");
		rightImage=new ImageIcon("src/images/register/right.png").getImage();
	}

	protected void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(backGround, 0, 0, 927,661,this);
		
		
		if(isBoy){
			g2d.drawImage(rightImage, 408, 404, 27, 26,this);
		}else{
			g2d.drawImage(rightImage,570, 405, 27,26,this);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source=e.getSource();
		if(source==regLabel){
			String name = nameField.getText();
			String pw = new String(passwordField.getPassword() );
			int gender = 0 ;
			if(!isBoy){
				gender = 1 ;
			}
			int head = new Random().nextInt(5);
			SignInClient client = new SignInClient();
			String result = client.connect(name, pw, gender+"", head+"") ;
			if(result.equals("Success")){
				removeAll();
				SignPanel sp=new SignPanel(bootFrame);
				sp.setBounds(0, 0, 927, 661);
				add(sp);
				repaint();
			}else{
				JOptionPane.showMessageDialog(null, 
						   "◊¢≤· ß∞‹£°«Î÷ÿ ‘£°","µ«¬º ß∞‹",JOptionPane.INFORMATION_MESSAGE);  	
			}
		}else if(source==girlPanel){
			isBoy=false;
			repaint();
		}else if(source==boyPanel){
			isBoy=true;
			repaint();
		}else if(source==backLabel){
			removeAll();
			SignPanel sp=new SignPanel(bootFrame);
			sp.setBounds(0, 0, 927, 661);
			add(sp);
			repaint();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}else if(source == quitButton){
			bootFrame.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
//	public static void main(String[] args){
//		JFrame jf=new JFrame();
//		ResPanel wp=new ResPanel();
//		wp.setBounds(0, 0, 927, 661);
//		jf.setLayout(null);
//		jf.add(wp);
//		jf.setSize(943,699);
//		jf.setVisible(true);
//	}
	
	
	
}

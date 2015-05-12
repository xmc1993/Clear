package clear.ui.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoneyDialog extends JFrame implements MouseListener {
	JPanel backPanel;
	JLabel close;
	JLabel msg;
	JLabel yes;
	JLabel cancle;

	public MoneyDialog(){
		backPanel = new DialogPanel("src/images/Dialog/background1.png");
		backPanel.setLayout(null);
		close = new JLabel(new ImageIcon("src/images/Dialog/close.png"));
		yes = new JLabel(new ImageIcon("src/images/Dialog/yes.png"));
		msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/nomoney.png"));
		System.out.println(msg.getWidth());
		// 设置组件大小
		close.setSize(36, 36);
		yes.setSize(78, 32);
		msg.setSize(88,17);
		// 设置组件位置
		close.setLocation(353, 7);
		yes.setLocation(253,147);
		msg.setLocation(206,110);
		// 设置组件监听
		close.addMouseListener(this);
		yes.addMouseListener(this);
		// 添加组件
		backPanel.add(close);
		backPanel.add(yes);
		backPanel.add(msg);
		getContentPane().add(backPanel);
		setUndecorated(true);
		setLocation(927/2, 661/2);
		setSize(backPanel.getWidth(),backPanel.getHeight());
		setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// 创建组件
		
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
		if(source == close){
			dispose();
		}else if (source == yes){
			dispose();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args){
		new MoneyDialog();
	}

}

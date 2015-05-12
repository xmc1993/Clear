package clear.ui.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SucceedDialog extends JFrame implements MouseListener {
	JPanel backPanel;
	JLabel close;
	JLabel msg;
	JLabel yes;
	JLabel cancle;
	public SucceedDialog(){
		backPanel = new DialogPanel("src/images/Dialog/background2.png");
		backPanel.setLayout(null);
		close = new JLabel(new ImageIcon("src/images/Dialog/close.png"));
		yes = new JLabel(new ImageIcon("src/images/Dialog/yes.png"));
		msg = new JLabel(new ImageIcon("src/images/Dialog/succeed.png"));
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
	public void mouseClicked(MouseEvent e) {
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
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == close){
			dispose();
		}else if (source == yes){
			dispose();
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		new SucceedDialog();
	}

}

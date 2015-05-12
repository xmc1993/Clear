package clear.ui.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyJDialog extends JFrame implements MouseListener{
	JPanel backPanel;
	JLabel close;
	JLabel msg;
	JLabel yes;
	JLabel cancle;
	public MyJDialog(int type){
		//backPanel会使用每个自己的DialogPanel，他们有着相同的框架Dialogpanel
		//创建组件
		backPanel = new DialogPanel("src/images/Dialog/background.png");
		backPanel.setLayout(null);
		close = new JLabel(new ImageIcon("src/images/Dialog/close.png"));
		yes = new JLabel(new ImageIcon("src/images/Dialog/yes.png"));
		cancle = new JLabel(new ImageIcon("src/images/Dialog/cancle.png"));
		if (type == 0){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/dontmatch.png"));
			msg.setSize(212,18);
			msg.setLocation(156,93);
		}else if(type ==1){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/noneps.png"));
			msg.setSize(130,18);
			msg.setLocation(197,93);
		}else if(type == 2){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/noneuser.png"));
			msg.setSize(149,18);
			msg.setLocation(182,93);
		}else if (type == 3){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/overtime.png"));
			msg.setSize(212,18);
			msg.setLocation(155,93);
		}else if(type == 4){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/psid.png"));
			msg.setSize(171,18);
			msg.setLocation(177,93);
		}else if(type == 5){
			msg = new JLabel(new ImageIcon("src/images/Dialog/wenzi/userull.png"));
			msg.setSize(148,18);
			msg.setLocation(198,93);
		}
		//设置组件大小
		close.setSize(36,36);
		yes.setSize(78,32);
		cancle.setSize(78,32);
		//设置组件位置
		close.setLocation(353, 7);
		yes.setLocation(160,153);
		cancle.setLocation(276,153);
		//设置组件监听
		close.addMouseListener(this);
		yes.addMouseListener(this);
		cancle.addMouseListener(this);
		//添加组件
		backPanel.add(close);
		backPanel.add(yes);
		backPanel.add(cancle);
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
		}else if(source == yes){
			dispose();
		}else if (source == cancle){
			dispose();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		new MyJDialog(4);
	}
}

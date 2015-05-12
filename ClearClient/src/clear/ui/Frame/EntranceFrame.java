package clear.ui.Frame;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;





import clear.ui.panel.LoginPanel;
import clear.ui.panel.SignPanel;
import clear.ui.panel.WaitPanel;

public class EntranceFrame extends JFrame {
	/**
	 * ��Ϸ���ڿ��
	 */
	private static final int WINDOW_W = 927;
	/**
	 * ��Ϸ���ڸ߶�
	 */
	private static final int WINDOW_H = 661;
	/**
	 * ��Ϸ��������Ļ���˵ľ���
	 */
	private static final int PADDING = 20;
	/**
	 * �����϶�����ʱ��¼�����ʱλ��
	 */
	private Point temp = null;

	/**
	 * �����϶�����ʱ�����ƶ���Ĵ���λ��
	 */
	private Point loc;

	/**
	 * ����ƶ�ʱ�Ƿ��ƶ���Ϸ����
	 */
	private boolean isDragged = false;

	/**
	 * ��Ϸ�������
	 */
	private JPanel gamePanel = null;

	public void test(JPanel gamePanel) {
		this.gamePanel = gamePanel;

		// ���ùر�����Ϊ���˳�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������Ϸ���ڲ��ɵ�����С
		this.setResizable(false);
		this.setUndecorated(true);

		// ������Ϸ���ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width - WINDOW_W >> 1;
		this.setBounds(x, PADDING, WINDOW_W, WINDOW_H);

		// �����Ϸ���
		this.getContentPane().add(gamePanel);
//		gamePanel.setFrame(this);

		// ���ô��ڿ��϶�
		this.setDragable(this);

		// ������Ϸ���ڿɼ�
		this.setVisible(true);
		
	}
	/**
	 * ���ô��ڿ���ק
	 * 
	 * @param jframe
	 */
	private void setDragable(final JFrame jframe) {
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				temp = new Point(e.getX(), e.getY());
				isDragged = true;
				jframe.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(jframe.getLocation().x + e.getX() - temp.x,
							jframe.getLocation().y + e.getY() - temp.y);
					jframe.setLocation(loc);
				}
			}

		});
		
	}
	public static void main(String[] args){
		EntranceFrame td2 = new EntranceFrame();
		td2.test(new WaitPanel(td2));
		td2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

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
	 * 游戏窗口宽度
	 */
	private static final int WINDOW_W = 927;
	/**
	 * 游戏窗口高度
	 */
	private static final int WINDOW_H = 661;
	/**
	 * 游戏窗口里屏幕顶端的距离
	 */
	private static final int PADDING = 20;
	/**
	 * 用于拖动窗口时记录鼠标临时位置
	 */
	private Point temp = null;

	/**
	 * 用于拖动窗口时计算移动后的窗口位置
	 */
	private Point loc;

	/**
	 * 鼠标移动时是否移动游戏窗口
	 */
	private boolean isDragged = false;

	/**
	 * 游戏界面面板
	 */
	private JPanel gamePanel = null;

	public void test(JPanel gamePanel) {
		this.gamePanel = gamePanel;

		// 设置关闭属性为：退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置游戏窗口不可调整大小
		this.setResizable(false);
		this.setUndecorated(true);

		// 设置游戏窗口剧中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width - WINDOW_W >> 1;
		this.setBounds(x, PADDING, WINDOW_W, WINDOW_H);

		// 添加游戏面板
		this.getContentPane().add(gamePanel);
//		gamePanel.setFrame(this);

		// 设置窗口可拖动
		this.setDragable(this);

		// 设置游戏窗口可见
		this.setVisible(true);
		
	}
	/**
	 * 设置窗口可拖拽
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

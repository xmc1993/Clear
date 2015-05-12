package clear.ui.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import clear.ui.test.TestDrive;

public class DialogPanel extends JPanel {
	ImageIcon imageIcon;
	int width;
	int hight;

	/**
	 * 
	 * @param type
	 *            表示Dialog的种类
	 */
	public DialogPanel(String url) {
		imageIcon = new ImageIcon(url);

		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
		System.out.println(width);
		System.out.println(hight);
		setVisible(true);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}

	/**
	 * 重写panel的绘图方法，改变panel的背景
	 */
	public void paintComponent(Graphics g) {
		Image image = imageIcon.getImage();
		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {
		TestDrive td = new TestDrive();
		td.test(new DialogPanel("src/images/Dialog/background2.png"));
	}
}

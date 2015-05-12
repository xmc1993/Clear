package clear.ui.test;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDrive extends JFrame {
	static final int hight = 38;
	static final int width = 16;

	public void test(JPanel panel) {
		TestDrive testFrame = new TestDrive();
		testFrame.getContentPane().add(panel);
		testFrame.setSize(panel.getWidth() + width, panel.getHeight() + hight);
		System.out.println("dfdf:" + panel.getWidth());
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
		setUndecorated(true);
	}

	public void testURL() {
		try {
			File f = new File("test.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			System.out.print(f.getPath());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//new TestDrive().testURL();
	}
}

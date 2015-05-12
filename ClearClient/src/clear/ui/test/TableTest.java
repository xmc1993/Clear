package clear.ui.test;

import java.awt.Component;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableTest {


	public TableTest() {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("背景透明展示");
		ImageIcon icon = new ImageIcon("src/images/loginbutton.png");
		JLabel lab = new JLabel(icon); // 将图片放入到label中
		lab.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置放有图片的label的位置
		JTable t = new JTable(20, 3) { // 设置jtable的单元格为透明的
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setOpaque(false);
				}
				return c;
			}
		};
		t.setOpaque(false); // 设置jtable本身为透明的
		frame.getContentPane().add(lab, -1); // jframe本身是窗体，不能放置任何组件，用getContentPane()方法得到frame的默认内容面板，将lab放入其中，-1表示放入面板的下层
		frame.getContentPane().add(t, 0); // 0表示放在面板的最顶层
		Container con = frame.getContentPane();
		((JPanel) con).setOpaque(false); // 设置面板为透明的
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
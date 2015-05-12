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
		JFrame frame = new JFrame("����͸��չʾ");
		ImageIcon icon = new ImageIcon("src/images/loginbutton.png");
		JLabel lab = new JLabel(icon); // ��ͼƬ���뵽label��
		lab.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // ���÷���ͼƬ��label��λ��
		JTable t = new JTable(20, 3) { // ����jtable�ĵ�Ԫ��Ϊ͸����
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setOpaque(false);
				}
				return c;
			}
		};
		t.setOpaque(false); // ����jtable����Ϊ͸����
		frame.getContentPane().add(lab, -1); // jframe�����Ǵ��壬���ܷ����κ��������getContentPane()�����õ�frame��Ĭ��������壬��lab�������У�-1��ʾ���������²�
		frame.getContentPane().add(t, 0); // 0��ʾ�����������
		Container con = frame.getContentPane();
		((JPanel) con).setOpaque(false); // �������Ϊ͸����
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
package clear.ui.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTest extends JFrame implements ActionListener {
	JButton jbt = new JButton("改变面板");
	JButton jbt2 = new JButton("恢复面板");
	JPanel pa = new JPanel();

	PanelTest() {
		setLayout(null);
		pa.setBackground(Color.cyan);
		add(pa);

		add(jbt);
		jbt.setBounds(100, 50, 90, 30);
		jbt.addActionListener(this);

		add(jbt2);
		jbt2.setBounds(100, 105, 90, 30);
		jbt2.addActionListener(this);

		pa.setBounds(10, 20, 80, 50);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(420, 320, 500, 500);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbt) {
			pa.setBounds(0, 0, 100, 500);
			this.repaint();
		} else {
			pa.setBounds(10, 20, 80, 50);
			this.repaint();
		}

	}
	public static void main(String[] args){
		PanelTest f = new PanelTest();
	}
}

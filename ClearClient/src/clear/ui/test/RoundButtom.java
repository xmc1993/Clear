package clear.ui.test;

import javax.swing.*;
import java.awt.*;

public class RoundButtom extends JButton {
	public RoundButtom(String Label) {
		super(Label);
		this.setContentAreaFilled(false);
	}

	public void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.LIGHT_GRAY);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width, getSize().height);
		super.paintComponent(g);
	}

	public void paintBord(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().add(new RoundButtom("String"));
	}
}
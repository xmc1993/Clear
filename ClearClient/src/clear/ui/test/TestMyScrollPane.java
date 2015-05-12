package clear.ui.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TestMyScrollPane{
	JScrollPane jS;
	JPanel p;
	public TestMyScrollPane(){
		JFrame f = new JFrame();
		p = new JPanel();
		p.setBackground(Color.blue);
		JScrollPane jp=new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setViewportView(p);
		p.setPreferredSize(new Dimension(3000,2000));
		p.revalidate();
		jS.setSize(100, 100);
		f.getContentPane().add(jS);
		
		f.setSize(100, 100);
		f.setVisible(true);
		

	}
	
}

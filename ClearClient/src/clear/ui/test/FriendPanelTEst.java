package clear.ui.test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import clear.ui.component.MyPanel;
import clear.ui.panel.FriendInfoPanel;
import enupackage.PanelType;

public class FriendPanelTEst extends JPanel{
	ArrayList<JPanel> infolist;
	ArrayList<JPanel> panelList;
	JPanel bootpanel;
	JButton nextButton;
	CardLayout card;
	public FriendPanelTEst(ArrayList<String> nameList,ArrayList<String> stateList){
		Dimension infoPaneSize = new Dimension(100, 25);
		Dimension panelSize = new Dimension(100, 100);
		int panelnum = (nameList.size()/4)+1;
		int infonum = nameList.size();
		System.out.println(panelnum+"---"+infonum);
		panelList = new ArrayList<JPanel>();
		infolist = new ArrayList<JPanel>();
		System.out.println(panelList.size()+"---"+infolist.size());
		for(int i = 0;i<panelnum;i++){
			JPanel aPanel = new JPanel();
			aPanel.setBackground(new Color(i));
			aPanel.setLayout(null);
			aPanel.setLocation(0, 0);
			aPanel.setSize(panelSize);
			panelList.add(aPanel);
		}
		panelList.get(0).setBackground(Color.gray);
		panelList.get(1).setBackground(Color.green);
		
		for(int i = 0;i<infonum;i++){
			JPanel infoPanel = new JPanel();
			infoPanel.setBackground(new Color(i));
			infoPanel.setLocation(0, infoPaneSize.height*i);
			infoPanel.setSize(infoPaneSize);
			infolist.add(infoPanel);
		}
		infolist.get(0).setBackground(Color.red);
		infolist.get(1).setBackground(Color.blue);
		int j = 0;
		for(int i = 0;i<infonum;i++){
			JPanel tem = panelList.get(j);
			tem.add(infolist.get(i));
			if(i%4 == 0){
				j++;
			}
		}
		
		bootpanel = new JPanel();
		bootpanel.setSize(panelSize);
		card = new CardLayout();
		bootpanel.setLayout(card);
		for(int i = 0;i<panelnum;i++){
			bootpanel.add(panelList.get(i));
		}
		setLayout(null);
		setSize(120,120);
		bootpanel.setLocation(0, 0);
		nextButton = new JButton("next");
		nextButton.addActionListener(new ButtonListener());
		nextButton.setSize(20, 18);
		nextButton.setLocation(0,102);
		add(bootpanel);
		add(nextButton);
		setVisible(true);
	}
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			card.next(bootpanel);
			System.out.println("next");
		}
	}
	public static void main(String[] args){
		TestDrive tc = new TestDrive();
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> stateList =new ArrayList<String>();
		for(int i = 0;i<20;i++){
			nameList.add(""+i);
			stateList.add(""+i);
		}
		tc.test(new FriendPanelTEst(nameList, stateList));
	}
}

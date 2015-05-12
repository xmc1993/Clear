package clear.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import clear.ui.component.MyButton;
import clear.ui.component.MyPanel;
import clear.ui.test.TestDrive;
import enupackage.ButtonType;
import enupackage.PanelType;

public class FriendsPanel extends JPanel{
	ArrayList<FriendInfoPanel> infolist;
	ArrayList<JPanel> panelList;
	JPanel bootpanel;
	MyButton nextButton;
	MyButton lastButton;
	CardLayout card;
	Image image = null;
	ImageIcon imageIcon = null;
	public FriendsPanel(ArrayList<String> nameList,ArrayList<Integer> headList
,ArrayList<Integer> lvlList,ArrayList<String> goldList){
//		Dimension buttonSize = new Dimension(74, 66);
		Dimension buttonSize = new Dimension(63, 45);
		Dimension thispaneSize = new Dimension(690, 290);
		Dimension infoPaneSize = new Dimension(515, 79);
		Dimension panelSize = new Dimension(515, 237);
		int panelnum = (nameList.size()/3)+1;
		int infonum = nameList.size();
		panelList = new ArrayList<JPanel>();
		infolist = new ArrayList<FriendInfoPanel>();
		for(int i = 0;i<panelnum;i++){
			JPanel aPanel = new MyPanel(PanelType.FriendsBack);
			aPanel.setLayout(null);
			aPanel.setLocation(0, 0);
			aPanel.setSize(panelSize);
			panelList.add(aPanel);
		}
		for(int i = 0;i<infonum;i++){
			FriendInfoPanel friendInfo = new FriendInfoPanel(nameList.get(i), headList.get(i),i%3,lvlList.get(i)
					,goldList.get(i));
			friendInfo.setLocation(0, infoPaneSize.height*(i%3));
			friendInfo.setSize(infoPaneSize);
			infolist.add(friendInfo);
			
		}
		System.out.println(infolist.size());
		int j = 0;
		for (int i = 0;i<infonum;){
			JPanel tem = panelList.get(j);
			tem.add(infolist.get(i));
			panelList.get(j).add(infolist.get(i));
			System.out.println(j+"tian jia"+i);
			i++;
			if(i%3 == 0){
				j++;
			}
		}
		//创建bootPanel，设置为cardLayout，用来实现好友列表的换页，大小应该比this小一个按钮的样子
		bootpanel = new MyPanel(PanelType.BootPanel);
		bootpanel.setSize(panelSize);
		bootpanel.setLocation(80, 4);
		card = new CardLayout();
		bootpanel.setLayout(card);
		for(int i = 0;i<panelnum;i++){
			bootpanel.add(panelList.get(i));
		}
		setLayout(null);
		setSize(thispaneSize);
		//创建nextButton，按下后会使BootPanel切到下一张，该button放置在this上，
		nextButton = new MyButton(ButtonType.nextButton);
		nextButton.setSize(buttonSize);
		nextButton.setLocation(590, 242);
		nextButton.addMouseListener(new NextButtonListen());
		lastButton = new MyButton(ButtonType.lastButton);
		lastButton.setSize(buttonSize);
		lastButton.setLocation(84, 242);
		lastButton.addMouseListener(new NextButtonListen());
		add(bootpanel);
		add(nextButton);
		add(lastButton);
		setVisible(true);
		
	}
	public void paintComponent(Graphics g) {
		imageIcon = new ImageIcon("src/images/friendsRankPanel/background01.png");
		image = imageIcon.getImage();
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		g.drawImage(image, 0, 0, this);
	}
	class NextButtonListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if(source == nextButton){
//			System.out.println("next");
			card.next(bootpanel);
			}else if(source == lastButton){
				card.previous(bootpanel);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String [] args){
		TestDrive td = new TestDrive();
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<Integer> stateList = new ArrayList<Integer>();
		ArrayList<Integer> headList = new ArrayList<Integer>();
		ArrayList<String> expList = new ArrayList<String>();
		for(int i = 0;i<20;i++){
			nameList.add("name"+i);
			stateList.add((int)(Math.random()*5));
			headList.add((int)(Math.random()*5));
			expList.add(i*10000+"");
		}
		td.test(new FriendsPanel(nameList, stateList,headList,expList));
	}
}

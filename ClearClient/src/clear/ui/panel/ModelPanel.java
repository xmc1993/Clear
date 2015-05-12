package clear.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalBorders.ToolBarBorder;

import clear.ui.component.MyPanel;
import clear.ui.data.DayScores;
import clear.ui.data.DayTimes;
import clear.ui.data.RankData;
import clear.ui.data.RoundScores;
import clear.ui.logic.FriendData;
import clear.ui.logic.GameData;
import clear.ui.logic.RoomDataService;
import clear.ui.logic.RoomDataServiceInterface;
import clear.ui.test.TestDrive;
import enupackage.PanelType;

public class ModelPanel extends JPanel implements MouseListener{
	ImageIcon imageIcon;
	JPanel button1;
	JPanel button2;
	JPanel button3;
	private JLabel minButton;
	private JLabel quitButton;
	JPanel button7;
	int width;
	int hight;
	JFrame bootFrame;
	public ModelPanel(JFrame boot){
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		bootFrame = boot;
		imageIcon = new ImageIcon("src/images/model.jpg");
		button1 = new JPanel();
		button2 = new JPanel();
		button3 = new JPanel();
		button7 = new JPanel();
		button7.setBackground(Color.white);
		//设置button的位置
		button1.setLocation(430,160);
		button2.setLocation(430, 290);
		button3.setLocation(430, 430);
		button7.setLocation(60, 550);
		//设置button的大小
		button1.setSize(167, 60);
		button2.setSize(167, 60);
		button3.setSize(167, 60);
		button7.setSize(167, 60);
		//设置button的透明和可见
		button1.setVisible(true);
		button1.setOpaque(false);
		button2.setVisible(true);
		button2.setOpaque(false);
		button3.setVisible(true);
		button3.setOpaque(false);
		button7.setVisible(true);
		button7.setOpaque(false);
		
		//添加监听
		button1.addMouseListener(this);
		button2.addMouseListener(this);
		button3.addMouseListener(this);
		button7.addMouseListener(this);
		//添加组件，
		setLayout(null);
		add(button1);
		add(button2);
		add(button3);
		add(button7);
		//这个panel的大小
		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();
	}
	public void paintComponent(Graphics g){
		Image image = imageIcon.getImage();
		g.drawImage(image,0,0,this);
	}

	/*
	 * 	(non-Javadoc)
	 * @see javax.swing.JComponent#getWidth()
	 * 重写得到panel宽的方法
	 */
	public int getWidth(){
		return width;
	}
	/*@
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#getHeight()
	 * 重写得到panel长的方法
	 */
	public int getHeight(){
		return hight;
	}

	public ArrayList<String> analysis(ArrayList<String> lists,int num){
		ArrayList<String> result = new ArrayList<String>();
		for(String s : lists){
			String[] temps = s.split(" ") ;
			result.add(temps[num]) ;
		}
		return result;
	}
	
	public ArrayList<Integer> analysis2(ArrayList<String> lists,int num){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String s : lists){
			String[] temps = s.split(" ") ;
			result.add(Integer.parseInt(temps[num])) ;
		}
		return result;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if(source == button1){
			removeAll();
			
			ToolPanel tp = new ToolPanel(Player.getGold(),"src/images/toolpanel/toolpanel.png",bootFrame);
			tp.setSize(927, 661);
			tp.setLocation(0, 0);
			add(tp);
			repaint();
		}else if(source == button2){
			RoomDataServiceInterface data = new RoomDataService();
			
			ArrayList<String> homeInfos = data.room1Info() ;

			CoopGameHall hm =new CoopGameHall(homeInfos,0,bootFrame);
			removeAll();
			hm.setSize(927, 661);
			hm.setLocation(0, 0);
			add(hm);
			repaint();
		}else if(source == button3){
			RoomDataServiceInterface data = new RoomDataService();
			
			ArrayList<String> homeInfos = data.room2Info() ;

			FightGameHall hm =new FightGameHall(homeInfos,1,bootFrame);
			removeAll();
			hm.setSize(927, 661);
			hm.setLocation(0, 0);
			add(hm);
			repaint();
		}else if(source == quitButton){
			bootFrame.dispose();
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}
		else if(source == button7){
			removeAll();
			//扯！！！到时候要换到本地去！！！
			double[] f_avg = new double[7];
			int[] f_game_day = new int[7];
			ArrayList<String> f_score_game = new ArrayList<String>();
			ArrayList<String> f_info = new ArrayList<String>();
			ArrayList<String> f_name = new ArrayList<String>();
			ArrayList<Integer> f_state = new ArrayList<Integer>();
			ArrayList<Integer> f_head = new ArrayList<Integer>();
			ArrayList<String> f_exp = new ArrayList<String>();
			
			for(int i = 0 ;i <7 ; i++){
				f_avg[i] = 0;
				f_game_day[i] = 0 ;
				f_score_game.add("0") ;
				f_info.add("0") ;
			}
			f_name.add("暂无好友");
			f_state.add(-1) ;
			f_head.add(0);
			f_exp.add("0") ;
			
			FriendData data = new FriendData();
			ArrayList<String> lists = data.getFriends(Player.getUserName()) ;
			GameData data2 = new GameData(Player.getUserName());
			String x = data2.getData() ;
			if(lists.get(0).equals("")){
				RankData rankData = new RankData(f_avg,f_game_day,f_score_game,f_info);
				FriendsRankPanel friendsRankPanel= 
						new FriendsRankPanel(f_name, 
								f_state, f_head, f_exp,rankData,bootFrame);
				friendsRankPanel.setSize(927, 661);
				friendsRankPanel.setLocation(0, 0);
				removeAll();
				add(friendsRankPanel);
				FriendsData.setNames(f_name);
				FriendsData.setHeadList(f_state);
				FriendsData.setLvlList(f_head);
				FriendsData.setGoldList(f_exp);
			}else{
				RankData rankData=  new RankData(data2.getAvgPerDay(),
					data2.getGamesPerDay(),data2.getScorePerGame(),data2.getRecords());
				FriendsRankPanel friendsRankPanel= 
					new FriendsRankPanel(analysis(lists,1), 
							analysis2(lists,5), analysis2(lists,2), analysis(lists,3),rankData,bootFrame);
				friendsRankPanel.setSize(927, 661);
				friendsRankPanel.setLocation(0, 0);
				removeAll();
				add(friendsRankPanel);
				
				FriendsData.setNames(analysis(lists,1));
				FriendsData.setHeadList(analysis2(lists,5));
				FriendsData.setLvlList(analysis2(lists,2));
				FriendsData.setGoldList(analysis(lists,3));
			}
			
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
//	public static void main(String[] args){
//		new TestDrive().test(new ModelPanel());
//	}
}

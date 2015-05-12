package clear.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clear.ui.component.DialogPanel;
import clear.ui.logic.ShowPlayerDetailData;
import clear.ui.network.CreateRoomClient;

public class CoopGameHall extends JPanel implements MouseListener {
	private JLabel minButton;
	private JLabel quitButton;
	private Image backGround;
	private ImageIcon backImage;
	private ImageIcon createImage;
	private ImageIcon searchImage;
	private Image line;
	private JPanel addPanel[];
	private JLabel up;
	private JLabel down;
	private JLabel back;
	private JLabel create;
	private JLabel search;
	private JTextField field;
	private ArrayList<String> roomData;
	private int ptr = 0;
	private int numRoom;
	private int numLine = 5;
	int tag;
	private String roomName;
	private int playerNum;

	private JLabel prompt;
	String lvl[] = { "", "", "", "" };
	String gold[] = { "", "", "", "" };
	int head[] = { 1, 1, 1, 1 };
	int state[] = { 0, 0, 0 };
	boolean ready[] = { false, false, false, false };
	String[] name = { "", "", "", "" };
	JFrame bootFrame;
	private Hashtable<String, String> roomInfo = new Hashtable<String, String>();
	private Hashtable<String, String> roomInfo2 = new Hashtable<String, String>();
	private Hashtable<JPanel, String> getRoom = new Hashtable<JPanel, String>();
	private Hashtable<JPanel, String> getRoom2 = new Hashtable<JPanel, String>();
	private ArrayList<String> roomNumber = new ArrayList<String>();

	public CoopGameHall(ArrayList<String> roomData, int tag,JFrame boot) {
		bootFrame = boot;
		this.tag = tag;
		String x = "";
		String y = "";
		String z = "";
		if (roomData.get(0).equals("")
				|| roomData.get(0).split(" ")[0].equals("null")) {

			roomData.remove(0);
			roomData.add("啊哦 无房间  快来创建");
		} else {
			for (String s : roomData) {
				roomNumber.add(s.split(" ")[0]);
				System.out.println("这儿报错了" + s);
				roomInfo.put(s.split(" ")[0], s.split(" ")[2]);
				roomInfo2.put(s.split(" ")[0], s.split(" ")[1]);
			}
		}

		this.roomData = roomData;
		numRoom = roomData.size();
		setLayout(null);
		setSize(927, 661);
		init();

	}

	private void init() {

		prompt = new JLabel("不存在此房间");
		prompt.setFont(new Font("Dialog", 1, 25));
		prompt.setSize(100, 50);
		prompt.setLocation(818, 139);
		prompt.setLocation(738, 159);
		prompt.setForeground(Color.blue);

		backImage = new ImageIcon("src/images/homepanel/back.png");
		createImage = new ImageIcon("src/images/homepanel/create.png");
		searchImage = new ImageIcon("src/images/homepanel/search.png");
		backGround = new ImageIcon("src/images/homepanel/background.png")
				.getImage();
		line = new ImageIcon("src/images/homepanel/ahomebackground.png")
				.getImage();
		up = new JLabel(new ImageIcon("src/images/homepanel/up.png"));
		up.addMouseListener(this);
		down = new JLabel(new ImageIcon("src/images/homepanel/down.png"));
		down.addMouseListener(this);
		back = new JLabel(backImage);
		back.addMouseListener(this);
		create = new JLabel(createImage);
		create.addMouseListener(this);
		search = new JLabel(searchImage);
		search.addMouseListener(this);
		field = new JTextField("001");
		field.setOpaque(false);
		field.setFont(new Font("Dialog", 1, 25));
		minButton =new JLabel(new ImageIcon("src/images/button/minbutton.png"));
		quitButton = new JLabel(new ImageIcon("src/images/button/quitbutton.png"));
		minButton.setBounds(802,19, 50, 53);
		quitButton.setBounds(852,19, 50,53);
		minButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		add(quitButton);
		add(minButton);
		// 设置组件大小
		field.setSize(163, 50);
		up.setSize(38, 23);
		down.setSize(38, 23);
		back.setSize(87, 578);
		create.setSize(101, 89);
		search.setSize(65, 50);
		// 设置组件位置
		field.setLocation(655, 119);
		up.setLocation(100, 498);
		down.setLocation(566, 498);
		back.setLocation(87, 578);
		create.setLocation(772, 521);
		search.setLocation(818, 119);

		add(field);
		add(back);
		add(up);
		add(down);
		add(create);
		add(search);
		addPanel = new JPanel[5];
		for (int i = 0; i < 5; i++) {
			addPanel[i] = new JPanel();
			addPanel[i].setOpaque(false);
			addPanel[i].setVisible(true);
			;
			addPanel[i].addMouseListener(this);
			addPanel[i].setBounds(520, 95 + 74 * i, 103, 74);
			add(addPanel[i]);
		}

	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backGround, 0, 0, 927, 661, this);
		g2d.setFont(new Font("黑体", 1, 39));
		g2d.setColor(Color.blue);

		if (numRoom < numLine) {
			int a = 0;
			for (String s : roomData) {
				g2d.drawImage(line, 87, 95 + 74 * a, 535, 74, this);
				String[] tmp = s.split(" ");
				g2d.drawString(tmp[0], 93, 154 + a * 74);
				g2d.drawString(tmp[1], 237, 154 + a * 74);
				g2d.drawString(tmp[2], 365, 154 + a * 74);
				getRoom.put(addPanel[a], tmp[2]);
				getRoom2.put(addPanel[a], tmp[1]);
				a++;
			}
		} else if (numRoom > numLine) {
			for (int i = 0; i < 5; i++) {
				g2d.drawImage(line, 87, 95 + 74 * i, 535, 74, this);
			}
			for (int j = 0; j < numLine; j++) {
				String[] tmp = roomData.get((ptr + j) % numRoom).split(" ");
				g2d.drawString(tmp[0], 93, 154 + j * 74);
				g2d.drawString(tmp[1], 237, 154 + j * 74);
				g2d.drawString(tmp[2], 365, 154 + j * 74);
				getRoom.put(addPanel[j], tmp[2]);
				getRoom2.put(addPanel[j], tmp[1]);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source == up) {
			if (numRoom < numLine) {
				return;
			}
			;
			ptr--;
			ptr = (ptr + numRoom) % numRoom;

			repaint();
		} else if (source == down) {
			if (numRoom < numLine) {
				return;
			}
			;
			ptr++;
			ptr = ptr % numRoom;
			repaint();
		} else if (source == search) {

			boolean existed = false;
			int count = 0;
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[0];
				if (tmpNum.equals(field.getText().trim())) {
					existed = true;
					room = s;
					break;
				}
				count++;
			}
			if (existed) {
				remove(prompt);
				repaint();
				getDetail(room);
				if (Player.getUserName().equals(
						roomInfo.get(field.getText().trim()))) {
					CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
							roomInfo.get(field.getText().trim()),
							roomInfo2.get(field.getText().trim()), name, head,
							lvl, gold, ready, state,bootFrame);
					removeAll();
					rp.setBounds(0, 0, 927, 661);
					add(rp);
					repaint();
				} else {
					CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
							roomInfo.get(field.getText().trim()),
							roomInfo2.get(field.getText().trim()), name, head,
							lvl, gold, ready, state,bootFrame);
					removeAll();
					rp.setBounds(0, 0, 927, 661);
					add(rp);
					repaint();
				}
			} else {
				add(prompt);
				repaint();

			}

		} else if (source == back) {
			ModelPanel md = new ModelPanel(bootFrame);
			md.setSize(927, 661);
			md.setLocation(0, 0);
			removeAll();
			add(md);
			repaint();
		} else if (source == create) {
			/* 进入填写房间信息界面 */
			RoomDialog rd = new RoomDialog(Player.getUserName());
			rd.setVisible(true);

		} else if (source == addPanel[0]) {
			if (1 > numRoom) {
				return;
			}
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[2];
				if (tmpNum.equals(getRoom.get(addPanel[0]))) {
					room = s;
					break;
				}

			}
			getDetail(room);
			if (Player.getUserName().equals(getRoom.get(addPanel[0]))) {
				CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
						getRoom2.get(addPanel[0]), getRoom.get(addPanel[0]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			} else {
				CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
						getRoom2.get(addPanel[0]), getRoom.get(addPanel[0]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			}

		} else if (source == addPanel[1]) {
			if (2 > numRoom) {
				return;
			}
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[2];
				if (tmpNum.equals(getRoom.get(addPanel[1]))) {
					room = s;
					break;
				}

			}
			getDetail(room);
			System.out.println(getRoom2.get(addPanel[1]));
			System.out.println(Player.getUserName().equals(
					getRoom2.get(addPanel[1])));

			if (Player.getUserName().equals(getRoom.get(addPanel[1]))) {
				CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
						getRoom2.get(addPanel[1]), getRoom.get(addPanel[1]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			} else {
				CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
						getRoom2.get(addPanel[1]), getRoom.get(addPanel[1]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			}

		} else if (source == addPanel[2]) {
			if (3 > numRoom) {
				return;
			}
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[2];
				if (tmpNum.equals(getRoom.get(addPanel[2]))) {
					room = s;
					break;
				}

			}
			getDetail(room);
			System.out.println(getRoom2.get(addPanel[2]));
			System.out.println(Player.getUserName().equals(
					getRoom2.get(addPanel[2])));
			if (Player.getUserName().equals(getRoom.get(addPanel[2]))) {
				CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
						getRoom2.get(addPanel[2]), getRoom.get(addPanel[2]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			} else {
				CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
						getRoom2.get(addPanel[2]), getRoom.get(addPanel[2]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			}
		} else if (source == addPanel[3]) {
			if (4 > numRoom) {
				return;
			}
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[2];
				if (tmpNum.equals(getRoom.get(addPanel[3]))) {
					room = s;
					break;
				}

			}
			getDetail(room);

			if (Player.getUserName().equals(getRoom.get(addPanel[3]))) {
				CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
						getRoom2.get(addPanel[3]), getRoom.get(addPanel[3]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			} else {
				CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
						getRoom2.get(addPanel[3]), getRoom.get(addPanel[3]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			}
		} else if (source == addPanel[4]) {
			if (5 > numRoom) {
				return;
			}
			String room = "";
			for (String s : roomData) {

				String tmpNum = s.split(" ")[2];
				if (tmpNum.equals(getRoom.get(addPanel[4]))) {
					room = s;
					break;
				}

			}
			getDetail(room);
			if (Player.getUserName().equals(getRoom.get(addPanel[4]))) {
				CoopHosterRoomPanel rp = new CoopHosterRoomPanel(
						getRoom2.get(addPanel[4]), getRoom.get(addPanel[4]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			} else {

				CoopPlayerRoomPanel rp = new CoopPlayerRoomPanel(
						getRoom2.get(addPanel[4]), getRoom.get(addPanel[4]),
						name, head, lvl, gold, ready, state,bootFrame);
				removeAll();
				rp.setBounds(0, 0, 927, 661);
				add(rp);
				repaint();
			}
		}else if(source == minButton){
			bootFrame.setExtendedState(JFrame.ICONIFIED);
		}else if(source == quitButton){
			bootFrame.dispose();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void getDetail(String room) {
System.out.println("room : "+room);
		name[0] = room.split(" ")[2];
		int i = 1;
		for (String s : room.split(" ")[4].split("%")) {
			if (s.equals("空")) {

			} else {
				name[i] = s;
				i++;
			}

		}

		ShowPlayerDetailData data = new ShowPlayerDetailData();

		String[] info = data.show(name).split(";");

		int j = 0;
		for (String s : info) {

System.out.println("房客:" + s);
			String[] temp = s.split("_");

			head[j] = Integer.parseInt(temp[2]);
			lvl[j] = temp[3];
			gold[j] = temp[4];
			// state[j] = 2 ;
			j++;
		}

		int x = info.length - 1;
		for (int m = 0; m < x; m++) {
			state[m] = 2;
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

	public void createRoom(String name, int limit) {
		CreateRoomClient client = new CreateRoomClient();

		String result = client.connect(name, Player.getUserName(), limit);
		if (result.equals("Success")) {
			/**
			 * 写到这儿
			 */
			String[] names = { Player.getUserName(), "", "", "" };
			ShowPlayerDetailData data = new ShowPlayerDetailData();

			String[] info = data.show(names).split(";");

			int j = 0;
			for (String s : info) {
				String[] temp = s.split("_");
				head[j] = Integer.parseInt(temp[2]);
				lvl[j] = temp[3];
				gold[j] = temp[4];

				j++;
			}
			state[0] = 0;
			state[1] = 0;
			state[2] = 0;

			CoopHosterRoomPanel rp = new CoopHosterRoomPanel(name,
					Player.getUserName(), names, head, lvl, gold, ready, state,bootFrame);

			rp.setBounds(0, 0, 927, 661);
			removeAll();
			add(rp);
			repaint();
		} else {
//			JOptionPane.showMessageDialog(null, "您已经建立过一个房间，不能再建！", "提示",
//					JOptionPane.INFORMATION MESSAGE);
			CreatedDialog ctd = new CreatedDialog("房间已创建，不能再建");
		}
	}

	class RoomDialog extends JFrame implements MouseListener {
		JPanel backPanel;
		JLabel right2;
		JLabel right3;
		JLabel right4;
		JLabel create;
		JLabel close;
		JLabel back;
		JTextField input = new JTextField();
		int num;
		String hostN;

		public RoomDialog(String name) {
			hostN = name;
			num = 4;
			backPanel = new DialogPanel(
					"src/images/Dialog/roomDialog/background.png");
			backPanel.setLayout(null);
			right2 = new JLabel();
			right3 = new JLabel();
			right4 = new JLabel();
			create = new JLabel(new ImageIcon(
					"src/images/Dialog/roomDialog/create.png"));
			close = new JLabel(new ImageIcon(
					"src/images/Dialog/roomDialog/close.png"));
			back = new JLabel(new ImageIcon(
					"src/images/Dialog/roomDialog/back.png"));
			JLabel nameLabel = new JLabel(name);

			// 设置组件性质
			input.setBorder(null);
			input.setOpaque(false);
			// 设置组件大小
			nameLabel.setSize(127, 21);
			close.setSize(41, 49);
			input.setSize(127, 21);
			back.setSize(76, 35);
			create.setSize(76, 35);
			right2.setSize(27, 25);
			right3.setSize(27, 25);
			right4.setSize(27, 25);
			// 设置组件位置
			nameLabel.setLocation(230, 85);
			close.setLocation(351, 7);
			input.setLocation(229, 118);
			back.setLocation(68, 188);
			create.setLocation(269, 188);
			right2.setLocation(242, 156);
			right3.setLocation(288, 156);
			right4.setLocation(334, 156);
			// 添加组件监听
			close.addMouseListener(this);
			back.addMouseListener(this);
			create.addMouseListener(this);
			right2.addMouseListener(this);
			right3.addMouseListener(this);
			right4.addMouseListener(this);
			right4.setIcon(new ImageIcon(
					"src/images/Dialog/roomDialog/right.png"));
			right3.setIcon(null);
			right2.setIcon(null);
			// 添加组件
			backPanel.add(nameLabel);
			backPanel.add(back);
			backPanel.add(close);
			backPanel.add(create);
			backPanel.add(input);
			backPanel.add(right2);
			backPanel.add(right3);
			backPanel.add(right4);
			getContentPane().add(backPanel);
			setUndecorated(true);
			setLocation(927 / 2, 661 / 2);
			setSize(backPanel.getWidth(), backPanel.getHeight());
			setVisible(true);

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

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
			Object source = arg0.getSource();
			if (source == right2) {
				right2.setIcon(new ImageIcon(
						"src/images/Dialog/roomDialog/right.png"));
				right3.setIcon(null);
				right4.setIcon(null);
				num = 2;
			} else if (source == right3) {
				right3.setIcon(new ImageIcon(
						"src/images/Dialog/roomDialog/right.png"));
				right2.setIcon(null);
				right4.setIcon(null);
				num = 3;
			} else if (source == right4) {
				right4.setIcon(new ImageIcon(
						"src/images/Dialog/roomDialog/right.png"));
				right3.setIcon(null);
				right2.setIcon(null);
				num = 4;
			} else if (source == close) {
				dispose();
			} else if (source == back) {
				dispose();
			} else if (source == create) {

				roomName = input.getText();

				createRoom(roomName, num);

				dispose();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	private class CreatedDialog extends JFrame implements MouseListener{
		JPanel backPanel;
		JLabel close;
		JLabel msg;
		JLabel yes;
		JLabel cancle;
		public CreatedDialog(String msgs){
			backPanel = new DialogPanel("src/images/Dialog/OtherDialog/warning.png");
			backPanel.setLayout(null);
			close = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/close.png"));
			yes = new JLabel(new ImageIcon("src/images/Dialog/OtherDialog/yes.png"));
			msg = new JLabel(msgs);
			msg.setFont(new Font("幼圆",1,15));
			msg.setForeground(Color.gray);
			System.out.println(msg.getWidth());
			// 设置组件大小
			close.setSize(36, 36);
			yes.setSize(78, 32);
			msg.setSize(300,18);
			// 设置组件位置
			close.setLocation(353, 7);
			yes.setLocation(253,147);
			msg.setLocation(155,93);
			// 设置组件监听
			close.addMouseListener(this);
			yes.addMouseListener(this);
			// 添加组件
			backPanel.add(close);
			backPanel.add(yes);
			backPanel.add(msg);
			getContentPane().add(backPanel);
			setUndecorated(true);
			setLocation(927/2, 661/2);
			setSize(backPanel.getWidth(),backPanel.getHeight());
			System.out.println(backPanel.getWidth());
			setVisible(true);
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
			Object source = arg0.getSource();
			if (source == yes){
				dispose();
			}else if (source == close){
				dispose();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

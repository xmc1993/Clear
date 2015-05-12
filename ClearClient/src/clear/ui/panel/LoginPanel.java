package clear.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enupackage.ButtonType;
import enupackage.MouseType;
import clear.ui.component.MyButton;
import clear.ui.component.MyJDialog;
import clear.ui.component.MyJTextField;
import clear.ui.component.MyPSField;
import clear.ui.data.DayScores;
import clear.ui.data.DayTimes;
import clear.ui.data.RankData;
import clear.ui.data.RoundScores;
import clear.ui.logic.FriendData;
import clear.ui.logic.GameData;
import clear.ui.logic.LoginData;
import clear.ui.test.*;

public class LoginPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imageIcon;
	int width;
	int hight;
	MouseType type = MouseType.EXIT;
	int position;
	int head;
	MyButton quitButton;
	MyButton minButton;
	MyButton stopButton;
	MyButton loginButton;
	MyButton signInButton;
	MyButton visitButton;

	JCheckBox rememberPS;
	JTextField idfield;
	JTextField psfield;

	JLabel forgetPS;

	JFrame bootFrame;
	public LoginPanel(JFrame boot) {
		bootFrame = boot;
		
		imageIcon = new ImageIcon("src/images/login2.jpg");
		// 创建各个组建
		idfield = new MyJTextField();
		psfield = new MyPSField();

		rememberPS = new JCheckBox("记住密码");
		rememberPS.setBorder(null);
		forgetPS = new JLabel("忘记密码");

		quitButton = new MyButton(ButtonType.quitButton);
		minButton = new MyButton(ButtonType.minButton);
		stopButton = new MyButton(ButtonType.stopButton);
		loginButton = new MyButton(ButtonType.loginButton);
		signInButton = new MyButton(ButtonType.signInButton);
		visitButton = new MyButton(ButtonType.visitButton);
		// 设置各个组建的位置
		idfield.setLocation(81, 257);
		idfield.setBorder(null);
		psfield.setLocation(81, 300);
		psfield.setBorder(null);
		rememberPS.setLocation(85, 350);
		rememberPS.setBackground(Color.black);
		forgetPS.setLocation(250, 350);

		loginButton.setLocation(57, 385);
		signInButton.setLocation(231, 385);

		visitButton.setLocation(780, 542);
		quitButton.setLocation(844, 2);
		minButton.setLocation(715, 0);
		stopButton.setLocation(780, 0);
		// 各组件设置大小
		minButton.setSize(54, 72);
		stopButton.setSize(54, 72);
		quitButton.setSize(54, 72);
		rememberPS.setSize(125, 13);
		forgetPS.setSize(152, 13);
		loginButton.setSize(139, 26);
		signInButton.setSize(139, 26);
		visitButton.setSize(84, 55);
		idfield.setSize(279, 27);
		psfield.setSize(279, 27);
		//

		// 设置监听
		rememberPS.addMouseListener(this);
		forgetPS.addMouseListener(this);
		loginButton.addMouseListener(this);
		signInButton.addMouseListener(this);
		quitButton.addMouseListener(this);
		minButton.addMouseListener(this);
		stopButton.addMouseListener(this);
		visitButton.addMouseListener(this);
		// 添加组建
		setLayout(null);
		add(idfield);
		add(psfield);
		add(rememberPS);
		add(forgetPS);
		add(loginButton);
		add(signInButton);
		add(quitButton);
		add(minButton);
		add(stopButton);
		add(visitButton);

		width = imageIcon.getIconWidth();
		hight = imageIcon.getIconHeight();

		setVisible(true);
	}

	/**
	 * 重写panel的getWidth和getHeight方法
	 */
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}

	/**
	 * 重写panel的绘图方法，改变panel的背景
	 */
	public void paintComponent(Graphics g) {
		Image image = imageIcon.getImage();
		g.drawImage(image, 0, 0, this);
	}

	public ArrayList<String> analysis(ArrayList<String> lists, int num) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : lists) {
			String[] temps = s.split(" ");
			result.add(temps[num]);
		}
		return result;
	}

	public ArrayList<Integer> analysis2(ArrayList<String> lists, int num) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (String s : lists) {
			String[] temps = s.split(" ");
			result.add(Integer.parseInt(temps[num]));
		}
		return result;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == loginButton) {
			String id = idfield.getText();
			String ps = psfield.getText();

			if (id.equals("")) {
//				JOptionPane.showMessageDialog(null, "请输入用户名！", "提示",
//						JOptionPane.INFORMATION_MESSAGE);
				MyJDialog mydialog = new MyJDialog(5);
			} else {
				if (ps.equals("")) {
//					JOptionPane.showMessageDialog(null, "请输入密码！", "提示",
//							JOptionPane.INFORMATION_MESSAGE);
					MyJDialog myJDialog = new MyJDialog(1);
				} else {
					String result = new LoginData().check(id, ps, 0);
					String[] temp = result.split("_");
					if (temp[0].equals("Success")) {
						// 登录成功时的动作
						Player.setHead(Integer.parseInt(result.split(";")[0]
								.split("_")[3]));
						Player.setLvl(Integer.parseInt(result.split(";")[0]
								.split("_")[4]));
						Player.setGold(Integer.parseInt(result.split(";")[0]
								.split("_")[5]) + 100);
						Player.setExp(Integer.parseInt(result.split(";")[0]
								.split("_")[6]));
						Player.setMax(Integer.parseInt(result.split(";")[0]
								.split("_")[7]));
						System.out.println(Player.getExp() + " "
								+ Player.getLvl() + " " + Player.getHead()
								+ " " + Player.getGold() + " "
								+ Player.getMax());
						double[] f_avg = new double[7];
						int[] f_game_day = new int[7];
						ArrayList<String> f_score_game = new ArrayList<String>();
						ArrayList<String> f_info = new ArrayList<String>();
						ArrayList<String> f_name = new ArrayList<String>();
						ArrayList<Integer> f_state = new ArrayList<Integer>();
						ArrayList<Integer> f_head = new ArrayList<Integer>();
						ArrayList<String> f_exp = new ArrayList<String>();

						for (int i = 0; i < 7; i++) {
							f_avg[i] = 0;
							f_game_day[i] = 0;
							f_score_game.add("0");
							f_info.add("0");
						}
						f_name.add("暂无好友");
						f_state.add(-1);
						f_head.add(0);
						f_exp.add("0");

						Player.setUserName(id);
						FriendData data = new FriendData();
						ArrayList<String> lists = data.getFriends(id);
						GameData data2 = new GameData(Player.getUserName());
						String x = data2.getData();

						if (lists.get(0).equals("")
								|| lists.get(0).split(" ")[0].equals("null")) {

							RankData rankData = new RankData(f_avg, f_game_day,
									f_score_game, f_info);
							FriendsRankPanel friendsRankPanel = new FriendsRankPanel(
									f_name, f_state, f_head, f_exp, rankData,bootFrame);
							friendsRankPanel.setSize(927, 661);
							friendsRankPanel.setLocation(0, 0);
							removeAll();
							add(friendsRankPanel);
							FriendsData.setNames(f_name);
							FriendsData.setHeadList(f_state);
							FriendsData.setLvlList(f_head);
							FriendsData.setGoldList(f_exp);
						} else {
							RankData rankData = new RankData(
									data2.getAvgPerDay(),
									data2.getGamesPerDay(),
									data2.getScorePerGame(), data2.getRecords());
							FriendsRankPanel friendsRankPanel = new FriendsRankPanel(
									analysis(lists, 1), analysis2(lists, 5),
									analysis2(lists, 2), analysis(lists, 3),
									rankData,bootFrame);
							friendsRankPanel.setSize(927, 661);
							friendsRankPanel.setLocation(0, 0);
							removeAll();
							add(friendsRankPanel);

							FriendsData.setNames(analysis(lists, 1));
							FriendsData.setHeadList(analysis2(lists, 5));
							FriendsData.setLvlList(analysis2(lists, 2));
							FriendsData.setGoldList(analysis(lists, 3));
						}

						repaint();

					} else if (temp[0].equals("DataNotExist")) {
//						JOptionPane.showMessageDialog(null, "用户名不存在！", "登录失败",
//								JOptionPane.INFORMATION_MESSAGE);
						MyJDialog myJDialog = new MyJDialog(2);
						idfield.setText("");
					} else if (temp[0].equals("DataUnmatch")) {
//						JOptionPane.showMessageDialog(null, "密码错误!", "登录失败",
//								JOptionPane.INFORMATION_MESSAGE);
						MyJDialog myJDialog = new MyJDialog(4);
						psfield.setText("");
					} else if (temp[0].equals("DataExist")) {
//						JOptionPane.showMessageDialog(null, "您已登录，无需重复登录!",
//								"登录失败", JOptionPane.INFORMATION_MESSAGE);
						//暂无
						psfield.setText("");
					} else if (temp[0].equals("OverTime")) {
//						JOptionPane.showMessageDialog(null, "连接超时,请检查网络连接！",
//								"登录失败", JOptionPane.INFORMATION_MESSAGE);
						MyJDialog myJDialog = new MyJDialog(3);
						psfield.setText("");
					} else {
						idfield.setText("");
						psfield.setText("");
					}
				}
			}

		} else if (source == signInButton) {
			removeAll();
			RegisterPanel registerpanel = new RegisterPanel(bootFrame);
			registerpanel.setSize(927, 661);
			registerpanel.setLocation(0, 0);
			add(registerpanel);
			repaint();
		} else if (source == forgetPS) {
			System.out.println("forgetPS");
		} else if (source == quitButton) {
			bootFrame.dispose();
			System.out.println("quitButton");
		} else if (source == minButton) {
			System.out.println("minButton");
		} else if (source == stopButton) {
			System.out.println("stopButton");
		} else if (source == visitButton) {
			removeAll();
			// 调用方法获得musiclist在此处就简单点自己创建了
			ArrayList<String> musiceList = new ArrayList<String>();
			for (int i = 0; i < 10; i++) {
				musiceList.add("" + i);
			}
			SetPanel setpanel = new SetPanel(musiceList);
			setpanel.setSize(927, 661);
			setpanel.setLocation(0, 0);

			add(setpanel);
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == quitButton) {
			quitButton.changeState(MouseType.ENTER);
			quitButton.repaint();
		} else if (source == minButton) {
			minButton.changeState(MouseType.ENTER);
			minButton.repaint();
		} else if (source == stopButton) {
			stopButton.changeState(MouseType.ENTER);
			stopButton.repaint();
		} else if (source == loginButton) {
			loginButton.changeState(MouseType.ENTER);
			loginButton.repaint();
		} else if (source == signInButton) {
			signInButton.changeState(MouseType.ENTER);
			signInButton.repaint();
		} else if (source == visitButton) {
			visitButton.changeState(MouseType.ENTER);
			visitButton.repaint();
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == quitButton) {
			quitButton.changeState(MouseType.OUT);
			quitButton.repaint();
		} else if (source == minButton) {
			minButton.changeState(MouseType.OUT);
			minButton.repaint();
		} else if (source == stopButton) {
			stopButton.changeState(MouseType.OUT);
			stopButton.repaint();
		} else if (source == loginButton) {
			loginButton.changeState(MouseType.OUT);
			loginButton.repaint();
		} else if (source == signInButton) {
			signInButton.changeState(MouseType.OUT);
			signInButton.repaint();
		} else if (source == visitButton) {
			visitButton.changeState(MouseType.OUT);
			visitButton.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

//	public static void main(String[] args) {
//		
//		new TestDrive().test(new LoginPanel());
//	}

	class SetPanel extends JPanel implements MouseListener {
		private JPanel leftPanel;
		private JPanel rightPanel;
		private JButton okButton;
		private JLabel headLabel;
		ImageIcon okImage;
		ImageIcon imageIcon;
		ImageIcon headImage[];
		Image leftImage;
		Image rightImage;
		int width;
		int hight;
		JLabel OnOff;
		JLabel musicname;
		JPanel up;
		JPanel down;
		JPanel back;
		JTextField ip1;
		JTextField ip2;
		JTextField ip3;
		JTextField ip4;
		ArrayList<String> musiclist;
		int num;
		int len;
		boolean open = false;

		String ip;

		public SetPanel(ArrayList<String> musiclist) {
			this.musiclist = musiclist;

			len = musiclist.size();
			imageIcon = new ImageIcon("src/images/setpanel/setclose.png");
			okImage = new ImageIcon("src/images/setpanel/okImage.png");
			OnOff = new JLabel(new ImageIcon("src/images/setpanel/open.png"));
			leftImage = new ImageIcon("src/images/setpanel/leftImage.png")
					.getImage();
			rightImage = new ImageIcon("src/images/setpanel/rightImage.png")
					.getImage();
			headImage = new ImageIcon[6];
			for (int i = 0; i < 6; i++) {
				headImage[i] = new ImageIcon("src/images/chatHead/squareHead"
						+ i + ".jpg");
			}
			headLabel = new JLabel(headImage[head]);
			headLabel.setBounds(387, 416, 115, 115);
			add(headLabel);
			okButton = new JButton(okImage);
			musicname = new JLabel(musiclist.get(0));
			leftPanel = new JPanel();
			rightPanel = new JPanel();
			up = new JPanel();
			down = new JPanel();
			back = new JPanel();
			ip1 = new JTextField();
			ip2 = new JTextField();
			ip3 = new JTextField();
			ip4 = new JTextField();
			ip1.setBackground(new Color(90, 95, 95));
			ip1.setBorder(null);
			ip2.setBackground(new Color(90, 95, 95));
			ip2.setBorder(null);
			ip3.setBackground(new Color(90, 95, 95));
			ip3.setBorder(null);
			ip4.setBackground(new Color(90, 95, 95));
			ip4.setBorder(null);

			back.setVisible(true);
			back.setOpaque(false);
			up.setVisible(true);
			up.setOpaque(false);
			down.setVisible(true);
			down.setOpaque(false);
			// 设置组件大小
			OnOff.setSize(104, 37);
			back.setSize(111, 47);
			ip1.setSize(59, 34);
			ip2.setSize(59, 34);
			ip3.setSize(59, 34);
			ip4.setSize(59, 34);
			up.setSize(32, 36);
			down.setSize(32, 36);
			musicname.setSize(279, 36);
			// 设置组件位置
			OnOff.setLocation(310, 219);
			back.setLocation(83, 578);
			ip1.setLocation(311, 287);
			ip2.setLocation(407, 287);
			ip3.setLocation(503, 287);
			ip4.setLocation(600, 287);
			up.setLocation(310, 352);
			down.setLocation(628, 355);
			musicname.setLocation(346, 353);

			leftPanel.setOpaque(false);
			rightPanel.setOpaque(false);
			leftPanel.addMouseListener(this);
			rightPanel.addMouseListener(this);
			leftPanel.setBounds(352, 418, 32, 115);
			rightPanel.setBounds(502, 418, 32, 115);
			okButton.setBounds(720, 572, 129, 59);
			okButton.addMouseListener(this);
			add(okButton);
			add(leftPanel);
			add(rightPanel);

			// 添加监听
			OnOff.addMouseListener(this);
			back.addMouseListener(this);
			up.addMouseListener(this);
			down.addMouseListener(this);
			// 添加组件
			setLayout(null);
			add(OnOff);
			add(musicname);
			add(up);
			add(down);
			add(back);
			add(ip1);
			add(ip2);
			add(ip3);
			add(ip4);

			width = imageIcon.getIconWidth();
			hight = imageIcon.getIconHeight();
			System.out.println(width);
			System.out.println(hight);

		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return hight;
		}

		public void paintComponent(Graphics g) {
			Image image = imageIcon.getImage();
			g.drawImage(image, 0, 0, this);
			g.drawImage(leftImage, 352, 418, 32, 115, this);
			g.drawImage(rightImage, 502, 418, 32, 115, this);
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
			if (source == OnOff) {
				if (!open) {
					OnOff.setIcon(new ImageIcon("src/images/setpanel/open.png"));
					open = true;
				} else {
					OnOff.setIcon(new ImageIcon("src/images/setpanel/close.png"));
					open = false;
				}
				// 卉神处理音乐开环
				System.out.println("OnOff");
			} else if (source == up) {
				num--;
				int a = num % len;
				if (a < 0) {
					a = -a;
				}
				musicname.setText(musiclist.get(a));
				musicname.repaint();

			} else if (source == down) {
				num++;
				int a = num % len;
				if (a < 0) {
					a = -a;
				}
				musicname.setText(musiclist.get(a));
				musicname.repaint();

			} else if (source == back) {
				ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText()
						+ "." + ip4.getText();
				// 卉神获取ip，音乐编号等信息

				removeAll();
				LoginPanel login = new LoginPanel(bootFrame);
				login.setSize(927, 661);
				login.setLocation(0, 0);
				add(login);
				repaint();
			} else if (source == okButton) {
				IPAddress.setIP(ip);
				Player.setHead(head);

				removeAll();
				LoginPanel login = new LoginPanel(bootFrame);
				login.setSize(927, 661);
				login.setLocation(0, 0);
				add(login);
				repaint();
			} else if (source == leftPanel) {
				head--;
				if (head < 3) {
					head = (head + 3) % 3;
				} else {
					head = (head + 6) % 6;
				}
				headLabel.setIcon(headImage[head]);
			} else if (source == rightPanel) {
				head++;
				if (head <= 3) {
					head = head % 3;
				} else {
					head = (head - 3) % 3 + 3;
				}
				headLabel.setIcon(headImage[head]);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

}

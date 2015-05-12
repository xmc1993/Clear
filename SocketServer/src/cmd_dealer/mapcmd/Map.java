package cmd_dealer.mapcmd;
import java.util.ArrayList;

//import java.util.Scanner;

public class Map implements MapServiceInterface {

	private final int EMPTY = 7;// 设定EMPTY值，为被消除状态
	private final int A_prop_0 = 8;// 设定A道具的值
	private final int A_prop_1 = 9;// 设定A道具的值
	private final int A_prop_2 = 10;// 设定A道具的值
	private final int A_prop_3 = 11;// 设定A道具的值
	private final int A_prop_4 = 12;// 设定A道具的值
	private final int A_prop_5 = 13;// 设定A道具的值
	private final int A_prop_6 = 14;// 设定A道具的值

	private final int B_prop = 15;// 设定B道具的值
	private final int Map_Row = 9;// Map的行数
	private final int Map_Col = 9;// Map的列数
	private final int NumOfPattern = 7;// 图案的种类数
	public static int Map[][];// 地图
	private int xClicked;// 用于存储第一次点击的图案的x坐标
	private int yClicked;// 用于存储第一次点击的图案的y坐标
	private boolean isDoubleClicked = false;// 用来判断是否是第一次点击
	private int col_count;// 计算竖列消除个数
	private int row_count;// 计算横排消除个数
	public static int score = 0;// 得分
	private boolean is_D = false;
	private boolean is_super = false;
	
	private String roomMaster ;

	public Map() {
		//init();
	}

	public String getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(String roomMaster) {
		this.roomMaster = roomMaster;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Map = new int[Map_Row][Map_Col];
		for (int i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				int ran = (int) (Math.random() * NumOfPattern);
				Map[i][j] = ran;
			}
		}
		if (haveDis()) {
			init();
		}
		if (!isNotDead()) {
			init();
		}
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		init();
	}

	// 查看左边是否有相同的图案
	public boolean left(int x, int y) {
		if (y == 0) {
			return false;
		} else if ((Map[x][y] == Map[x][y - 1])
				|| (Map[x][y] == Map[x][y - 1] + 8)
				|| (Map[x][y] == Map[x][y - 1] - 8) && (Map[x][y - 1] != EMPTY)) {
			return true;
		} else {
			return false;
		}
	}

	// 查看右边是否有相同的图案
	public boolean right(int x, int y) {
		if (y == Map_Col - 1) {
			return false;
		} else if ((Map[x][y] == Map[x][y + 1])
				|| (Map[x][y] == Map[x][y + 1] + 8)
				|| (Map[x][y] == Map[x][y + 1] - 8) && (Map[x][y + 1] != EMPTY)) {
			return true;
		} else {
			return false;
		}
	}

	// 查看上边是否有相同的图案
	public boolean above(int x, int y) {
		if (x == 0) {
			return false;
		} else if ((Map[x][y] == Map[x - 1][y])
				|| (Map[x][y] == Map[x - 1][y] + 8)
				|| (Map[x][y] == Map[x - 1][y] - 8) && (Map[x - 1][y] != EMPTY)) {
			return true;
		} else {
			return false;
		}
	}

	// 查看下边是否有相同的图案
	public boolean under(int x, int y) {
		if (x == Map_Row - 1) {
			return false;
		} else if ((Map[x + 1][y] == Map[x][y])
				|| (Map[x + 1][y] == Map[x][y] + 8)
				|| (Map[x + 1][y] == Map[x][y] - 8) && (Map[x + 1][y] != EMPTY)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clearLinked(int x, int y) {
		// TODO Auto-generated method stub
		int temp = Map[x][y];// 一个临时值，用来记录这个坐标本来的值以便当出现A道具时能生成相同的种类
		int i;
		// 赋值一个新的二维数组，方便进行操作，操作完之后再赋值为Map
		// Map直接操作的话会使得赋值EMPTY时出现错误
		int copy[][] = new int[Map_Row][Map_Col];
		for (i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				copy[i][j] = Map[i][j];
			}
		}
		row_count = 0;
		col_count = 0;
		if (is_Chain(x, y) == 1) {
			// getScore();
			// 清除左边
			for (i = 0; i < y + 1; i++) {
				if (Map[x][y - i] > 7) {
					A_prop(x, y - i);
				}
				if (left(x, y - i)) {
					copy[x][y - i] = EMPTY;
					row_count++;
				} else {
					copy[x][y - i] = EMPTY;
					row_count++;
					break;
				}
			}

			// 清除右边
			for (i = 0; i < Map_Col - y; i++) {
				if (Map[x][y + i] > 7) {
					A_prop(x, y + i);
				}
				if (right(x, y + i)) {
					copy[x][y + i] = EMPTY;
					row_count++;
				} else {
					copy[x][y + i] = EMPTY;
					row_count++;
					break;
				}
			}
		} else if (is_Chain(x, y) == -1) {
			// getScore();
			// 清除下边
			for (i = 0; i < Map_Row - x; i++) {
				if (Map[x + i][y] > 7) {
					A_prop(x + i, y);
				}
				if (under(x + i, y)) {
					copy[x + i][y] = EMPTY;
					col_count++;
				} else {
					copy[x + i][y] = EMPTY;
					col_count++;
					break;
				}
			}
			// 清除上边
			for (i = 0; i < x + 1; i++) {
				if (Map[x - i][y] > 7) {
					A_prop(x + i, y);
				}
				if (above(x - i, y)) {
					copy[x - i][y] = EMPTY;
					col_count++;
				} else {
					copy[x - i][y] = EMPTY;
					col_count++;
					break;
				}
			}
		} else if (is_Chain(x, y) == 2) {
			// getScore();
			// 清除左边
			for (i = 0; i < y + 1; i++) {
				if (left(x, y - i)) {
					copy[x][y - i] = EMPTY;
					row_count++;
				} else {
					copy[x][y - i] = EMPTY;
					row_count++;
					break;
				}
			}

			// 清除右边
			for (i = 0; i < Map_Col - y; i++) {
				if (right(x, y + i)) {
					copy[x][y + i] = EMPTY;
					row_count++;
				} else {
					copy[x][y + i] = EMPTY;
					row_count++;
					break;
				}
			}
			// 清除下边
			for (i = 0; i < Map_Row - x; i++) {
				if (under(x + i, y)) {
					copy[x + i][y] = EMPTY;
					col_count++;
				} else {
					copy[x + i][y] = EMPTY;
					col_count++;
					break;
				}
			}
			// 清除上边
			for (i = 0; i < x + 1; i++) {
				if (above(x - i, y)) {
					copy[x - i][y] = EMPTY;
					col_count++;
				} else {
					copy[x - i][y] = EMPTY;
					col_count++;
					break;
				}
			}

		}
		if (row_count != 0) {
			row_count--;
		}
		if (col_count != 0) {
			col_count--;
		}
		getScore();
		if ((row_count == 3 && col_count == 3) || (row_count == 4)
				|| (col_count == 4)) {
			switch (temp) {
			case 0:
				copy[x][y] = A_prop_0;
				break;
			case 1:
				copy[x][y] = A_prop_1;
				break;
			case 2:
				copy[x][y] = A_prop_2;
				break;
			case 3:
				copy[x][y] = A_prop_3;
				break;
			case 4:
				copy[x][y] = A_prop_4;
				break;
			case 5:
				copy[x][y] = A_prop_5;
				break;
			case 6:
				copy[x][y] = A_prop_6;
				break;
			default:
				break;
			}

		} else if ((row_count == 3 && col_count == 4)
				|| (row_count == 4 && col_count == 3) || (row_count == 5)
				|| (col_count == 5)) {
			copy[x][y] = B_prop;
		}
		// 在这里把copy再赋值回Map
		for (i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				Map[i][j] = copy[i][j];
			}
		}

	}

	@Override
	public boolean isInChain(int x, int y) {
		if (is_Chain(x, y) != 0) {
			// getScore();
			return true;
		} else {
			return false;
		}
	}

	// 返回1表示横向有连锁，-1表示纵向有连锁，0表示无连锁，2表示横竖都有
	public int is_Chain(int x, int y) {
		// TODO Auto-generated method stub
		int _row_count = 0;
		int _col_count = 0;
		int i;
		// 检查左边
		for (i = 0; i < y + 1; i++) {
			if (left(x, y - i)) {
				_row_count++;
			} else {
				break;
			}
		}
		// 检查右边
		for (i = 0; i < Map_Col - y; i++) {
			if (right(x, y + i)) {
				_row_count++;
			} else {
				break;
			}
		}
		// 检查下边
		for (i = 0; i < Map_Row - x; i++) {
			if (under(x + i, y)) {
				_col_count++;
			} else {
				break;
			}
		}
		// 检查上边
		for (i = 0; i < x + 1; i++) {
			if (above(x - i, y)) {
				_col_count++;
			} else {
				break;
			}
		}
		if (_row_count > 1 && _col_count > 1) {
			return 2;
		}
		if (_row_count > 1) {
			return 1;
		} else if (_col_count > 1) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public void moveMap() {
		// TODO Auto-generated method stub
		int ran = (int) (Math.random() * 2);
		if (ran == 0) {
			for (int i = 0; i < Map_Col; i++) {
				for (int j = 0; j < Map_Row; j++) {
					if (Map[i][j] == EMPTY) {
						int h = i;
						while (h > 0) {
							int temp = Map[h][j];
							Map[h][j] = Map[h - 1][j];
							Map[h - 1][j] = temp;
							h--;
						}
					}
				}
			}
		} else {
			for (int j = 0; j < Map_Col; j++) {
				for (int i = 0; i < Map_Row; i++) {
					if (Map[j][i] == EMPTY) {
						int h = i;
						while (h > 0) {
							int temp = Map[j][i];
							Map[j][h] = Map[j][h - 1];
							Map[j][h - 1] = temp;
							h--;
						}
					}
				}
			}
		}

		// show();
	}

	@Override
	public void updateMap() {
		// TODO Auto-generated method stub
		for (int j = 0; j < Map_Col; j++) {
			for (int i = 0; i < Map_Row; i++) {
				if (Map[i][j] == EMPTY) {
					Map[i][j] = (int) (Math.random() * NumOfPattern);
					while (!isNotDead()) {
						Map[i][j] = (int) (Math.random() * NumOfPattern);
					}
				}
			}
		}

	}

	@Override
	public boolean haveDis() {
		// TODO Auto-generated method stub
		for (int j = 0; j < Map_Col; j++) {
			for (int i = 0; i < Map_Row; i++) {
				if (isInChain(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void clearAllMap() {
		// TODO Auto-generated method stub
		for (int j = 0; j < Map_Col; j++) {
			for (int i = 0; i < Map_Row; i++) {
				if (is_Chain(i, j) != 0) {

					clearLinked(i, j);
					// show();
				}
				// getScore();
			}
		}
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub

		if (col_count > 2) {
			switch (col_count) {
			case 3:
				score = score + 100;
				break;
			case 4:
				score = score + 200;
				break;
			default:
				score = score + 500;
				break;
			}
		}
		if (row_count > 2) {
			switch (row_count) {
			case 3:
				score = score + 100;
				break;
			case 4:
				score = score + 200;
				break;
			default:
				score = score + 500;
				break;
			}
		}
		// System.out.println(score);
		if (is_super) {
			score = score * 2;
		}
		return score;
	}

	@Override
	public int returnMapValue(int x, int y) {
		// TODO Auto-generated method stub
		return Map[x][y];
	}

	@Override
	public boolean swap(int x, int y) {
		
		// TODO Auto-generated method stub
		if (isDoubleClicked) { //如果是第二次单击 .
			
			if (((xClicked == x - 1) && (yClicked == y))
					|| ((xClicked == x + 1) && (yClicked == y))
					|| ((xClicked == x) && (yClicked == y - 1))
					|| ((xClicked == x) && (yClicked == y + 1))) {
				int temp = Map[x][y];
				Map[x][y] = Map[xClicked][yClicked];
				Map[xClicked][yClicked] = temp;

				if (isInChain(x, y) || isInChain(xClicked, yClicked)) {
					if (isInChain(x, y)) {
						clearLinked(x, y);
					}
					if (isInChain(xClicked, yClicked)) {
						clearLinked(xClicked, yClicked);
					}
					isDoubleClicked = false;                  /*如果交换后存在消除刷新点击记录 .*/

					return true;
				} else {
					temp = Map[x][y];
					Map[x][y] = Map[xClicked][yClicked];
					Map[xClicked][yClicked] = temp;
					isDoubleClicked = false;                        /*如果叫换以后没有消除*/
				}
			} else {  
				isDoubleClicked = true;                            /*如果两次点击的不相邻则刷新第一次点击的记录 .*/
				xClicked = x;
				yClicked = y;
			}
		} else {       // 如果不是第二次单击 .
			
			isDoubleClicked = true;                           /*如果是第一次点击则修改标志*/
			xClicked = x;
			yClicked = y;
		}
		return false;
	}

	public void show() {
		for (int i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public boolean clickDone(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNotDead() {
		for (int i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				if ((i == 0) && (j == 0)) {
					if (small_check(0, 0, 0, 1) || small_check(0, 0, 1, 0)) {
						return true;
					}
				} else if ((i == 0) && (j == Map_Col - 1)) {
					if (small_check(0, Map_Col - 1, 0, Map_Col - 2)
							|| small_check(0, Map_Col - 1, 1, Map_Col - 1)) {
						return true;
					}
				} else if ((i == 0) && (j != 0) && (j != Map_Col - 1)) {
					if (small_check(0, j, 0, j - 1)
							|| small_check(0, j, 0, j + 1)
							|| small_check(0, j, 1, j)) {
						return true;
					}
				} else if ((i == Map_Row - 1) && (j == 0)) {
					if (small_check(Map_Row - 1, 0, Map_Row - 2, 0)
							|| small_check(Map_Row - 1, 0, Map_Row - 1, 1)) {
						return true;
					}
				} else if ((i == Map_Row - 1) && (j == Map_Col - 1)) {
					if (small_check(Map_Row - 1, Map_Col - 1, Map_Row - 1,
							Map_Col - 2)
							|| small_check(Map_Row - 1, Map_Col - 1,
									Map_Row - 2, Map_Col - 1)) {
						return true;
					}
				} else if ((i == Map_Row - 1) && (j != Map_Col - 1) && (j != 0)) {
					if (small_check(Map_Row - 1, j, Map_Row - 1, j - 1)
							|| small_check(Map_Row - 1, j, Map_Row - 2, j)
							|| small_check(Map_Row - 1, j, Map_Row - 1, j + 1)) {
						return true;
					}
				} else if ((j == Map_Col - 1) && (i != 0) && (i != Map_Row - 1)) {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j - 1)) {
						return true;
					}
				} else if ((j == 0) && (i != 0) && (i != Map_Row - 1)) {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j + 1)) {
						return true;
					}
				} else {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i, j - 1)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j + 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 防止代码重用的一个方法
	public boolean small_check(int x1, int y1, int x2, int y2) {
		// System.out.println(x1+" "+y1+" "+x2+" "+y2);
		int temp = Map[x1][y1];
		Map[x1][y1] = Map[x2][y2];
		Map[x2][y2] = temp;
		if (isInChain(x1, y1) || isInChain(x2, y2)) {
			temp = Map[x1][y1];
			Map[x1][y1] = Map[x2][y2];
			Map[x2][y2] = temp;
			return true;
		} else {
			temp = Map[x1][y1];
			Map[x1][y1] = Map[x2][y2];
			Map[x2][y2] = temp;
			return false;
		}
	}

	@Override
	public ArrayList<Integer> hint() {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < Map_Row; i++) {
			for (int j = 0; j < Map_Col; j++) {
				if ((i == 0) && (j == 0)) {
					if (small_check(0, 0, 0, 1) || small_check(0, 0, 1, 0)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((i == 0) && (j == Map_Col - 1)) {
					if (small_check(0, Map_Col - 1, 0, Map_Col - 2)
							|| small_check(0, Map_Col - 1, 1, Map_Col - 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((i == 0) && (j != 0) && (j != Map_Col - 1)) {
					if (small_check(0, j, 0, j - 1)
							|| small_check(0, j, 0, j + 1)
							|| small_check(0, j, 1, j)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((i == Map_Row - 1) && (j == 0)) {
					if (small_check(Map_Row - 1, 0, Map_Row - 2, 0)
							|| small_check(Map_Row - 1, 0, Map_Row - 1, 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((i == Map_Row - 1) && (j == Map_Col - 1)) {
					if (small_check(Map_Row - 1, Map_Col - 1, Map_Row - 1,
							Map_Col - 2)
							|| small_check(Map_Row - 1, Map_Col - 1,
									Map_Row - 2, Map_Col - 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((i == Map_Row - 1) && (j != Map_Col - 1) && (j != 0)) {
					if (small_check(Map_Row - 1, j, Map_Row - 1, j - 1)
							|| small_check(Map_Row - 1, j, Map_Row - 2, j)
							|| small_check(Map_Row - 1, j, Map_Row - 1, j + 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((j == Map_Col - 1) && (i != 0) && (i != Map_Row - 1)) {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j - 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else if ((j == 0) && (i != 0) && (i != Map_Row - 1)) {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j + 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				} else {
					if (small_check(i, j, i - 1, j)
							|| small_check(i, j, i, j - 1)
							|| small_check(i, j, i + 1, j)
							|| small_check(i, j, i, j + 1)) {
						list.add(i);
						list.add(j);
						break;
					}
				}
			}
		}
		return list;
	}

	@Override
	public void A_prop(int x, int y) {
		// TODO Auto-generated method stub
		/*
		 * Map[x][y]=EMPTY; Map[x+1][y]=EMPTY; Map[x-1][y]=EMPTY;
		 * Map[x][y+1]=EMPTY; Map[x][y-1]=EMPTY; Map[x+1][y+1]=EMPTY;
		 * Map[x-1][y+1]=EMPTY; Map[x+1][y-1]=EMPTY; Map[x-1][y-1]=EMPTY;
		 */
		Map[x][y] = EMPTY;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((x + i >= 0) && (x + i < Map_Row) && (y + j >= 0)
						&& (y + j < Map_Col) && (Map[x + i][y + j] < 7)
						&& (!(i == 0 && j == 0))) {
					Map[x + i][y + j] = EMPTY;
				}
			}
		}
		if (is_super) {
			score += 1000;
		} else {
			score += 500;
		}

	}

	@Override
	public void B_prop(int x, int y) {
		// TODO Auto-generated method stub
		int i;
		for (i = 0; i < Map_Row; i++) {
			Map[i][y] = EMPTY;
		}
		for (i = 0; i < Map_Col; i++) {
			Map[x][i] = EMPTY;
		}
		if (is_super) {
			score += 2000;
		} else {
			score += 1000;
		}

	}

	@Override
	public void C_prop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void D_prop() {
		// TODO Auto-generated method stub
		is_D = true;
	}

	@Override
	public void E_prop() {
		// TODO Auto-generated method stub
		score = score + score / 10;
	}

	@Override
	public void superMode(boolean is_D) {
		// TODO Auto-generated method stub

	}
	
	public boolean needSwap(int x,int y){
		return (isDoubleClicked&&(Math.abs((x+y)-(xClicked+yClicked))==1));
	}
	public String getLastClicked(){
		return yClicked+";"+xClicked;
	}
	
	public static void main(String[] args){
		int[][] tempMap = {{1,1,0,1,2,3,4,5,6},
				{2,3,4,5,6,0,1,2,3},
				{3,4,5,6,0,1,2,3,4},
				{4,5,6,0,1,2,3,4,5},
				{5,6,0,1,2,3,4,5,6},{6,0,1,2,3,4,5,6,0},{0,1,2,3,4,5,6,0,1},{1,2,3,4,5,6,0,1,2},
				{2,3,4,5,2,0,2,2,3}};
		Map = tempMap;
		Map map = new Map();
		map.swap(8, 2) ;
		System.out.println(map.swap(8,4)) ;
		System.out.println(map.swap(8,5)) ;
	}
	
}

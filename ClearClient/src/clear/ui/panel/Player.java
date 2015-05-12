package clear.ui.panel;

public class Player {
	
	static String userName ;
	static int lvl ;
	static int gold = 100;
	static int head ;
	static int exp ;
	static int max ;
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Player.userName = userName;
	}

	public static int getLvl() {
		return lvl;
	}

	public static void setLvl(int lvl) {
		Player.lvl = lvl;
	}

	public static int getGold() {
		return gold;
	}

	public static void setGold(int gold) {
		Player.gold = gold;
	}

	public static int getHead() {
		return head;
	}

	public static void setHead(int head) {
		Player.head = head;
	}

	public static int getExp() {
		return exp;
	}

	public static void setExp(int exp) {
		Player.exp = exp;
	}

	public static int getMax() {
		return max;
	}

	public static void setMax(int max) {
		Player.max = max;
	}
	
}

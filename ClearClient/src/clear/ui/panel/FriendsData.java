package clear.ui.panel;

import java.util.ArrayList;

public class FriendsData {
	
	static ArrayList<String> names = new ArrayList<String>();
	static ArrayList<Integer> headList = new ArrayList<Integer>();
	static ArrayList<Integer> lvlList = new ArrayList<Integer>();
	static ArrayList<String> goldList = new ArrayList<String>();
	public static ArrayList<String> getNames() {
		return names;
	}
	public static void setNames(ArrayList<String> names) {
		FriendsData.names = names;
	}
	public static ArrayList<Integer> getHeadList() {
		return headList;
	}
	public static void setHeadList(ArrayList<Integer> headList) {
		FriendsData.headList = headList;
	}
	public static ArrayList<Integer> getLvlList() {
		return lvlList;
	}
	public static void setLvlList(ArrayList<Integer> lvlList) {
		FriendsData.lvlList = lvlList;
	}
	public static ArrayList<String> getGoldList() {
		return goldList;
	}
	public static void setGoldList(ArrayList<String> goldList) {
		FriendsData.goldList = goldList;
	}
	
}

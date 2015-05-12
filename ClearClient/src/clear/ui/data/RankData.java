package clear.ui.data;

import java.util.ArrayList;

public class RankData {
	double[] averagecount;
	int[] gamecount;
	ArrayList<String> list;
	ArrayList<String> info;
	public RankData(double[] acount,int[] gcount,ArrayList<String> li,ArrayList<String> info){
		list = li;
		averagecount = acount;
		gamecount = gcount;
		this.info=info;
	}
	public double[] getAverageCount(){
		return averagecount;
	}
	public int[] getGameCount(){
		return gamecount;
	}
	public ArrayList<String> getList(){
		return list;
	}
	public ArrayList<String> getInfo(){
		return info;
	}
	
}

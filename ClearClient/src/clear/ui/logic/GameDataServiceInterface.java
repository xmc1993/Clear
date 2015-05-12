package clear.ui.logic;

import java.util.ArrayList;
import clear.bean.*;
/**
 * 获得玩家信息模块
 */
public interface GameDataServiceInterface {
	public String getTotal();
	public double[] getAvgPerDay();
	public int[] getGamesPerDay();
	public double getAverage();
	public ArrayList<String> getScorePerGame();
	public ArrayList<String> getDays();
	public ArrayList<String> getRecords();
	public int getMaxScore();
	public int getMaxLJ();
	public int getGold();
	public int getExp();
	public String updateData(String name , String data);
	
	public String getData();
	

}

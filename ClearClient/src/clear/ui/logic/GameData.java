package clear.ui.logic;

import java.util.ArrayList;

import clear.bean.*;
import clear.ui.network.GameDataClient;

public class GameData implements GameDataServiceInterface{

	String data = "";
	String[] temp ;
	public GameData(String name){
		GameDataClient client = new GameDataClient();
		data = client.connect(name) ;
System.out.println("data : "+data);
		if(data!=null){
			temp = data.split("_") ;
		}else{
			temp = new String[8] ;
			for(int i = 0 ; i<8 ; i++){
				temp[i] = "0";
			}
		}
		
	}
	
	@Override
	public String updateData(String name, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTotal() {
		// TODO Auto-generated method stub
		return temp[0];
	}

	@Override
	public double[] getAvgPerDay() {
		// TODO Auto-generated method stub
		String s = temp[4] ;
		double[] avgs = new double[100];
		if(s.equals("0")){
			for(int i = 0 ; i<10 ; i++){
				avgs[i] = 0 ;
			}
		}else{
			String[] _s = s.split(";");
		
			avgs = new double[_s.length];
			int i = 0 ;
			for(String x : _s){
				avgs[i] = (Double.parseDouble(x.split(" ")[1]));
				i ++;
			}
		}
		
		return avgs;
	}

	@Override
	public int[] getGamesPerDay() {
		// TODO Auto-generated method stub
		String s = temp[1] ;
		
		
		int[] games = new int[100];
		if(s.equals("0")){
			for(int i = 0 ; i<10 ; i++){
				games[i] = 0 ;
			}
		}else{
			String[] _s = s.split(";");
			games = new int[_s.length];
			int i = 0 ;
			for(String x : _s){
				games[i] = (Integer.parseInt(x.split(" ")[1]));
				i ++;
			}
		}
		
		return games;
	}

	@Override
	public double getAverage() {
		// TODO Auto-generated method stub
		
		return Double.parseDouble(temp[3]);
	}

	@Override
	public ArrayList<String> getScorePerGame() {
		// TODO Auto-generated method stub
		String s = temp[2] ;
		ArrayList<String> result = new ArrayList<String>();
		if(s.equals("0")){
			for(int i = 0 ; i<10 ; i++){
				result.add("0") ;
			}
		}else{
			String[] _s = s.split(";");
		
			for(String x : _s){
				result.add(x);
			}
		}
		return result;
	}

	@Override
	public int getMaxScore() {
		// TODO Auto-generated method stub
		return Integer.parseInt(temp[6]);
	}

	@Override
	public int getMaxLJ() {
		// TODO Auto-generated method stub
		return Integer.parseInt(temp[5]);
	}

	@Override
	public int getGold() {
		// TODO Auto-generated method stub
		return Integer.parseInt(temp[7]);
	}

	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return Integer.parseInt(temp[8]);
	}

	@Override
	public ArrayList<String> getDays() {
		// TODO Auto-generated method stub
		ArrayList<String> score_per_game = getScorePerGame();
		ArrayList<String> result  = new ArrayList<String>();
		if(score_per_game.equals("0")){
			result.add("0");
		}else{
			for(String s:score_per_game){
				result.add(s.split(" ")[0]) ;
			}
		}
		
		return result;
	}

	@Override
	public ArrayList<String> getRecords() {
		// TODO Auto-generated method stub
		
		ArrayList<String> result  = new ArrayList<String>();
		result.add(getTotal()+"");
		result.add(getAverage()+"");
		result.add(getMaxLJ()+"");
		result.add(getMaxScore()+"");
		
		return result;
	}

	public String getData() {
System.out.println(data);
		return data;
	}

}

package cmd_dealer.gamecmd;

import java.util.ArrayList;

import server.data.gamedata.GameData;

public class GameDealer implements GameDealerInterface{
	GameData data = new GameData();
	@Override
	public String getData(String name ) {
		// TODO Auto-generated method stub
		
		String result = "";
		
		String number = data.find(name);
		//0 总局数
		String total = data.total(number)+"" ;
		//1 每日局数 返回格式：日期(空格)局数
		ArrayList<String> gamePerDay = data.getGamePerDay(number) ;
		//2 每局得分
		ArrayList<String> gameScore = data.getGameScore(number) ;
		//3 平均得分
		double average = data.getAverge(number) ;
		//4 每日平均得分
		ArrayList<String> avePerDay =  data.getAvePerDay(number) ;
		//5 最高连击数量
		int maxLJ = data.maxLJ(number) ;
		//6 最高得分
		int maxScore = data.maxScore(number) ;
		// 7 金币数
		int gold = data.getGold(number) ;
		//8 经验值
		int exp = data.getExp(number) ;
		//总局数_（每日局数：日期：局数）_（每局得分：日期：得分）_平均得分_（每日平均：日期：平均）_
		//最高连击数_最高分_金币_经验
		result = result + total+"_";
		for(String temp : gamePerDay){
			result = result+temp+";" ;
		}
		result = result+"_";
		for(String temp : gameScore){
			result = result+temp+";" ;
		}
		result = result+"_";
		result = result + average+"_";
		for(String temp : avePerDay){
			result = result+temp+";" ;
		}
		result = result+"_";
		result = result+maxLJ+"_" ;
		result = result+maxScore+"_" ;
		result = result+gold+"_" ;
		result = result+exp ;
		return result;
	}
	
	@Override
	public boolean updateData(String name, String _data) {
		// TODO Auto-generated method stub
		
		String number = data.find(name) ;
		int ID = data.findLast(number) ;
		
		String[] temp = _data.split(";") ;
		
		String date = temp[0] ;
		String score = temp[1] ;
		String lianji = temp[2] ;
		String gold = temp[3] ;
		String exp = temp[4] ;
//System.out.println(_data);		
		return data.insert(number, ID, date, score, lianji, gold, exp);
	}

	public static void main(String[] args){
		
		GameDealer dealer = new GameDealer();
		//System.out.println(dealer.updateData("郁寒歌1","2014/5/24 500 4 12 15")) ;
		System.out.println(dealer.getData("郁寒歌1")) ;
	}

}

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
		//0 �ܾ���
		String total = data.total(number)+"" ;
		//1 ÿ�վ��� ���ظ�ʽ������(�ո�)����
		ArrayList<String> gamePerDay = data.getGamePerDay(number) ;
		//2 ÿ�ֵ÷�
		ArrayList<String> gameScore = data.getGameScore(number) ;
		//3 ƽ���÷�
		double average = data.getAverge(number) ;
		//4 ÿ��ƽ���÷�
		ArrayList<String> avePerDay =  data.getAvePerDay(number) ;
		//5 �����������
		int maxLJ = data.maxLJ(number) ;
		//6 ��ߵ÷�
		int maxScore = data.maxScore(number) ;
		// 7 �����
		int gold = data.getGold(number) ;
		//8 ����ֵ
		int exp = data.getExp(number) ;
		//�ܾ���_��ÿ�վ��������ڣ�������_��ÿ�ֵ÷֣����ڣ��÷֣�_ƽ���÷�_��ÿ��ƽ�������ڣ�ƽ����_
		//���������_��߷�_���_����
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
		//System.out.println(dealer.updateData("������1","2014/5/24 500 4 12 15")) ;
		System.out.println(dealer.getData("������1")) ;
	}

}

package cmd_dealer.roomcmd;

import java.util.ArrayList;

import server.data.roomdata.AddLogerData;

public class ShowLogerDealer implements ShowLogerDealerInterface {

	@Override
	public ArrayList<String> show(String name) {
		// TODO Auto-generated method stub
		AddLogerData data = new AddLogerData();
		String number = data.find(name, 3,1) ;//获得房间名
		ArrayList<String> members = data.getLoger(number) ;
		return members;
	}

	public static void main(String[] args){
		ArrayList<String> members = new ShowLogerDealer().show("郁寒歌1");
		for(String s : members){
			System.out.println(s);
		}
	}
}

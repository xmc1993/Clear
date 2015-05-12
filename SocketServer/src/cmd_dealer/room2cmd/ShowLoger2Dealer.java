package cmd_dealer.room2cmd;

import java.util.ArrayList;

import server.data.room2data.AddLoger2Data;

public class ShowLoger2Dealer implements ShowLoger2Interface{

	@Override
	public ArrayList<String> show(int tag, String name) {
		// TODO Auto-generated method stub
		ArrayList<String> members = new ArrayList<String>();
		AddLoger2Data data = new AddLoger2Data();
		String number = data.find(name, 3, 1) ;//获得房间名
		if(tag == 0){
			members = data.getLoger(number,6) ;
		}else{
			members = data.getLoger(number,7) ;
		}
		
		return members;
	
	}
	
	public static void main(String[] args){
		ArrayList<String> members = new ShowLoger2Dealer().show(0, "郁寒歌1");
		for(String s : members){
			System.out.println(s);
		}
	}

}

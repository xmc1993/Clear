package cmd_dealer.room2cmd;

import java.util.ArrayList;

import server.data.room2data.ShowRoom2Data;

public class ShowRoom2Dealer implements ShowRoom2Interface{

	@Override
	public String show() {
		// TODO Auto-generated method stub
		ShowRoom2Data data = new ShowRoom2Data();
		ArrayList<String> lists = data.show() ;
		String result = "";
		for(String s : lists){
			result = result+s+";" ;
		}
		return result;
	}
	
	public static void main(String[] args){
		String list = new ShowRoom2Dealer().show();
		
			System.out.println(list) ;
		
	}

}

package cmd_dealer.roomcmd;

import java.util.ArrayList;

import server.data.roomdata.ShowRoomData;

public class ShowRoomDealer implements ShowRoomDealerInterface{

	@Override
	public String show() {
		// TODO Auto-generated method stub
		ShowRoomData data = new ShowRoomData();
		ArrayList<String> lists = data.show() ;
		String result = "";
		for(String s : lists){
			result = result+s+";" ;
		}
		return result;
	}
	public static void main(String[] args){
		ArrayList<String> list = new ShowRoomData().show() ;
		for(String s : list){
			System.out.println(s) ;
		}
	}
	
}

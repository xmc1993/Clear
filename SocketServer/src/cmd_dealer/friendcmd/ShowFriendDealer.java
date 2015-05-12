package cmd_dealer.friendcmd;

import java.util.ArrayList;

import server.data.frienddata.ShowFriendData;
import DO.friendDO.showFriendDO.ShowFriendDO;

public class ShowFriendDealer implements ShowFriendDealerInterface{

	@Override
	public String show(String userName) {
		// TODO Auto-generated method stub
		ShowFriendData data = new ShowFriendData();
		ArrayList<String> doList = data.finds("F"+data.find(userName)) ;
		String list = "" ;
		for(String temp : doList){
			list = list+temp+";";
		}
		return list;
	}

	public static void main(String[] args){
		String doList = new ShowFriendDealer().show("”Ù∫Æ∏Ë1");
		
			System.out.println(doList);
		
	}
	
}

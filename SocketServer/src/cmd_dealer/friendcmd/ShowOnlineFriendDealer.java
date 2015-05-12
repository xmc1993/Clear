package cmd_dealer.friendcmd;

import java.util.ArrayList;

import server.data.frienddata.ShowFriendData;
import server.data.frienddata.ShowOnlineFriendData;
import DO.friendDO.showFriendDO.ShowOnlineFriendDO;

public class ShowOnlineFriendDealer implements ShowOnlineFriendDealerInterface{

	@Override
	public String show(String userName) {
		// TODO Auto-generated method stub
		ShowOnlineFriendData data = new ShowOnlineFriendData();
		//ArrayList<String> doList =(data.finds(data.find(userName))) ;
	
		ArrayList<String> doList = data.finds(data.finds(data.find(userName))) ;
		String list = "";
		for(String line : doList){
//System.out.println(line);
			list = list+line+";" ;
			
		}
		return list;
	}

	public static void main(String[] args){
		ShowOnlineFriendDealer show = new ShowOnlineFriendDealer() ;
		System.out.println(show.show("”Ù∫Æ∏Ë1"));
	}
}

package cmd_dealer.friendcmd;

import server.data.frienddata.ShowSingleFriendData;

public class ShowSingleFriendDealer implements ShowSingleFriendInterface{

	@Override
	public String get(String number) {
		// TODO Auto-generated method stub
		ShowSingleFriendData data = new ShowSingleFriendData();
		return data.find(number);
	}
	
	public static void main(String[] args){
		System.out.println(new ShowSingleFriendDealer().get("10000001")) ;
	}

}

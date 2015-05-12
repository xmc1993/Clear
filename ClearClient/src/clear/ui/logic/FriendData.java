package clear.ui.logic;

import java.util.ArrayList;

import clear.bean.PlayerInfo;
import clear.ui.network.ShowFriendClient;
import clear.ui.network.ShowOnlineFriendClient;

public class FriendData implements FriendDataServiceInterface{

	@Override
	public ArrayList<String> getFriends(String name) {
		// TODO Auto-generated method stub
		ShowFriendClient client = new ShowFriendClient();
		ArrayList<String> lists = new ArrayList<String>();
		String s = client.connect(name);
		String[] temp = s.split(";") ;
		for(String line : temp ){
			lists.add(line) ;
			
		}
		return lists;
	}

	@Override
	public String addFriend(String name) {
		// TODO Auto-generated method stub
		//String result = FriendPanel.client.send(name, Player.getUserName());
		//return result ;
		return null ;
	}

	@Override
	public PlayerInfo scanPlayerInfo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteFriend(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String agreeFriend(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args){
		FriendData data = new FriendData();
		data.getFriends("”Ù∫Æ∏Ë1");
		
	}

	@Override
	public ArrayList<String> getOnlineFriends(String name) {
		// TODO Auto-generated method stub
		ShowOnlineFriendClient client = new ShowOnlineFriendClient();
		ArrayList<String> lists = new ArrayList<String>();
		String s = client.connect(name);
		String[] temp = s.split(";") ;
		for(String line : temp ){
			lists.add(line) ;
		}
		return lists;
	}

}

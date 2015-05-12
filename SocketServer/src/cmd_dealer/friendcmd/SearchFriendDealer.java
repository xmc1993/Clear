package cmd_dealer.friendcmd;

import server.data.frienddata.SearchFriendData;

public class SearchFriendDealer implements SearchFriendInterface{

	@Override
	public String search(String name , String num) {
		// TODO Auto-generated method stub
		SearchFriendData data = new SearchFriendData();
		String result = "";
		
		if(!data.isExits(num)){
			result = "此用户不存在！";
		}else{
			if(!data.isOnline(num)){
				result = "此用户暂时不在线，请稍后再邀请！";
			}else{
				if(data.isState1(num)){
					result = "此用户正在游戏中或已经进入游戏大厅，请稍后再邀请！";
				}else{
					if(!data.isNotFriend(data.get(name), num)){
						result = "此用户已经是您的好友，无需重复邀请！";
					}else{
						ShowSingleFriendDealer dealer = new ShowSingleFriendDealer();
						result = dealer.get(num);
					}
				}
			}
		}
		return result ;
	}
	
	public static void main(String[] args){
		
		System.out.println(new SearchFriendDealer().search("郁寒歌1", "10000001"));
	}

}

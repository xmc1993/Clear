package cmd_dealer.friendcmd;

import server.data.frienddata.SearchFriendData;

public class SearchFriendDealer implements SearchFriendInterface{

	@Override
	public String search(String name , String num) {
		// TODO Auto-generated method stub
		SearchFriendData data = new SearchFriendData();
		String result = "";
		
		if(!data.isExits(num)){
			result = "���û������ڣ�";
		}else{
			if(!data.isOnline(num)){
				result = "���û���ʱ�����ߣ����Ժ������룡";
			}else{
				if(data.isState1(num)){
					result = "���û�������Ϸ�л��Ѿ�������Ϸ���������Ժ������룡";
				}else{
					if(!data.isNotFriend(data.get(name), num)){
						result = "���û��Ѿ������ĺ��ѣ������ظ����룡";
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
		
		System.out.println(new SearchFriendDealer().search("������1", "10000001"));
	}

}

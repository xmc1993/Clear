package cmd_dealer.friendcmd;

import server.data.frienddata.DeleteFriendData;

public class DeleteFriendDealer implements DeleteFriendDealerInterface{

	@Override
	public boolean delete(String userName, String friName) {
		// TODO Auto-generated method stub
		
		DeleteFriendData data = new DeleteFriendData();
		
		String number1 = data.find(userName) ;
		String number2 = data.find(friName) ;
		
		boolean b1 = data.delete(number1, friName) ;
		boolean b2 = data.delete(number2, userName) ;
		
		return b1&b2;
	}

	public static void main(String[] args){
		System.out.println( new DeleteFriendDealer().delete("Óôº®¸è1", "ÏÄµ°1"));
	}
	
}

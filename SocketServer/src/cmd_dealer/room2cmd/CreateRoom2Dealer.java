package cmd_dealer.room2cmd;

import java.util.ArrayList;

import server.data.room2data.CreateRoom2Data;
import ResultMessage.ResultMessage;

public class CreateRoom2Dealer implements CreateRoom2Interface{

	@Override
	public ResultMessage create(String data1, String data2, int data3, int data4) {
		// TODO Auto-generated method stub
		CreateRoom2Data data = new CreateRoom2Data();
		ResultMessage result = ResultMessage.Success ;
		int number = data.getLast() ;
		ArrayList<String> masters = data.getMasters() ;
		boolean legal = true;
		for(String temp : masters){
			if(data2.equals(temp)){
				legal = false;
				break;
			}
		}
		if(legal){
			data.add(number, data1, data2, data3,data4) ;
			result = ResultMessage.Success;
		}else{
			result = ResultMessage.DataExist ;
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(new CreateRoom2Dealer().create("hi", "ÏÄî£î£", 1, 2)) ;
		System.out.println(new CreateRoom2Dealer().create("hi", "Óôº®¸è", 1, 2)) ;
	}
	
}

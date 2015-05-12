package cmd_dealer.roomcmd;

import java.util.ArrayList;

import server.data.roomdata.CreateRoomData;
import ResultMessage.ResultMessage;

public class CreateRoomDealer implements CreateRoomDealerInterface {

	@Override
	public ResultMessage create(String data1, String data2,
			int data3) {
		// TODO Auto-generated method stub
		CreateRoomData data = new CreateRoomData();
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
			data.add(number, data1, data2, data3) ;
			result = ResultMessage.Success;
		}else{
			result = ResultMessage.DataExist ;
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println( new CreateRoomDealer().create("123", "”Ù∫Æ∏Ë1", 4));
	}

}

package cmd_dealer.roomcmd;

import java.util.ArrayList;

import server.data.roomdata.AddLogerData;
import ResultMessage.ResultMessage;

public class AddLogerDealer implements AddLogerDealerInterface{

	@Override
	public ResultMessage addLoger(String data1, String data2, String data3) {
		// TODO Auto-generated method stub
		AddLogerData data = new AddLogerData();
		String number = data.find(data2,3,1) ;//获得房间号

		int roomSize = Integer.parseInt(data.find(data2, 3,4)) ;//获得房间限额；
		System.out.println(number);
		String loger = data.find(data2, 3,5) ;
		ResultMessage result = ResultMessage.Success ;
		ArrayList<String> logers = data.getLoger(number) ;
		if(logers.size()<roomSize){
			boolean legal = true;
			for(String temp : logers){
				if(temp.equals(data3)){
					legal = false;
					break;
				}
			}
			
			if(legal){
				if(loger.equals("空")){
					loger = data3;
					
				}else{
					loger = loger+"%"+data3 ;
				}
				
				data.update(number, data1, data2, roomSize+"", loger) ;
				result = ResultMessage.Success ;
			}else{
				result = ResultMessage.DataExist ;
			}
			
		}else{
			result = ResultMessage.OperationFail ;
		}
		
		return result ;
	}
	

	public static void main(String[] args){
		System.out.println(new AddLogerDealer().addLoger("hi", "郁寒歌1", "徐成1"));
	}
	

}

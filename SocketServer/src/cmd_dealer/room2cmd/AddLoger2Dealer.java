package cmd_dealer.room2cmd;

import java.util.ArrayList;

import server.data.room2data.AddLoger2Data;
import ResultMessage.ResultMessage;

public class AddLoger2Dealer implements AddLoger2Interface{

	@Override
	public ResultMessage addLoger(int tag, String data1, String data2,
			String data3) {
		// TODO Auto-generated method stub
		ResultMessage result = ResultMessage.Success ;
		AddLoger2Data data = new AddLoger2Data();
		String number = data.find(data2,3,1) ;//获得房间号
		int roomSize1 = Integer.parseInt(data.find(data2, 3,4)) ;//获得我方房间限额；
		int roomSize2 = Integer.parseInt(data.find(data2, 3,5)) ;//获得敌方房间限额；
		//如果是我方加入了成员
		if(tag == 0){
			String loger = data.find(data2, 3,6) ;
			String loger2 = data.find(data2, 3,7) ;
			
			ArrayList<String> logers = data.getLoger(number,6) ;
			for(String s: logers){
				System.out.println(s);
			}
			if(logers.size()<roomSize1){
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
					
					data.update(number, data1, data2, roomSize1+"", roomSize2+"",loger,loger2) ;
					result = ResultMessage.Success ;
				}else{
					result = ResultMessage.DataExist ;
				}
				
			}else{
				result = ResultMessage.OperationFail ;
			}
			
		}
		//敌方加入了成员
		else if(tag == 1){
			String loger1 = data.find(data2, 3,6) ;
			String loger = data.find(data2, 3,7) ;
			ArrayList<String> logers = data.getLoger(number,7) ;
			if(logers.size()<roomSize2){
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
					
					data.update(number, data1, data2, roomSize1+"", roomSize2+"",loger1,loger) ;
					result = ResultMessage.Success ;
				}else{
					result = ResultMessage.DataExist ;
				}
				
			}else{
				result = ResultMessage.OperationFail ;
			}
		}
		
		return result ;
		
	}
	
	public static void main(String[] args){
		System.out.println(new AddLoger2Dealer().addLoger(1, "hi", "郁寒歌1", "夏睿睿"));
	}

}

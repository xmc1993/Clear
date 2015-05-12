package cmd_dealer.roomcmd;

import java.util.ArrayList;

import server.data.roomdata.DeleteLogerData;
import ResultMessage.ResultMessage;

public class DeleteLogerDealer implements DeleteLogerDealerInterface{

	@Override
	public ResultMessage delete(String data1, String data2) {
		// TODO Auto-generated method stub
		
		DeleteLogerData data = new DeleteLogerData();
		
		String number = data.find(data1, 1) ;//��÷����
		String master = data.find(data1, 3) ;//��÷�����
		String roomSize = data.find(data1, 4);//��÷����޶�
		String loger = "����:";

		ArrayList<String> logers = data.getLoger(number) ;
		ResultMessage result = ResultMessage.Success ;
		for(String temp : logers){

			if(!data2.equals(temp)){
				loger = loger+" "+temp ;

			}
		}
		data.update(number, data1, master, roomSize, loger) ;
		return result;
	}

}

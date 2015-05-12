package cmd_dealer.quitcmd;

import server.data.quitdata.QuitData;
import ResultMessage.ResultMessage;

public class QuitDealer implements QuitDealerInterface {

	@Override
	public ResultMessage dealer(String userName) {
		// TODO Auto-generated method stub
		
		QuitData data = new QuitData();
		
		ResultMessage result = null ;
		boolean existed = false; 
		if(data.find(userName, 2, "在线列表")){
			existed = true;
		}
		if(existed){
			data.delete(userName);
			result = ResultMessage.Success ;
			
		}else{
			result =  ResultMessage.DataNotExist ;
		}
		return result;
	}

}

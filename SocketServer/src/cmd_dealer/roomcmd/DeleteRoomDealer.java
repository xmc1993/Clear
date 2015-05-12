package cmd_dealer.roomcmd;

import server.data.roomdata.DeleteRoomData;
import ResultMessage.ResultMessage;

public class DeleteRoomDealer implements DeleteRoomDealerInterface{

	@Override
	public ResultMessage delete(String roomName) {
		// TODO Auto-generated method stub
		DeleteRoomData data = new DeleteRoomData();
		data.delete(roomName);
		return ResultMessage.Success;
	}
	
}

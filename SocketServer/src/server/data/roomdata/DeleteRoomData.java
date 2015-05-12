package server.data.roomdata;

import server.data.datautility.DBUtility;

public class DeleteRoomData {
	DBUtility connector = new DBUtility();

	public boolean delete(String data) {
		// TODO Auto-generated method stub
		connector.connect();
		
		String SQL = "delete from Room where RoomMaster = "+"'"+data+"'";
		connector.execute(SQL);
		
		connector.close();
		return true;
	}
}

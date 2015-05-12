package server.data.roomdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;

public class DeleteLogerData {
	
	DBUtility connector = new DBUtility();
	

	public String find(String s , int i){
		String result = "" ;
		connector.connect();
		String SQL = "Select * FROM Room";
		ResultSet rel = connector.query(SQL);
		
		try {
			
			while(rel.next()){
				if(rel.getString(3).equals(s)){
					result = rel.getString(i) ;
					break;
				}
			}
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result ;
	}
	
	public ArrayList<String> getLoger(String data){
		ArrayList<String> result = new ArrayList<String>() ;
		connector.connect();
		String SQL = "Select * FROM Room";
		ResultSet rel = connector.query(SQL);
		String loger = "";
		try {
			while(rel.next()){
				if(rel.getString(1).equals(data)){
					loger = rel.getString(5) ;
					break; 
				}
			}
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] temp = loger.split(" ") ;
		for(int i = 1 ; i<temp.length ; i++){
			result.add(temp[i]);
		}
		return result ;
	}

	public boolean update(String number, String data1, String data2,
			String roomSize, String loger) {
		// TODO Auto-generated method stub
		boolean result = true;
		connector.connect();
		
		String SQL = "update Room set RoomName = '"+data1+"' ,RoomMaster = '"+data2+"',"
				+ "RoomLimit = '"+roomSize+"',RoomMember = '"+loger
				+"' WHERE RoomID = '"+number+"'";
		connector.execute(SQL);
		connector.close();
		return result;
	}

}

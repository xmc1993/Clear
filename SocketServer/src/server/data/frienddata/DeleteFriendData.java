package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.data.datautility.DBUtility;

public class DeleteFriendData {
	DBUtility connector = new DBUtility();
	
	public String find(String data){
		String result = "" ;
		connector.connect();
		String SQL = "Select * FROM ”√ªß";
		ResultSet rel = connector.query(SQL);
		
		try {
			
			while(rel.next()){
				if(rel.getString(2).equals(data)){
					result = rel.getString(1) ;
					break;
				}
			}
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (result) ;
		
	}
	
	public boolean delete(String data1 , String data2){
		connector.connect();
		boolean result = true ; 

		String tableName = "F"+data1;
		String SQL = "delete from "+tableName + " where ∫√”—Í«≥∆='"+data2+"' ";
		connector.execute(SQL) ;
		connector.close();
		return result ;
	}
	
	
}

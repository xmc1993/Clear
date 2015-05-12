package server.data.quitdata;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.data.datautility.DBUtility;

public class QuitData {
	
	DBUtility connector = new DBUtility();
	
	public boolean find( String data , int num ,  String tableName ){
		boolean result = false ;
		connector.connect();
		String SQL = "Select * FROM "+tableName;
		ResultSet rel = connector.query(SQL);
		
		try {
			
			while(rel.next()){
				if(rel.getString(num).equals(data)){
					result = true ;
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
	
	public boolean isSuc( String name , String pw){
		boolean result = false ;
		connector.connect();
		String SQL = "Select * FROM 用户";
		ResultSet rel = connector.query(SQL);
		
		try {
			
			while(rel.next()){
				if(rel.getString(2).equals(name)){
					if(rel.getString(4).equals(pw)){
						result = true;
						break;
					}
				}
			}
			connector.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result ;
		
	}

	public boolean delete(String data){
		connector.connect();
	
		String SQL = "delete from 在线列表 where 玩家昵称 = "+"'"+data+"'";
		connector.execute(SQL);
		
		connector.close();
		
	return true;
	}
	
	public static void main(String[] args){
		
	}


}

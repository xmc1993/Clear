package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class AddFriendData {
	DataUtility du = new DataUtility();
	public String find( String data , int num){
		return du.get("src/数据/用户.dat", data, num) ;
		
	}
	
	public boolean add( String numberT , String number , String name , String lvl ,String gold ,
			String head){
		boolean result = true ;
		du.append("src/数据/F"+numberT+".dat",number+" "+name+" "+lvl+" "+gold+" "+head);
		return result ;
	}
	
}

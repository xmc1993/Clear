package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class AddFriendData {
	DataUtility du = new DataUtility();
	public String find( String data , int num){
		return du.get("src/����/�û�.dat", data, num) ;
		
	}
	
	public boolean add( String numberT , String number , String name , String lvl ,String gold ,
			String head){
		boolean result = true ;
		du.append("src/����/F"+numberT+".dat",number+" "+name+" "+lvl+" "+gold+" "+head);
		return result ;
	}
	
}

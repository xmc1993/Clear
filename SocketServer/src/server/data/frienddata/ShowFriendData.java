package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowFriendData {
	DBUtility connector = new DBUtility();
	DataUtility du = new DataUtility() ;
	public String find(String data ){
		
		return du.get("src/����/�û�.dat", data, 1) ;
	}

	public ArrayList<String> finds( String data){
		
		return du.read("src/����/"+data+".dat") ;
		
	}
	
	public static void main(String[] args){
		ShowFriendData show = new ShowFriendData() ;
		show.finds("10000002");
		//show.finds("10000001") ;
	}
}

package server.data.roomdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowRoomData {
	
	DataUtility du = new DataUtility() ;
	
	public ArrayList<String> show(){
		
		return du.read("src/����/Room.dat") ; 
	}

}

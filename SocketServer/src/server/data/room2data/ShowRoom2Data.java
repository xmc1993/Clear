package server.data.room2data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowRoom2Data {

	DataUtility du = new DataUtility() ;
	public ArrayList<String> show(){
		
			
			return du.read("src/Êý¾Ý/Room2.dat") ; 
	
	}

}

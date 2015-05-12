package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowOnlineFriendData {
DataUtility du = new DataUtility();
	public String find(String data ){
		return du.get("src/数据/用户.dat", data, 1) ;
	}

	public ArrayList<String> finds( String data){
		return du.read("src/数据/F"+data+".dat",1) ;
		
	}
	
	public ArrayList<String> finds(ArrayList<String> friends){
		ArrayList<String> result = new ArrayList<String>() ;
		ArrayList<String> temp = du.read("src/数据/在线列表.dat") ;
		int i = 0 ;
		for(String s : temp){
			String[] t = s.split(" ") ;
			if(t[0].equals(friends.get(i))){
				result.add(t[0]+"_"+t[1]+"_"+t[2]) ;
			}
		}
		return result ;
	}
	
	
	public static void main(String[] args){
		ShowOnlineFriendData show = new ShowOnlineFriendData() ;
		show.finds(show.finds("10000001"));
	}
}

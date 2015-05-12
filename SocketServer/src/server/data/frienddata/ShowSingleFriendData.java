package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cmd_dealer.friendcmd.ExpToLvl;
import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowSingleFriendData {
	
	public String find(String data ){
		String result = "" ;
		DataUtility du = new DataUtility();
		String line = du.getLine("src/数据/用户.dat", data, 1) ;
System.out.println(line);		
		String[] temp = line.split(" ");
		result = data+" "+ temp[1]+" "+temp[4];
		String tableName = temp[6];
		ArrayList<String> lists = du.read("src/数据/"+tableName+".dat") ;
		
		int total = 0 ;
		int gold = 0 ;
		
		int max = 0 ;
		for(String s : lists){
			String[] t = s.split(" ");
			int score = Integer.parseInt(t[2]) ;
			total = total +  Integer.parseInt(t[5]);
			gold = gold + Integer.parseInt(t[4]) ;
			if(max<score){
				max = score ;
			}
			
		}
		result = result+" "+max+" "+new ExpToLvl().trans(total)+" "+gold;
		return result ;
	}

}

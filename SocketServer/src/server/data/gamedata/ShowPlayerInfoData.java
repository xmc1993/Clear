package server.data.gamedata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cmd_dealer.friendcmd.ExpToLvl;
import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class ShowPlayerInfoData {
	
	
	DataUtility du = new DataUtility();
	public String find(String data){
		String result = "" ;
		
		ArrayList<String> lists = du.read("src/数据/用户.dat") ;
		String tableName = "" ;
		String head = "" ;
		String number = "" ;
		for(String s : lists){
			String[] temp = s.split(" ") ;
			if(data.equals(temp[1])){
				number = temp[0];
				tableName = temp[6];
				head = temp[4] ;
			}
		}
		ArrayList<String> lists2 = du.read("src/数据/"+tableName+".dat") ;
		
		int totalExp = 0 ;
		int totalGold = 0 ;
		int max = 0;
		if(lists2.size()== 0){
			
		}else{
			for(String s : lists2){
				String[] temp = s.split(" ") ;
				int score = Integer.parseInt(temp[2]) ;
				totalExp = totalExp + Integer.parseInt(temp[5]) ;
				totalGold = totalGold +Integer.parseInt(temp[4]);
				if(max<score){
					max = score ;
				}
			}
		}
		
		result = number+"_"+data+"_"+head+"_"+(new ExpToLvl().trans(totalExp))+"_"+totalGold+"_"+totalExp+"_"+max ;
		return result ;
	}
	
	public String finds(String[] name){
		String result = "" ;
	
		for(String s : name){
			if(s.equals("")){
				
			}else{
				result = result+find(s)+";";
			}
			
					
		}
		return result ;
	}

	public static void main(String[] args){
		String[] name = {""};
		System.out.println(new ShowPlayerInfoData().finds(name));
	}
	
}

package server.data.logindata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class LoginData {
	
	DataUtility du = new DataUtility();
	
	public ArrayList<String> read(){
		return du.read("src/数据/用户.dat") ;
	}
	
	public boolean find(String url , String data,int num){
		return du.find(url,data,num);
	}
	
	public String get(String data,String tableName){
		
		return du.get("src/数据/"+tableName+".dat", data, 1) ;
		
	}
	public boolean add(String data1,String data2){
		du.append("src/数据/在线列表.dat", data1+" "+data2+" "+"1");
		return true ;
	}
	//10000001 郁寒歌1 1 0 1 F10000001 D10000001
	public boolean isSuc( String name , String pw , int head){
		boolean result = true ;
		String id = "";
		String gen = "";
		String oH = "" ;
		String table1 = "";
		String table2 = "" ;
		ArrayList<String> lists = du.read("src/数据/用户.dat") ;
		for(String s : lists){
			String[] temp = s.split(" ");
			if(temp[1].equals(name)){
				if(temp[2].equals(pw)){
					id = temp[0] ;
					gen = temp[3] ;
					oH = temp[4] ;
					table1 =temp[5];
					table2 =temp[6] ;
					result = true;
					break;
				}
			}
		}
		if(result){
			du.update("src/数据/用户.dat", id+" "+name+" "+pw+" "+gen+" "+oH+" "+table1+" "+table2, 
					 id+" "+name+" "+pw+" "+gen+" "+head+" "+table1+" "+table2);
		}
		
		return result ;
		
	}

	
	public static void main(String[] args){
		
		
	}

}

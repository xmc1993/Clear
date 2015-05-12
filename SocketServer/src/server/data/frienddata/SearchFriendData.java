package server.data.frienddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class SearchFriendData {
	
	DataUtility du = new DataUtility();
	//����û����
	public String get(String data ){
		return du.get("src/����/�û�.dat", data, 1) ;
	}
	public boolean  isNotFriend(String data1,String data2){
		boolean notFriend = true ;
		ArrayList<String> lists = du.read("src/����/F"+data1+".dat") ;
		for(String s : lists){
			String[] temp = s.split(" ") ;
			if(temp[1].equals(data2)){
				if(temp[2].equals("1")){
					notFriend = false ;
					break ;
				}
			}
		}
		
		return notFriend ;
	}
	public boolean  isState1(String data){
		boolean state1 = false ;
		ArrayList<String> lists = du.read("src/����/�����б�.dat") ;
		for(String s : lists){
			String[] temp = s.split(" ") ;
			if(temp[1].equals(data)){
				if(temp[2].equals("1")){
					state1 = true ;
					break ;
				}
			}
		}
		
		return state1 ;
	}
	public boolean  isOnline(String data){
		
		return du.find("src/����/�����б�.dat",data,1) ;
		
	}
	
	public boolean isExits(String data){
		return du.find("src/����/�û�.dat",data,1) ;
	}
	
}

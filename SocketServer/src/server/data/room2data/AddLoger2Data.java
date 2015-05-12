package server.data.room2data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class AddLoger2Data {
	DataUtility du = new DataUtility();
	public String find(String s , int i , int m){
		return du.get("src/����/Room2.dat", s, i,m) ;
	}
	//i = 6 �������ҷ���i=7�����ǵз�
	public ArrayList<String> getLoger(String data,int num){
		ArrayList<String> result = new ArrayList<String>() ;
		String room = du.getLine("src/����/Room2.dat", data, 1) ;
		String temp[] = room.split(" ") ;
		String loger =temp[num-1];
		
		String[] t = loger.split("%");
		for(int i = 0 ; i<t.length ; i++){
			result.add(t[i]);
		}
		
		return result ;
	}

	public boolean update(String number, String data1, String data2,
			String roomSize1, String roomSize2 , String loger1,String loger2) {
		// TODO Auto-generated method stub
		boolean result = true;
		
		du.update("src/����/Room2.dat", du.getLine("src/����/Room2.dat", number, 1), number+" "+
		data1+" "+data2+" "+roomSize1+" "+roomSize2+" "+loger1+" "+loger2 
				);
		
		return result;
	}
}

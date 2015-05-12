package server.data.room2data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class CreateRoom2Data {

	DataUtility du = new DataUtility();
	public int getLast(){
		return du.findLast("src/数据/Room2.dat",2001);
	}
	
	public ArrayList<String> getMasters(){
		return du.read("src/数据/Room2.dat",3) ;
	}
	
	public boolean add(int data1 , String data2 , String data3 , int data4 , int data5){
		
		du.append("src/数据/Room2.dat", data1+" "+data2+" "+data3+" "+data4+" "+data5+" 空 空");
		return true ;
		
	}
	
	public static void main(String[] args){
		new CreateRoom2Data().add(20004, "hi", "郁寒歌1", 2, 2) ;
	}
	
}

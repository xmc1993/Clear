package server.data.roomdata;

import java.util.ArrayList;

import server.data.datautility.DataUtility;

public class CreateRoomData {

	DataUtility du = new DataUtility();
	public int getLast(){
		return du.findLast("src/数据/Room.dat",1001);
	}
	
	public ArrayList<String> getMasters(){
		return du.read("src/数据/Room.dat",3) ;
	}
	
	public boolean add(int data1 , String data2 , String data3 , int data4 ){
		
		du.append("src/数据/Room.dat", data1+" "+data2+" "+data3+" "+data4+" "+"空");
		return true ;
		
	}
	
	public static void main(String[] args){
		new CreateRoomData().add(1004, "hi", "郁寒歌1", 2) ;
	}
	
}

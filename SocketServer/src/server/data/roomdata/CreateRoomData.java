package server.data.roomdata;

import java.util.ArrayList;

import server.data.datautility.DataUtility;

public class CreateRoomData {

	DataUtility du = new DataUtility();
	public int getLast(){
		return du.findLast("src/����/Room.dat",1001);
	}
	
	public ArrayList<String> getMasters(){
		return du.read("src/����/Room.dat",3) ;
	}
	
	public boolean add(int data1 , String data2 , String data3 , int data4 ){
		
		du.append("src/����/Room.dat", data1+" "+data2+" "+data3+" "+data4+" "+"��");
		return true ;
		
	}
	
	public static void main(String[] args){
		new CreateRoomData().add(1004, "hi", "������1", 2) ;
	}
	
}

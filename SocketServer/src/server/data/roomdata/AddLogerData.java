package server.data.roomdata;

import java.util.ArrayList;

import server.data.datautility.DataUtility;

public class AddLogerData {
	
	DataUtility du = new DataUtility();
	public String find(String s , int i,int m){
		return du.get("src/����/Room.dat", s, i,m) ;
	}
	
	public ArrayList<String> getLoger(String data){
		ArrayList<String> result = new ArrayList<String>() ;
		String room = du.getLine("src/����/Room.dat", data, 1) ;
		String temp[] = room.split(" ") ;
		String loger =temp[4];
	
		String[] t = loger.split("%");
		for(int i = 0 ; i<t.length ; i++){
			result.add(t[i]);
		}
		return result ;
	}

	public boolean update(String number, String data1, String data2,
			String roomSize, String loger) {
		// TODO Auto-generated method stub
		boolean result = true;
		du.update("src/����/Room.dat", du.getLine("src/����/Room.dat", number, 1), number+" "+
		data1+" "+data2+" "+roomSize+" "+loger
				);
		
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(new AddLogerData().update("1001", "hi", "������1", "3", "��è��1"));
	}
	
}

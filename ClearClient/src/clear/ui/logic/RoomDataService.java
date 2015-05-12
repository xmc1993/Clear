package clear.ui.logic;

import java.util.ArrayList;

import clear.ui.network.GetAll2RoomClient;
import clear.ui.network.GetAllRoom1Client;

public class RoomDataService implements RoomDataServiceInterface{

	@Override
	public ArrayList<String> room1Info() {
		// TODO Auto-generated method stub
		GetAllRoom1Client client = new GetAllRoom1Client();
		ArrayList<String> result =new  ArrayList<String>();
		String list = client.connect() ;
		for(String s : list.split(";")){
			
			result.add(s);
		}
		return result;
	}

	@Override
	public ArrayList<String> room2Info() {
		// TODO Auto-generated method stub
		GetAll2RoomClient client = new GetAll2RoomClient();
		ArrayList<String> result =new  ArrayList<String>();
		String list = client.connect() ;
		for(String s : list.split(";")){
			
			result.add(s);
		}
		return result;
	}

}

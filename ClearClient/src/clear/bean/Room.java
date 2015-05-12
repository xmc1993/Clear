package clear.bean;

import java.util.ArrayList;

public class Room {
	
	String roomName ;//房间名
	String roomMaster;//房主名，房间建立者
	int NumOfpeople;
	ArrayList<Player> players;
	
	public Room(String roomName ,String roomMaster,int NumOfpeople,
			ArrayList<Player> players){
		this.roomName = roomName ;
		this.roomMaster = roomMaster;
		this.NumOfpeople = NumOfpeople;
		this.players = players;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(String roomMaster) {
		this.roomMaster = roomMaster;
	}

	public int getNumOfpeople() {
		return NumOfpeople;
	}

	public void setNumOfpeople(int numOfpeople) {
		NumOfpeople = numOfpeople;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
}

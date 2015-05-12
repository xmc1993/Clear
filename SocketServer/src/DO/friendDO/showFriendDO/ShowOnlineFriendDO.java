package DO.friendDO.showFriendDO;

import java.io.Serializable;

public class ShowOnlineFriendDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String number ;
	String name ;
	String state;
	
	public ShowOnlineFriendDO(String number , String name , String state){
		this.number = number ;
		this.name = name ;
		this.state = state;
		
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

}

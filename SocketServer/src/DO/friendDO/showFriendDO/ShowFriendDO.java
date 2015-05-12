package DO.friendDO.showFriendDO;

import java.io.Serializable;

public class ShowFriendDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String number ;
	String name ;
	String score;
	String gender ;
	String head ;
	
	public ShowFriendDO(String number , String name , String score ,String gender,String head){
		this.number = number ;
		this.name = name ;
		this.score = score;
		this.gender = gender ;
		this.head = head ;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getScore() {
		return score;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	
	
	
}

package DO.registerDO;

import java.io.Serializable;

import DO.fatherDO.DO;

public class RegisterDO  extends DO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String pw;
	String gender ;
	String headPic ;
	
	public RegisterDO (String name, String pw,String gender , String headPic){
		this.name = name;
		
		this.pw = pw;
		this.gender = gender ;
		this.headPic = headPic ;
	}

	public String getName() {
		return name;
	}
	public String getPw() {
		return pw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	
}

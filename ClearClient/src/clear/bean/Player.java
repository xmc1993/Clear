package clear.bean;

public class Player {
	
	//Player��Ϣ�����������룬��С��
	
	private String name;
	private String password;
	private String score;
	/*
	 * Others
	 */
	
	public Player(String name,String password,String score){
		this.name = name;
		this.password = password;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}

package clear.bean;

public class PlayerInfo {
//Player的详细信息
	
	String name ;
	String pw;
	int jushu;//局数。。。不会英语只好用拼音
	int[] score_pre_ju;//尼玛。。每局得分。。
	int avg_score;//平均得分
	int max_score;//最高得分
	int max_lianji;//最高连击数量
	
	public PlayerInfo(String name ,String pw,int jushu,
			int[] score_pre_ju,int avg_score,int max_score,int max_lianji){
		this.name = name;
		this.pw = pw;
		this.jushu = jushu;
		this.score_pre_ju = score_pre_ju ;
		this.avg_score = avg_score;
		this.max_score = max_score;
		this.max_lianji = max_lianji ;
	}

	public String getName() {
		return name;
	}

	public String getPw() {
		return pw;
	}

	public int getJushu() {
		return jushu;
	}

	public int[] getScore_pre_ju() {
		return score_pre_ju;
	}

	public int getAvg_score() {
		return avg_score;
	}

	public int getMax_score() {
		return max_score;
	}

	public int getMax_lianji() {
		return max_lianji;
	}
	
}

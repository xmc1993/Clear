package clear.bean;

public class PlayerInfo {
//Player����ϸ��Ϣ
	
	String name ;
	String pw;
	int jushu;//��������������Ӣ��ֻ����ƴ��
	int[] score_pre_ju;//���ꡣ��ÿ�ֵ÷֡���
	int avg_score;//ƽ���÷�
	int max_score;//��ߵ÷�
	int max_lianji;//�����������
	
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

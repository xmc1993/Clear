package clear.ui.data;

public class DayScores {
	String day;
	float scores;
	public DayScores(String d,float s){
		day = d;
		scores =s;
	}
	public String getDay(){
		return day;
	}
	public float getScores(){
		return scores;
	}
}

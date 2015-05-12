package clear.ui.data;

public class RoundScores {
	String roundName;
	float scores;
	public RoundScores(String name,float scores){
		roundName = name;
		this.scores = scores;
	}
	public String getName(){
		return roundName;
	}
	public float getScore(){
		return scores;
	}
}

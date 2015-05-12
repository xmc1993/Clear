package cmd_dealer.friendcmd;

public class ExpToLvl {

	int realExp;
	public int trans(int exp){
		realExp = exp/200 ;
		int delta = (int) Math.sqrt(25-2*realExp) ;
		
		return (int) (delta/10-0.5)+1;
	}
}

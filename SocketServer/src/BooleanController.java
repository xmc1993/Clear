
import DO.registerDO.RegisterDO;
import cmd_dealer.logincmd.LoginDealer;
import cmd_dealer.logincmd.LoginDealerInterface;
import cmd_dealer.quitcmd.QuitDealer;
import cmd_dealer.quitcmd.QuitDealerInterface;
import cmd_dealer.registercmd.RegisterDealer;
import cmd_dealer.registercmd.RegisterDealerInterface;

public class BooleanController {
	private LoginDealerInterface logindealer;
	private RegisterDealerInterface registerdealer;
	private QuitDealerInterface quitdealer;
	private  String commander   ;
	String result ;
	public BooleanController( String commander  ){
		this.commander = commander ;
	}
	
	public String questAnlysis(){
//System.out.println("BooleanController "+commander);
		String[] temp = commander.split(" ");
		
		if(temp[1].equals("µÇÂ¼ÇëÇó")){
			result = login(temp[2],temp[3],Integer.parseInt(temp[4]));
		}else if(temp[1].equals("×¢²áÇëÇó")){
			result = register(temp[2],temp[3],temp[4],temp[5]);
		}else if(temp[1].equals("×¢ÏúÇëÇó")){
			result = quit(temp[2])+"";
		}
		return result ;
	}
	
	public String login(String data1,String data2,int data3){
		logindealer = new LoginDealer();
		result = logindealer.check(data1,data2,data3)+"" ;
		
		return result+" "+data1 ;
	}
	public String register(String data1,String data2,String data3,String data4){
		registerdealer = new RegisterDealer();
		result = registerdealer.dealer(data1+" "+data2+" "+data3+" "+data4)+"" ;
		return result ;
	}
	public String quit(String data ){
		quitdealer = new QuitDealer();
		result = quitdealer.dealer(data)+"" ;
		return result ;
	}
}

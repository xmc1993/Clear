package cmd_dealer.logincmd;

import cmd_dealer.gamecmd.ShowPlayerInfoDealer;
import server.data.logindata.LoginData;
import ResultMessage.ResultMessage;

public class LoginDealer implements LoginDealerInterface{

	@Override
	public String check(String userName, String pw ,int head) {
		// TODO Auto-generated method stub
		String result = "" ;
		LoginData loginData = new LoginData();
		if(!loginData.find("src/数据/用户.dat",userName,2)){
			result = "DataNotExist" ;
		}else if(!loginData.isSuc(userName, pw,head)){
			result = "DataUnmatch" ;
		}else if(loginData.find("src/数据/在线列表.dat",userName,2)){
			result ="DataExist" ;
		}else{
			result = "Success";
			String line = new ShowPlayerInfoDealer().show(userName) ;
			result = result+"_"+line;
System.out.println(loginData.get(userName, "用户"));
			loginData.add(loginData.get(userName, "用户"),userName);
		}
		return result ;
	}
	
	public static void main(String[] args){
		System.out.println(new LoginDealer().check("郁寒歌1","1",0));
	}

}

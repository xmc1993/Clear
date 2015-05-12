package clear.ui.logic;

import clear.ui.network.LoginClient;

public class LoginData implements LoginServiceInterface{
	public static LoginClient client = new LoginClient() ;
	
	@Override
	public String check(String name, String password,int index ) {
		// TODO Auto-generated method stub
		
		String result = client.connect(name , password,index);
		
		return result ;
	}

}

package clear.ui.logic;

import clear.ui.network.SignInClient;

public class SignInData implements SignInInterface{

	public static void main(String[] args){
		System.out.println(new SignInData().check("”Ù∫Æ∏Ë8", "123","≈Æ","1"));
	}

	@Override
	public String check(String userName, String userPw, String userGender,
			String userHead) {
		// TODO Auto-generated method stub
		SignInClient client = new SignInClient();
		
		String result = client.connect(userName,userPw,userGender,userHead);
		
		return result ;
	}

}

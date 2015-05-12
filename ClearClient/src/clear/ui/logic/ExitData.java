package clear.ui.logic;

import clear.ui.network.ExitClient;

public class ExitData implements ExitDataInterface {

	@Override
	public String exit(String name) {
		// TODO Auto-generated method stub
		ExitClient client = new ExitClient();
		
		return client.connect(name);
	}

	public static void main(String[] args){
		System.out.println(new ExitData().exit("”Ù∫Æ∏Ë1"));
	}
	
}

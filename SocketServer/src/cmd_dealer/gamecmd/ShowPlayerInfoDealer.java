package cmd_dealer.gamecmd;

import server.data.gamedata.ShowPlayerInfoData;

public class ShowPlayerInfoDealer {
	
	public String show(String name){
		ShowPlayerInfoData data = new ShowPlayerInfoData();
		
		String[] names = name.split("_");
for(String s : names ){		
	System.out.println("name:"+s);
}
		return data.finds(names) ;
	}

}

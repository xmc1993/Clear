package clear.ui.logic;

import clear.ui.network.ShowPlayerDetailClient;

public class ShowPlayerDetailData {
	
	public String show(String[] name){
		ShowPlayerDetailClient data = new ShowPlayerDetailClient();
		String names = "" ;
		for(String s : name){
			if(s.equals("")){
				
			}else{
				names = names+s+"_";
			}
			
		}
		
		return data.connect(names) ;
		
	}
	
	public static void main(String[] args){
		
	}

}

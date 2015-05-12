package cmd_dealer.mapcmd;

public class GetMapDealer implements GetMapDealerInterface {

	@Override
	public String init(Map map) {
		// TODO Auto-generated method stub
System.out.println("Map" + map);
map.init();
		String result = "" ;
		for(int i = 0; i<9 ;i++){
			for(int j = 0 ; j<9 ;j++){
				result = result +map.Map[i][j]+" ";
			}
			result = result+";";
		}
		return result;
	}
	
}

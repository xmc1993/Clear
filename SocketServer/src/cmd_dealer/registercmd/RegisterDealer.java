package cmd_dealer.registercmd;

import java.util.ArrayList;

import server.data.registerdata.RegisterData;
import DO.registerDO.RegisterDO;
import ResultMessage.ResultMessage;

public class RegisterDealer implements RegisterDealerInterface {

	@Override
	public ResultMessage dealer(String data) {
		// TODO Auto-generated method stub
		String[] temp = data.split(" ");
		String name = temp[0] ;
		
		String pw =temp[1]  ;
		String gender = temp[2]  ;
		String headPic = temp[3]  ;
		
		RegisterData rd = new RegisterData();
		
		ResultMessage result = null ;
		
		boolean existed = false;
		
		if(rd.find(name,2)){
			result = ResultMessage.DataExist ;
			existed = true ;
		}
		if(!existed){
			int lastNum = rd.findLast()  ;
			result = ResultMessage.Success ;
			rd.add(lastNum+"", name, pw,gender,headPic);
			ArrayList<String> list1 = new ArrayList<String>();
			list1.add("好友编号") ;
			list1.add("好友昵称") ;
			list1.add("好友等级") ;
			list1.add("好友金币") ;
			list1.add("好友性别") ;
		    list1.add("好友头像");
			ArrayList<String> list2 = new ArrayList<String>();
			list2.add("ID");
			list2.add("日期") ;
			list2.add("此局得分") ;
			list2.add("连击数量") ;
			list2.add("金币") ;
			list2.add("经验") ;
			
			rd.createFile("src/数据/F"+lastNum+".dat");
			rd.createFile("src/数据/D"+lastNum+".dat");
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(new RegisterDealer().dealer(
				"郁寒歌7 868@youtobe 0 1"));
	}

}

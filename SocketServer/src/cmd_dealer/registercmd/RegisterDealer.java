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
			list1.add("���ѱ��") ;
			list1.add("�����ǳ�") ;
			list1.add("���ѵȼ�") ;
			list1.add("���ѽ��") ;
			list1.add("�����Ա�") ;
		    list1.add("����ͷ��");
			ArrayList<String> list2 = new ArrayList<String>();
			list2.add("ID");
			list2.add("����") ;
			list2.add("�˾ֵ÷�") ;
			list2.add("��������") ;
			list2.add("���") ;
			list2.add("����") ;
			
			rd.createFile("src/����/F"+lastNum+".dat");
			rd.createFile("src/����/D"+lastNum+".dat");
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(new RegisterDealer().dealer(
				"������7 868@youtobe 0 1"));
	}

}

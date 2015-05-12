package cmd_dealer.friendcmd;

import server.data.frienddata.AddFriendData;
import ResultMessage.ResultMessage;

public class AddFriendDealer implements AddFriendDealerInterface{
//���������ﶼҪ�����Ϣ��
	@Override
	//data1 Ϊ����Ӻ��ѵ��û�����data2 ΪҪ��Ӻ��ѵ��û���
	//�����ҵȼ�
	//�ȼ���Ҿ���
	public ResultMessage add(String data1, String data2,String data3,String data4,String data5,String data6,
			String data7,String data8) {
		// TODO Auto-generated method stub
		AddFriendData data = new AddFriendData();
//System.out.println("----AddFriendDealer"+data1+" "+data2);
		String number1 = data.find(data1,1) ;
		String number2 = data.find(data2,1) ;
		
//System.out.println("----AddFriendDealer"+number1+" "+number2);
		ResultMessage result = ResultMessage.Success ;
		
		//�ҵĺ��� ���Ѻ��� �����ǳ� ���ѵ÷� �����Ա� ����ͷ��
		boolean b1 = data.add(number2,number1, data1,data3,data4 ,
				data5);
		boolean b2 = data.add(number1,number2, data2,data6,data7,data8) ;
		if(b1==true&&b2==true){
			result = ResultMessage.Success  ;
		}
		return result ;
	}

	public static void main(String[] args){
		
	}
	
}
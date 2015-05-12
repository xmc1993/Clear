import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import cmd_dealer.friendcmd.*;
import cmd_dealer.gamecmd.GameDealer;
import cmd_dealer.gamecmd.GameDealerInterface;
import cmd_dealer.gamecmd.ShowPlayerInfoDealer;
import cmd_dealer.mapcmd.*;
import cmd_dealer.room2cmd.AddLoger2Dealer;
import cmd_dealer.room2cmd.AddLoger2Interface;
import cmd_dealer.room2cmd.CreateRoom2Dealer;
import cmd_dealer.room2cmd.CreateRoom2Interface;
import cmd_dealer.room2cmd.ShowLoger2Dealer;
import cmd_dealer.room2cmd.ShowLoger2Interface;
import cmd_dealer.room2cmd.ShowRoom2Dealer;
import cmd_dealer.room2cmd.ShowRoom2Interface;
import cmd_dealer.roomcmd.*;
import DO.friendDO.showFriendDO.ShowFriendDO;
import DO.friendDO.showFriendDO.ShowOnlineFriendDO;
import ResultMessage.ResultMessage;

public class IOThread extends Thread{
	
	 private ShowFriendDealerInterface showfrienddealer;
	 private SearchFriendInterface searchdealer;
	 private ShowOnlineFriendDealerInterface showonlienfrienddealer;
	 private AddFriendDealerInterface addfridealer;
	 private CreateRoomDealerInterface createroomdealer;
	 private CreateRoom2Interface createroom2dealer;
	 private AddLogerDealerInterface addlogerdealer;
	 private AddLoger2Interface addloger2dealer ;
	 private DeleteFriendDealerInterface deletefridealer ;
	 private ShowRoomDealerInterface showroomdealer;
	 private ShowRoom2Interface showroom2dealer;
	 private DeleteLogerDealerInterface deletelogerdealer ;
	 private DeleteRoomDealerInterface deleteroomdealer ;
	 private ShowLogerDealerInterface showlogerdealer ;
	 private ShowLoger2Interface showloger2dealer ;
	 private GetMapDealerInterface getmapdealer ;
	 private GameDealerInterface gamedealer ;
	 private ShowPlayerInfoDealer showplayerinfodealer ;
	 String message = "";
	 Socket s;
	 PrintWriter output;
	 BufferedReader input;
	 String result ;
	 
	 String key = "";
	 
	 //���췽��
	 public IOThread(Socket s){
		 
		 this.s=s;

		 try {
			output=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			input=new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 //����run()
	 public void run()
	 {
		try{ 
			while(true){
				 if((message=input.readLine())!=null){
					 
System.out.println(message);
					 if(message.split(" ")[0].equals("��֤")){
						 result = new BooleanController(message).questAnlysis();
System.out.println(result);
						 String[] temp = result.split(" ") ;
						 if(temp.length==2){
							 key = temp[1] ;
							 Server.list.put(key,s);//��һ�������Ӽ���Hash����

							 sendData(temp[0]);
						 }else{
							 sendData(result);
						 }

					 }else if(message.split(" ")[0].equals("����")){
						 String[] temp = message.split(" ");
						 
						 if(temp[1].equals("��ʾ��������")){
							 showfrienddealer = new ShowFriendDealer();
							 String result = showfrienddealer.show(temp[2]) ;
							 
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println(result);
							 output_this.flush();
						 }else if(temp[1].equals("��ʾ���ߺ�������")){
							 showonlienfrienddealer = new ShowOnlineFriendDealer();
							 String result = showonlienfrienddealer.show(temp[2]) ;
							 if(result == null || result.equals("")){
								 result = "��" ;
							 }
System.out.println(result);
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println("ϵͳ��Ϣ: ֻ�Ǵ��� ���ߺ��� "+result+" "+temp[3]);
							 output_this.flush();
						}else if(temp[1].equals("��ʾ���ߺ�������2")){
							 showonlienfrienddealer = new ShowOnlineFriendDealer();
							 String result = showonlienfrienddealer.show(temp[2]) ;
							 if(result == null || result.equals("")){
								 result = "��" ;
							 }
System.out.println(result);
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println("ϵͳ��Ϣ: ֻ�Ǵ��� ���ߺ��� "+result+" "+temp[3]);
							 output_this.flush();
						}else if(temp[1].equals("��ȡ�û�����")){
							gamedealer = new GameDealer();
							result = gamedealer.getData(temp[2]) ;
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
							
						}else if(temp[1].equals("�����û�����")){
							gamedealer = new GameDealer();
							boolean result = gamedealer.updateData(temp[2], temp[3]) ;
System.out.println(result);
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
						}else if(temp[1].equals("��ʾ�����û�����")){
							showplayerinfodealer = new ShowPlayerInfoDealer();
							String result = showplayerinfodealer.show(temp[3]) ;
System.out.println(result);
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
						}
					}else if(message.split(" ")[0].equals("����")){

						 String[] temp = message.split(" ");
					
						 if(temp[1].equals("������������")){
							searchdealer = new SearchFriendDealer();
							String s = searchdealer.search(temp[2], temp[3]) ;
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(s);
							output_this.flush();
							
						}else if(temp[1].equals("��Ӻ�������")){
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							//ͷ�� �ȼ� ���
							output_this.println(temp[3]+" ������Ϊ���� "+temp[4]+" "+temp[5]+" "+temp[6]);
							output_this.flush();
							Socket s_this2 = Server.list.get(temp[3]);
							PrintWriter output_this2 = new PrintWriter(s_this2.getOutputStream());
							output_this2.println("���������ѷ��ͣ���ȴ��Է�ͬ�⣡");
							output_this2.flush();
							
						}else if(temp[1].equals("��Ӻ�������")){
							//��Ӻ������������ʽ������ ��Ӻ������� ������ �û���
							//
							addfridealer = new AddFriendDealer();
							addfridealer.add(temp[2], temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9]) ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" �������Ϊ����"+" "+temp[4]+" "+temp[5]+" "+temp[6]);
							output.flush();
						}else if(temp[1].equals("�ܾ���Ӻ���")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" �ܾ������Ϊ����");
							output.flush();
						}else if(temp[1].equals("ɾ����������")){
							deletefridealer = new DeleteFriendDealer();
							deletefridealer.delete(temp[2], temp[3]) ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" �ѽ�������");
							output.flush();
						}else if(temp[1].equals("��������")){
							//�������������ʽ������ �������� ������ �û��� �����޶�
							createroomdealer = new CreateRoomDealer();
							ResultMessage result = 
									createroomdealer.create(temp[2], temp[4], 
											Integer.parseInt(temp[3]));
							sendData(result);
System.out.println(result);							
							Server.readyCount.put(temp[4], 0) ;
							
						}else if(temp[1].equals("��������2")){
							//�������������ʽ������ ��������2 ������ �û��� �����޶�1 �����޶�2
							createroom2dealer = new CreateRoom2Dealer();
							ResultMessage result = 
									createroom2dealer.create(temp[2], temp[5], 
											Integer.parseInt(temp[3]),Integer.parseInt(temp[4]));
							sendData(result);
							
							Server.readyCount2.put(temp[5], 0) ;
							Server.ready_my.put(temp[5], 0) ;
							Server.ready_other.put(temp[5], 0) ;
						}else if(temp[1].equals("���뷿������")){
							//���뷿�������ʽ������ ���뷿������ ������ ������ �û���
							Socket s = Server.list.get(temp[3]);
							
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[4]+" ����������� "+temp[2]+" ���� "+
									temp[5]+" "+temp[6]+" "+temp[7]);
							output.flush();
						}else if(temp[1].equals("���뷿������2")){
							//���뷿�������ʽ������ ���뷿������ tag ������ ������ �û���
							Socket s = Server.list.get(temp[4]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							int tag = Integer.parseInt(temp[2]) ;
							if(tag == 0){
								output.println("ϵͳ��Ϣ: "+temp[5]+" ����Э������ "+(tag+1)+" "+temp[3]+" ��������Э��");
								output.flush();
							}else{
								output.println("ϵͳ��Ϣ: "+temp[5]+" ������ս���� "+(tag+1)+" "+temp[3]+" ���� ������ս");
								output.flush();
							}
							
						}else if(temp[1].equals("������뷿��")){
							//������뷿���ʽ������ ������뷿�� ������ �û��� ������
							addlogerdealer = new AddLogerDealer() ;
							ResultMessage result = addlogerdealer.addLoger(temp[2], temp[4], temp[3]) ;
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(result == ResultMessage.Success){
								
								output.println("ϵͳ��Ϣ: "+temp[4]+" ����������TA�ķ���");
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println("���Ѿ���"+temp[4]+"�ķ��ͣ������ظ�����");
								output.flush();
							}else{
								output.println("���������������ټ���");
								output.flush();
							}
						}else if(temp[1].equals("������뷿��2")){
							//������뷿���ʽ������ ������뷿��2 tag ��λ�� ������ �û��� ������
							addloger2dealer = new AddLoger2Dealer() ;
							ResultMessage result = addloger2dealer.addLoger(Integer.parseInt(temp[2]), 
									temp[4], temp[6],temp[5]) ;
							Socket s = Server.list.get(temp[5]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(result == ResultMessage.Success){
								
								output.println("ϵͳ��Ϣ: "+temp[6]+" ����������TA�ķ��� "+temp[3]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println("���Ѿ���"+temp[6]+"�ķ��ͣ������ظ�����");
								output.flush();
							}else{
								output.println("���������������ټ���");
								output.flush();
							}
						}else if(temp[1].equals("ͬ����뷿��")){
							//ͬ����뷿���ʽ������ ͬ����뷿�� ������ ������ �ҵ�����
							addlogerdealer = new AddLogerDealer() ;
							ResultMessage result = addlogerdealer.addLoger(temp[2], temp[3], temp[4]) ;
							
							if(result == ResultMessage.Success){
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("ϵͳ��Ϣ: "+temp[4]+" ͬ��������ķ��� "+temp[5]+" "+temp[6]
										+" "+temp[7]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println(temp[4]+"�Ѿ������ķ��ͣ������ظ�����");
								output.flush();
							}else{
								output.println("���������������ټ���");
								output.flush();
							}
							
						}else if(temp[1].equals("ͬ����뷿��2")){
							//ͬ����뷿���ʽ������ ͬ����뷿��2  tag  ��λ�� ������ ������ �ҵ�����
							addloger2dealer = new AddLoger2Dealer() ;
							ResultMessage result = addloger2dealer.addLoger(Integer.parseInt(temp[2]), 
									temp[4], temp[5],temp[6]) ;
							
							if(result == ResultMessage.Success){
								Socket s = Server.list.get(temp[5]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("ϵͳ��Ϣ: "+temp[6]+" ͬ��������ķ���"+" "+temp[3]+" "+temp[7]
										+" "+temp[8]+" "+temp[9]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println(temp[6]+"�Ѿ������ķ��ͣ������ظ�����");
								output.flush();
							}else{
								output.println("���������������ټ���");
								output.flush();
							}
							
						}else if(temp[1].equals("�ܾ����뷿��")){
							//ͬ����뷿���ʽ������ ͬ����뷿�� ������ ������ �û���
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[3]+" �ܾ��������ķ���");
							output.flush();
						}else if(temp[1].equals("�ܾ����뷿��2")){
							//ͬ����뷿���ʽ������ ͬ����뷿�� ������ ������ �û���
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[3]+" �ܾ��������ķ���");
							output.flush();
						}else if(temp[1].equals("������뷿��")){
							//������뷿���ʽ������ ������뷿�� ������ �û��� ������ 
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[4]+" ������������/���� "+temp[2]+" ����");
							output.flush();
						}else if(temp[1].equals("������뷿��2")){
							//������뷿���ʽ������ ������뷿�� ������ �û��� ������ tag 
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(temp[5].equals("1")){
								output.println("ϵͳ��Ϣ: "+temp[4]+" ����Э������ "+temp[2]+" ���� "+temp[5]);
								output.flush();
							}else{
								output.println("ϵͳ��Ϣ: "+temp[4]+" ������ս���� "+temp[2]+" ���� "+temp[5]);
								output.flush();
							}
							
						}else if(temp[1].equals("��ʾ���з���")){
							showroomdealer = new ShowRoomDealer();
							String list = showroomdealer.show() ;
							Socket s = Server.list.get(temp[2]);

							PrintWriter output = new PrintWriter(s.getOutputStream());
							
							output.println(list);
							output.flush();
							
						}else if(temp[1].equals("��ʾ���з���2")){
							//������뷿���ʽ������ ������뷿�� ������ ������ �û���
							showroom2dealer = new ShowRoom2Dealer();
							String list = showroom2dealer.show() ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
						
							output.println(list);
							output.flush();
						
						}else if(temp[1].equals("������뷿��")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[3]+" �ܾ�������ta�ķ���");
							output.flush();
						}else if(temp[1].equals("������뷿��2")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[3]+" �ܾ�������ta�ķ���");
							output.flush();
						}else if(temp[1].equals("ɾ������")){
							deletelogerdealer = new DeleteLogerDealer();
							deletelogerdealer.delete(temp[2], temp[3]) ;
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("��Ϣ ���ѱ� "+temp[4]+" �߳�TA�ķ���");
							output.flush();
						}else if(temp[1].equals("�˳�����")){
							deletelogerdealer = new DeleteLogerDealer();
							deletelogerdealer.delete(temp[2], temp[4]) ;
							Socket s = Server.list.get(temp[4]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ: "+temp[3]+" �Ѿ��뿪���ķ���");
							output.flush();
						}else if(temp[1].equals("ɾ������")){
							deleteroomdealer = new DeleteRoomDealer();
							ResultMessage result = deleteroomdealer.delete(temp[2]) ;
							sendData(result);
						}else if(temp[1].equals("����")){
						
							showlogerdealer = new ShowLogerDealer();
							ArrayList<String> _temp = showlogerdealer.show(temp[3]) ;

							_temp.add(temp[3]) ;
for(String s : _temp){
	System.out.println(s);	
}
							Server.memberList.put(temp[3], _temp) ;

							int roomMember = _temp.size() ;
							int ready = Server.readyCount.get(temp[3] ) ;
							ready++;
							
							Server.readyCount.put(temp[3], ready) ;
							if(ready < roomMember){
System.out.println("ready:"+ready);
								for(String name : Server.memberList.get(temp[3]) ){
System.out.println(name);
									if(!name.equals(temp[4])){
										Socket s = Server.list.get(name);
System.out.println("����");
										PrintWriter output = new PrintWriter(s.getOutputStream());
										output.println("ϵͳ��Ϣ: "+temp[4]+" �Ѿ�׼������ "+temp[5]);
										output.flush();
									}
								}
							}else{
System.out.println("����");
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("ϵͳ��Ϣ: ����: ������Ҿ�������Ϸ���Կ�ʼ");
								output.flush();
							}
						}else if(temp[1].equals("����2")){
							
							int ready = Server.readyCount2.get(temp[3]);
							showloger2dealer = new ShowLoger2Dealer();
							ArrayList<String> list_my = showloger2dealer.show(0,temp[3]) ;
							Server.myList.put(temp[3],list_my);
							ArrayList<String> list_other = showloger2dealer.show(1,temp[3]) ;
							Server.otherList.put(temp[3],list_other);
							list_my.add(temp[3]);
							ArrayList<String> lists = new ArrayList<String>();
							for(String s : list_my){
								if(s.equals("")){
									
								}else{
									lists.add(s);
								}
								
							}
							for(String s : list_other){
								if(s.equals("")){
									
								}else{
									lists.add(s);
								}
							}
							ready++;
							Server.readyCount2.put(temp[3], ready) ;
							int roomMember = lists.size();
							if(ready>=roomMember){
								System.out.println("�������׼������");
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("ϵͳ��Ϣ: ���� :������Ҿ�������Ϸ��ʼ");
								output.flush();
							}else{
								for(String name : lists){
									if(!name.equals(temp[4])){
										Socket s = Server.list.get(name);
										PrintWriter output = new PrintWriter(s.getOutputStream());
										output.println("ϵͳ��Ϣ: "+temp[4]+" �Ѿ�׼������"+" "+temp[5]);
										output.flush();
									}
								}
							}
							
						}
					
				}else if(message.split(" ")[0].equals("����")){
					String[] temp = message.split(" ");
					ArrayList<Socket> sockets = Server.list2.get(temp[3]) ;
					
					for(Socket s : sockets){
						if(s!=Server.list.get(temp[1])){
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("ϵͳ��Ϣ "+temp[1]+" ˵: "+temp[2]);
							output.flush();
						}
						
					}
					
				}else if(message.split(" ")[0].equals("�޸�")){
						String[] temp = message.split(" ");
						key = temp[2] ; 
						Server.list.put(key, s) ;
					}else if(message.split("_")[0].equals("Map")){

						String[] temp = message.split("_");
						if(temp[1].equals("map.init")){
							Map map = new Map();
							map.setRoomMaster(temp[2]);//�趨�÷���ķ�����
							Server.maps.put(temp[2],map) ;
							
							int ready = Server.readyCount.get(temp[3]) ;
							
							if(ready==Server.memberList.get(temp[2]).size()){

								getmapdealer = new GetMapDealer();
								String result = getmapdealer.init(map) ;
								for( String name : Server.memberList.get(temp[2]) ){
									Socket s = Server.list.get(name);
									PrintWriter output = new PrintWriter(s.getOutputStream());
									output.println("Map_map.init_"+result );
									output.flush();
								}
							}else{
								sendData("�������û�п�ʼ�����Ժ�");
							}
						}else if(temp[1].equals("map.init2")){
							Map map1 = new Map();
							Server.maps.put(temp[2],map1) ;
							Map map2 = new Map();
							Server.maps.put(temp[2],map2) ;
							
							getmapdealer = new GetMapDealer();
							String result1 = getmapdealer.init(map1) ;		
							String result2 = getmapdealer.init(map2) ;
							int ready = Server.readyCount2.get(temp[3]) ;
							ArrayList<String> list_my = Server.myList.get(temp[3]) ;
							ArrayList<String> list_other = Server.otherList.get(temp[3]) ;
							if(ready==list_my.size()+list_other.size()){
								for( String name : list_my ){
									Socket s = Server.list.get(name);
									PrintWriter output = new PrintWriter(s.getOutputStream());
									output.println("Map_map.init2_"+result1);
									output.flush();
								}for( String name : list_other ){
									Socket s = Server.list.get(name);
									PrintWriter output = new PrintWriter(s.getOutputStream());
									output.println("Map_map.init2_"+result2);
									output.flush();
								}
							}else{
								sendData("�������û�п�ʼ�����Ժ�");
							}
						}else if(temp[1].equals("ˢ��")){
							
							for( String name : Server.memberList.get(temp[3]) ){
								
								Socket s = Server.list.get(name);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("ˢ��_"+temp[2]+"_"+temp[5] );
								output.flush();
								
							}
						}else if(temp[1].equals("ˢ��2")){
							String name = temp[4] ;
							ArrayList<String> list_my = Server.myList.get(temp[3]) ;
							ArrayList<String> list_other = Server.otherList.get(temp[3]) ;
							int tag = 0;
							for( String s : list_my ){
								if(s.equals(name)){
									tag = 0 ;//˵����list1�е�һ����Աˢ���˽���
									break ;
								}
							}
							for( String s : list_other ){
								if(s.equals(name)){
									tag = 1 ;//˵����list2�е�һ����Աˢ���˽���
									break ;
								}
							}
							
							if(tag == 0 ){
								//������ҷ����������ˣ���Ӧ��ͬ���ҷ������ˣ�����ͬ����ķ�����֪���жԷ�
								for( String s : list_my ){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("ˢ��0_"+temp[2]+"_"+temp[5] );
									output.flush();
									
								}
								for(String s : list_other){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("ˢ��1_"+temp[2]);
									output.flush();
								}
							}else{
								for( String s : list_my ){
									
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("ˢ��1_"+temp[2]);
									output.flush();
								
								}
								for(String s : list_other){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("ˢ��0_"+temp[2]+"_"+temp[5] );
									output.flush();
								}
							}
						}
					}
				 }
			}
	 	}catch(EOFException eof){
	 		
	 	}catch(IOException e){
	 		
	 	}catch(Exception e){
	 			
	 	} finally{
				
			  try{
				 if(input!=null)input.close();   
				 if(output!=null)output.close();   
				 if(s!=null)s.close();   
				 input=null;   
				 output=null;   
				 s=null;   
				 
			 }catch(IOException e){
				 
			 }
			 
		 }
		
	 }
	 public void sendData(String message){
		 try{
			 output.println(message);
			 output.flush();
		 }
		 catch(Exception e){
			
		 }
	 }
	 public void sendData(ResultMessage result){
		 
		 try{
			
			 message=result.toString();
			 output.println(message);
			 output.flush();
		 }catch(Exception e){
			
		 }
		 
	 }

	 public void sendData(PrintWriter output ,ArrayList<ShowFriendDO> message ){
		 String result = "" ;
		for(ShowFriendDO lineItem : message){
			
			String line = lineItem.getNumber()+" "+lineItem.getName()+" "+lineItem.getScore()
					+" "+lineItem.getGender()+" "+lineItem.getHead();
			result = result+line+";" ;
		}
		output.println(result);
		output.flush();
	 }
	 
	 public void sendData2(PrintWriter output ,ArrayList<ShowOnlineFriendDO> message ){
		 String result = "" ;
		for(ShowOnlineFriendDO lineItem : message){
			
			String line = lineItem.getNumber()+" "+lineItem.getName()+" "+lineItem.getState();
			result = result+line+";" ;
			
		}
		output.println(result);
		output.flush();
			
	 }
}

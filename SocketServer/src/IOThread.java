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
	 
	 //构造方法
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
	 //方法run()
	 public void run()
	 {
		try{ 
			while(true){
				 if((message=input.readLine())!=null){
					 
System.out.println(message);
					 if(message.split(" ")[0].equals("验证")){
						 result = new BooleanController(message).questAnlysis();
System.out.println(result);
						 String[] temp = result.split(" ") ;
						 if(temp.length==2){
							 key = temp[1] ;
							 Server.list.put(key,s);//将一个新连接加入Hash表中

							 sendData(temp[0]);
						 }else{
							 sendData(result);
						 }

					 }else if(message.split(" ")[0].equals("数据")){
						 String[] temp = message.split(" ");
						 
						 if(temp[1].equals("显示好友请求")){
							 showfrienddealer = new ShowFriendDealer();
							 String result = showfrienddealer.show(temp[2]) ;
							 
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println(result);
							 output_this.flush();
						 }else if(temp[1].equals("显示在线好友请求")){
							 showonlienfrienddealer = new ShowOnlineFriendDealer();
							 String result = showonlienfrienddealer.show(temp[2]) ;
							 if(result == null || result.equals("")){
								 result = "空" ;
							 }
System.out.println(result);
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println("系统消息: 只是凑数 在线好友 "+result+" "+temp[3]);
							 output_this.flush();
						}else if(temp[1].equals("显示在线好友请求2")){
							 showonlienfrienddealer = new ShowOnlineFriendDealer();
							 String result = showonlienfrienddealer.show(temp[2]) ;
							 if(result == null || result.equals("")){
								 result = "空" ;
							 }
System.out.println(result);
							 Socket s_this = Server.list.get(temp[2]);
							 PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							 
							 output_this.println("系统消息: 只是凑数 在线好友 "+result+" "+temp[3]);
							 output_this.flush();
						}else if(temp[1].equals("获取用户数据")){
							gamedealer = new GameDealer();
							result = gamedealer.getData(temp[2]) ;
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
							
						}else if(temp[1].equals("更新用户数据")){
							gamedealer = new GameDealer();
							boolean result = gamedealer.updateData(temp[2], temp[3]) ;
System.out.println(result);
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
						}else if(temp[1].equals("显示房间用户数据")){
							showplayerinfodealer = new ShowPlayerInfoDealer();
							String result = showplayerinfodealer.show(temp[3]) ;
System.out.println(result);
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(result);
							output_this.flush();
						}
					}else if(message.split(" ")[0].equals("操作")){

						 String[] temp = message.split(" ");
					
						 if(temp[1].equals("搜索好友请求")){
							searchdealer = new SearchFriendDealer();
							String s = searchdealer.search(temp[2], temp[3]) ;
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							output_this.println(s);
							output_this.flush();
							
						}else if(temp[1].equals("添加好友申请")){
							Socket s_this = Server.list.get(temp[2]);
							PrintWriter output_this = new PrintWriter(s_this.getOutputStream());
							//头像 等级 金币
							output_this.println(temp[3]+" 邀请您为好友 "+temp[4]+" "+temp[5]+" "+temp[6]);
							output_this.flush();
							Socket s_this2 = Server.list.get(temp[3]);
							PrintWriter output_this2 = new PrintWriter(s_this2.getOutputStream());
							output_this2.println("好友申请已发送，请等待对方同意！");
							output_this2.flush();
							
						}else if(temp[1].equals("添加好友请求")){
							//添加好友请求命令格式：操作 添加好友申请 好友名 用户名
							//
							addfridealer = new AddFriendDealer();
							addfridealer.add(temp[2], temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9]) ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" 已添加您为好友"+" "+temp[4]+" "+temp[5]+" "+temp[6]);
							output.flush();
						}else if(temp[1].equals("拒绝添加好友")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" 拒绝添加您为好友");
							output.flush();
						}else if(temp[1].equals("删除好友请求")){
							deletefridealer = new DeleteFriendDealer();
							deletefridealer.delete(temp[2], temp[3]) ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println(temp[3]+" 已将您拉黑");
							output.flush();
						}else if(temp[1].equals("创建房间")){
							//创建房间命令格式：操作 创建房间 房间名 用户名 房间限额
							createroomdealer = new CreateRoomDealer();
							ResultMessage result = 
									createroomdealer.create(temp[2], temp[4], 
											Integer.parseInt(temp[3]));
							sendData(result);
System.out.println(result);							
							Server.readyCount.put(temp[4], 0) ;
							
						}else if(temp[1].equals("创建房间2")){
							//创建房间命令格式：操作 创建房间2 房间名 用户名 房间限额1 房间限额2
							createroom2dealer = new CreateRoom2Dealer();
							ResultMessage result = 
									createroom2dealer.create(temp[2], temp[5], 
											Integer.parseInt(temp[3]),Integer.parseInt(temp[4]));
							sendData(result);
							
							Server.readyCount2.put(temp[5], 0) ;
							Server.ready_my.put(temp[5], 0) ;
							Server.ready_other.put(temp[5], 0) ;
						}else if(temp[1].equals("加入房间申请")){
							//加入房间命令格式：操作 加入房间申请 房间名 房主名 用户名
							Socket s = Server.list.get(temp[3]);
							
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[4]+" 申请加入您的 "+temp[2]+" 房间 "+
									temp[5]+" "+temp[6]+" "+temp[7]);
							output.flush();
						}else if(temp[1].equals("加入房间申请2")){
							//加入房间命令格式：操作 加入房间申请 tag 房间名 房主名 用户名
							Socket s = Server.list.get(temp[4]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							int tag = Integer.parseInt(temp[2]) ;
							if(tag == 0){
								output.println("系统消息: "+temp[5]+" 发出协作申请 "+(tag+1)+" "+temp[3]+" 房间与您协作");
								output.flush();
							}else{
								output.println("系统消息: "+temp[5]+" 发出对战申请 "+(tag+1)+" "+temp[3]+" 房间 与您对战");
								output.flush();
							}
							
						}else if(temp[1].equals("允许加入房间")){
							//允许加入房间格式：操作 允许加入房间 房间名 用户名 房主名
							addlogerdealer = new AddLogerDealer() ;
							ResultMessage result = addlogerdealer.addLoger(temp[2], temp[4], temp[3]) ;
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(result == ResultMessage.Success){
								
								output.println("系统消息: "+temp[4]+" 允许您加入TA的房间");
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println("您已经是"+temp[4]+"的房客，无需重复加入");
								output.flush();
							}else{
								output.println("房间已满，不能再加入");
								output.flush();
							}
						}else if(temp[1].equals("允许加入房间2")){
							//允许加入房间格式：操作 允许加入房间2 tag 座位号 房间名 用户名 房主名
							addloger2dealer = new AddLoger2Dealer() ;
							ResultMessage result = addloger2dealer.addLoger(Integer.parseInt(temp[2]), 
									temp[4], temp[6],temp[5]) ;
							Socket s = Server.list.get(temp[5]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(result == ResultMessage.Success){
								
								output.println("系统消息: "+temp[6]+" 允许您加入TA的房间 "+temp[3]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println("您已经是"+temp[6]+"的房客，无需重复加入");
								output.flush();
							}else{
								output.println("房间已满，不能再加入");
								output.flush();
							}
						}else if(temp[1].equals("同意加入房间")){
							//同意加入房间格式：操作 同意加入房间 房间名 房主名 我的名字
							addlogerdealer = new AddLogerDealer() ;
							ResultMessage result = addlogerdealer.addLoger(temp[2], temp[3], temp[4]) ;
							
							if(result == ResultMessage.Success){
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("系统消息: "+temp[4]+" 同意加入您的房间 "+temp[5]+" "+temp[6]
										+" "+temp[7]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println(temp[4]+"已经是您的房客，不可重复加入");
								output.flush();
							}else{
								output.println("房间已满，不能再加入");
								output.flush();
							}
							
						}else if(temp[1].equals("同意加入房间2")){
							//同意加入房间格式：操作 同意加入房间2  tag  座位号 房间名 房主名 我的名字
							addloger2dealer = new AddLoger2Dealer() ;
							ResultMessage result = addloger2dealer.addLoger(Integer.parseInt(temp[2]), 
									temp[4], temp[5],temp[6]) ;
							
							if(result == ResultMessage.Success){
								Socket s = Server.list.get(temp[5]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("系统消息: "+temp[6]+" 同意加入您的房间"+" "+temp[3]+" "+temp[7]
										+" "+temp[8]+" "+temp[9]);
								output.flush();
								
							}else if(result == ResultMessage.DataExist){
								output.println(temp[6]+"已经是您的房客，不可重复加入");
								output.flush();
							}else{
								output.println("房间已满，不能再加入");
								output.flush();
							}
							
						}else if(temp[1].equals("拒绝加入房间")){
							//同意加入房间格式：操作 同意加入房间 房间名 房主名 用户名
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[3]+" 拒绝加入您的房间");
							output.flush();
						}else if(temp[1].equals("拒绝加入房间2")){
							//同意加入房间格式：操作 同意加入房间 房间名 房主名 用户名
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[3]+" 拒绝加入您的房间");
							output.flush();
						}else if(temp[1].equals("邀请加入房间")){
							//邀请加入房间格式：操作 邀请加入房间 房间名 用户名 房主名 
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[4]+" 邀请您加入他/她的 "+temp[2]+" 房间");
							output.flush();
						}else if(temp[1].equals("邀请加入房间2")){
							//邀请加入房间格式：操作 邀请加入房间 房间名 用户名 房主名 tag 
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							if(temp[5].equals("1")){
								output.println("系统消息: "+temp[4]+" 发出协作邀请 "+temp[2]+" 房间 "+temp[5]);
								output.flush();
							}else{
								output.println("系统消息: "+temp[4]+" 发出对战邀请 "+temp[2]+" 房间 "+temp[5]);
								output.flush();
							}
							
						}else if(temp[1].equals("显示所有房间")){
							showroomdealer = new ShowRoomDealer();
							String list = showroomdealer.show() ;
							Socket s = Server.list.get(temp[2]);

							PrintWriter output = new PrintWriter(s.getOutputStream());
							
							output.println(list);
							output.flush();
							
						}else if(temp[1].equals("显示所有房间2")){
							//邀请加入房间格式：操作 邀请加入房间 房间名 房主名 用户名
							showroom2dealer = new ShowRoom2Dealer();
							String list = showroom2dealer.show() ;
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
						
							output.println(list);
							output.flush();
						
						}else if(temp[1].equals("不许加入房间")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[3]+" 拒绝您加入ta的房间");
							output.flush();
						}else if(temp[1].equals("不许加入房间2")){
							
							Socket s = Server.list.get(temp[2]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[3]+" 拒绝您加入ta的房间");
							output.flush();
						}else if(temp[1].equals("删除房客")){
							deletelogerdealer = new DeleteLogerDealer();
							deletelogerdealer.delete(temp[2], temp[3]) ;
							Socket s = Server.list.get(temp[3]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("消息 您已被 "+temp[4]+" 踢出TA的房间");
							output.flush();
						}else if(temp[1].equals("退出房间")){
							deletelogerdealer = new DeleteLogerDealer();
							deletelogerdealer.delete(temp[2], temp[4]) ;
							Socket s = Server.list.get(temp[4]);
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息: "+temp[3]+" 已经离开您的房间");
							output.flush();
						}else if(temp[1].equals("删除房间")){
							deleteroomdealer = new DeleteRoomDealer();
							ResultMessage result = deleteroomdealer.delete(temp[2]) ;
							sendData(result);
						}else if(temp[1].equals("就绪")){
						
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
System.out.println("发送");
										PrintWriter output = new PrintWriter(s.getOutputStream());
										output.println("系统消息: "+temp[4]+" 已经准备就绪 "+temp[5]);
										output.flush();
									}
								}
							}else{
System.out.println("发送");
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("系统消息: 房主: 所有玩家就绪，游戏可以开始");
								output.flush();
							}
						}else if(temp[1].equals("就绪2")){
							
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
								System.out.println("所有玩家准备就绪");
								Socket s = Server.list.get(temp[3]);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("系统消息: 房主 :所有玩家就绪，游戏开始");
								output.flush();
							}else{
								for(String name : lists){
									if(!name.equals(temp[4])){
										Socket s = Server.list.get(name);
										PrintWriter output = new PrintWriter(s.getOutputStream());
										output.println("系统消息: "+temp[4]+" 已经准备就绪"+" "+temp[5]);
										output.flush();
									}
								}
							}
							
						}
					
				}else if(message.split(" ")[0].equals("聊天")){
					String[] temp = message.split(" ");
					ArrayList<Socket> sockets = Server.list2.get(temp[3]) ;
					
					for(Socket s : sockets){
						if(s!=Server.list.get(temp[1])){
							PrintWriter output = new PrintWriter(s.getOutputStream());
							output.println("系统消息 "+temp[1]+" 说: "+temp[2]);
							output.flush();
						}
						
					}
					
				}else if(message.split(" ")[0].equals("修改")){
						String[] temp = message.split(" ");
						key = temp[2] ; 
						Server.list.put(key, s) ;
					}else if(message.split("_")[0].equals("Map")){

						String[] temp = message.split("_");
						if(temp[1].equals("map.init")){
							Map map = new Map();
							map.setRoomMaster(temp[2]);//设定该房间的房主名
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
								sendData("还有玩家没有开始，请稍后！");
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
								sendData("还有玩家没有开始，请稍后！");
							}
						}else if(temp[1].equals("刷新")){
							
							for( String name : Server.memberList.get(temp[3]) ){
								
								Socket s = Server.list.get(name);
								PrintWriter output = new PrintWriter(s.getOutputStream());
								output.println("刷新_"+temp[2]+"_"+temp[5] );
								output.flush();
								
							}
						}else if(temp[1].equals("刷新2")){
							String name = temp[4] ;
							ArrayList<String> list_my = Server.myList.get(temp[3]) ;
							ArrayList<String> list_other = Server.otherList.get(temp[3]) ;
							int tag = 0;
							for( String s : list_my ){
								if(s.equals(name)){
									tag = 0 ;//说明是list1中的一个成员刷新了界面
									break ;
								}
							}
							for( String s : list_other ){
								if(s.equals(name)){
									tag = 1 ;//说明是list2中的一个成员刷新了界面
									break ;
								}
							}
							
							if(tag == 0 ){
								//如果是我方有人消除了，则应该同步我方所有人，并将同步后的分数告知所有对方
								for( String s : list_my ){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("刷新0_"+temp[2]+"_"+temp[5] );
									output.flush();
									
								}
								for(String s : list_other){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("刷新1_"+temp[2]);
									output.flush();
								}
							}else{
								for( String s : list_my ){
									
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("刷新1_"+temp[2]);
									output.flush();
								
								}
								for(String s : list_other){
									Socket _s = Server.list.get(s);
									PrintWriter output = new PrintWriter(_s.getOutputStream());
									output.println("刷新0_"+temp[2]+"_"+temp[5] );
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

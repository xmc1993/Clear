
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import cmd_dealer.mapcmd.Map;
import cmd_dealer.room2cmd.ShowRoom2Dealer;
import cmd_dealer.room2cmd.ShowRoom2Interface;
import cmd_dealer.roomcmd.ShowRoomDealer;
import cmd_dealer.roomcmd.ShowRoomDealerInterface;
import ResultMessage.ResultMessage;

public class Server {
	//用一个Hash表将socket与一个键值关联起来
	
	static Hashtable<String,Socket> list = new Hashtable<String , Socket>();
	static Hashtable<String,ArrayList<Socket>> list2 = new Hashtable<String , ArrayList<Socket>>();
	static Hashtable<String,Map> maps = new Hashtable<String ,Map>();
	String key = "";
	
	static Hashtable<String,ArrayList<String>> memberList = 
			new Hashtable<String,ArrayList<String>>();
	
	static Hashtable<String,ArrayList<String>> myList = 
			new Hashtable<String,ArrayList<String>>();
	
	static Hashtable<String,ArrayList<String>> otherList = 
			new Hashtable<String,ArrayList<String>>();
	 
	 static Hashtable<String,Integer> readyCount = new Hashtable<String,Integer>() ;
	 static Hashtable<String,Integer> readyCount2 = new Hashtable<String,Integer>() ;
	 static Hashtable<String,Integer> ready_my = new Hashtable<String,Integer>() ;
	 static Hashtable<String,Integer> ready_other = new Hashtable<String,Integer>() ;
		
	ServerSocket server;
	Socket socket ;
	ResultMessage result ;
	
	public Server(){
		connect() ;
	}
	public void connect(){
		
		 ShowRoomDealerInterface showroomdealer = new ShowRoomDealer();
		 ShowRoom2Interface showroom2dealer = new ShowRoom2Dealer();
		 String room1_info = showroomdealer.show();
		 ArrayList<String> rooms = new ArrayList<String>();
		 for(String s : room1_info.split(";")){

			 rooms.add(s);
		 }
		 ArrayList<String> room2s = new ArrayList<String>();
		 String room2_info = showroom2dealer.show();
		 for(String s : room2_info.split(";")){

			 room2s.add(s);
		 }
		 if(rooms.size()<=1){
			 //readyCount.put(arg0, arg1)
		 }else{
			 for(String s : rooms){
				 readyCount.put(s.split(" ")[2], 0) ;
			 
			 }
		 }
		 if(room2s.size()<=1){
			 
		 }else{
			 for(String s : room2s){
					readyCount2.put(s.split(" ")[2],0);
					ready_my.put(s.split(" ")[2],0);
					ready_other.put(s.split(" ")[2],0);
				}
		 }
		
		 try{
			 //创建ServerSocket对象，指定端口为8999
			 server=new ServerSocket(10000);
			 System.out.println("服务器端已连接！");
			 while(true){
				 System.out.println("等待客户端请求......");
				 
				 socket=server.accept();
				 System.out.println("\n连接"+"来自"+
				 socket.getInetAddress().getHostName());
				 System.out.println("主机IP地址为："+
				 socket.getInetAddress().getHostAddress());
				 //创建IOThread类对象
				 IOThread iot=new IOThread(socket);
				 //启动线程
 				 iot.start();
 				 
			}
				
		 }catch(Exception e){
				 e.printStackTrace();
		 }finally{
			 try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	public void close(){
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Server();
	}

}

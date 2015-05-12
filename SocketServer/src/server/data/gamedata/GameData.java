package server.data.gamedata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;

public class GameData {

	DataUtility du = new DataUtility() ;
	public String find(String data){
		return du.get("src/数据/用户.dat", data, 1) ;
	}
	//总局数
	public int total(String data){
		ArrayList<String> lists = du.read("src/数据/D"+data+".dat") ;
		return lists.size();
	}
	//每日局数 返回格式：日期(空格)局数
	public ArrayList<String> getGamePerDay(String data){
		ArrayList<String> temp = du.read("src/数据/D"+data+".dat",2) ;
		
		ArrayList<String> result = new ArrayList<String>() ;
		
		if(temp.size()==0){
			result.add("0 0");
		}else{
		
			ArrayList<String> result1 = new ArrayList<String>();
			for(String s : temp ){
				String date = s.split(" ")[0];
				int count = 0;
				boolean once = false ;
				for(String t : temp ){
					String date2 = t.split(" ")[0];
					if(date.equals(date2)){
						once = true ;
						count ++ ;
					}
				}
				if(once&&!result.contains(date)){
					result.add(date);
					result1.add(count+"") ;
				}
			}
			for(int i = 0 ; i<result.size();i++){
				result.set(i, result.get(i)+" "+result1.get(i)) ;
			}
		}
		return result ;
	}
	//每局得分
	public ArrayList<String> getGameScore(String data1){
			ArrayList<String> temp1 = du.read("src/数据/D"+data1+".dat",2) ;
		ArrayList<String> temp2 = du.read("src/数据/D"+data1+".dat",2) ;
		ArrayList<String> result = new ArrayList<String>() ;
		
		if(result.size()==0){
			result.add("0 0");
		}else{
			for(int i = 0 ; i<temp1.size(); i++){
				result.add(temp1.get(i)+" "+temp2.get(i));
			}
		}
		return result ;
	}
	
	//平均得分
	public double getAverge(String data){
		
		ArrayList<String> temp = du.read("src/数据/D"+data+".dat",3) ;
		double result = 0 ;
		int sum = 0 ;
		int count = 0 ;
		for(String s : temp){
			sum = sum + Integer.parseInt(s);
			count ++ ;
		}
		
		if(count == 0){
			result = 0;
		}else {
			result = sum/count ;
		}
		return result ;
	}
	
	//每日平均得分
	public ArrayList<String> getAvePerDay(String data){
		ArrayList<String> temp = du.read("src/数据/D"+data+".dat") ;
		ArrayList<String> result = new ArrayList<String>();
		if(temp.size() == 0){
			result.add("0 0");
		}else{
			ArrayList<String> result1 = new ArrayList<String>();
			for(String s : temp ){

				String date = s.split(" ")[1];
				int count = 0;
				int sum = 0;
				boolean once = false ;
				for(String t : temp ){
					String date2 = t.split(" ")[1];
						
					if(date.equals(date2)){
						once = true ;
						count ++ ;
						sum = sum+Integer.parseInt(t.split(" ")[2]);
					}
				}
				if(once&&!result.contains(date)){
					result.add(date);
					result1.add(sum/count+"") ;
				}
			}
			if(result.size() == 1){
				result.set(0, result.get(0)+" "+result1.get(0)) ;
			}else{
				for(int i = 0 ; i<result.size();i++){

					result.set(i, result.get(i)+" "+result1.get(i)) ;
				}
			}
			
		}
		
		return result ;
	}
	
	//最高连击数量
	public int maxLJ(String data){
		ArrayList<String> temp = du.read("src/数据/D"+data+".dat",4) ;
		
		int max = 0;
		if(temp.size() == 0){
			max = 0 ;
		}else{
			max = Integer.parseInt(temp.get(0)) ;
			for(int i = 0 ; i<temp.size() ;i++){
				if(max<Integer.parseInt(temp.get(i))){
					max = Integer.parseInt(temp.get(i));
				}
			}
		}
		
		return max ;
	}
	
	//最高得分
	public int maxScore(String data){
		ArrayList<String> temp = du.read("src/数据/D"+data+".dat",3) ;
		
		int max = 0;
		if(temp.size() == 0){
			max = 0 ;
		}else{
			max = Integer.parseInt(temp.get(0)) ;
			for(int i = 0 ; i<temp.size() ;i++){
				if(max<Integer.parseInt(temp.get(i))){
					max = Integer.parseInt(temp.get(i));
				}
			}
		}
		
		return max ;
	}
	//金币数
	public int getGold(String data){
ArrayList<String> temp = du.read("src/数据/D"+data+".dat",5) ;
		
		int total = 0;
		if(temp.size() == 0){
			total = 0 ;
		}else{
			total = Integer.parseInt(temp.get(0)) ;
			for(int i = 0 ; i<temp.size() ;i++){
				total = total + Integer.parseInt(temp.get(i)) ;
			}
		}
		
		return total ;
	}
	
	//经验值
	public int getExp(String data){
ArrayList<String> temp = du.read("src/数据/D"+data+".dat",6) ;
		
		int total = 0;
		if(temp.size() == 0){
			total = 0 ;
		}else{
			total = Integer.parseInt(temp.get(0)) ;
			for(int i = 0 ; i<temp.size() ;i++){
				total = total + Integer.parseInt(temp.get(i)) ;
			}
		}
		
		return total ;
	}
	
	public int findLast(String data){
		return du.findLast("src/数据/D"+data+".dat", 1001) ;
	}

	public boolean insert(String data,int i,String data2,String data3
			,String data4,String data5,String data6){
		du.append("src/数据/D"+data+".dat", i+" "+data2+" "+data3+" "+data4+" "+data5+" "+
				data6);
		return true ;
	}
	
	public static void main(String[] args){
		/*ArrayList<String> result = (new GameData().getAvePerDay(new GameData().find("郁寒歌1")));
		for(String s : result){
			System.out.println(s) ;
		}*/
		GameData data = new GameData();
		System.out.println(data.insert(data.find("郁寒歌1"),data.findLast(data.find("郁寒歌1")), "2014/5/23", "100", "5", "12", "13")) ;
	}
		
}
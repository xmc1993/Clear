package server.data.datautility;

import java.util.ArrayList;
import java.io.*;
public class DataUtility {
	String url ; 
	
	public DataUtility(){
		
	}
	
	public ArrayList<String> read(String url){
		ArrayList<String> context = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader(new File(url)));
			
			String line = "";
			
			try {
				while((line = br.readLine())!=null){
					context.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return context ;
	}
	public boolean find(String url , String cdt , int num){
		boolean result = false ;
		ArrayList<String> lists = read(url) ;
		for(String s : lists){
			if(s == null || s.equals("")){
				
			}else{
				String[] temp = s.split(" ") ;
				if(cdt.equals(temp[num-1])){
					result=  true ;
					break ;
				}
			}
			
		}
		
		return result ;
	}
	public String get(String url , String cdt , int num){
		String result = ""  ;
		ArrayList<String> lists = read(url) ;
		for(String s : lists){
			if(s == null || s.equals("")){
				
			}else{
				String[] temp = s.split(" ") ;
				if(cdt.equals(temp[num])){
					result=  temp[num-1] ;
					break ;
				}
			}
			
		}
		
		return result ;
	}
	public String get(String url , String cdt , int num, int num2){
		String result = ""  ;
		ArrayList<String> lists = read(url) ;
		for(String s : lists){
			if(s == null || s.equals("")){
				
			}else{
				String[] temp = s.split(" ") ;
				if(cdt.equals(temp[num-1])){
					result=  temp[num2-1] ;
					break ;
				}
			}
			
		}
		
		return result ;
	}
	public String getLine(String url , String cdt , int num){
		String result = ""  ;
		ArrayList<String> lists = read(url) ;
		for(String s : lists){
			if(s == null || s.equals("")){
				
			}else{
				String[] temp = s.split(" ") ;
				if(cdt.equals(temp[num-1])){
					result= s ;
					break ;
				}
			}
			
		}
		
		return result ;
	}
	public int findLast(String url , int beginNum){
		int num = beginNum ;
		try {
			BufferedReader br = new BufferedReader( new FileReader(new File(url)));
			
			String line = "";
			
			try {
				while((line = br.readLine())!=null){
					num ++ ;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	public ArrayList<String> read(String url , int num){
		ArrayList<String> context = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader(new File(url)));
			
			String line = "";
			
			try {
				while((line = br.readLine())!=null){
					String[] temp = line.split(" ") ;
					context.add(temp[num-1]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return context ;
	}
	
	
	
	/**
	 * <p>写入并覆盖: 用于一次多行记录</p>
	 * @param context
	 */
	public void write(String url , ArrayList<String> context){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
			String out = "";
			for(String s : context){
				out = out+s+"\r\n" ;
			}
			bw.write(out);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * <p>写入并覆盖 : 用于一次一行记录</p>
	 * @param context
	 */
	public void write(String url ,String context){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
			
			bw.write(context);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void append(String url ,String line){
		ArrayList<String> lines = read(url);
		String out = "" ;
		for(String s : lines){
			out = out + s + "\r\n" ;
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
			out = out+line +"\r\n";
			bw.write(out);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void append(String url ,ArrayList<String> lines){
		ArrayList<String> readin = read(url);
		String out = "" ;
		for(String s : readin){
			out = out + s + "\r\n" ;
		}
		for(String s : lines){
			out = out + s + "\r\n" ;
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
			bw.write(out);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(String url , String oldOne , String newOne){

		ArrayList<String> lists = read(url) ;
		ArrayList<String> newList = new ArrayList<String> ();
		for(String s : lists){
			if(s.equals(oldOne)){
				newList.add(newOne);
			}else{
				newList.add(s);
			}
		}
		for(String s:newList){
			System.out.println(s);
		}
		write(url , newList) ;
	}

	public static void main(String[] args){
		DataUtility u = new DataUtility();
		ArrayList<String> lines = new ArrayList<String> ();
		lines.add("1");
		lines.add("2");
		u.append("D:\\我的程序\\进存销服务器端\\src\\数据\\进货管理数据\\进货单.txt",lines);
		
	}
	
}

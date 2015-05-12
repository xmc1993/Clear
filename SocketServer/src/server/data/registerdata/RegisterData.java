package server.data.registerdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.data.datautility.DBUtility;
import server.data.datautility.DataUtility;
import java.io.*;
public class RegisterData {
	DBUtility connector = new DBUtility();
	DataUtility du = new DataUtility();
	public boolean find( String data , int num){
		boolean result = false ;
		ArrayList<String> lists = du.read("src/数据/用户.dat",num) ;
		for(String s : lists){
			if(data.equals(s)){
				result = true ;
				break ;
			}
		}
		
		return result ;
		
	}
	
	public int findLast(){
		
		return du.findLast("src/数据/用户.dat", 10000001) ;
	}

	public boolean add( String number , String name , String pw,String gender , String headPic){
		boolean result = false ;
		String fT = "F"+number ;
		String dT = "D"+number ;
		du.append("src/数据/用户.dat",number+" "+name+" "+pw+" "+gender+" "+headPic+" "+fT+" "+dT);
		return true ;
	}
	
	public boolean createFile(String destFileName) {
		File file = new File(destFileName);
	    if(file.exists()) {
	        System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
	        return false;
	    }
	    if (destFileName.endsWith(File.separator)) {
	        System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
	        return false;
	    }
	    //判断目标文件所在的目录是否存在
	    if(!file.getParentFile().exists()) {
	        //如果目标文件所在的目录不存在，则创建父目录
	        System.out.println("目标文件所在目录不存在，准备创建它！");
	        if(!file.getParentFile().mkdirs()) {
	            System.out.println("创建目标文件所在目录失败！");
	            return false;
	        }
	    }
	    //创建目标文件
	    try {
	        if (file.createNewFile()) {
	            System.out.println("创建单个文件" + destFileName + "成功！");
	            return true;
	        } else {
	            System.out.println("创建单个文件" + destFileName + "失败！");
	            return false;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
	        return false;
	    }
	 }
	

	
}

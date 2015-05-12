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
		ArrayList<String> lists = du.read("src/����/�û�.dat",num) ;
		for(String s : lists){
			if(data.equals(s)){
				result = true ;
				break ;
			}
		}
		
		return result ;
		
	}
	
	public int findLast(){
		
		return du.findLast("src/����/�û�.dat", 10000001) ;
	}

	public boolean add( String number , String name , String pw,String gender , String headPic){
		boolean result = false ;
		String fT = "F"+number ;
		String dT = "D"+number ;
		du.append("src/����/�û�.dat",number+" "+name+" "+pw+" "+gender+" "+headPic+" "+fT+" "+dT);
		return true ;
	}
	
	public boolean createFile(String destFileName) {
		File file = new File(destFileName);
	    if(file.exists()) {
	        System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");
	        return false;
	    }
	    if (destFileName.endsWith(File.separator)) {
	        System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");
	        return false;
	    }
	    //�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����
	    if(!file.getParentFile().exists()) {
	        //���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼
	        System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");
	        if(!file.getParentFile().mkdirs()) {
	            System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");
	            return false;
	        }
	    }
	    //����Ŀ���ļ�
	    try {
	        if (file.createNewFile()) {
	            System.out.println("���������ļ�" + destFileName + "�ɹ���");
	            return true;
	        } else {
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�");
	            return false;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�" + e.getMessage());
	        return false;
	    }
	 }
	

	
}

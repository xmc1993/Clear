package server.data.datautility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {
	Connection con;
	Statement state;
	ResultSet rel;
	
	public void connect(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+e);
		}
		//String result = null;
		try {
			con = DriverManager.getConnection("jdbc:odbc:ÌìÌì°®Ïû³ý","","");
			state = con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+e);
		}
	}
	public ResultSet query(String sqlQuery){
		try {
			rel = state.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+e);
		}
		return rel;
	}
	
	public boolean execute(String sqlQuery){
		boolean isSuc = true;
		try {
			isSuc =  state.execute(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void close(){
		try {
			con.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+e);
		}
		
	}
}

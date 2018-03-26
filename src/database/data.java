package database;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.*;
import java.io.*;
import java.util.*;


import java.text.*;


public class data {
	static String driverName = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String userPasswd = "";
	static String dbName = "sdu";
	static String tableName = "sdu_user";
	static Connection conn= null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static String str;
	public  static void connect(){
		try{
		String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=" + userName + "&password=" + userPasswd;
		System.out.println(url);
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = (Connection) DriverManager.getConnection(url);
		stmt=(Statement) conn.createStatement();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public  static void connect(String url) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = (Connection) DriverManager.getConnection(url);
		stmt=(Statement) conn.createStatement();
	}
	public static int operate(int n,String name ,String pass ) throws SQLException{		
		if(n==1){
		try{
		str="select * from "+tableName+" where User_name='"+name+"'"+" and Password='"+pass+"'";
		rs=(ResultSet) stmt.executeQuery(str);
		if(rs.next()){
			return Integer.parseInt(rs.getString(4));
		}else{
			return -1;
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			rs.close();
		}
		}else if(n==2){
			try {
				str="insert into sdu_user (User_name,Password,Score) values(\""+name+"\",\""+pass+"\",0)";
//				System.out.println(str);
				stmt.execute(str);
				return 0;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
	public static int operate(String table,String line,String name) throws SQLException{
		try{
		str="select * from "+table+" where +"+line+"='"+name+"'";
		rs=(ResultSet) stmt.executeQuery(str);
		if(rs.next()){
			return Integer.parseInt(rs.getString(4));
		}else{
			return -1;
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}finally {
			rs.close();

			
		}
		return -1;
	}
	public static void cls(){
		try {
			stmt.close(); 
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int insdata(String question,String tip){
		try {
			str="insert into qa_table (question,tip_chara,tip_size) values(\""+question.trim()+"\",\""+tip+"\",\""+Integer.parseInt(""+question.trim().length())+"\")";
//			System.out.println(str);
			stmt.execute(str);
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	public static int deldata(String table,String line,String name) {
		str="delete from "+table+" where "+line+" = \""+name+"\"";
//		System.out.println(str);
		try {
			stmt.execute(str);
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	static ArrayList<String> qa = new ArrayList<>();
	public static String getQA(){
		try {
//			str = "SELECT *FROM `qa_table` AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM `qa_table`)-(SELECT MIN(id) FROM `qa_table`))+(SELECT MIN(id) FROM `qa_table`)) AS id) AS t2 WHERE t1.id >= t2.id ORDER BY t1.id  ;";
			str = "SELECT * FROM qa_table ORDER BY rand() ;";
			rs=(ResultSet) stmt.executeQuery(str);
			rs.first();
			while(rs.next()){
				String qas = rs.getString(2);
				String tips = rs.getString(3);
				String num = rs.getString(4);
				String s = qas+" "+tips+" "+num;
				if(!qa.contains(s)){
					qa.add(s);
					return s;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ÌâÄ¿Î´ÕÒµ½£¡";
	}
	
	public static int updateScore(String table,String name,int sco) throws SQLException{
		try{
		str="UPDATE  "+table+" SET Score = \'"+sco+"\' WHERE User_name = "+name;
		stmt.execute(str);
		return 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

		
}

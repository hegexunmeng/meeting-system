package com.meeting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 获得数据库链接,以及关闭
 * 连接方法getConnection()
 * 关闭方法closeConnection()
 * @author Administrator
 *注:这种方法 是不合理的,面对并发的时候会出现很多问题,无法同时使用
 */


public class ConnectionFactory {
	//声明了静态属性
	private static Connection conn=null;
	
	/**
	 * 返回一个唯一的数据库连接对象
	 * @return
	 */
	public static Connection getConnection(){
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获得链接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting?useUnicode=true&characterEncoding=utf8", "root", "123456");
			System.out.println("连接成功!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 关闭数据库连接对象的方法
	 */
	public static void closeConnection(){
		//只要conn不是空的,就可以关掉
		if(conn!=null){
		try {
			conn.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//定义一个测试的main方法
	public static void main(String[] args) {
		ConnectionFactory.getConnection();
	}
}

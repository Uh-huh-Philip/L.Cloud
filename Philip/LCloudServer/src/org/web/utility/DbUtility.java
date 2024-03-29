package org.web.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {
	
	private Connection connection;
	
	public DbUtility(){
		
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败");
		}
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/app_database", "root", "");
			System.out.println("连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接失败");
		}
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	
	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

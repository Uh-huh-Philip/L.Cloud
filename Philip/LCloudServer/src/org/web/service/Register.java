package org.web.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.web.utility.DbUtility;



public class Register {
	DbUtility database = new DbUtility();
	
	public void register(String imei, String username){
		
		database.connect();
		PreparedStatement statement = null;
		
		try {
			if (imeicompare(imei)) {
				// 更新用户信息
				try {
					statement = database
							.getConnection()
							.prepareStatement(
									"update userlist set username = ?, lasttime = ? where imei = ?");
				} catch (SQLException e) {
					System.out.println("写入数据库错误");
				}

				try {
					statement.setString(1, username);
					statement.setString(2, null);
					statement.setString(3, imei);
					statement.executeUpdate();

				} catch (SQLException e) {
					System.out.println("写入数据库错误");
				}
			} else {
				//将新用户注册到数据库
				try {
					statement = database
							.getConnection()
							.prepareStatement(
									"insert into userlist (userid, username, imei, firsttime) values(?, ?, ?, ?)");
				} catch (SQLException e) {
					System.out.println("写入数据库错误");
				}

				try {
					int id = (int) (Math.random() * 1000);
					statement.setInt(1, id);
					statement.setString(2, username);
					statement.setString(3, imei);
					statement.setString(6, null);
					statement.executeUpdate();

				} catch (SQLException e) {
					System.out.println("写入数据库错误");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断数据库内是否已经存在该IMEI
	 */
	private boolean imeicompare(String imei) throws SQLException {
		// 跟数据库数据进行比对
		Statement statement = database.getConnection().createStatement();
		ResultSet resultSet = statement
				.executeQuery("select * from userlist where imei ='" + imei
						+ "'");
		if (resultSet.next())
			return true; // 当数据库中已存在对应imei的纪录时返回真值
		else
			return false;
	}
}

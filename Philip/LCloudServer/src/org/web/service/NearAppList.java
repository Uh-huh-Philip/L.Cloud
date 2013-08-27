package org.web.service;

import org.json.*;

import java.sql.*;

public class NearAppList {
	public static String getNearAppList(){
		String imei;
		String latitude;
		String longitude;
		String radius;
		JSONArray jsonArray = new JSONArray();
		
		
		// 测试数据库
		DbUtility database = new DbUtility();
		database.connect();
		Statement statement;
		ResultSet resultSet = null;
		try {
			statement = database.connection.createStatement();
			resultSet = statement
					.executeQuery("select appname, packagename, icon, description from applist");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("查询失败");
		}
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("appname", resultSet.getString(1));
					jsonObject.put("packagename", resultSet.getString(2));
					jsonObject.put("icon", resultSet.getString(3));
					jsonObject.put("description", resultSet.getString(4));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				jsonArray.put(jsonObject);
			}
			database.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray.toString();
	}
}

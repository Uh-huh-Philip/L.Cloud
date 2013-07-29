package com.example.android_client;

import java.util.List;

import org.json.JSONArray;

import net.sf.json.JSONObject;

public class JSonFormat {

	private List<String> applist;
	private double[] GPS = new double[3];
	private double[] localdata = new double[10];
	
	public static Object getObject(String string){
		JSONObject jobject = JSONObject.fromObject(string);
		Object result = JSONObject.toBean(jobject, JSonFormat.class);
		return result;
	}
	
	public static String jsonToString(JSONObject jobject){
		JSONObject json = jobject;
		JSONArray array = new JSONArray();
		array.put(json);
		String jsonStr = array.toString();
		return jsonStr;
	}
}

package com.example.android_client;

import java.io.*;
import java.net.*;
import android.util.Log;

public class ClientPart {

  private String host = "www.baidu.com";
	private int port = 80;
	private int timeout = 10000;// 设置10秒之后即认为是超时

	public String receiveFromServer() {
		try {
			Socket socket = new Socket(host, port);
			socket.setSoTimeout(timeout);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String result = br.readLine();// need more code
			br.close();
			socket.close();
			return result;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			Log.e("UnknownHost", "来自服务器的数据");
			e.printStackTrace();
			return "Error";
		} catch (IOException e) {
			Log.e("IOException", "来自服务器的数据");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
	}

	public String sendToServer(String data) {
		try {
			// 创建socket对象，指定服务器端地址和端口号
			Socket socket = new Socket(host, port);
			socket.setSoTimeout(timeout);
			// 获取 Client 端的输出流
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			// 填充信息
			out.println(data);
			// 关闭
			socket.close();
			return "Ok";
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return "Error";
		} catch (IOException e1) {
			e1.printStackTrace();
			return "Error";
		}
	}
}

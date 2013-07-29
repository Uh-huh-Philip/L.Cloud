package com.example.android_client;

import java.io.*;
import java.net.*;

public class ServerPart implements Runnable {

  private static int listenport = 30000;

	public static void main(String[] args) throws IOException {
		Thread ServerThread = new Thread(new ServerPart());
		ServerThread.start();
	}

	public void run() {
		// TODO Auto-generated method stub
		// 创建一个ServerSocket,用于监听客户端socket的连接请求
		int count = 0;
		ServerSocket ss;
		try {

			System.out.println("Server Start!");
			ss = new ServerSocket(listenport);

			// 采用循环不断接受来自客户端的请求,服务器端也对应产生一个Socket
			while (true) {
				Socket s = ss.accept();
				System.out.println("Number " + (++count) + "client");
				try {
					// 接收客户端发送的请求
					BufferedReader receiveData = new BufferedReader(
							new InputStreamReader(s.getInputStream()));
					int item = 1;
					// actually item is decided by receiveData to aim at what
					// data need to be sent.
					String sendData = null;
					switch (item) {
					case 1:
						sendData = "case 1 data";
						break;
					case 2:
						sendData = "case 2 data";
						break;
					case 3:
						sendData = "case 3 data";
						break;
					case 4:
						sendData = "case 4 data";
						break;
					}
					OutputStream os = s.getOutputStream();
					os.write(sendData.getBytes("utf-8"));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					s.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errors happen");
		}
	}
}

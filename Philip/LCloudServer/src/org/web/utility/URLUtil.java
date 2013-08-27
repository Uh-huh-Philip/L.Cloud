package org.web.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.*;

public class URLUtil {

	public static String getHtml(String urlString) throws IOException {
		try {
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				html.append(temp).append("\n");
			}
			br.close();
			isr.close();
			conn.disconnect();

			return html.toString();
		} catch (Exception e) {
			try {
				StringBuffer html = new StringBuffer();
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStreamReader isr = new InputStreamReader(
						conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String temp;
				while ((temp = br.readLine()) != null) {
					html.append(temp).append("\n");
				}
				br.close();
				isr.close();
				conn.disconnect();

				return html.toString();
			} catch (Exception e1) {

				StringBuffer html = new StringBuffer();
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStreamReader isr = new InputStreamReader(
						conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String temp;
				while ((temp = br.readLine()) != null) {
					html.append(temp).append("\n");
				}
				br.close();
				isr.close();
				conn.disconnect();

				return html.toString();

			}
		}
	}

	public static void main(String[] args) {
		boolean tag = true;
		while (tag) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入包名：");
			String command = scanner.nextLine().toString();
			if (command.equalsIgnoreCase("exit"))
				tag = false;
			else {
				String UrlString = "https://play.google.com/store/apps/details?id="
						+ command + "&hl=zh";
				System.out.println(UrlString);

				String result = null;
				try {
					result = URLUtil.getHtml(UrlString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("网络错误404");
				}
				// System.out.println(result);

				if (result != null) {
					Matcher m = null;

					String regexd = "<div [^>]*class=\"app-orig-desc\"[^>]*>.*?</div>";

					m = Pattern.compile(regexd).matcher(result);

					System.out.println("应用简介");

					while (m.find()) {
						System.out.println(m.group());
					}

					String regexi = "<div [^>]*class=\"cover-container\"[^>]*>.*?>";

					m = Pattern.compile(regexi).matcher(result);

					System.out.println("应用图标");

					while (m.find()) {
						System.out.println(m.group());
						System.out.println(m.group().substring(
								m.group().indexOf("http"),
								m.group().indexOf("alt") - 2));
					}
				} else
					System.out.println("Finished with error");
			}
		}
	}
}
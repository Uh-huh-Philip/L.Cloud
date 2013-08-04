package com.example.android_client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AppList extends Activity {
	
	private ListView listView;
	private PackageManager pm;

	public List<PackageInfo> getAppList() {
		PackageManager packageManager = getPackageManager();
		final List<PackageInfo> packageInfos = packageManager
				.getInstalledPackages(0);
		List<PackageInfo> local_pkgInfoNoSys = new ArrayList<PackageInfo>();
		for (int i = 0; i < packageInfos.size(); i++) {
			PackageInfo packageInfo = packageInfos.get(i);
			// 获取 非系统的应用
			if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
				local_pkgInfoNoSys.add(packageInfo);
			}
			// 本来是系统程序，被用户手动更新后，该系统程序也成为第三方应用程序了
			else if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				local_pkgInfoNoSys.add(packageInfo);
			}
		}
		return local_pkgInfoNoSys;
	}

	public void getRunningAppList(Context context) {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE); // 获得ActivityManager对象
			List<RunningAppProcessInfo> runningTasks = am
					.getRunningAppProcesses(); // 获得所有正在进行的程序存放在一个list中
			for (int i = 0; i < runningTasks.size(); i++) {
				PackageInfo2 pInfo = new PackageInfo2(context);// 获得PackageInfo对象
				// get application which is not in system and the usually
				// 如果是非系统应用程序以及一些常用的应用程序就加到list中
				if ((pInfo.getInfo(runningTasks.get(i).processName).flags & pInfo
						.getInfo(runningTasks.get(i).processName).FLAG_SYSTEM) == 0
						|| (runningTasks.get(i).processName)
								.equals("com.android.contacts")
						|| (runningTasks.get(i).processName)
								.equals("com.android.email")
						|| (runningTasks.get(i).processName)
								.equals("com.android.settings")
						|| (runningTasks.get(i).processName)
								.equals("com.android.music")
						|| (runningTasks.get(i).processName)
								.equals("com.android.calendar")
						|| (runningTasks.get(i).processName)
								.equals("com.android.calculator2")
						|| (runningTasks.get(i).processName)
								.equals("com.android.browser")
						|| (runningTasks.get(i).processName)
								.equals("com.android.camera")
						|| (runningTasks.get(i).processName)
								.equals("com.cooliris.media")
						|| (runningTasks.get(i).processName)
								.equals("com.android.bluetooth")
						|| (runningTasks.get(i).processName)
								.equals("com.android.mms")) {
					String dir = pInfo.getInfo(runningTasks.get(i).processName).publicSourceDir;
					Float size = Float
							.valueOf((float) ((new File(dir).length() * 1.0)));// 获得应用程序的大小如果size大于一M就用M为单位，否则用KB
					// long date = new Date(new File(dir).lastModified()).getTime();
					// System.out.println(pInfo.getInfo(runningTasks.get(i).processName).loadIcon(pm));
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("icon",
							pInfo.getInfo(runningTasks.get(i).processName)
									.loadIcon(pm));
					map.put("name",
							pInfo.getInfo(runningTasks.get(i).processName)
									.loadLabel(pm));
					if (size > 1024 * 1024)
						map.put("info", size / 1024 / 1024 + " MB");
					else
						map.put("info", size / 1024 + " KB");
					map.put("packagename",
							runningTasks.get(i).processName.toString());// 获得包名给后面用
					list.add(map);
				}
			}
		} catch (Exception ex) {}
		 //SimpleAdapter listadapter=new SimpleAdapter(this, list, R.layout.task_list, new String[]{"icon","name","info"}, new int []{R.id.icon,R.id.name,R.id.info});
		 //listView.setAdapter(listadapter);//listview加载识别器
	}
}

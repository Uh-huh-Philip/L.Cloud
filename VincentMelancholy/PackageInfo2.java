package com.example.android_client;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

class PackageInfo2 {

  private List<ApplicationInfo> appList;

	public PackageInfo2(Context context) {
		// get all package data
		PackageManager pm = context.getApplicationContext().getPackageManager();
		appList = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
	}

	public ApplicationInfo getInfo(String name) {
		if (name == null) {
			return null;
		}
		for (ApplicationInfo appInfo : appList) {
			if (name.equals(appInfo.processName)) {
				return appInfo;
			}
		}
		return null;
	}
}

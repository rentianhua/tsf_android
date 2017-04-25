package com.dumu.housego.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityManager {
	public static List<Activity> activityList = new ArrayList<Activity>();

	public static void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	public static void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public static void finishApplication() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}

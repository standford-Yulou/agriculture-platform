package com.emamp.emampapp;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * @ClassName: DreamApplication
 * @Description: 全局Application.
 * @date Sep 6, 2015 12:47:47 PM
 */
public class EmampApplication extends Application {
	private static EmampApplication mInstance;
	private Activity activity = null;
	private final List<Activity> activityList = new LinkedList<Activity>();
	private Activity currentRunningActivity = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static EmampApplication getInstance() {
		if (null == mInstance) {
			synchronized (EmampApplication.class) {
				if (null == mInstance) {
					mInstance = new EmampApplication();
				}
			}
		}
		return mInstance;
	}

	public void addActivity(Activity activity) {
		if (activityList != null && activityList.contains(activity)) {
			return;
		}
		activityList.add(activity);
	}

	public void exit() {
		for (Activity activity : activityList) {
			if (activity != null) {
				if (!activity.isFinishing()) {
					activity.finish();
				}
				activity = null;
			}
			activityList.remove(activity);
		}
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getCurrentRunningActivity() {
		return currentRunningActivity;
	}

	public void setCurrentRunningActivity(Activity currentRunningActivity) {
		this.currentRunningActivity = currentRunningActivity;
	}

}

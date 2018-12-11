package com.kotlin.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.kotlin.base.common.BaseApplication;

public class UIUtils {

	/**得到上下文*/
	public static Context getContext() {
		return BaseApplication.getContext();
	}

	/**得到Resouce对象*/
	public static Resources getResource() {
		return getContext().getResources();
	}

	/**得到String.xml中的字符串*/
	public static String getString(int resId) {
		return getResource().getString(resId);
	}

	/**得到String.xml中的字符串数组*/
	public static String[] getStringArr(int resId) {
		return getResource().getStringArray(resId);
	}

	/**得到colors.xml中的颜色*/
	public static int getColor(int colorId) {
		return getResource().getColor(colorId);
	}

	/**得到应用程序的包名*/
	public static String getPackageName() {
		return getContext().getPackageName();
	}

	/**得到主线程id*/
	public static long getMainThreadId() {
		return BaseApplication.getMainTreadId();
	}

	/**得到主线程Handler*/
	public static Handler getMainThreadHandler() {
		return BaseApplication.getHandler();
	}
	
	/**安全的执行一个任务*/
	public static void postTaskSafely(Runnable task) {
		int curThreadId = android.os.Process.myTid();

		if (curThreadId == getMainThreadId()) { // 如果当前线程是主线程
			task.run();
		} else { // 如果当前线程不是主线程
			getMainThreadHandler().post(task);
		}
	}

	// 两次点击按钮之间的点击间隔不能少于1000毫秒
	private static final int MIN_CLICK_DELAY_TIME = 1000;
	private static long lastClickTime;

	public static boolean isNormalClick() {
		boolean flag = false;
		long curClickTime = System.currentTimeMillis();
		if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
			flag = true;
		}
		lastClickTime = curClickTime;
		return flag;
	}
	
}

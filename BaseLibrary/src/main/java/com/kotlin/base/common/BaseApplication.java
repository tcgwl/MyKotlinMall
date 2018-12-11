package com.kotlin.base.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application {

    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainTreadId;
    private static Looper mMainThreadLooper;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        // 主线程Id
        mMainTreadId = android.os.Process.myTid();
        mMainThreadLooper = getMainLooper();
        mHandler = new Handler();
        super.onCreate();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainTreadId() {
        return mMainTreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Handler getHandler() {
        return mHandler;
    }
}

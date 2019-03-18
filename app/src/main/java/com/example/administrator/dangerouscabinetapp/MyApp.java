package com.example.administrator.dangerouscabinetapp;

import android.app.Application;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 15:41
 * Description:
 */
public class MyApp extends Application {
    public volatile static MyApp myApp;

    public static synchronized MyApp getInstance() {
        if (myApp == null) {
            synchronized (MyApp.class) {
                myApp = new MyApp();
            }
        }
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}

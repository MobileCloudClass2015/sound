package com.sound.app.common;

import android.app.Application;
import android.util.Log;

/**
 * Created by slhyv_000 on 2015-03-03.
 * Global context object. This is start point of application. *
 */
public class App extends Application {
    public static Application app_context = null;

    public static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "================Start of application=============");
        app_context = this;
    }
}

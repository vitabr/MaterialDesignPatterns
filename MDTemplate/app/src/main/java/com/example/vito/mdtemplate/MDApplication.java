package com.example.vito.mdtemplate;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vito on 10.09.2015.
 */
public class MDApplication extends Application{
    private static Context mAppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getAppContext();
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}

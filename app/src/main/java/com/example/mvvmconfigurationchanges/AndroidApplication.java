package com.example.mvvmconfigurationchanges;

import android.app.Application;
import android.content.Context;

import com.example.mvvmconfigurationchanges.di.application.ApplicationComponent;
import com.example.mvvmconfigurationchanges.utils.viewmodel.DaggerUtils;

public class AndroidApplication extends Application {

    private static Context context;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidApplication.context = getApplicationContext();

        this.applicationComponent = DaggerUtils.getDaggerApplicationComponent(this);

    }

    public static Context getAndroidApplicationContext() {
        return AndroidApplication.context;
    }

    public static ApplicationComponent applicationComponent(Context context) {
        return ((AndroidApplication) context.getApplicationContext()).applicationComponent;
    }
}

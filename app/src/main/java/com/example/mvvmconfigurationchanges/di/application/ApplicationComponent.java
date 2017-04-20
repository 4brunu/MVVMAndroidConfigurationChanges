package com.example.mvvmconfigurationchanges.di.application;

import android.app.Application;
import android.content.Context;

import dagger.Component;

@Component(
        modules = {
                ApplicationModule.class
        }
)
public interface ApplicationComponent {

    Application getApplication();

    Context getContext();

}

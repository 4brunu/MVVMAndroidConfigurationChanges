package com.example.mvvmconfigurationchanges.utils.viewmodel;

import android.app.Application;
import android.os.Bundle;

import com.example.mvvmconfigurationchanges.di.application.ApplicationComponent;
import com.example.mvvmconfigurationchanges.di.application.ApplicationModule;
import com.example.mvvmconfigurationchanges.di.application.DaggerApplicationComponent;
import com.example.mvvmconfigurationchanges.di.model.DaggerModelComponent;
import com.example.mvvmconfigurationchanges.di.model.ModelComponent;
import com.example.mvvmconfigurationchanges.di.viewmodel.DaggerViewModelComponent;
import com.example.mvvmconfigurationchanges.di.viewmodel.ViewModelComponent;
import com.example.mvvmconfigurationchanges.di.viewmodel.ViewModelModule;

public class DaggerUtils {

    public static ApplicationComponent getDaggerApplicationComponent(Application application) {

        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();

    }

    public static ModelComponent getModelComponent(Application application) {

        return DaggerModelComponent.builder()
                .applicationComponent(getDaggerApplicationComponent(application))
                .build();

    }

    public static ViewModelComponent getViewModelComponent(Application application, Bundle savedInstanceState) {

        return DaggerViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(getModelComponent(application), savedInstanceState))
                .build();


    }

}

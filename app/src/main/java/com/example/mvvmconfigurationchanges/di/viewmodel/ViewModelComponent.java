package com.example.mvvmconfigurationchanges.di.viewmodel;

import com.example.mvvmconfigurationchanges.MainActivity;
import com.example.mvvmconfigurationchanges.ViewModelMain;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import dagger.Component;

@Component(
        modules = ViewModelModule.class
)
public interface ViewModelComponent {

    void inject(MainActivity mainActivity);

    ViewModelManager getViewModelManager();

    ViewModelMain getViewModelMain();

}

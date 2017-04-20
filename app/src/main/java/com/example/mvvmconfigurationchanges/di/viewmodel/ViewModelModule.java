package com.example.mvvmconfigurationchanges.di.viewmodel;

import android.os.Bundle;

import com.example.mvvmconfigurationchanges.ModelMain;
import com.example.mvvmconfigurationchanges.ViewModelMain;
import com.example.mvvmconfigurationchanges.di.model.ModelComponent;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    private ModelComponent modelComponent;
    private Bundle savedInstanceState;

    public ViewModelModule(ModelComponent modelComponent, Bundle savedInstanceState) {
        this.modelComponent = modelComponent;
        this.savedInstanceState = savedInstanceState;
    }

    @Provides
    ViewModelManager provideViewModelManager() {
        return ViewModelManager.getInstance();
    }

    @Provides
    ViewModelMain provideViewModelMain(ViewModelManager viewModelManager) {

        ViewModelMain viewModelMainMenu = (ViewModelMain) viewModelManager.restoreViewModel(savedInstanceState);

        if (viewModelMainMenu == null) {

            ModelMain modelMain = modelComponent.getModelMain();

            viewModelMainMenu = new ViewModelMain(modelMain, viewModelManager);
        }

        return viewModelMainMenu;

    }

}

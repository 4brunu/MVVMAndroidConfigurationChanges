package com.example.mvvmconfigurationchanges.di.model;

import com.example.mvvmconfigurationchanges.ModelMain;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    public ModelModule() {
    }

    @Provides
    ModelMain provideModelMain() {

        return new ModelMain();

    }

}

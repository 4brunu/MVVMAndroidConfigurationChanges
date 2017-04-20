package com.example.mvvmconfigurationchanges.di.model;

import com.example.mvvmconfigurationchanges.ModelMain;
import com.example.mvvmconfigurationchanges.di.application.ApplicationComponent;

import dagger.Component;

@Component(
        dependencies = ApplicationComponent.class,
        modules = ModelModule.class
)
public interface ModelComponent {

    ModelMain getModelMain();

}

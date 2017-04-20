package com.example.mvvmconfigurationchanges.di.application;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return this.application;
    }
    
    @Provides
    Context provideApplicationContext() {
        return this.application;
    }

}

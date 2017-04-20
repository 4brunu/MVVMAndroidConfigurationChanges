package com.example.mvvmconfigurationchanges.utils.viewmodel;

import android.os.Bundle;
import android.support.v4.util.LruCache;

import java.util.concurrent.atomic.AtomicLong;

public class ViewModelManager {
    //Inspired here
    //https://github.com/remind101/android-arch-sample/blob/master/app/src/main/java/com/remind101/archexample/PresenterManager.java
    private static final String SIS_KEY_VIEWMODEL_ID = "viewmodel_id";
    private static ViewModelManager instance;

    private final AtomicLong currentId;

    private final LruCache<Long, ViewModel> viewModelCache;

    ViewModelManager(int maxSize) {
        currentId = new AtomicLong();

        viewModelCache = new LruCache<>(maxSize);

    }

    public static ViewModelManager getInstance() {
        if (instance == null) {
            instance = new ViewModelManager(50);
        }
        return instance;
    }

    public ViewModel restoreViewModel(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Long keyId = savedInstanceState.getLong(SIS_KEY_VIEWMODEL_ID);
            ViewModel viewModel = viewModelCache.get(keyId);
            viewModelCache.remove(keyId);
            return viewModel;
        }
        else {
            return null;
        }
    }

    public void saveViewModel(ViewModel viewModel, Bundle outState) {
        Long keyId = viewModel.getKeyId();
        if (keyId == null) {
            keyId = currentId.incrementAndGet();
            viewModel.setKeyId(keyId);
        }
        viewModelCache.put(keyId, viewModel);
        outState.putLong(SIS_KEY_VIEWMODEL_ID, keyId);
    }

    public void removeViewModel(ViewModel viewModel) {
        Long keyId = viewModel.getKeyId();
        if (keyId != null) {
            viewModelCache.remove(keyId);
        }
    }


}
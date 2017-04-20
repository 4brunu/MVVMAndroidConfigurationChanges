package com.example.mvvmconfigurationchanges.base;

import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModel;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import io.reactivex.disposables.CompositeDisposable;

public abstract class ViewModelBase implements ViewModel {

    protected abstract CompositeDisposable getCompositeDisposable();

    protected abstract ViewModelManager getViewModelManager();

    private Long keyId;

    @Override
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    @Override
    public Long getKeyId() {
        return keyId;
    }

    @Override
    public void onDestroy() {

        CompositeDisposable compositeDisposable = getCompositeDisposable();

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        ViewModelManager viewModelManager = getViewModelManager();

        if (viewModelManager != null) {
            viewModelManager.removeViewModel(this);
        }

    }

}

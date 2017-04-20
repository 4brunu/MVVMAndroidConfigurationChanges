package com.example.mvvmconfigurationchanges.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModel;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import io.reactivex.disposables.CompositeDisposable;

public abstract class FragmentBase extends Fragment {

    protected abstract ViewModel getViewModel();

    protected abstract CompositeDisposable getCompositeDisposable();

    protected abstract ViewModelManager getViewModelManager();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ViewModel viewModel = getViewModel();

        ViewModelManager viewModelManager = getViewModelManager();

        if (viewModel != null && viewModelManager != null) {
            viewModelManager.saveViewModel(getViewModel(), outState);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        CompositeDisposable compositeDisposable = getCompositeDisposable();

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (isRemoving()) {
            ViewModel viewModel = getViewModel();
            if (viewModel != null) {
                viewModel.onDestroy();
            }
        }
    }

}

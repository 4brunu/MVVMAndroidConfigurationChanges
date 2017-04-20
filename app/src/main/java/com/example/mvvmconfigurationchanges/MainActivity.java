package com.example.mvvmconfigurationchanges;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmconfigurationchanges.base.ActivityBase;
import com.example.mvvmconfigurationchanges.databinding.ActivityMainBinding;
import com.example.mvvmconfigurationchanges.utils.viewmodel.DaggerUtils;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModel;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends ActivityBase {

    @Inject
    ViewModelManager viewModelManager;

    @Inject
    ViewModelMain viewModelMain;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ActivityMainBinding binding;

    @Override
    protected ViewModel getViewModel() {
        return viewModelMain;
    }

    @Override
    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected ViewModelManager getViewModelManager() {
        return viewModelManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DaggerUtils.getViewModelComponent(getApplication(), savedInstanceState).inject(this);

        initUI();

        bindViewModel();

    }

    private void initUI() {

        binding.buttonGetListOfData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.getListOfData();
            }
        });

        binding.buttonSendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.sendDataToServer();
            }
        });

    }

    private void bindViewModel() {

        compositeDisposable.add(
                viewModelMain.showProgressBar
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean showProgressBar) throws Exception {
                                binding.progressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
                            }
                        })
        );

        compositeDisposable.add(
                viewModelMain.showErrorListOfData
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean showErrorListOfData) throws Exception {

                                if (showErrorListOfData) {

                                    new AlertDialog
                                            .Builder(MainActivity.this)
                                            .setMessage(R.string.error_get_list_of_data)
                                            .setCancelable(false)
                                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    viewModelMain.showErrorListOfData.onNext(false);
                                                }
                                            })
                                            .show();

                                }

                            }
                        })
        );

        compositeDisposable.add(
                viewModelMain.showResultListOfData
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ArrayList<String>>() {
                            @Override
                            public void accept(@NonNull ArrayList<String> strings) throws Exception {

                                String text = "";

                                for (String str : strings) {
                                    text += str;
                                }

                                binding.textViewGetListOfData.setText(text);
                            }
                        })
        );

        compositeDisposable.add(
                viewModelMain.showErrorSendDataToServer
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean showErrorSendDataToServer) throws Exception {

                                if (showErrorSendDataToServer) {

                                    new AlertDialog
                                            .Builder(MainActivity.this)
                                            .setMessage(R.string.error_send_data_to_server)
                                            .setCancelable(false)
                                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    viewModelMain.showErrorSendDataToServer.onNext(false);
                                                }
                                            })
                                            .show();

                                }

                            }
                        })
        );

        compositeDisposable.add(
                viewModelMain.showResultSendDataToServer
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                binding.textViewSendDataToServer.setText(s);

                            }
                        })
        );

    }

}

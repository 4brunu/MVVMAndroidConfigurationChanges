package com.example.mvvmconfigurationchanges;

import com.example.mvvmconfigurationchanges.base.ViewModelBase;
import com.example.mvvmconfigurationchanges.utils.viewmodel.ViewModelManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class ViewModelMain extends ViewModelBase {

    private ModelMain modelMain;
    private ViewModelManager viewModelManager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected ViewModelManager getViewModelManager() {
        return viewModelManager;
    }

    public ViewModelMain(ModelMain modelMain, ViewModelManager viewModelManager) {
        this.modelMain = modelMain;
        this.viewModelManager = viewModelManager;
    }

    public BehaviorSubject<Boolean> showProgressBar = BehaviorSubject.createDefault(false);

    public BehaviorSubject<Boolean> showErrorListOfData = BehaviorSubject.createDefault(false);

    public BehaviorSubject<ArrayList<String>> showResultListOfData = BehaviorSubject.createDefault(new ArrayList<String>());

    public void getListOfData() {

        compositeDisposable.add(
                Observable.defer(new Callable<ObservableSource<ArrayList<String>>>() {
                    @Override
                    public ObservableSource<ArrayList<String>> call() throws Exception {

                        showProgressBar.onNext(true);

                        final AsyncSubject<ArrayList<String>> async = AsyncSubject.create();

                        modelMain.getListOfData(new ModelMain.ListOfDataCallback() {
                            @Override
                            public void onResult(ArrayList<String> strings) {
                                async.onNext(strings);
                                async.onComplete();
                            }
                        });

                        return async;



                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<String>>() {

                    @Override
                    public void onNext(ArrayList<String> strings) {

                        showProgressBar.onNext(false);
                        showResultListOfData.onNext(strings);

                    }

                    @Override
                    public void onError(Throwable e) {

                        showProgressBar.onNext(false);
                        showErrorListOfData.onNext(true);

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );

    }

    public BehaviorSubject<Boolean> showErrorSendDataToServer = BehaviorSubject.createDefault(false);

    public BehaviorSubject<String> showResultSendDataToServer = BehaviorSubject.createDefault("");

    public void sendDataToServer() {

        compositeDisposable.add(
                Observable.defer(new Callable<ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> call() throws Exception {

                        showProgressBar.onNext(true);

                        if (new Random().nextBoolean()) {

                            throw new Exception();

                        }


                        final AsyncSubject<String> async = AsyncSubject.create();

                        modelMain.sendDataToServer(new ModelMain.SendDataToServerCallback() {
                            @Override
                            public void onResult(String serverResponse) {
                                async.onNext(serverResponse);
                                async.onComplete();
                            }
                        });


                        return async;



                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<String>() {

                            @Override
                            public void onNext(String serverResponse) {

                                showProgressBar.onNext(false);
                                showResultSendDataToServer.onNext(serverResponse);

                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar.onNext(false);
                                showErrorSendDataToServer.onNext(true);

                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );

    }

}

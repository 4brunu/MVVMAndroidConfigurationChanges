package com.example.mvvmconfigurationchanges.utils.viewmodel;

public interface ViewModel {

    void setKeyId(Long keyId);
    Long getKeyId();

    void onDestroy();

}

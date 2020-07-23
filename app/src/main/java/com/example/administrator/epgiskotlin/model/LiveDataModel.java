package com.example.administrator.epgiskotlin.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.epgiskotlin.bean.DownloadInfo;

/**
 * Created by liu on 2020/7/23.
 * <p>
 * activity , fragment 销毁保存重建保存数据大的情况
 */

public class LiveDataModel extends ViewModel {
    public LiveData<DownloadInfo> liveData;

    public LiveDataModel() {
        super();
        liveData = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

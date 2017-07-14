package com.example.devskywalker.mvpwithdagger2sample.app.main;

import android.app.Application;

import com.example.devskywalker.mvpwithdagger2sample.api.ApiConfig;
import com.example.devskywalker.mvpwithdagger2sample.api.IApiService;

import javax.inject.Inject;

/**
 * Created by admin on 14/7/17.
 */

public class MainPresenter implements IMainContract.Presenter {

    private IMainContract.View mMainView;
    private Application mApplication;
    private IApiService mApiService;
    private ApiConfig mApiConfig;

    @Inject
    MainPresenter(IMainContract.View mMainView, Application application, IApiService apiService, ApiConfig apiConfig) {
        this.mMainView = mMainView;
        mApplication = application;
        mApiService = apiService;
        mApiConfig = apiConfig;
    }

}

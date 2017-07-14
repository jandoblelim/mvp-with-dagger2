package com.example.devskywalker.mvpwithdagger2sample.app;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.devskywalker.mvpwithdagger2sample.api.ApiConfig;
import com.example.devskywalker.mvpwithdagger2sample.api.IApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 14/7/17.
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface IAppComponent {

    Application application();

    IApiService apiService();

    ApiConfig apiConfig();

    void inject(App app);

}
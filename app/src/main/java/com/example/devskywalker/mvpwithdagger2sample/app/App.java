package com.example.devskywalker.mvpwithdagger2sample.app;

import android.app.Application;

import com.example.devskywalker.mvpwithdagger2sample.api.ApiConfig;
import com.example.devskywalker.mvpwithdagger2sample.api.ApiModule;
import com.example.devskywalker.mvpwithdagger2sample.api.IApiAuthenticator;
import com.example.devskywalker.mvpwithdagger2sample.database.realm.RealmSampleRepository;

import javax.inject.Inject;

/**
 * Created by admin on 14/7/17.
 */

public class App extends Application implements IApiAuthenticator {

    private IAppComponent mAppComponent;


    @Inject
    AppConfig mAppConfig;

    @Inject
    ApiConfig mApiConfig;


    //entry point to application
    @Override
    public void onCreate() {
        super.onCreate();

        //let's initialize the appComponent
        initializeAppComponent();

        //let's intialize the realm repositories
        initRealmRepository();
    }

    private void initRealmRepository() {
        RealmSampleRepository.initialize(this);
    }

    private void initializeAppComponent() {
        mAppComponent = null;
        mAppComponent =
                DaggerIAppComponent.builder()
                        .appModule(new AppModule(this))
                        .apiModule(new ApiModule(this))
                        .build();
        mAppComponent.inject(this);
    }

    public IAppComponent getAppComponent() {
        return mAppComponent;
    }

    public static IAppComponent getAppComponent(Application application) {
        return ((App) application).getAppComponent();
    }

    @Override
    public int refreshToken() {
        return -1;
    }

    @Override
    public void forceLogout() {
        // do force logout cleanup here
    }
}

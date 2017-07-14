package com.example.devskywalker.mvpwithdagger2sample.app;

import android.app.Application;

import com.example.devskywalker.mvpwithdagger2sample.database.realm.RealmSampleRepository;

/**
 * Created by admin on 14/7/17.
 */

public class App extends Application {

    private IAppComponent mAppComponent;
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
                        .build();
        mAppComponent.inject(this);
    }

    public IAppComponent getAppComponent() {
        return mAppComponent;
    }

    public static IAppComponent getAppComponent(Application application) {
        return ((App) application).getAppComponent();
    }

}

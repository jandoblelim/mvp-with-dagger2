package com.example.devskywalker.mvpwithdagger2sample.app;

import android.app.Application;

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
        intializeAppComponent();
    }

    private void intializeAppComponent() {
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

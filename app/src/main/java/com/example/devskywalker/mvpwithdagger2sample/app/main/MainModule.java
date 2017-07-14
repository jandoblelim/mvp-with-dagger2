package com.example.devskywalker.mvpwithdagger2sample.app.main;

import com.example.devskywalker.mvpwithdagger2sample.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 14/7/17.
 */

@Module
public class MainModule {

    private final IMainContract.View mMainView;

    MainModule(IMainContract.View mMainView) { this.mMainView = mMainView; }

    @Provides
    @ActivityScope
    IMainContract.View provideMainView() { return mMainView; }
}
package com.example.devskywalker.mvpwithdagger2sample.app.main;

import com.example.devskywalker.mvpwithdagger2sample.app.ActivityScope;
import com.example.devskywalker.mvpwithdagger2sample.app.IAppComponent;

import dagger.Component;

/**
 * Created by admin on 14/7/17.
 */

@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = MainModule.class
)
public interface IMainComponent {

    void inject(MainActivity mainActivity);

}

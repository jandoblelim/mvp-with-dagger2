package com.example.devskywalker.mvpwithdagger2sample.app.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.devskywalker.mvpwithdagger2sample.R;
import com.example.devskywalker.mvpwithdagger2sample.app.App;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainContract.View {


    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerIMainComponent.builder()
                .iAppComponent(App.getAppComponent(getApplication()))
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }
}

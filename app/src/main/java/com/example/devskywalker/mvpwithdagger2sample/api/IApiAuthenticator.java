package com.example.devskywalker.mvpwithdagger2sample.api;

/**
 * Created by admin on 14/7/17.
 */

public interface IApiAuthenticator {


    int refreshToken();

    void forceLogout();
}
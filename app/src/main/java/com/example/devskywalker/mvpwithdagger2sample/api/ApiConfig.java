package com.example.devskywalker.mvpwithdagger2sample.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;

/**
 * Created by admin on 14/7/17.
 */

public class ApiConfig {
    private static final String KEY_API_CONFIG = "api_config";
    private static final String KEY_API_URL = "api_url";

    private final SharedPreferences mSharedPreferences;
    private final String DEFAULT_API_URL;

    private boolean mIsDebugBuild;

    public ApiConfig(Context context) {
        mSharedPreferences = context.getSharedPreferences(KEY_API_CONFIG, Context.MODE_PRIVATE);

        DEFAULT_API_URL = "https://my-api.com/";

        mIsDebugBuild = 0 != (context.getApplicationInfo().flags & ApplicationInfo
                .FLAG_DEBUGGABLE);
    }

    public String getApiUrl() {
        return mSharedPreferences.getString(KEY_API_URL, DEFAULT_API_URL);
    }

    public void setApiUrl(String url) {
        mSharedPreferences.edit().putString(KEY_API_URL, url).apply();
    }

    public void clearAll() {
        mSharedPreferences.edit()
                .clear()
                .apply();
    }

    public boolean isDebugBuild() {
        return mIsDebugBuild;
    }

}

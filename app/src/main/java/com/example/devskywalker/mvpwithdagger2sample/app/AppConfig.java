package com.example.devskywalker.mvpwithdagger2sample.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by admin on 14/7/17.
 */
// note the user name and password are currently stored here for just sample purposes.
    // We'll have to remove this as this is not a secure way of handling sensitive data.
public class AppConfig {
    private static final String KEY_LOGGED_IN = "logged_in";

    // FIXME: 19/12/16 Remove these when Token Refresh is Implemented
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL_ID = "email_id";
    private static final String KEY_GOOGLE_ID_TOKEN = "google_id_token";

    // User Credentials for each Login Session
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";


    private final SharedPreferences mSharedPreferences;


    public AppConfig(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLoggedIn(boolean isLogin) {
        mSharedPreferences.edit()
                .putBoolean(KEY_LOGGED_IN, isLogin)
                .apply();
    }

    public boolean isLoggedIn() {
        return mSharedPreferences.getBoolean(KEY_LOGGED_IN, false);
    }

    public void clearAll() {
        mSharedPreferences.edit()
                .clear()
                .apply();
    }

    public void setUsername(String username) {
        mSharedPreferences.edit()
                .putString(KEY_USERNAME, username)
                .apply();
    }

    public String getUsername() {
        return mSharedPreferences.getString(KEY_USERNAME, "");
    }

    public void setPassword(String password) {
        mSharedPreferences.edit()
                .putString(KEY_PASSWORD, password)
                .apply();
    }

    public String getPassword() {
        return mSharedPreferences.getString(KEY_PASSWORD, "");
    }

    public void setEmailId(String emailId) {
        mSharedPreferences.edit()
                .putString(KEY_EMAIL_ID, emailId)
                .apply();
    }

    public String getEmailId() {
        return mSharedPreferences.getString(KEY_EMAIL_ID, "");
    }

    public void setGoogleIdToken(String googleIdToken) {
        mSharedPreferences.edit()
                .putString(KEY_GOOGLE_ID_TOKEN, googleIdToken)
                .apply();
    }

    public String getGoogleIdToken() {
        return mSharedPreferences.getString(KEY_GOOGLE_ID_TOKEN, "");
    }

    public boolean isLoginViaPassword() {
        return !getUsername().isEmpty() && !getPassword().isEmpty();
    }

    public boolean isLoginViaGoogleSso() {
        return !getEmailId().isEmpty() && !getGoogleIdToken().isEmpty();
    }

    public void setAccessToken(String accessToken) {
        mSharedPreferences.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .apply();
    }

    public String getAccessToken() {
        return mSharedPreferences
                .getString(KEY_ACCESS_TOKEN, "");
    }

    public void setFirstName(String firstName) {
        mSharedPreferences.edit()
                .putString(KEY_FIRST_NAME, firstName)
                .apply();
    }

    public String getFirstName() {
        return mSharedPreferences
                .getString(KEY_FIRST_NAME, "");
    }

    public void setLastName(String lastName) {
        mSharedPreferences.edit()
                .putString(KEY_LAST_NAME, lastName)
                .apply();
    }

    public String getLastName() {
        return mSharedPreferences
                .getString(KEY_LAST_NAME, "");
    }

}
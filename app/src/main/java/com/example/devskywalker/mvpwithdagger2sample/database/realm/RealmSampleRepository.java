package com.example.devskywalker.mvpwithdagger2sample.database.realm;

import android.content.Context;

import com.example.devskywalker.mvpwithdagger2sample.database.model.Sample;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

/**
 * Created by admin on 14/7/17.
 */

public class RealmSampleRepository {

    public static void initialize(Context context) {
        // Initialize Realm once, and don't forget to close() every use
        Realm.init(context);

        // If Schema changed, just remove Database and start anew
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public static void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    public static void add(Sample driver) {
        Realm realm = Realm.getDefaultInstance();
        try {
            //commit to db
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(driver);
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    public static void addAll(List<Sample> drivers) {
        Realm realm = Realm.getDefaultInstance();
        try {
            //commit to db
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(drivers);
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    public static void addChangeListener(RealmChangeListener<Realm> listener) {
        Realm.getDefaultInstance().addChangeListener(listener);
    }

    public static void removeChangeListener(RealmChangeListener<Realm> listener) {
        Realm.getDefaultInstance().removeChangeListener(listener);
    }

    public static void removeAllChangeListeners() {
        // Remove ALL Listeners just to make sure
        Realm.getDefaultInstance().removeAllChangeListeners();
    }

    public static void close() {
        // Close the Realm. Sorry Barb!!
        Realm.getDefaultInstance().close();
    }
}

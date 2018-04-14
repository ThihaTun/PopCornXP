package com.example.gl62m7rdx.sqlite_multi_database_test;

import android.app.Application;
import android.content.Context;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.model.MovieModel;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";

    public static MyApplication objInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        objInstance = this;

        MovieModel.getInstance().loadMovie();
    }

    public static Context getContext() {
        return objInstance.getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        objInstance = null;
    }
}

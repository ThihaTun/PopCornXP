package com.example.gl62m7rdx.sqlite_multi_database_test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public class NetworkUtils {

    private static NetworkUtils objInstance;
    private ConnectivityManager manager;
    private NetworkInfo networkInfo;
    private Context context;

    public NetworkUtils() {
        context = MyApplication.getContext();
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetworkUtils getInstance() {
        if (objInstance == null) {
            objInstance = new NetworkUtils();
        }
        return objInstance;
    }

    public boolean isOnline() {
        networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}

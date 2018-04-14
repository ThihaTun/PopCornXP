package com.example.gl62m7rdx.sqlite_multi_database_test.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GL62M 7RDX on 06-Feb-18.
 */

public class SharedPreUtil {

    public static final String CONFIG_USER = "CONFIG_USER";
    public static final String CONNECT_TYPE = "CONNET_TYPE";
    public static final String LOGIN_USER_ID = "PK_LOGIN_USER_ID";

    private static Context context;

    public static int getUserInfoFrom(int connectType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(CONNECT_TYPE, connectType);
    }

    public static String getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LOGIN_USER_ID, null);
    }

    public static void setUserId(int connetType, String userId) {
        SharedPreferences loginUserPref = context.getSharedPreferences(CONFIG_USER, Context.MODE_PRIVATE);
        loginUserPref.edit().putInt(CONNECT_TYPE, connetType).apply();
        loginUserPref.edit().putString(LOGIN_USER_ID, userId).apply();
    }

    public static void clearUserInfo(int connetTypeNone) {
        SharedPreferences loginUserPref = context.getSharedPreferences(CONFIG_USER, Context.MODE_PRIVATE);
        loginUserPref.edit().putInt(CONNECT_TYPE, connetTypeNone).apply();
        loginUserPref.edit().putString(LOGIN_USER_ID, null).apply();
    }

}

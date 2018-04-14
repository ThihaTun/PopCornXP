package com.example.gl62m7rdx.sqlite_multi_database_test.data.model;

import android.content.Context;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.UserVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.UserEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.SharedPreUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by GL62M 7RDX on 05-Feb-18.
 */

public class UserModel {

    private Context context;
    private UserVO user;
    private static UserModel objInstance;
    private Realm realm;
    private int connectType;

    private static final int CONNECT_TYPE_NONE = 1;
    private static final int CONNECT_TYPE_FACEBOOK = 2;
    private static final int CONNECT_TYPE_GOOGLE = 3;

    public static UserModel getInstance() {
        if (objInstance == null) {
            objInstance = new UserModel();
        }
        return objInstance;
    }

    public UserModel() {
        context = MyApplication.getContext();
        realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();

        connectType = SharedPreUtil.getUserInfoFrom(CONNECT_TYPE_NONE);
        if (connectType != CONNECT_TYPE_NONE) {
            String userId = SharedPreUtil.getUserId();
            if (userId != null) {
                RealmResults<UserVO> results =
                        realm.where(UserVO.class).equalTo("facebookId", userId).findAll();
                if (results.size() > 0) {
                    user = results.get(0);
                } else {
                    user = realm.createObject(UserVO.class);
                }
            } else {
                user = realm.createObject(UserVO.class);
            }
        } else {
            user = realm.createObject(UserVO.class);
        }
        realm.commitTransaction();

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public boolean isUserLogin() {
        return connectType != CONNECT_TYPE_NONE;
    }

    public UserVO getLoginUser() {
        return user;
    }

    public void initUserByFaceBook(JSONObject jsonObject) {
        realm.beginTransaction();
        try {
            if (jsonObject.has(UserVO.JK_FB_ID)) {
                user.setFacebookId(jsonObject.getString(UserVO.JK_FB_ID));
            }
            if (jsonObject.has(UserVO.JK_NAME)) {
                user.setName(jsonObject.getString(UserVO.JK_NAME));
            }
            if (jsonObject.has(UserVO.JK_GENDER)) {
                user.setGender(jsonObject.getString(UserVO.JK_GENDER));
            }
            if (jsonObject.has(UserVO.JK_EMAIL)) {
                user.setEmail(jsonObject.getString(UserVO.JK_EMAIL));
            }
            if (jsonObject.has(UserVO.JK_DOB)) {
                user.setDateOfBirth(jsonObject.getString(UserVO.JK_DOB));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        realm.commitTransaction();
        SharedPreUtil.setUserId(CONNECT_TYPE_FACEBOOK, user.getFacebookId());
    }

    public void addFaceBookCoverUrl(String url) {
        realm.beginTransaction();
        user.setCoverUrl(url);
        realm.commitTransaction();
    }

    public void addFacebookProfileUrl(String url) {
        realm.beginTransaction();
        user.setProfileUrl(url);
        realm.commitTransaction();
    }

    public void onEventMainEvent(UserEvent.LogOutEvent event) {
        SharedPreUtil.clearUserInfo(CONNECT_TYPE_NONE);
        realm.beginTransaction();
        //TODO- WORK ?
//        realm.allObjects(UserVO.class).clear();
        realm.delete(UserVO.class);
        user = realm.createObject(UserVO.class);
        realm.commitTransaction();
    }

}

package com.example.gl62m7rdx.sqlite_multi_database_test.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
//
//    @BindView(R.id.login_layout)
//    ConstraintLayout view;
//
//    @BindView(R.id.login_button)
//    LoginButton loginButton;
//
//    CallbackManager manager;
//
//    public static Intent newIntent() {
//        Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
//        return intent;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//
//        manager = CallbackManager.Factory.create();
//
//        loginButton.setReadPermissions("email");
//
//        loginButton.registerCallback(manager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(MyApplication.TAG, loginResult.getAccessToken().toString());
//                Intent intent = MainActivity.newIntent();
//                startActivity(intent);
//            }
//
//            @Override
//            public void onCancel() {
//                Snackbar.make(view, "Facebook login canceled.", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                Snackbar.make(view, "Error Facebook login.", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        manager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}

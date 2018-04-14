package com.example.gl62m7rdx.sqlite_multi_database_test.mvp.presenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public abstract class BasePresenter {

    public void onCreate() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public abstract void onStart();

    public abstract void onStop();

    public void onDestroy() {
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }
}

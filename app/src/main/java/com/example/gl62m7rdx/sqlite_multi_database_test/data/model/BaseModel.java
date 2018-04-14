package com.example.gl62m7rdx.sqlite_multi_database_test.data.model;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.MovieDataAgent;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.OfflineDataAgent;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.retrofit.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public abstract class BaseModel {

    private static final int INIT_DATA_AGENT_OFFLINE = 1;
    private static final int INIT_DATA_AGENT_RETROFIT = 2;

    protected MovieDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_OFFLINE);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {
            case INIT_DATA_AGENT_OFFLINE:
                dataAgent = OfflineDataAgent.getInstance();
                break;
            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {

    }
}

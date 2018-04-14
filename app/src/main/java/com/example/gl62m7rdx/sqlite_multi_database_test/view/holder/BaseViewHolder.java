package com.example.gl62m7rdx.sqlite_multi_database_test.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    protected boolean mDetechedFromWindow;

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(this);

        EventBus eventBus = new EventBus();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                mDetechedFromWindow = false;
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                mDetechedFromWindow = true;
            }
        });
    }

    public abstract void bind(T data);

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object obj) {

    }

    @Override
    public void onClick(View view) {

    }
}

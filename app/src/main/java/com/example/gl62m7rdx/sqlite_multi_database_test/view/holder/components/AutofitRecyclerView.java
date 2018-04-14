package com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.gl62m7rdx.sqlite_multi_database_test.R;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public class AutofitRecyclerView extends RecyclerView {

    private GridLayoutManager manager;
    private int columnWidth = -1;
    private View emptyView;

    private static final int DEFAULT_COLUMN_SPAN_COUNT = 2;

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutofitRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs );
    }

    private void init(Context context, AttributeSet attrs) {
//        addItemDecoration(new DividerItemDecoration(getContext()));
        setHasFixedSize(true);

        int gridColumnSpanCount = getResources().getInteger(R.integer.movie_grid_count);

        manager = new GridLayoutManager(getContext(), gridColumnSpanCount);
        setLayoutManager(manager);

        setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(dataObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserver);
        }
        checkIfEmpty();
    }

    private AdapterDataObserver dataObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }
    };

    protected void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final Boolean isEmpty = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(isEmpty ? VISIBLE : GONE);
            setVisibility(isEmpty ? GONE : VISIBLE);
        }
    }

    public void setEmptyView(View view) {
        this.emptyView = view;
        checkIfEmpty();
    }

    public void setGridColumnSpan(int columnSpan) {
        manager = new GridLayoutManager(getContext(), columnSpan);
        setLayoutManager(manager);
    }
}

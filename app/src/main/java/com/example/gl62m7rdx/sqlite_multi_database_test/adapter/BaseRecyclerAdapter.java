package com.example.gl62m7rdx.sqlite_multi_database_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GL62M 7RDX on 20-Dec-17.
 */

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected LayoutInflater mLayoutInflater;

    protected List<W> mData;

    public BaseRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<W> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<W> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public W getItemAt(int position) {
        if (position < mData.size() - 1)
            return mData.get(position);

        return null;
    }

    public List<W> getItems() {
        if (mData == null)
            return new ArrayList<>();

        return mData;
    }

    public void removeData(W data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void addNewData(W data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
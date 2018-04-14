package com.example.gl62m7rdx.sqlite_multi_database_test.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.adapter.MovieAdapter;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.model.MovieModel;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence.DBContract;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.MovieViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListingFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    private MovieAdapter adapter;
    private MovieViewHolder.ControllerMovieItem controller;

    private BroadcastReceiver mDataLoadedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String extra = intent.getStringExtra("key-for-extra");
            Log.d(MyApplication.TAG, extra);

            List<MovieVO> movieList = MovieModel.getInstance().getMovieList();
            adapter.setNewData(movieList);
        }
    };

    public static MoviesListingFragment newInstance() {
        MoviesListingFragment fragment = new MoviesListingFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controller = (MovieViewHolder.ControllerMovieItem) context;
    }

    public MoviesListingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movies_listing, container, false);
        ButterKnife.bind(this, view);

        adapter = new MovieAdapter(getContext(), controller);
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.movie_grid_count)));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportLoaderManager().initLoader(PopCornCommonConstants.MOVIE_LIST_LOADER,
                null, this);

        getActivity().getSupportLoaderManager().restartLoader(PopCornCommonConstants.MOVIE_LIST_LOADER,
                null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                DBContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<MovieVO> movieList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                MovieVO movie = MovieVO.parseFromCuror(data);
                movieList.add(movie);
            } while (data.moveToNext());
        }
        Log.d(MyApplication.TAG, "Retrieved Movies: " + movieList.size());
        adapter.setNewData(movieList);

        Log.d(MyApplication.TAG, "onLoadFinished" + movieList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.MovieDataLoadedEvent event) {

//        List<MovieVO> newMovieList = MovieModel.getInstance().getMovieList();
        List<MovieVO> newMovieList = event.getMovieList();
        adapter.setNewData(newMovieList);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoadedBroadcastReceiver,
                new IntentFilter(MovieModel.BROADCAST_DATA_LOADED));

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDataLoadedBroadcastReceiver);

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

}

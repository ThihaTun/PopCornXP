package com.example.gl62m7rdx.sqlite_multi_database_test.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.adapter.MovieAdapter;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence.DBContract;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.mvp.presenter.SearchPresenter;
import com.example.gl62m7rdx.sqlite_multi_database_test.mvp.view.SearchView;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.MovieViewHolder;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.components.AutofitRecyclerView;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.pods.ViewPodEmpty;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment
        implements SearchView, LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.vp_empty_search)
    ViewPodEmpty vpEmptySearch;

    @BindView(R.id.et_search)
    AutoCompleteTextView etSearch;

    @BindView(R.id.input_layout_search)
    TextInputLayout tilSearch;

    @BindView(R.id.rv_search_results)
    AutofitRecyclerView rvSearchResults;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter adapter;
    private MovieViewHolder.ControllerMovieItem controller;
    private SearchPresenter presenter;
    private View rootView;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controller = (MovieViewHolder.ControllerMovieItem) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(this);
        presenter.onCreate();

        adapter = new MovieAdapter(getContext(), controller);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        vpEmptySearch.setEmptyLabel(getString(R.string.empty_search));
        tilSearch.setHint(getString(R.string.search_hint_movies));
        rvSearchResults.setGridColumnSpan(1);
        rvSearchResults.setEmptyView(vpEmptySearch);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String query = etSearch.getText().toString();
                    presenter.search(query);
                    return true;
                }
                return false;
            }
        });
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showSearchResultMovie(List<MovieVO> searchResultList) {
        rvSearchResults.setGridColumnSpan(
                getResources().getInteger(R.integer.movie_grid_count));
        adapter.setNewData(searchResultList);
        rvSearchResults.setAdapter(adapter);

        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMsgFailInSearch(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();

        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showMsgSpecialCharacterDetected() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.contain_special_char_in_query_msg)
                .setIcon(R.drawable.ic_announcement_black_24dp)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void showMsgNoNetwork() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.no_network_msg)
                .setIcon(R.drawable.ic_announcement_black_24dp)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                DBContract.MovieEntry.CONTENT_URI,
                new String[]{DBContract.MovieEntry.COLUMN_TITLE},
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<String> suggestionList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                suggestionList.add(data.getString(data.getColumnIndex(DBContract.MovieEntry.COLUMN_TITLE)));
            } while (data.moveToNext());
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, suggestionList);
            etSearch.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        List<MovieVO> movieList = new ArrayList<>();
        showSearchResultMovie(movieList);
    }

}

package com.example.gl62m7rdx.sqlite_multi_database_test.mvp.presenter;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.model.MovieModel;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.mvp.view.SearchView;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.NetworkUtils;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public class SearchPresenter extends BasePresenter{

    private SearchView view;

    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void search(String query) {
        if (NetworkUtils.getInstance().isOnline()) {
            if (query.matches(PopCornCommonConstants.REG_VALID_SEARCH_QUERY_RANGE)) {
                view.showLoading();
                MovieModel.getInstance().searchOnMovies(query);
            } else {
                view.showMsgSpecialCharacterDetected();
            }
        } else {
            view.showMsgNoNetwork();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.SearchedMovieEvent event) {
        view.showSearchResultMovie(event.getResponse().getResults());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.FailMovieLoad event) {
        view.showMsgFailInSearch(event.getMessage());
    }

}

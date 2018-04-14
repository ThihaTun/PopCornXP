package com.example.gl62m7rdx.sqlite_multi_database_test.data.model;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class MovieModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static MovieModel objInstance;

    private List<MovieVO> movieList;

    private MovieModel() {
        super();
        movieList = new ArrayList<>();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public static MovieModel getInstance() {
        if (objInstance == null) {
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public void loadMovie(){
        dataAgent.loadPopularMovie();
    }

    public List<MovieVO> getMovieList() {
        return movieList;
    }

//    public MovieVO getMoviebyTitle(String movieTitle) {
//        for (MovieVO movie : movieList) {
//            if (movie.getTitle().equals(movieTitle)) {
//                return movie;
//            }
//        }
//        return null;
//    }

    public void searchOnMovies(String query) {
        Log.d(MyApplication.TAG, "Search on movie with query " + query);
        dataAgent.searchOnMovies(query);
    }

    public void notifyMoviesLoaded(List<MovieVO> myMovieList) {
        movieList = myMovieList;
        MovieVO.saveMovies(movieList);
//        broadcastMovieLoadedWithEventBus(myMovieList);
//        broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorLoadedMovies(String message) {
        Log.d(MyApplication.TAG, message);
    }

    private void broadcastAttractionLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(MyApplication.getContext()).sendBroadcast(intent);
    }

    private void broadcastMovieLoadedWithEventBus(List<MovieVO> movieList) {
        EventBus.getDefault().post(
                new DataEvent.MovieDataLoadedEvent("extra-in-broadcast", movieList));
    }

    public void setStoredData(List<MovieVO> myMovieList) {
        movieList = myMovieList;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveAttactionList(DataEvent.MovieDataLoadedEvent event) {
        event.getMovieList();
    }
}

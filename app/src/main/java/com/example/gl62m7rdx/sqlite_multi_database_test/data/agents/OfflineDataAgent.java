package com.example.gl62m7rdx.sqlite_multi_database_test.data.agents;

import android.util.Log;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.model.MovieModel;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class OfflineDataAgent implements MovieDataAgent {

    public static final String OFFLINE_MOVIE_LIST = "popular_movies.json";

    private static OfflineDataAgent objInstance;
    private Gson gson = new Gson();

    private OfflineDataAgent() {

    }

    public static OfflineDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new OfflineDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadPopularMovie(){
        try {
            String movies = JsonUtil.getInstance().loadDummyData(OFFLINE_MOVIE_LIST);
            Type listType = new TypeToken<List<MovieVO>>() {
            }.getType();
            List<MovieVO> movieList = gson.fromJson(movies, listType);
            MovieModel.getInstance().notifyMoviesLoaded(movieList);
            Log.d(MyApplication.TAG, "loadPopularMovie" + movieList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchOnMovies(String query) {

    }
}

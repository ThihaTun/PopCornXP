package com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.retrofit;

import android.util.Log;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.MovieDataAgent;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.model.MovieModel;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.response.MovieResponse;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants;
import com.google.gson.Gson;


import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GL62M 7RDX on 20-Dec-17.
 */

public class RetrofitDataAgent implements MovieDataAgent {

    private static RetrofitDataAgent objInstance;
    private final MovieApi api;
    private final Gson gson = new Gson();


    public RetrofitDataAgent() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PopCornCommonConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api = retrofit.create(MovieApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadPopularMovie() {
        Call<MovieResponse> loadPopularMoviesCall = api.loadPopularMovies(PopCornCommonConstants.API_KEY);
        loadPopularMoviesCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse == null) {
                    MovieModel.getInstance().notifyErrorLoadedMovies("Error Loaded Movies" + movieResponse);
                } else {
                    MovieModel.getInstance().notifyMoviesLoaded(movieResponse.getResults());
                    MovieVO.saveMovies(movieResponse.getResults());
                    MovieModel.getInstance().setStoredData(movieResponse.getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(MyApplication.TAG, t.getMessage());
            }
        });
    }

    @Override
    public void searchOnMovies(final String query) {
        Call<MovieResponse> searchOnMoviesCall = api.searchOnMovie(query, PopCornCommonConstants.API_KEY);
        searchOnMoviesCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieListResponse = response.body();
                if (movieListResponse != null) {
                    DataEvent.SearchedMovieEvent event = new DataEvent.SearchedMovieEvent(movieListResponse, query);
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(MyApplication.TAG, t.getMessage());
            }
        });
    }
}

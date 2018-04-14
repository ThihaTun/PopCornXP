package com.example.gl62m7rdx.sqlite_multi_database_test.data.agents.retrofit;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by GL62M 7RDX on 20-Dec-17.
 */

public interface MovieApi {

    @GET("movie/popular")
    Call<MovieResponse> loadPopularMovies(
            @Query("api_key") String api_key
    );

    @GET("search/movie")
    Call<MovieResponse> searchOnMovie(
            @Query("query") String query,
            @Query("api_key") String apiKey
    );
}

package com.example.gl62m7rdx.sqlite_multi_database_test.event;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.response.MovieResponse;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;

import java.util.List;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class DataEvent {

    public static class MovieDataLoadedEvent {

        private String extraMessage;
        private List<MovieVO> movieList;

        public MovieDataLoadedEvent(String extraMessage, List<MovieVO> movieList) {
            this.extraMessage = extraMessage;
            this.movieList = movieList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MovieVO> getMovieList() {
            return movieList;
        }
    }

    public static class FailMovieLoad {
        public String message;

        public FailMovieLoad(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class onTapSearchMovie {
        public String message;

        public onTapSearchMovie(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class SearchedMovieEvent {
        private MovieResponse response;
        private String query;

        public SearchedMovieEvent(MovieResponse response, String query) {
            this.response = response;
            this.query = query;
        }

        public MovieResponse getResponse() {
            return response;
        }

        public String getQuery() {
            return query;
        }
    }
}

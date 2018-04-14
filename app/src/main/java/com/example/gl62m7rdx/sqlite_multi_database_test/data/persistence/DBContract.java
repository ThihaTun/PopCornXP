package com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by GL62M 7RDX on 18-Dec-17.
 */

public class DBContract {

    public static final String CONTENT_AUTHORITY  = "com.example.gl62m7rdx.sqlite_multi_database_test";
    public static final Uri BASE_CONTENT_URI      = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES        = "movies";
    public static final String PATH_MOVIES_POSTER = "movie_posters";

    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String DIRTYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME              = "movies";

        public static final String MOVIE_ID                = "movie_id";
        public static final String COLUMN_OVERVIEW         = "overview";
        public static final String COLUMN_RELEASEDATE      = "release_date";
        public static final String COLUMN_POSTERPATH       = "poster_path";
        public static final String COLUMN_BACKDROPPATH     = "backdrop_path";
        public static final String COLUMN_TITLE            = "title";
        public static final String COLUMN_VOTEAVERAGE      = "vote_average";
        public static final String COLUMN_VOTECOUNT        = "vote_count";
        public static final String COLUMN_VIDEO            = "video";
        public static final String COLUMN_POPULARITY       = "popularity";
        public static final String COLUMN_ORIGINALLANGUAGE = "original_language";
        public static final String COLUMN_ADULT            = "adult";

        //  content://com.example.gl62m7rdx.sqlite_multi_database_test/movies/1
        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //  content://com.example.gl62m7rdx.sqlite_multi_database_test/movies?id="1234"
        public static Uri buildMovieWithMovieId(int movie_id) {
            return CONTENT_URI.buildUpon().appendQueryParameter(MOVIE_ID, Integer.toString(movie_id)).build();
        }

        public static String getTitleFromParam(Uri uri){
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class MoviePosterEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES_POSTER).build();

        public static final String DIRTYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES_POSTER;

        public static final String TABLE_NAME  = "movie_images";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";

        //  content://com.example.gl62m7rdx.sqlite_multi_database_test/movie_posters/1
        public static Uri buildMovieImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //  content://com.example.gl62m7rdx.sqlite_multi_database_test/movie_posters?title="justice_league.jpg"
        public static Uri buildMovieImageUriWithTitle(String movieTitle) {
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_TITLE, movieTitle).build();
        }

        public static String getMovieTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }
}

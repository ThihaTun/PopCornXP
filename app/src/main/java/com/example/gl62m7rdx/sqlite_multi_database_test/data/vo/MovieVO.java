package com.example.gl62m7rdx.sqlite_multi_database_test.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence.DBContract;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence.DBContract.MovieEntry;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants.IMAGE_BASE_PATH;
import static com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants.IMAGE_SIZE_W500;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class MovieVO {

    @SerializedName("id")
    private int id;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("adult")
    private Boolean adult;

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return IMAGE_BASE_PATH + IMAGE_SIZE_W500 + posterPath;
    }

    public String getBackdropPath() {
        return IMAGE_BASE_PATH + IMAGE_SIZE_W500 + backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public static void saveMovies(List<MovieVO> movies) {
        ContentValues[] contentValues = new ContentValues[movies.size()];
        for (int i = 0; i < movies.size(); i++) {
            MovieVO movie = movies.get(i);
            contentValues[i] = movie.parseToContentValues();
        }
        Context context = MyApplication.getContext();

        int insertedCount = context.getContentResolver().bulkInsert(MovieEntry.CONTENT_URI, contentValues);
        Log.d(MyApplication.TAG, "Bulk inserted into Movie table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(MovieEntry.MOVIE_ID, id);
        cv.put(MovieEntry.COLUMN_OVERVIEW, overview);
        cv.put(MovieEntry.COLUMN_RELEASEDATE, releaseDate);
        cv.put(MovieEntry.COLUMN_POSTERPATH, posterPath);
        cv.put(MovieEntry.COLUMN_BACKDROPPATH, backdropPath);
        cv.put(MovieEntry.COLUMN_TITLE, title);
        cv.put(MovieEntry.COLUMN_VOTEAVERAGE, voteAverage);
        return cv;
    }

    public static MovieVO parseFromCuror(Cursor data) {
        MovieVO movieVO      = new MovieVO();
        movieVO.id           = data.getInt(data.getColumnIndex(MovieEntry.MOVIE_ID));
        movieVO.overview     = data.getString(data.getColumnIndex(MovieEntry.COLUMN_OVERVIEW));
        movieVO.releaseDate  = data.getString(data.getColumnIndex(MovieEntry.COLUMN_RELEASEDATE));
        movieVO.posterPath   = data.getString(data.getColumnIndex(MovieEntry.COLUMN_POSTERPATH));
        movieVO.backdropPath = data.getString(data.getColumnIndex(MovieEntry.COLUMN_BACKDROPPATH));
        movieVO.title        = data.getString(data.getColumnIndex(MovieEntry.COLUMN_TITLE));
        movieVO.voteAverage  = data.getFloat(data.getColumnIndex(MovieEntry.COLUMN_VOTEAVERAGE));
        return movieVO;
    }

}

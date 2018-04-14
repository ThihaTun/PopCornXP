package com.example.gl62m7rdx.sqlite_multi_database_test.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.activity.DetailActivity;
import com.example.gl62m7rdx.sqlite_multi_database_test.activity.MainActivity;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence.DBContract;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.util.PopCornCommonConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.iv_movie_poster)
    ImageView moviePoster;

    @BindView(R.id.tv_movie_name)
    TextView movieName;

    @BindView(R.id.tv_movie_year)
    TextView movieYear;

    @BindView(R.id.tv_movie_rating)
    TextView movieRating;

    @BindView(R.id.tv_movie_description)
    TextView moviedesc;

    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFav;

    private int movieId;
    private MovieVO mMovie;
    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";
    private View view;

    public static MovieDetailFragment newInstance(int movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MovieDetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            readArguments(bundle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportLoaderManager()
                .initLoader(PopCornCommonConstants.MOVIE_DETAIL_LOADER, null, this);
        getActivity().getSupportLoaderManager()
                .restartLoader(PopCornCommonConstants.MOVIE_DETAIL_LOADER, null, this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.menu_share) {
            Snackbar.make(view, "Sorry. Share is not being supported yet.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ARG_MOVIE_ID, movieId);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                DBContract.MovieEntry.buildMovieWithMovieId(movieId),
                null,
                DBContract.MovieEntry.MOVIE_ID + " = ?",
                new String[]{String.valueOf(movieId)},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mMovie = MovieVO.parseFromCuror(data);
            bindData(mMovie);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void bindData(MovieVO movie) {
//        toolbar.setTitle(movie.getTitle());

        Glide.with(moviePoster.getContext())
                .load(movie.getBackdropPath())
                .into(moviePoster);

        movieName.setText(movie.getTitle());
        movieYear.setText(movie.getReleaseDate());
        movieRating.setText(Float.toString(movie.getVoteAverage()));
        moviedesc.setText(movie.getOverview());
    }

    @OnClick(R.id.fab_favorite)
    public void onTapFav() {
        if (fabFav != null)
            fabFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Sorry. Fav is not being supported yet.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }

    private void readArguments(Bundle bundle)    {
        movieId = bundle.getInt(ARG_MOVIE_ID);
    }

}

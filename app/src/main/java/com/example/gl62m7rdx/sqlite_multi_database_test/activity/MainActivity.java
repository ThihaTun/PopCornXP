package com.example.gl62m7rdx.sqlite_multi_database_test.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.SearchButtonClickedEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.fragment.MovieDetailFragment;
import com.example.gl62m7rdx.sqlite_multi_database_test.fragment.MoviesListingFragment;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.MovieViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements MovieViewHolder.ControllerMovieItem {
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.fab_search)
    FloatingActionButton fabSearch;

    private boolean mTwoPane;
    int movieId;
    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

    public static Intent newIntent() {
        Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

//        if (findViewById(R.id.fl_detail_container) != null) {
//            mTwoPane = true;
//            if (savedInstanceState == null) {
//                Bundle bundle = new Bundle();
//                movieId = bundle.getInt(ARG_MOVIE_ID, 0);
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.fl_detail_container, MovieDetailFragment.newInstance(movieId))
//                        .commit();
//            }
//        } else {
//            mTwoPane = false;
//        }

        getSupportFragmentManager().beginTransaction()
                .add( R.id.fl_main_container , MoviesListingFragment.newInstance())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapMovie(MovieVO movie, ImageView ivMovie) {
//        if (mTwoPane) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fl_detail_container, MovieDetailFragment.newInstance(movie.getId()))
//                    .commit();
//        } else {
            Intent intent = DetailActivity.newIntent(movie.getId());
            startActivity(intent);
//        }
    }

    @OnClick(R.id.fab_search)
    public void search(View view) {
        if (fabSearch != null)
            fabSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = SearchActivity.createNewIntent();
                    startActivity(intent);
                }
            });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivedSearchButtonClickedEvent(SearchButtonClickedEvent event) {
        Log.d(MyApplication.TAG, "onReceivedSearchButtonClickedEvent");
    }

}

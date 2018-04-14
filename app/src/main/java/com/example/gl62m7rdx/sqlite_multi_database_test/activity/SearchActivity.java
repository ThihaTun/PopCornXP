package com.example.gl62m7rdx.sqlite_multi_database_test.activity;

import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.event.DataEvent;
import com.example.gl62m7rdx.sqlite_multi_database_test.fragment.SearchFragment;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.MovieViewHolder;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SearchActivity extends AppCompatActivity
        implements MovieViewHolder.ControllerMovieItem {

    public static Intent createNewIntent() {
        Intent intent = new Intent(MyApplication.getContext(), SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_container_search_activity, SearchFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onTapMovie(MovieVO movie, ImageView ivMovie) {
        Intent intent = DetailActivity.newIntent(movie.getId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivedSearchButtonClickedEvent(DataEvent.onTapSearchMovie event) {
        Log.d(MyApplication.TAG, "onTapSearchMovie");
    }

}

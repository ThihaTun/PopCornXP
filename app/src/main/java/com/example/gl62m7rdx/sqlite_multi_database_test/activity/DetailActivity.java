package com.example.gl62m7rdx.sqlite_multi_database_test.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.fragment.MovieDetailFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.fl_detail_container)
    FrameLayout view;

    private static final String INTENT_EXTRA_MOVIE_ID = "INTENT_EXTRA_MOVIE_ID";

    public static Intent newIntent(int movie_id) {
        Intent intent = new Intent(MyApplication.getContext(), DetailActivity.class);
        intent.putExtra(INTENT_EXTRA_MOVIE_ID, movie_id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int movieId = getIntent().getIntExtra(INTENT_EXTRA_MOVIE_ID, 0);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_detail_container, MovieDetailFragment.newInstance(movieId))
                .commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.menu_share) {
            Snackbar.make(view, "Sorry. Share is not being supported yet.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        return super.onOptionsItemSelected(item);
    }

}

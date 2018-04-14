package com.example.gl62m7rdx.sqlite_multi_database_test.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class MovieViewHolder extends BaseViewHolder<MovieVO> {

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.iv_movie_poster)
    ImageView ivMoviePoster;

    private ControllerMovieItem mController;

    private MovieVO mMovie;

    public MovieViewHolder(View view, ControllerMovieItem controller) {
        super(view);
        mController = controller;
    }

    @Override
    public void onClick(View view) {
        mController.onTapMovie(mMovie, ivMoviePoster);
    }

    @Override
    public void bind(MovieVO data) {
        mMovie = data;
        tvMovieName.setText(data.getTitle());
        String imageUrl = data.getPosterPath();
        Glide.with(ivMoviePoster.getContext())
                .load(imageUrl)
//                .centerCrop()
//                .placeholder(R.drawable.place_holder)
//                .error(R.drawable.ic_error_black_24dp)
                .into(ivMoviePoster);
    }

    public interface ControllerMovieItem {
        void onTapMovie(MovieVO movie, ImageView ivMovie);
    }

}

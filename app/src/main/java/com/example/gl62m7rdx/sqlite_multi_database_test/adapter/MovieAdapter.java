package com.example.gl62m7rdx.sqlite_multi_database_test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.gl62m7rdx.sqlite_multi_database_test.R;
import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;
import com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.MovieViewHolder;


/**
 * Created by GL62M 7RDX on 20-Dec-17.
 */

public class MovieAdapter extends BaseRecyclerAdapter<MovieViewHolder, MovieVO> {

    private MovieViewHolder.ControllerMovieItem controller;

    public MovieAdapter(Context context, MovieViewHolder.ControllerMovieItem controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view, controller);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}

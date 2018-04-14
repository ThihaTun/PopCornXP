package com.example.gl62m7rdx.sqlite_multi_database_test.view.holder.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gl62m7rdx.sqlite_multi_database_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public class ViewPodEmpty extends RelativeLayout {

    @BindView(R.id.lbl_empty)
    TextView lblEmpty;

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    public ViewPodEmpty(Context context) {
        super(context);
    }

    public ViewPodEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodEmpty(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setEmptyLabel(String label) {
        lblEmpty.setText(label);
    }
}

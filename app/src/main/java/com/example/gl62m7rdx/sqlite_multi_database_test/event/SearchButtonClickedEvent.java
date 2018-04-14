package com.example.gl62m7rdx.sqlite_multi_database_test.event;

/**
 * Created by GL62M 7RDX on 20-Dec-17.
 */

public class SearchButtonClickedEvent {
    private long x;
    private long y;


    public SearchButtonClickedEvent(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}

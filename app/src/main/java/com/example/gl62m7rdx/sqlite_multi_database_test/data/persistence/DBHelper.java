package com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GL62M 7RDX on 18-Dec-17.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "popcorn.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
            DBContract.MovieEntry.TABLE_NAME          + " (" +
            DBContract.MovieEntry._ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DBContract.MovieEntry.MOVIE_ID            + " INTEGER NOT NULL, " +
            DBContract.MovieEntry.COLUMN_OVERVIEW     + " TEXT NOT NULL, " +
            DBContract.MovieEntry.COLUMN_RELEASEDATE  + " TEXT NOT NULL, " +
            DBContract.MovieEntry.COLUMN_POSTERPATH   + " TEXT NOT NULL, " +
            DBContract.MovieEntry.COLUMN_BACKDROPPATH + " TEXT, " +
            DBContract.MovieEntry.COLUMN_TITLE        + " TEXT NOT NULL, " +
            DBContract.MovieEntry.COLUMN_VOTEAVERAGE  + " REAL NOT NULL " +

//            " UNIQUE (" + DBContract.MovieEntry.MOVIE_ID + " CONFLICT IGNORE" +
            " );";
//
//    private static final String SQL_CREATE_MOVIE_POSTER_TABLE = "CREATE TABLE " + DBContract.MovieEntry.TABLE_NAME + " (" +
//            DBContract.MoviePosterEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            DBContract.MoviePosterEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
//            DBContract.MoviePosterEntry.COLUMN_IMAGE + "TEXT NOT NULL, " +
//
//            " UNIQUE (" + DBContract.MoviePosterEntry.COLUMN_TITLE + " ," +
//            DBContract.MoviePosterEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
//            " );";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
//        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_POSTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.MovieEntry.TABLE_NAME);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.MoviePosterEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}

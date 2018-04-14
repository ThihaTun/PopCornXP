package com.example.gl62m7rdx.sqlite_multi_database_test.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by GL62M 7RDX on 18-Dec-17.
 */

public class MyContentProvider extends ContentProvider {

    public static final int MOVIE = 100;
    public static final int MOVIE_POSTER = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DBHelper myDB;

    @Override
    public boolean onCreate() {
        myDB = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor;
        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case MOVIE:
                String movieTitle = DBContract.MovieEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(movieTitle)) {
                    selection = DBContract.MovieEntry.COLUMN_TITLE + " = ?";
                    selectionArgs = new String[]{movieTitle};
                }
                cursor = db.query(DBContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case MOVIE_POSTER:
                String title = DBContract.MoviePosterEntry.getMovieTitleFromParam(uri);
                if (!TextUtils.isEmpty(title)) {
                    selection = DBContract.MoviePosterEntry.COLUMN_TITLE + " = ?";
                    selectionArgs = new String[]{title};
                }
                cursor = db.query(DBContract.MoviePosterEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
        Context context = getContext();

        if (context != null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = myDB.getWritableDatabase();
        int matcher = sUriMatcher.match(uri);
        Uri insertedUri;
        switch (matcher) {
            case MOVIE: {
                long _id = db.insert(DBContract.MovieEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = DBContract.MovieEntry.buildMovieUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case MOVIE_POSTER: {
                long _id = db.insert(DBContract.MoviePosterEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = DBContract.MoviePosterEntry.buildMovieImageUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return insertedUri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = myDB.getWritableDatabase();
        String tableName = getTableName(uri);
        int count;

        count = db.update(tableName, contentValues, s, strings);

        Context context = getContext();
        if (context != null && count > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = myDB.getWritableDatabase();
        String tableName = getTableName(uri);
        int count;

        count = db.delete(tableName, s, strings);

        Context context = getContext();
        if (context != null && count > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = myDB.getWritableDatabase();
        int count = 0;
        String tableName = getTableName(uri);

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    count++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case MOVIE:
                return DBContract.MovieEntry.DIRTYPE;
            case MOVIE_POSTER:
                return DBContract.MoviePosterEntry.DIRTYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DBContract.CONTENT_AUTHORITY, DBContract.PATH_MOVIES, MOVIE);
        uriMatcher.addURI(DBContract.CONTENT_AUTHORITY, DBContract.PATH_MOVIES_POSTER, MOVIE_POSTER);
        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = buildUriMatcher().match(uri);
        switch (matchUri) {
            case MOVIE:
                return DBContract.MovieEntry.TABLE_NAME;
            case MOVIE_POSTER:
                return DBContract.MoviePosterEntry.TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
    }
}

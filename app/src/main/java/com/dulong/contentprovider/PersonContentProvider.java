package com.dulong.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by dulong on 2017/3/30.
 */

public class PersonContentProvider extends ContentProvider {
    private static final int INSERT = 1;
    private static final int DELECT = 2;
    private static final int UPDATE = 3;
    private static final int QUERY = 4;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("content://com.dulong.contentproviderservice.PersonContentProvider", "query", QUERY);
        uriMatcher.addURI("content://com.dulong.contentproviderservice.PersonContentProvider", "delect", DELECT);
        uriMatcher.addURI("content://com.dulong.contentproviderservice.PersonContentProvider", "insert", INSERT);
        uriMatcher.addURI("content://com.dulong.contentproviderservice.PersonContentProvider", "update", UPDATE);
    }

    private SqliteHelper sqliteHelper;


    @Override
    public boolean onCreate() {
        sqliteHelper = new SqliteHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return sqliteHelper.queryStudent(projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqliteHelper.insertStudent(values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return sqliteHelper.delectStudent(selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return sqliteHelper.updateStudent(values, selection, selectionArgs);
    }
}

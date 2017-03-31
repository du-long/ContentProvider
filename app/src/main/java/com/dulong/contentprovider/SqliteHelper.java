package com.dulong.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dulong on 2017/3/30.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PERSON";
    public static final String TAB_NAME = "student_info";

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TAB_NAME + "("
                + "id integer primary key,"
                + "name varchar,"
                + "sex varchar,"
                + "age integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertStudent(ContentValues values) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        long insert = writableDatabase.insert(TAB_NAME, null, values);
        writableDatabase.close();
        return insert;
    }

    public int delectStudent(String selection, String[] selectionArgs) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        //  int delete = writableDatabase.delete(TAB_NAME, "name=? and sex=?", new String[]{"杜龙", "男"});
        int delete = writableDatabase.delete(TAB_NAME, selection, selectionArgs);
        writableDatabase.close();
        return delete;
    }

    public int updateStudent(ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", "杜龙");
//        contentValues.put("sex", "男");
//        contentValues.put("age", 27);
        // int update = writableDatabase.update(TAB_NAME, contentValues, "name=? and age=?", new String[]{"杜龙", "20"});
        int update = writableDatabase.update(TAB_NAME, values, selection, selectionArgs);
        writableDatabase.close();
        return update;
    }

    public Cursor queryStudent(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        //  Cursor cursor = readableDatabase.query(TAB_NAME, null, "name=?", new String[]{"杜龙"}, null, null, "id desc");
        Cursor cursor = readableDatabase.query(TAB_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.moveToFirst();
        readableDatabase.close();
        return cursor;
    }
}

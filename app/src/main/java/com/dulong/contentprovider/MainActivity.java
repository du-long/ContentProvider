package com.dulong.contentprovider;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delect).setOnClickListener(this);
        sqliteHelper = new SqliteHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues;
        switch (v.getId()) {
            case R.id.btn_insert:
                contentValues = new ContentValues();
                contentValues.put("name", "杜龙");
                contentValues.put("sex", "男");
                contentValues.put("age", 20);
                sqliteHelper.insertStudent(contentValues);
                break;
            case R.id.btn_delect:
                sqliteHelper.delectStudent("name=? and age=?", new String[]{"杜龙", "20"});
                break;
            case R.id.btn_update:
                contentValues = new ContentValues();
                contentValues.put("name", "杜龙");
                contentValues.put("sex", "男");
                contentValues.put("age", 27);
                sqliteHelper.updateStudent(contentValues, "name=? and age=?", new String[]{"杜龙", "20"});
                break;
            case R.id.btn_query:
                sqliteHelper.queryStudent(null,"name=?",new String[]{"杜龙"},"id desc");
                break;

        }
    }
}

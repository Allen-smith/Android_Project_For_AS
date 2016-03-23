package com.example.hjh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HJH on 2016/3/12.
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override//首次创建数据库的时候调用，一般可以在这里建库，建表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists stu(_id integer primary key," +
                "user_name text not null,user_pass text not null)");
        ContentValues values = new ContentValues();
        values.put("_id", 10);
        values.put("user_name", "aaaa");
        values.put("user_pass", "2333");
        db.insert("stu", null, values);

    }

    @Override//当数据库版本发生变化时，会自动执行
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

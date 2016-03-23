package com.example.hjh.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HJH on 2016/3/10.
 */
public class Sqlite_activity extends AppCompatActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //每个程序都有自己的数据库，默认是互不干扰
        //创建数据库并打开
         db = openOrCreateDatabase("user.db",MODE_PRIVATE,null);
        //如果熟悉数据库的操作可以使用sql语句来进行操作
        //如果不熟悉，可以使用其ContentValues

        //创建表
        String sql = "create table if not exists user(_id integer primary key," +
                "user_name text not null,user_pass text not null)";
        db.execSQL(sql);
        findViewById(R.id.btn_creatSQL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("_id", 1);
                values.put("user_name", "展示");
                values.put("user_pass", "123");
                db.insert("user", null, values);
                Toast.makeText(Sqlite_activity.this, "好了", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_showSQL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textSQL);
                Cursor cursor = db.query("user",null,"_id>?",new String[]{"0"},null,null,"user_name");
                if(cursor!=null){
                    String[] columnName = cursor.getColumnNames();
                    while(cursor.moveToNext()){
                        String s = "";
                        for(String cn : columnName) {
                            s+=cn+":"+cursor.getInt(cursor.getColumnIndex(cn))+"  ";
                        }
                        textView.setText(s);
                    }
                }
            }
        });
        findViewById(R.id.btn_op).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBOpenHelper dbOpenHelper = new DBOpenHelper(Sqlite_activity.this,"stu.db",null,1);
                SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();//获取一个可读的数据库

                TextView textView = (TextView) findViewById(R.id.textSQL);
                Cursor cursor = sqLiteDatabase.query("stu", null, "_id>?", new String[]{"0"}, null, null, "user_name");
                if(cursor!=null){
                    String[] columnName = cursor.getColumnNames();
                    while(cursor.moveToNext()){
                        String s = "";
                        for(String cn : columnName) {
                            s+=cn+":"+cursor.getInt(cursor.getColumnIndex(cn))+"  ";
                        }
                        textView.setText(s);
                    }
                }
            }
        });
    }


}

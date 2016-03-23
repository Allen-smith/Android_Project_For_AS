package com.example.hjh.database;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HJH on 2016/3/10.
 */
public class SharedPreferences_activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences_main);
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
        findViewById(R.id.but_creat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences对象
//        SharedPreferences perf = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences perf = getSharedPreferences("myperf", MODE_PRIVATE);//参数：文件名，打开模式
                //获取editor 编辑器对象
                SharedPreferences.Editor editor = perf.edit();
                //通过editor存放数据，一般为配置文件
                editor.putString("name", "张三");
                editor.putInt("age", 30);
                editor.putLong("time", System.currentTimeMillis());
                editor.putBoolean("default", true);
                //提交操作
                editor.commit();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.text_sp);
                String text;
                SharedPreferences perf = getSharedPreferences("myperf", MODE_ENABLE_WRITE_AHEAD_LOGGING);
                text="name"+perf.getString("name","")+" ";
                text+="age"+perf.getInt("age", 0)+" ";
                text+="time"+perf.getLong("time", 0)+" ";
                textView.setText(text);
            }
        });

    }
}

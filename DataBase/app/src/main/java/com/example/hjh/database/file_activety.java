package com.example.hjh.database;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by HJH on 2016/3/12.
 */
public class file_activety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_main);
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

        findViewById(R.id.btn_creatFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("/mnt/sdcard/text22.text");
                if (!file.exists()) {
                    Toast.makeText(file_activety.this, "文件不存在，创建", Toast.LENGTH_SHORT).show();
                    try {
                        file.createNewFile();
                        Toast.makeText(file_activety.this, "文件创建成功", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(file_activety.this, "文件已经存在", Toast.LENGTH_SHORT).show();
//                file.delete();
                Toast.makeText(file_activety.this, "文件已经存在"+file_activety.this.getFilesDir(), Toast.LENGTH_SHORT).show();
                //文件操作基本上与java基本一样
            }
        });

        //获取当前应用的目录
        File file = this.getFilesDir();
    }
}

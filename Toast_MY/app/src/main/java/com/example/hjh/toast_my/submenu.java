package com.example.hjh.toast_my;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HJH on 2016/3/9.
 */
public class submenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_main);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu file=menu.addSubMenu("文件");
        SubMenu edit = menu.addSubMenu("编辑");

        file.setHeaderTitle("文件操作");
        file.setHeaderIcon(R.mipmap.ic_launcher);
        file.add(1, 1, 1, "新建");
        file.add(1, 2, 1, "打开");
        file.add(1,3,1,"保存");

        edit.setHeaderTitle("编辑操作");
        edit.setHeaderIcon(R.mipmap.ic_launcher);
        edit.add(2, 1, 1, "复制");
        edit.add(2,2,1,"粘贴");
        edit.add(2,3,1,"剪切");

        //静态加载
        getMenuInflater().inflate(R.menu.sub_menu,menu );

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getGroupId()==1)
            switch (item.getItemId()){
                case 1:
                    Toast.makeText(submenu.this,"新建",Toast.LENGTH_SHORT).show();
                    break;
                case  2:
                    Toast.makeText(submenu.this,"打开",Toast.LENGTH_SHORT).show();
                    break;
                case  3:
                    Toast.makeText(submenu.this,"保存",Toast.LENGTH_SHORT).show();
                    break;
            }
        else
            switch (item.getItemId()){
                case 1:
                    Toast.makeText(submenu.this,"复制",Toast.LENGTH_SHORT).show();
                    break;
                case  2:
                    Toast.makeText(submenu.this,"粘贴",Toast.LENGTH_SHORT).show();
                    break;
                case  3:
                    Toast.makeText(submenu.this,"剪切",Toast.LENGTH_SHORT).show();
                    break;
            }
        return super.onOptionsItemSelected(item);
    }
}

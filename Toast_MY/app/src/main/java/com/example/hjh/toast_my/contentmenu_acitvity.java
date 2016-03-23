package com.example.hjh.toast_my;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HJH on 2016/3/9.
 */
public class contentmenu_acitvity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentmenu_main);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //设置listview显示内容
        showlistview();
    }

    private void showlistview() {
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayList<String> strings = new ArrayList<String>();
        for(int i=0;i<8;i++)
            strings.add(i+"");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,strings);
        listView.setAdapter(arrayAdapter);
        //给listview注册上下文菜单
        this.registerForContextMenu(listView);
    }
    //覆写上下文菜单事件
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置Menu内容
        menu.setHeaderTitle("标题");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.add(1,1,1,"复制");
        menu.add(1,2,1,"粘贴");
        menu.add(1,3,1,"暂停");
        menu.add(1,4,1,"开始");
        menu.add(1,5,1,"剪切");
        menu.add(1,6,1,"重命名");
        //xml静态加载
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //以下为上一行的详细代码
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main,menu);
    }
    //设置点击事件

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                Toast.makeText(contentmenu_acitvity.this,"复制",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(contentmenu_acitvity.this,"粘贴",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(contentmenu_acitvity.this,"暂停",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(contentmenu_acitvity.this,"开始",Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(contentmenu_acitvity.this,"剪切",Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(contentmenu_acitvity.this,"重命名",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

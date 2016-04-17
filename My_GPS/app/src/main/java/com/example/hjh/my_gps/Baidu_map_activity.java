package com.example.hjh.my_gps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

/**
 * Created by HJH on 2016/3/24.
 */
public class Baidu_map_activity extends Activity {
    private MapView mMapView;
    private BaiduMap mbaiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.baidu_map);//获取地图控件引用
        init();
        findViewById(R.id.bbbb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            }
        });
    }

    private void init() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mbaiduMap = mMapView.getMap();
        //设置显示大小：150
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mbaiduMap.setMapStatus(msu);
        registerForContextMenu(findViewById(R.id.bbbb));

//        mMapView.setVisibility(View.INVISIBLE);
        Log.i("tag","init");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.i("tag","creat");
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("标题");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add("sdfes");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.i("tag","select");
        switch (item.getItemId()){
            case R.id.action_comment:
                mbaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.action_site:
                mbaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.action_traffic:
                if(mbaiduMap.isTrafficEnabled()) {
                    mbaiduMap.setTrafficEnabled(false);
                }
                else
                    mbaiduMap.setTrafficEnabled(true);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

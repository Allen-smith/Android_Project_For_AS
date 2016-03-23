package com.example.hjh.systemServer;

import android.app.Activity;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hjh.fourcomponents.R;

/**
 * Created by HJH on 2016/3/14.
 */
public class systemserver_activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.sysser_main);
        //另一种设置layout的方法
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.sysser_main,null);
        super.onCreate(savedInstanceState);


    }
    public void doClick(View view) {
        switch (view.getId()){
            case R.id.btn_judgyNet: {
                ConnectivityManager manager = (ConnectivityManager) systemserver_activity.this.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                if(info!=null){
                    Toast.makeText(systemserver_activity.this,"有网络连接",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(systemserver_activity.this,"无网络连接",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_OCwifi: {
                WifiManager wifiManager = (WifiManager) systemserver_activity.this.getSystemService(WIFI_SERVICE);
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(systemserver_activity.this,"wifi已关闭",Toast.LENGTH_SHORT).show();
                }
                else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(systemserver_activity.this, "wifi已打开", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btn_getsound:{
                AudioManager audioManager = (AudioManager) systemserver_activity.this.getSystemService(AUDIO_SERVICE);
                int max = audioManager.getStreamMaxVolume(audioManager.STREAM_SYSTEM);
                int cur = audioManager.getStreamVolume(audioManager.STREAM_SYSTEM);
                Toast.makeText(systemserver_activity.this, "系统最大音量："+max+"当前音量："+cur, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}

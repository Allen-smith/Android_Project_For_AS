package com.example.hjh.my_alarmmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by HJH on 2016/3/23.
 */
public class AlarmActivity extends Activity{
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("tag", "1111111");
        setContentView(R.layout.clock);
        //加载音乐，并播放
//        mediaPlayer = MediaPlayer.create(this,R)
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();
        //创建一个对话框
        new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟")
                .setMessage("闹钟响了，Go!Go!Go!")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //结束音乐
//                        mediaPlayer.stop();
                        //结束该Activity
                        AlarmActivity.this.finish();
                    }
                }).show();
        Log.i("tag","sadfdsafe");
    }
}

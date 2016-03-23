package com.example.hjh.smsinterception;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by HJH on 2016/3/17.
 */
public class MyMms extends BroadcastReceiver {
    /*
        android系统中彩信接收接口的处理方式：
            1、class ReceivePushTask extends AsyncTask<Intent,Void,Void> ：
                    从数据堆（push—data）获取原始的PDU并解析
            2、void onReceive(Context context, Intent intent)：
                    判断意图对应不;
                    持有锁5秒，让我们有足够的时间去获取任意服务对应的锁
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("tag","接收到Mms短信");
    }
}

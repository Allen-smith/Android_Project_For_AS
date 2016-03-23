package com.example.hjh.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by HJH on 2016/3/13.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"收到了信息"+intent.getAction()+"  "+
                intent.getStringExtra("msg"),Toast.LENGTH_SHORT).show();
        Log.i("msg","收到了信息"+intent.getAction()+"  "+
                intent.getStringExtra("msg"));
    }
}

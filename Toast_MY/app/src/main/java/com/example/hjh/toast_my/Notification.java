package com.example.hjh.toast_my;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by HJH on 2016/3/9.
 */
public class Notification extends Activity implements View.OnClickListener {
    private Button send,cancel;
    android.app.NotificationManager notificationManager;//通知控制类
    int notification_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifi_activity);
        //获取使用通知栏权限
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //初始化按钮
        InitiBtn_Send();
    }

    private void InitiBtn_Send() {
        send = (Button) findViewById(R.id.btn_send);
        send.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send://发送通知
                sendNotification();
                break;
            case R.id.btn_cancel://取消通知
                notificationManager.cancel(notification_ID);
                break;

        }
    }


    private void sendNotification() {
        android.app.Notification.Builder builder = new android.app.Notification.Builder(this);
        //设置图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //手机状态栏的提示
        builder.setTicker("hello");
        //设置时间
        builder.setWhen(System.currentTimeMillis());
        //设置标题
        builder.setContentTitle("标题");
        //设置通知内容
        builder.setContentText("通知内容") ;
        //设置点击后的意图
        Intent intent = new Intent(this,Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        //设置声音提示，指示灯和震动需要设置权限
        builder.setDefaults(android.app.Notification.DEFAULT_SOUND);//声音
        builder.setDefaults(android.app.Notification.DEFAULT_SOUND);//指示灯
        builder.setDefaults(android.app.Notification.DEFAULT_SOUND);//震动
//        builder.setDefaults(android.app.Notification.DEFAULT_ALL);//三效果全开

        //4.1以上的用build（）方法，其它的用getNotification（）方法
        android.app.Notification notification = builder.build();

        //发送通知,id用来识别通知的
        notificationManager.notify(notification_ID,notification);
    }
}

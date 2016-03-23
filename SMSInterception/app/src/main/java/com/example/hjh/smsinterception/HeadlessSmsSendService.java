package com.example.hjh.smsinterception;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by HJH on 2016/3/17.
 */
//响应特定的意图，无需用户干预，发送短信。
public class HeadlessSmsSendService extends Service{
    /*
        android系统中 即时短信回应电话呼入 接口的处理方式：
            默认构造函数：
                super(NoConfirmationSendService.class.getName());//类名将是线程名
                setIntentRedelivery(true);//如果进程在完成任务前杀死，意图应该被再次投递/发送
            意图处理：protected void onHandleIntent(Intent intent)
                。。。。。。
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("tag","service");
        return null;
    }
}

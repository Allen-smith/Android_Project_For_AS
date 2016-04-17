package com.example.hjh.my_gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by HJH on 2016/3/24.
 */
public class ProximityAlertReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isenter = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING,false);
        if(isenter){
            Toast.makeText(context,"进入了",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context,"离开了",Toast.LENGTH_SHORT).show();
    }
}

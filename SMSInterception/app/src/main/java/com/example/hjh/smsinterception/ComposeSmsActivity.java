package com.example.hjh.smsinterception;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by HJH on 2016/3/17.
 */
public class ComposeSmsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.csa_main);
    Log.i("tag","接收到用户发送新的 SMS/MMS 短信的请求");
}
    @TargetApi(19)
    @Override
    protected void onResume() {
        super.onResume();

        final String myPackageName = getPackageName();
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            // App 不是默认短信应用.
            // 显示“当前没有设置为默认的短信应用”界面
            View viewGroup = findViewById(R.id.textview);
            viewGroup.setVisibility(View.VISIBLE);

            // Set up a button that allows the user to change the default SMS app
            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent =
                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                            myPackageName);
                    startActivity(intent);
                }
            });
        } else {
            // App 是默认短信应用.
            // 隐藏“当前没有设置为默认的短信应用”界面
            View viewGroup = findViewById(R.id.textview);
            viewGroup.setVisibility(View.GONE);
            Button button = (Button) findViewById(R.id.button);
            button.setVisibility(View.INVISIBLE);
        }
    }
}

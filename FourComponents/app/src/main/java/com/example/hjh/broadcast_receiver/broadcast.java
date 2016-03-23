package com.example.hjh.broadcast_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hjh.fourcomponents.R;

/**
 * Created by HJH on 2016/3/13.
 */
public class broadcast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_main);

        findViewById(R.id.btn_send_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.broadcast");
                intent.putExtra("msg","渣渣");
                sendBroadcast(intent);
            }
        });
    }
}

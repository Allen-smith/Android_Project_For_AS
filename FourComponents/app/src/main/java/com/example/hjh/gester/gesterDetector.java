package com.example.hjh.gester;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjh.fourcomponents.R;

/**
 * Created by HJH on 2016/3/14.
 */
public class gesterDetector extends Activity {
    private TextView textView;
    private GestureDetector detector;
    class myGestureListener extends GestureDetector.SimpleOnGestureListener{
        //滑动事件
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()-e2.getX()>50){
                Toast.makeText(com.example.hjh.gester.gesterDetector.this,"向左划",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(com.example.hjh.gester.gesterDetector.this,"向右划",Toast.LENGTH_SHORT).show();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.gesturedete);
        super.onCreate(savedInstanceState);
        textView = (TextView) findViewById(R.id.textView);
        //初始化
        detector = new GestureDetector(new myGestureListener());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override//可以捕获到触发屏幕事件
            public boolean onTouch(View v, MotionEvent event) {
                //进行转发,给
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}

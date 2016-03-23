package com.example.hjh.my_telephonymanager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView showview;
                //存放状态名数组
                String[] statusName = {"设备编号", "软件版本", "网络运营商代号", "网络运营商名称", "手机制式"
                        , "设备当前位置", "SIM卡的国别", "SIM卡序列号", "SIM卡状态"};
                //手机状态的集合
                ArrayList<String> statusValues = new ArrayList<String>();

                showview = (ListView) findViewById(R.id.listview);

                //获取系统的 电话管理器
                TelephonyManager telephonyManager = (TelephonyManager)
                        getSystemService(Context.TELEPHONY_SERVICE);

                //获取设备编号
                statusValues.add(telephonyManager.getDeviceId());
                //获取系统平台的版本
                statusValues.add(telephonyManager.getDeviceSoftwareVersion()
                        != null ? telephonyManager.getDeviceSoftwareVersion() : "未知");
                //获取网络运营商代号
                statusValues.add(telephonyManager.getNetworkOperator());
                //获取网络运营商名称
                statusValues.add(telephonyManager.getNetworkOperatorName());
                //获取手机网络类型
                statusValues.add("" + telephonyManager.getPhoneType());
                //获取设备所在位置
                statusValues.add(telephonyManager.getCellLocation()
                        != null ? telephonyManager.getCellLocation().toString() : "未知位置");
                //获取SIM卡的国别
                statusValues.add(telephonyManager.getSimCountryIso());
                //获取SIM卡序列号
                statusValues.add(telephonyManager.getSimSerialNumber());
                //获取SIM卡状态
                statusValues.add("" + telephonyManager.getSimState());

                ArrayList<Map<String, String>> status = new ArrayList<Map<String, String>>();
                for (int i = 0; i < statusValues.size(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name", statusName[i]);
                    map.put("value", statusValues.get(i));
                    status.add(map);
                    Log.i("map", statusName[i] + " : " + statusValues.get(i));
                }

                SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, status, R.layout.list,
                        new String[]{"name", "value"}, new int[]{R.id.name, R.id.value});

                showview.setAdapter(simpleAdapter);

            }
        });
        //监听来电功能只有一直开着程序才有用，或者放在service中后台运行
        findViewById(R.id.btn_listen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取系统的 电话管理器
                TelephonyManager telephonyManager = (TelephonyManager)
                        getSystemService(Context.TELEPHONY_SERVICE);
                //创建一个通话状态监听器
                PhoneStateListener phoneStateListener = new PhoneStateListener(){
                    @Override
                    public void onCallStateChanged(int state, String incomingNumber) {
                        super.onCallStateChanged(state, incomingNumber);
                        switch (state){
                            //无任何状态
                            case TelephonyManager.CALL_STATE_IDLE:
                                break;
                            case TelephonyManager.CALL_STATE_OFFHOOK:
                                break;
                            case TelephonyManager.CALL_STATE_RINGING:
                                Log.i("tag","来电了");
                                OutputStream os = null;
                                try{
                                    os =openFileOutput("phoneList",MODE_APPEND);
                                }catch (FileNotFoundException e){
                                    e.printStackTrace();
                                }
                                PrintStream ps = new PrintStream(os);
                                ps.println(new java.util.Date() + "来电：" + incomingNumber);
                                ps.close();
                                break;
                            default:
                                break;
                        }
                    }
                };
                //监听电话通话状态的改变
                telephonyManager.listen(phoneStateListener,phoneStateListener.LISTEN_CALL_STATE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

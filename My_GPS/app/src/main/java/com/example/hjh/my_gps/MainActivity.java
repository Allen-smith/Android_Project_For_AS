package com.example.hjh.my_gps;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button get;
    LocationManager locationManager;
    LocationProvider locationProvider;
    Location location;
    EditText editText;

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
        get = (Button) findViewById(R.id.btn_get);
        editText = (EditText) findViewById(R.id.et);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                //获取所有locationProvider的名称
                List<String> name = new ArrayList<String>();
                name = locationManager.getAllProviders();
                for (String s : name) {
                    Log.i("tag", s);
                }

                //获取符合条件的locationprovider
                Criteria criteria = new Criteria();
                //要求：免费的
                criteria.setCostAllowed(false);
                //要求：能提供高度的
                criteria.setAltitudeRequired(true);
                //要求：能提供方向信息的
                criteria.setBearingRequired(true);
                //获取符合条件的locationProvider的名称
                List<String> name2 = new ArrayList<String>();
                name2 = locationManager.getAllProviders();
                for (String s : name2) {
                    Log.i("tag", s);
                }

                //获取provider，和location对象
                locationProvider = locationManager.getProvider(name2.get(0));
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取最近的定位信息
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                UpdateLocation(location);
                //设置每隔3秒获取一次GPS定位信息
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 8, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //当GPS位置发生变化时，更新位置
                        UpdateLocation(location);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        UpdateLocation(locationManager.getLastKnownLocation(provider));
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        UpdateLocation(location);
                    }
                });


            }
        });

        //临近警告设置
        findViewById(R.id.begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                //定义警告点中心坐标,半径
                double longitude = 113.66819628;
                double latitude = 24.77960867;
                float rad = 50000;
                //定义Intent，并包装为pendingIntent
                Intent intent = new Intent(MainActivity.this, ProximityAlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, -1, intent, 0);
                //添加临近警告
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.addProximityAlert(latitude, longitude, rad, -1, pendingIntent);
            }
        });
        findViewById(R.id.btn_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到百度地图
                Intent intent = new Intent(MainActivity.this,Baidu_map_activity.class);
                startActivity(intent);
            }
        });
    }

    private void UpdateLocation(Location location) {
        if(location!=null){
            StringBuilder sb = new StringBuilder();
            sb.append("实时的位置信息：\n");
            sb.append("经度：");
            sb.append(location.getLongitude()+"\n");
            sb.append("纬度：");
            sb.append(location.getLatitude()+"\n");
            sb.append("高度：");
            sb.append(location.getAltitude()+"\n");
            sb.append("速度：");
            sb.append(location.getSpeed()+"\n");
            sb.append("方向：");
            sb.append(location.getBearing()+"\n");
            editText.setText(sb);
        }
        else
            editText.setText("");
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


        return super.onOptionsItemSelected(item);
    }
}

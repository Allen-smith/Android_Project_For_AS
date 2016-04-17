package com.example.hjh.papersystem2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by HJH on 2016/4/10.
 */
public class Home_activity extends AppCompatActivity{
    private ArrayList<Fragment> fragmentslist;
    private ArrayList<String> title_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initViewPage();
    }

    private void initViewPage() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpage_home);
        fragmentslist = new ArrayList<>();

        Fragment search = new Search();
        Fragment download_control = new Download_Control();
        Fragment personal = new Personal();

        fragmentslist.add(search);
        fragmentslist.add(download_control);
        fragmentslist.add(personal);

        title_list.add("1");
        title_list.add("2");
        title_list.add("3");

        findViewById(R.id.img_download).setBackgroundColor(getResources().getColor(R.color.white));
        findViewById(R.id.img_personal).setBackgroundColor(getResources().getColor(R.color.white));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentslist,title_list));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //设置标题页的转换
            }
            @Override
            public void onPageSelected(int position) {
                findViewById(R.id.img_search).setBackgroundColor(getResources().getColor(R.color.white));
                findViewById(R.id.img_download).setBackgroundColor(getResources().getColor(R.color.white));
                findViewById(R.id.img_personal).setBackgroundColor(getResources().getColor(R.color.white));
                switch (position){
                    case 0:
                        findViewById(R.id.img_search).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case 1:
                        findViewById(R.id.img_download).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case 2:
                        findViewById(R.id.img_personal).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}

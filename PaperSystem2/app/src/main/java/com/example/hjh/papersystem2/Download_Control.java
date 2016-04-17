package com.example.hjh.papersystem2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HJH on 2016/4/10.
 */
public class Download_Control extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View downloadview = inflater.inflate(R.layout.download_control,container,false);
        return downloadview;
        //设置点击事件，显示listview，
    }
}

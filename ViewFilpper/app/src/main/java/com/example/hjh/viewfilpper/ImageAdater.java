package com.example.hjh.viewfilpper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by HJH on 2016/3/5.
 */
public class ImageAdater extends BaseAdapter{

    private int []res;
    private Context context;
    public ImageAdater(int []res,Context context){
        this.res=res;
        this.context =context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return res[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(res[position]%res.length);
        imageView.setLayoutParams(new Gallery.LayoutParams(400, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}

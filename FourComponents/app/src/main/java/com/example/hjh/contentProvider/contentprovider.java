package com.example.hjh.contentProvider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.hjh.fourcomponents.R;

/**
 * Created by HJH on 2016/3/12.
 */
public class contentprovider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentprovider);
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
                ContentResolver cr = getContentResolver();
                Cursor c =cr.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if(c!=null){
                    while(c.moveToNext()){
                        int id = c.getInt(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        Log.i("info",id+"");
                        Log.i("info",name);
                    }
                }
                c.close();

            }
        });
    }
    public void in(){//向联系人插入一项数据
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        Uri uri = cr.insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long raw_contact_id = ContentUris.parseId(uri);
        values.clear();
        //插入人名
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,"abc");
        values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        uri = cr.insert(ContactsContract.Data.CONTENT_URI,values);
        //加入电话信息
        values.clear();
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"1232321322");
        values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        uri = cr.insert(ContactsContract.Data.CONTENT_URI,values);
    }
}

package com.example.hjh.toast_my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HJH on 2016/3/9.
 */
public class dalog_activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dalog_activity);
        //确认对话框
        InitBut1();
        //单选对话框
        InitBut2();
        //多选对话框
        InitBut3();
        //列表对话框
        InitBut4();
        //自定义对话框
        InitBut5();
    }

    private void InitBut5() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.my_dialog,null);
        findViewById(R.id.btn_my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(dalog_activity.this);
                alertDialog.setTitle("自定义对话框");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setView(view);
                //从AlertDialog.Builder中获取AlertDialog
                AlertDialog a = alertDialog.create();
                //显示AlertDialog
                a.show();
            }
        });
    }

    private void InitBut4() {
        findViewById(R.id.butLiner).setOnClickListener(new View.OnClickListener() {
            String[] s = {"男","女","小孩","大人"};
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(dalog_activity.this);
                alertDialog.setTitle("列表对话框");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setItems(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(dalog_activity.this, "点击了：" + s[which],
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //从AlertDialog.Builder中获取AlertDialog
                AlertDialog a = alertDialog.create();
                //显示AlertDialog
                a.show();
            }
        });
    }

    private void InitBut3() {
        findViewById(R.id.but_more).setOnClickListener(new View.OnClickListener() {
            String[] s = {"男","女","小孩","大人"};
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(dalog_activity.this);
                alertDialog.setTitle("多选对话框");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                //参数：①选项名；②默认选项的位置；③点击选择后触发的事件
                alertDialog.setMultiChoiceItems(s, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(dalog_activity.this, "选择了：" + s[which], Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(dalog_activity.this, "取消选择：" + s[which], Toast.LENGTH_SHORT).show();
                    }
                });
                //隐藏对话框
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //从AlertDialog.Builder中获取AlertDialog
                AlertDialog a = alertDialog.create();
                //显示AlertDialog
                a.show();
            }
        });
    }

    private void InitBut2() {
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            String[] s = {"男","女","小孩","大人"};
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(dalog_activity.this);
                alertDialog.setTitle("单选对话框");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                //参数：①选项名；②默认选项的位置；③点击选择后触发的事件
                alertDialog.setSingleChoiceItems(s, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//which：表示选择的第几个
                        String ss = s[which];
                        Toast.makeText(dalog_activity.this,"选择了："+ ss,Toast.LENGTH_SHORT).show();
                    }
                });
                //从AlertDialog.Builder中获取AlertDialog
                AlertDialog a = alertDialog.create();
                //显示AlertDialog
                a.show();
            }
        });
    }

    private void InitBut1() {
    findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //创建并显示dialog
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(dalog_activity.this);
            alertDialog.setTitle("确认对话框");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("对话框提示内容");
            alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(dalog_activity.this, "点击了确认按钮", Toast.LENGTH_SHORT);
                }
            });
            alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(dalog_activity.this, "点击了取消按钮", Toast.LENGTH_SHORT);
                }
            });
            //从AlertDialog.Builder中获取AlertDialog
            AlertDialog a = alertDialog.create();
            //显示AlertDialog
            a.show();
        }
    });
    }


    @Override
    public void setTitle(CharSequence title) {
//        super.setTitle("Dialog演示");
    }
}

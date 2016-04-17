package com.example.hjh.papersystem2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by HJH on 2016/4/4.
 */
public class Login_activity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText username ;
    private EditText userpassword;
    private String name;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登录");
//        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent.getIntExtra("flag",0)==1) {
            toolbar.setNavigationIcon(R.drawable.back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //返回个人中心
                    onBackPressed();
                }
            });
        }
        username = (EditText) findViewById(R.id.username);
        userpassword = (EditText) findViewById(R.id.userpassword);

    }


    public void doClick(View view) {
        switch (view.getId()){
            case R.id.txt_findpassword:{
                Log.i("abc","forget password");
                break;
            }
            case R.id.txt_register:{
                Log.i("abc","register");
                Intent intent = new Intent(this,Register_activity.class);
                startActivity(intent);
                break;
            }
            case R.id.login_noName:{
                Log.i("abc","login");
                Intent intent = new Intent(this,Home_activity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_login:{
                Log.i("abc","login");
                login();
                break;
            }
        }
    }

    private void login() {
        //用户登录信息检查
        name = username.getText().toString();
        password = userpassword.getText().toString();
        Pattern p1 = Pattern.compile("\\d{11}+");
        Pattern p2 = Pattern.compile("\\d{8,}+");
        Matcher m1 = p1.matcher(name);
        Matcher m2 = p2.matcher(password);
        if(m1.matches() && m2.matches()){//先进行本地的格式检查
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("UserName",name);
            query.findObjects(this, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    if(list.size()!=0){//返回结果不为空
                        for(User u : list){//进行密码对比，密码匹配则进行登录，保存登录信息
                            if(u.getUserPassword().equals(password)){

                                //保存登录信息
                                //——————————————————————————————————————————————————————————————————————————————————————
                                Intent intent = new Intent(Login_activity.this,Home_activity.class);
                                startActivity(intent);
                                toast("登录成功！");
                            }
                        }
                    }
                    else {
                        toast("该学号还没注册！");
                    }
                }
                @Override
                public void onError(int i, String s) {
                    toast("请检查网络！");
                }
            });
        }
        else{
            toast("学号或密码的格式不对！");
        }
    }

    private void toast(String s){
        Toast.makeText(Login_activity.this,s,Toast.LENGTH_SHORT);
    }

}

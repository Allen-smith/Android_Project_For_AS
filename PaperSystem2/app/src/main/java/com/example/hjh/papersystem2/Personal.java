package com.example.hjh.papersystem2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hjh.papersystem2.FileSelector.CallbackBundle;
import com.example.hjh.papersystem2.FileSelector.OpenFileDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HJH on 2016/4/10.
 */
public class Personal extends Fragment {
    private Dialog chosefile;
    private Dialog uploadfile;
    private LinearLayout layout;
    private String filepath;

    private ArrayAdapter adapter;

    private String PaperCollege = "计算机科学学院";//初始默认值
    private String PaperName = null;
    private String PaperTeacher = null;
    private String PaperClass = null;
    private int PaperYear;

    private Spinner college ;
    private Spinner subject ;
    private Spinner teacher ;
    private Spinner paper_class ;
    private TextView paper_year ;
    private ImageButton year_up;
    private ImageButton year_down;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (LinearLayout) getLayoutInflater(Bundle.EMPTY).inflate(R.layout.upload_file,null);
        return inflater.inflate(R.layout.personal,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button upload = (Button) getActivity().findViewById(R.id.btn_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v.getId());
                Log.i("abc","sssssssss");
            }
        });
        Button landout = (Button) getActivity().findViewById(R.id.btn_landout);
        Init_uploadfile(getActivity());
    }

    private void Init_uploadfile(Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setTitle("设置文件相关内容")
                .setView(layout)
                .setPositiveButton("确定上传", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消上传", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        //设置各个spinner的初始值，文件名 在选择后再设置
        init();
        init_college();//先选学院
        init_subject("计算机科学学院",layout.getContext());//根据学院给出
        init_teacher("计算机科学学院",layout.getContext());//老师选项也是根据学院给出
        init_paperclass("计算机科学学院",layout.getContext());//根据学院给出
        init_paperyear();
    }

    private void init() {
        college = (Spinner) layout.findViewById(R.id.upload_file_college);
        subject = (Spinner) layout.findViewById(R.id.upload_file_name);
        teacher = (Spinner) layout.findViewById(R.id.upload_file_teacher);
        paper_class = (Spinner) layout.findViewById(R.id.upload_file_paperclass);
        paper_year = (TextView) layout.findViewById(R.id.upload_file_year);

        year_up = (ImageButton) layout.findViewById(R.id.btn_year_up);
        year_down = (ImageButton) layout.findViewById(R.id.btn_year_down);
    }

    private void init_paperyear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        paper_year.setText(str);
        PaperYear = Integer.parseInt(str);

        year_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaperYear++;
                paper_year.setText(PaperYear+"");
            }
        });

        year_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaperYear--;
                paper_year.setText(PaperYear+"");
            }
        });
    }

    private void init_paperclass(String sInfo,Context context) {
        Resources res = getResources();
        String[] _class_name;
        switch(sInfo){
            case "计算机科学学院":{
                _class_name = res.getStringArray(R.array.computer_class_name);
                break;
            }
//            case "体育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "化学与环境工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "医学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "外语学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "教育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "数学与信息科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "文学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "旅游与地理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "经济管理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "美术学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东生命科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "音乐学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "韶州师范学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东食品农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
            default:
                _class_name = new String[1];
                _class_name[0] = new String("暂时无选项");
                break;
        }
        Log.i("abc",sInfo);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,_class_name);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        paper_class.setAdapter(adapter);
        //设置监听事件
        paper_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    PaperClass = null;
                }
                else {
                    PaperClass = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置默认值
        paper_class.setVisibility(View.VISIBLE);
    }
    private void init_teacher(String sInfo,Context context) {
        Resources res = getResources();
        String[] _class_name;
        switch(sInfo){
            case "计算机科学学院":{
                _class_name = res.getStringArray(R.array.computer_class_name);
                break;
            }
//            case "体育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "化学与环境工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "医学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "外语学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "教育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "数学与信息科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "文学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "旅游与地理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "经济管理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "美术学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东生命科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "音乐学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "韶州师范学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东食品农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
            default:
                _class_name = new String[1];
                _class_name[0] = new String("暂时无选项");
                break;
        }
        Log.i("abc",sInfo);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,_class_name);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        teacher.setAdapter(adapter);
        //设置监听事件
        teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    PaperTeacher = null;
                }
                else {
                    PaperTeacher = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //设置默认值
        teacher.setVisibility(View.VISIBLE);
    }

    private void init_subject(String sInfo,Context context) {
        Resources res = getResources();
        String[] _class_name;
        switch(sInfo){
            case "计算机科学学院":{
                _class_name = res.getStringArray(R.array.computer_class_name);
                break;
            }
//            case "体育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "化学与环境工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "医学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "外语学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
//            case "教育学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "数学与信息科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "文学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "旅游与地理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "经济管理学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "美术学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东生命科学学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "音乐学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "韶州师范学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东食品农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }case "英东农业科学与工程学院":{
//                _class_name = res.getStringArray(R.array.computer_class_name);
//                break;
//            }
            default:
                _class_name = new String[1];
                _class_name[0] = new String("暂时无选项");
                break;
        }
        Log.i("abc",sInfo);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,_class_name);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        subject.setAdapter(adapter);
        //设置监听事件
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    PaperName = null;
                }
                else {
                    PaperName = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //设置默认值
        subject.setVisibility(View.VISIBLE);
    }

    private void init_college(){

        Resources res = getResources();
        String[] college_name =res.getStringArray(R.array.college_name);
        //将可选内容与ArrayAdapter连接起来
        ArrayAdapter adapter = new ArrayAdapter<String>(layout.getContext(),android.R.layout.simple_spinner_item,college_name);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(college == null)
            Log.e("abc","college");
        if(adapter == null)
            Log.e("abc","adapter");
        //将adapter 添加到spinner中
        college.setAdapter(adapter);
        //添加事件Spinner事件监听
        college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //点击后，更新学科选项，并设置保存选择的值
                PaperCollege = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //设置初始选择值，
        //设置默认值
        college.setVisibility(View.VISIBLE);
    }


    private void showDialog(int id) {
        if(chosefile!=null){
            chosefile.show();
        }
        else{
            chosefile = onCreateDialog(id);
            chosefile.show();
        }

    }

    protected Dialog onCreateDialog(int id) {
        Map<String, Integer> images = new HashMap<String, Integer>();
        // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
        images.put(OpenFileDialog.sRoot, R.mipmap.ic_launcher);   // 根目录图标
        images.put(OpenFileDialog.sParent, R.mipmap.ic_launcher);    //返回上一层的图标
        images.put(OpenFileDialog.sFolder, R.mipmap.ic_launcher);   //文件夹图标
        images.put("doc", R.mipmap.ic_launcher);   //doc文件图标
        images.put("docx", R.mipmap.ic_launcher);   //docx文件图标
        images.put(OpenFileDialog.sEmpty, R.mipmap.ic_launcher);
        Dialog dialog = OpenFileDialog.createDialog(id, getActivity(), "打开文件", new CallbackBundle() {
            //回调时间
            @Override
            public void callback(Bundle bundle) {
                //获取选中文件的路径
                filepath = bundle.getString("path");
                //打开文件上传页面：显示文件，选择文件的各个属性；
                //确定则开始上传，显示进度
                Log.i("abc",filepath);
            }
        }, ".doc;.docx;", images);
        return dialog;
    }

}

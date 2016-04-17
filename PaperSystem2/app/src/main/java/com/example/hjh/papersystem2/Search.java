package com.example.hjh.papersystem2;

import android.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by HJH on 2016/4/7.
 */
public class Search extends Fragment {
    private View searchview;
    private ArrayAdapter adapter;
    private LinearLayout layout;
    //listview
    private ListView listview_result;
    private ArrayList<Map<String,Object>> date = new ArrayList<>();
    private Map<String, Object> map = new HashMap<String, Object>();

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private Button btn_search;
    private String PaperCollege = "计算机科学学院";//初始默认值
    private String PaperName = null;
    private String PaperTeacher = null;
    private String PaperClass = null;

    private Spinner college ;
    private Spinner subject ;
    private Spinner teacher ;
    private Spinner paper_class ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchview = inflater.inflate(R.layout.search,container,false);
        //初始化搜索对话框的布局
        layout = (LinearLayout) getLayoutInflater(Bundle.EMPTY).inflate(R.layout.search_item,null);

        college = (Spinner) layout.findViewById(R.id.search_paper_college);
        subject = (Spinner) layout.findViewById(R.id.search_paper_name);
        teacher = (Spinner) layout.findViewById(R.id.search_paper_teacher);
        paper_class = (Spinner) layout.findViewById(R.id.search_paper_class);

        listview_result = (ListView) searchview.findViewById(R.id.list_paper);
        btn_search = (Button) searchview.findViewById(R.id.search);
        //初始化对话框选项，以及对话框
        init();
        setDialogView();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialog();
            }
        });
        return searchview;
    }

    private void show_dialog() {
        alertDialog.show();
    }

    private void setDialogView() {
        builder=new AlertDialog.Builder(getContext())
                //设置标题
                .setTitle("搜索选项设置")
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //搜索操作,返回结果——文件名，组成链表，显示在lisview中
                        Log.i("abc", "search");
                        BmobQuery<Paper> query = new BmobQuery<Paper>();
                        //添加查询条件
                        query.addWhereEqualTo("PaperCollege",PaperCollege);
                        if(PaperName!=null){query.addWhereEqualTo("PaperName",PaperName);}
                        if(PaperTeacher!=null){query.addWhereEqualTo("PaperTeacher",PaperTeacher);}
                        if(PaperClass!=null){query.addWhereEqualTo("PaperClass",PaperClass);}
                        Log.i("abc","PaperCollege--"+PaperCollege+"PaperName--"+PaperName+"PaperTeacher--"+PaperTeacher+"PaperClass--"+PaperClass);
                        //查询，并获取结果
                        query.findObjects(getContext(),new FindListener<Paper>() {
                            @Override
                            public void onSuccess(List<Paper> list) {
                                Log.i("abc","一共有多少条数据："+list.size());
                                if(list.size()==0){//如果该条件下找不任何数据，则提示“找不到相关文档”
                                    Toast.makeText(searchview.getContext(),"对不起，找不到相关文档！",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                for(Paper paper : list){
                                    Log.i("abc","数据名："+paper.getPaperFileName());
                                    //添加数据
                                    map.put("img",R.mipmap.ic_launcher);
                                    map.put("filename",paper.getPaperFileName());
                                    map.put("college",paper.getPaperCollege());
                                    map.put("name",paper.getPaperName());
                                    map.put("teacher",paper.getPaperTeacher());
                                    map.put("year",paper.getPaperYear());
                                    date.add(map);
                                    Log.i("abc","PaperCollege--"+paper.getPaperCollege()+"PaperName--"+paper.getPaperName()+"PaperTeacher--"+paper.getPaperTeacher()+"PaperClass--"+paper.getPaperYear());
                                }
                                //设置适配器
                                SimpleAdapter simpleAdapter = new SimpleAdapter(searchview.getContext(),date,R.layout.search_result_listview,
                                        new String[]{"img","filename","college","name","teacher","year"},
                                        new int[]{R.id.img_list,R.id.result_paper_filename,R.id.result_paper_college,
                                                R.id.result_paper_name,R.id.result_paper_teacher,R.id.result_paper_year});
                                listview_result.setAdapter(simpleAdapter);
                            }

                            @Override
                            public void onError(int i, String s) {

                            }
                        });
                        //将结果绑定到listview中
                        alertDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消搜索，不做任何处理
                        Log.i("abc", "cancle_search");
                        alertDialog.dismiss();
                    }
                });
        alertDialog = builder.create();
    }
    private void init(){ //初始化搜索选项的 可选项目

            init_college();//先选学院
            init_subject("计算机科学学院",layout.getContext());//根据学院给出
            init_teacher("计算机科学学院",layout.getContext());//老师选项也是根据学院给出
            init_paperclass("计算机科学学院",layout.getContext());//根据学院给出
        }

        private void init_paperclass(String sInfo,Context context) {
            Resources res = getResources();
            String[] _class_name;
            String[] temp;
            switch(sInfo){
                case "计算机科学学院":{
                    temp = res.getStringArray(R.array.computer_class_name);
                    _class_name = new String[temp.length+1];
                    _class_name[0] = "无条件";
                    for(int i=1;i<=temp.length;i++)
                        _class_name[i] = temp[i-1];
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
            String[] temp;
            switch(sInfo){
                case "计算机科学学院":{
                    temp = res.getStringArray(R.array.computer_class_name);
                    _class_name = new String[temp.length+1];
                    _class_name[0] = "无条件";
                    for(int i=1;i<=temp.length;i++)
                        _class_name[i] = temp[i-1];
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
            String[] temp;
            switch(sInfo){
                case "计算机科学学院":{
                    temp = res.getStringArray(R.array.computer_class_name);
                    _class_name = new String[temp.length+1];
                    _class_name[0] = "无条件";
                    for(int i=1;i<=temp.length;i++)
                        _class_name[i] = temp[i-1];
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

}

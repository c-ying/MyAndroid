package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.data.NoteOperator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends Fragment {
    ListView listView;
    Button add;//添加按钮
    Button ceshi;
    TextView note_id;//向其他界面传值
    ArrayList<HashMap<String, String>> list;


    public MyFragment1() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        add = (Button) view.findViewById(R.id.add);


        //通过list获取数据库表中的所有id和title，通过ListAdapter给listView赋值
        final NoteOperator noteOperator = new NoteOperator(getActivity());
        list = noteOperator.getNoteList();
        final ListAdapter listAdapter = new SimpleAdapter(getActivity(), list, R.layout.item,
                new String[]{"id", "title"}, new int[]{R.id.note_id, R.id.note_title});
        listView.setAdapter(listAdapter);

        //通过添加界面传来的值判断是否要刷新listView
        /*Intent intent = getIntent();
        int flag = intent.getIntExtra("Insert", 0);
        if (flag == 1) {
            list = noteOperator.getNoteList();
            listView.setAdapter(listAdapter);
        }*/

        if (list.size() != 0) {
            //点击listView的任何一项跳到详情页面
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long i) {

                    String id = list.get(position).get("id");
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DetailActivity.class);
                    intent.putExtra("note_id", Integer.parseInt(id));
                    startActivity(intent);


                }
            });

            //长按实现对列表的删除
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("确定删除？");
                    builder.setTitle("提示");

                    //添加AlterDialog.Builder对象的setPositiveButton()方法
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String id = list.get(position).get("id");
                            noteOperator.delete(Integer.parseInt(id));
                            list.remove(position);
                            //listAdapter.notify();
                            listView.setAdapter(listAdapter);
                        }
                    });

                    //添加AlterDialog.Builder对象的setNegativeButton()方法
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                    return true;
                }
            });
        } else {
            Toast.makeText(getActivity(), "暂无待办事项，请添加", Toast.LENGTH_SHORT).show();
        }


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AddActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }



}

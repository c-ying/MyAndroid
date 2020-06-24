package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment4 extends Fragment implements AdapterView.OnItemClickListener {


   private FragmentManager fManager;
    //private ArrayList<Data> datas=null;
    private ListView list_news;
    String username4;
    TextView bt7;//退出登录
    //View tuichu;

    public MyFragment4(){}

    @Override
    public void onAttach(Context context) {   //获取MainActivity传递的用户名
        super.onAttach(context);
        username4=((MainActivity)getActivity()).getUsername();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four,container,false);

        TextView f4_username=(TextView)view.findViewById(R.id.f4_username);
       f4_username.setText(username4);

        bt7=(TextView)view.findViewById(R.id.bt7);
        bt7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });
       return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}

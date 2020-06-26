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
import android.widget.ImageButton;
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

    String username4;
    TextView security;
    ImageButton update;//修改资料
    TextView bt7;//退出登录
    TextView nicheng;
    public MyFragment4(){}
    String nichengstr;

    //定义回调接口
    public interface MyListener{
        public void sendValue();
        public void sendValue1();
    }
    private MainActivity myListener;

    @Override
    public void onAttach(Context context) {   //获取MainActivity传递的用户名
        super.onAttach(context);
        username4=((MainActivity)getActivity()).getUsername();
        myListener= (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four,container,false);
        //显示用户名
        TextView f4_username=(TextView)view.findViewById(R.id.f4_username);
        f4_username.setText(username4);


        //修改资料,跳转到资料修改页面
        update=(ImageButton)view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myListener.sendValue();
            }
        });

        //修改密码
        security=(TextView)view.findViewById(R.id.security);
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue1();
            }
        });

        //获取昵称
        final UserService userService=new UserService(getActivity());
        User user = new User();
        user.username=username4;
        nichengstr=userService.getNicheng(user);
        nicheng=(TextView)view.findViewById(R.id.nicheng);
        nicheng.setText(nichengstr);

        //退出程序
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

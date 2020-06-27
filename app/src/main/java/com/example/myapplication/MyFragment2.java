package com.example.myapplication;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * Created by Jay on 2015/8/28 0028.
 */

public class MyFragment2 extends Fragment {
    //private TabHost tabHost;//tabhost分页
    private View view;
    LocalActivityManager localActivityManager;
    TabHost tabHost;
    TabWidget tabWidget;

    public MyFragment2() {
       super();
        //tabHost=(TabHost)findViewById(android.R.id.tabhost); fragment中不能使用这句 不直接继承activity

       //tabHost.setup(localActivityManager);
//四个分页 使用tabhost
       // tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("闹钟").setContent(R.id.tabAlarm));
        //tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("时钟").setContent(R.id.tabTime));
       // tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("计时器").setContent(R.id.tabTimer));
      //  tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("秒表").setContent(R.id.tabStopWatch));
    }

    @Override
    public void onResume() {
        super.onResume();
        localActivityManager.dispatchResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        localActivityManager.dispatchPause(getActivity().isFinishing());
    }

    /**
     * 找到Tabhost布局
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第二个Fragment");

        findTabView();
        localActivityManager = new LocalActivityManager(getActivity(), true);
        localActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(localActivityManager);
        Resources localResources = getResources();
        //Intent localIntent1 = new Intent();
        //localIntent1.setClass(getActivity(), AlarmView.class);用intent方法去启动一个class 不知道这两句什么用处 暂定
        tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("闹钟").setContent(R.id.tabAlarm));
        tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("时钟").setContent(R.id.tabTime));
        tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("计时器").setContent(R.id.tabTimer));
        tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("秒表").setContent(R.id.tabStopWatch));




        return view;

    }

    public void findTabView() {
        tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        tabWidget = (TabWidget) view.findViewById(android.R.id.tabs);
    }

}

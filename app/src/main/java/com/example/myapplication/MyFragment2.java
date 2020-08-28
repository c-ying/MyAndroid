package com.example.myapplication;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import androidx.fragment.app.Fragment;



public class MyFragment2 extends Fragment {
    protected View mMainView;
    protected Context mContext;
    LocalActivityManager localActivityManager;
    TabHost tabHost;
    TabWidget tabWidget;

    public MyFragment2() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_tabhost,container,false);
       //TextView txt_content = (TextView) mMainView.findViewById(R.id.txt_content);
        //txt_content.setText("第二个Fragment");
        findTabView();
        localActivityManager = new LocalActivityManager(getActivity(), true);
        localActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(localActivityManager);
        Resources localResources = getResources();

        Intent localIntent1 = new Intent();
        localIntent1.setClass(getActivity(), Tab1Activity.class);
        tabHost.addTab(tabHost
                .newTabSpec("专注时刻")
                .setIndicator("专注时刻")//,
                //localResources.getDrawable(R.drawable.ic_launcher))
                .setContent(localIntent1));

        Intent localIntent2 = new Intent();
        localIntent2.setClass(getActivity(), Tab2Activity.class);

        TabHost.TabSpec localTabSpec2 = tabHost.newTabSpec("轻松时刻");
        localTabSpec2.setIndicator("轻松时刻");
        localTabSpec2.setContent(localIntent2);
        tabHost.addTab(localTabSpec2);


        return mMainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
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
    public void findTabView() {
        tabHost = (TabHost) mMainView.findViewById(android.R.id.tabhost);
        tabWidget = (TabWidget) mMainView.findViewById(android.R.id.tabs);
    }


}

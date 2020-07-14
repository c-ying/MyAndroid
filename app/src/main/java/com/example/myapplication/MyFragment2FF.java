package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class MyFragment2FF extends Activity {
    private TabHost tabHost;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tabhostac);
        tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
//四个分页 使用tabhost
        tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("闹钟").setContent(R.id.tabAlarm));
        tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("倒计时").setContent(R.id.tabTimer));
        tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("秒表").setContent(R.id.tabStopWatch));
    }
}

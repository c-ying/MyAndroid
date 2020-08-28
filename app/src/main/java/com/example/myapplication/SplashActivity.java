package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    long showTime = 5;  //广告时间/秒
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textview = findViewById(R.id.text);
        //延迟5000ms跳转到主界面
        handler.postDelayed(myRunnable, showTime * 1000);
        handler.sendEmptyMessage(111);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 111)
            {
                textview.setText(showTime+"  关闭");
                showTime--;
                if (showTime > 0) {
                    handler.sendEmptyMessageDelayed(111, 1000);  //一秒后给自己发送信息
                }
            }
        }
    };

    Runnable  myRunnable=new Runnable() {
        @Override
        public void run() {
            jundToMainActivity();
        }
    };

    //跳转到主页，关闭自身页面
    public void jundToMainActivity(){
        Intent intent = new Intent(SplashActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //关闭页面
    public void closeSplash(View view){
        Log.e("TAG","closeSplash");
        handler.removeCallbacks(myRunnable);//移出Runnable对象
        jundToMainActivity();

    }
    //回退键的监听方法，
    // 这里如果直接关闭页面，线程没有关闭的话，5秒后还是会启动主页面，除非移出线程对象
    @Override
    public void onBackPressed() {
        // super.onBackPressed();不让它关闭
        Toast.makeText(this,"广告之后更精彩！",Toast.LENGTH_SHORT).show();

        //如果按回退键，关闭程序，代码设计
        finish();//关闭页面
        handler.removeCallbacks(myRunnable);//取消runnable对象
    }
}

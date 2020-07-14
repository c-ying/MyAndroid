package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tab1Activity extends Activity {
    TextView mainTv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);
        mainTv = (TextView) findViewById(R.id.main_tv);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Tab1Activity.this, MyFragment2FF.class);
                startActivity(intent);
                Toast.makeText(Tab1Activity.this,"即将进入专注学习模式 千万别半途而废哦！", Toast.LENGTH_SHORT).show();

            }
        });
        new TimeThread().start();//启动线程
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //写一个新的线程每隔一秒发送一次消息,这样做会和系统时间相差1秒
    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
        private Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        mainTv.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
                        break;
                }
                return false;
            }
        });


    }

}

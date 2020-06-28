package com.example.myapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText nicheng;//昵称
    String name;//用户名
    EditText email;//邮箱
    RadioGroup sex;//性别
    EditText birth;//生日
    EditText signature;//签名
    EditText job;  //职业
    Button update;  //确认更新按钮
    ImageView update_back;//返回
    int flag;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViews();
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1=getIntent();
                name=intent1.getStringExtra("username_login1");
                String nichengstr=nicheng.getText().toString().trim();
                String emailstr=email.getText().toString().trim();
                String sexstr=((RadioButton)UpdateActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
                String birthstr=birth.getText().toString().trim();
                String signstr=signature.getText().toString().trim();
                String jobstr=job.getText().toString().trim();
                UserService uService=new UserService(UpdateActivity.this);
                User user = new User();
                user.nicheng=nichengstr;
                user.username=name;
                user.email=emailstr;
                user.sex=sexstr;
                user.birth=birthstr;
                user.signature=signstr;
                user.job=jobstr;
                boolean update1= uService.update(user);
                if(update1) {
                    Toast.makeText(UpdateActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                    flag=1;
                    Intent intent=new Intent(UpdateActivity.this,MyFragment4.class);
                    //intent.putExtra("flag",3);
                    /*传递昵称到fragment，先用intent传到MainActivity*/
                    //intent.putExtra("nicheng",nichengstr);
                    /*不用startActivity(intent)，直接finish（）,否则获取的ID会被清除*/
                  startActivity(intent);
                    UpdateActivity.this.finish();
                }
                else {
                    Toast.makeText(UpdateActivity.this, "修改失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        update_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=new Intent(UpdateActivity.this,MainActivity.class);
                intent.putExtra("flag",3);
                /*不用startActivity(intent)，直接finish（）,否则获取的ID会被清除*/
                UpdateActivity.this.finish();
            }
        });

    }
    private void findViews() {
        nicheng=(EditText)findViewById(R.id.nichengUpdate);
        email=(EditText) findViewById(R.id.emailUpdate);
        sex=(RadioGroup) findViewById(R.id.sexUpdate);
        birth=(EditText)findViewById(R.id.birthUpdate);
        signature=(EditText)findViewById(R.id.signatureUpdate);
        job=(EditText)findViewById(R.id.jobUpdate);
        update=(Button) findViewById(R.id.Update);
        update_back=findViewById(R.id.update_back);
    }



}

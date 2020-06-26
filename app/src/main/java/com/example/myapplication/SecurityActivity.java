package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecurityActivity extends AppCompatActivity {

    LinearLayout sec_back;
    EditText newpass;
    EditText newpass1;
    TextView sec_ok;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        findViews();

        sec_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1=getIntent();
                String name=intent1.getStringExtra("username_login1");

                String pass=newpass.getText().toString().trim();
                String pass1=newpass1.getText().toString().trim();

                UserService uService=new UserService(SecurityActivity.this);

                if(!TextUtils.isEmpty(pass)&&pass.equals(pass1)) {

                    User user = new User();
                    user.username=name;
                    user.password=pass;
                    boolean update1= uService.updatepass(user);
                    if(update1) {
                        Toast.makeText(SecurityActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SecurityActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(SecurityActivity.this, "修改失败", Toast.LENGTH_LONG).show();
                    }
                }

                else if(!pass.equals(pass1)){
                    Toast.makeText(SecurityActivity.this, "输入密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
                }

            }
        });
        sec_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=new Intent(SecurityActivity.this,MainActivity.class);
                intent.putExtra("flag",3);
                /*不用startActivity(intent)，直接finish（）,否则获取的ID会被清除*/
                SecurityActivity.this.finish();
            }
        });

    }
    private void findViews() {
        sec_back=findViewById(R.id.security_back);
        sec_ok=findViewById(R.id.sec_ok);
        newpass=findViewById(R.id.newpass);
        newpass1=findViewById(R.id.newpass1);
    }

}

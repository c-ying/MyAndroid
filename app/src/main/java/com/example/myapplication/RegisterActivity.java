package com.example.myapplication;


import android.content.Intent;
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


public class RegisterActivity extends AppCompatActivity {
    String realCode;
    EditText username;
    EditText password;
    EditText password1;
    EditText email;
    RadioGroup sex;
    Button register;
    TextView registeractivity_login;
    ImageView registeractivity_back;
    ImageView mIvRegisteractivityShowcode;
    EditText mEtRegisteractivityPhonecodes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();

        //将验证码用图片的形式显示出来
        mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();  //获取输入的值
                String pass=password.getText().toString().trim();
                String pass1=password1.getText().toString().trim();
                String emailstr=email.getText().toString().trim();
                //String sexstr=((RadioButton)RegisterActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
                String phoneCode = mEtRegisteractivityPhonecodes.getText().toString().toLowerCase();
                UserService uService=new UserService(RegisterActivity.this);

                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(emailstr)&&pass.equals(pass1)) {
                    if (phoneCode.equals(realCode)) { User user = new User();
                        user.username=name;
                        user.password=pass;
                        user.email=emailstr;

                    uService.register(user);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                        Toast.makeText(RegisterActivity.this, "验证码错误!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(!pass.equals(pass1)){
                    Toast.makeText(RegisterActivity.this, "输入密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "各项不能为空！", Toast.LENGTH_LONG).show();
                }

            }
        });

        registeractivity_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        registeractivity_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        mIvRegisteractivityShowcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
            }
        });
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        password1=(EditText) findViewById(R.id.passwordRegister1);
        email=(EditText) findViewById(R.id.emailRegister);
        //sex=(RadioGroup) findViewById(R.id.sexRegister);
        register=(Button) findViewById(R.id.Register);
        registeractivity_login=findViewById(R.id.registeractivity_login);
        registeractivity_back=findViewById(R.id.registeractivity_back);
        mIvRegisteractivityShowcode = findViewById(R.id.iv_registeractivity_showCode);
        mEtRegisteractivityPhonecodes = findViewById(R.id.et_registeractivity_phoneCodes);
    }

}


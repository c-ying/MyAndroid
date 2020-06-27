package com.example.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.Note;
import com.example.myapplication.data.NoteOperator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;  //返回按钮
    private EditText title;   //标题
    private EditText context;   //内容
    private EditText time1;
    private Button finish;  //完成按钮
    private String get_title;
    private String get_context;
    private String get_time;
    private int mYear, mMonth, mDay, mHours, mMinute, mSecond;
    private Calendar mCalendar;
    private AlarmManager mAlarmManager;
    //编辑模式标志
    private boolean mEdit = false;
    private Long noteId;
//    private int note_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        back = (Button) findViewById(R.id.back_add);
        title = (EditText) findViewById(R.id.title_add);
        time1 = (EditText) findViewById(R.id.time_add);
        context = (EditText) findViewById(R.id.context_add);

        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(this);
        back.setOnClickListener(this);
        time1.setText(formatTime());
        mCalendar = Calendar.getInstance();
       //长按弹出日历
        time1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //取得日历信息中的年月日时分秒
                mYear = mCalendar.get(Calendar.YEAR);
                mMonth = mCalendar.get(Calendar.MONTH);
                mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                mHours = mCalendar.get(Calendar.HOUR);
                mMinute = mCalendar.get(Calendar.MINUTE);
                mSecond = mCalendar.get(Calendar.SECOND);
                //新建一个日期选择控件
                DatePickerDialog dDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            //设置日期的时候触发
                            @Override
                            public void onDateSet(DatePicker view, int y,
                                                  int monthOfYear, int dayOfMonth) {
                                String[] time = {"", mHours + ":" + mMinute + ":" + mSecond};
                                try {
                                    //将日期和时间分割
                                    String[] time2 = time1.getText().toString().trim
                                            ().split(" ");
                                    //取得时间的信息保存到time[1]中
                                    if (time2.length == 2) {
                                        time[1] = time2[1];
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String mo = "", da = "";
                                //将月份转换成两位数
                                if (monthOfYear < 9) {
                                    mo = "0" + (monthOfYear + 1);
                                } else {
                                    mo = monthOfYear + 1 + "";
                                }
                                //将天数转换成两位数
                                if (dayOfMonth < 10) {
                                    da = "0" + dayOfMonth;
                                } else {
                                    da = dayOfMonth + "";
                                }
                                //将设置的结果保存到etTime中
                                time1.setText(y + "-" + mo + "-" + da + " " + time[1]);
                            }
                        }, mYear, mMonth, mDay);
                dDialog.setTitle("设置日期");
                //显示日期控件
                dDialog.show();
                return true;
            }
        });

        //设置单击监听器，弹出时间选择界面
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得当前的年月日信息
                mYear = mCalendar.get(Calendar.YEAR);
                mMonth = mCalendar.get(Calendar.MONTH);
                mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                //注意这里不是HOUR,HOUR返回的是12制的时间格式
                mHours = mCalendar.get(Calendar.HOUR_OF_DAY);
                mMinute = mCalendar.get(Calendar.MINUTE);
                mSecond = mCalendar.get(Calendar.SECOND);
                //新建时间选择器
                TimePickerDialog tDialog = new TimePickerDialog(AddActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String[] time = {mYear + "-" + mMonth + "-" + mDay, ""};
                                try {
                                    //分割时间和日期
                                    time = time1.getText().toString().trim().split(" ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String ho = "", mi = "";
                                //设置小时
                                if (hourOfDay < 10) {
                                    ho = "0" + hourOfDay;
                                } else {
                                    ho = hourOfDay + "";
                                }
                                //设置分钟
                                if (minute < 10) {
                                    mi = "0" + minute;
                                } else {
                                    mi = minute + "";
                                }
                                //将设置的结果保存到etTime中
                                time1.setText(time[0] + " " + ho + ":" + mi);
                            }
                        }, mHours, mMinute, true);
                tDialog.setTitle("设置时间");
                //显示时间控件
                tDialog.show();
            }
        });
    }




    //返回当前的时间
    public String formatTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(d);
        return time;
    }


    @Override
    public void onClick(View view) {


        if (view == findViewById(R.id.finish)) {
            NoteOperator noteOperator = new NoteOperator(AddActivity.this);
            get_title = title.getText().toString().trim();
            get_time = time1.getText().toString().trim();
            get_context = context.getText().toString().trim();


            if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_context) || TextUtils.isEmpty(get_time)) {
                Toast.makeText(AddActivity.this, "添加信息不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note();
                note.title = get_title;
                note.time = get_time;
                note.context = get_context;

                boolean add = noteOperator.insert(note);
                //如果添加数据成功，跳到待办事项界面，并通过传值，让目标界面进行刷新
                if (add) {
                    //Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(AddActivity.this, MyFragment1.class);
                    intent.putExtra("Insert", 1);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == findViewById(R.id.back_add)) {
            Intent intent = new Intent(AddActivity.this, MyFragment1.class);
            startActivity(intent);
        }

    }
}




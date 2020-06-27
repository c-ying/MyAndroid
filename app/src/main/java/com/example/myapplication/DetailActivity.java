package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button save;
    private EditText title;
    private EditText context;
    private EditText time1;
    private int note_id=0;
    private String get_title;
    private String get_context;
    private String get_time;
    private int mYear, mMonth, mDay, mHours, mMinute, mSecond;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        title = (EditText) findViewById(R.id.title_detail);
        context = (EditText) findViewById(R.id.context_detail);
        time1 = (EditText) findViewById(R.id.time_detail);
        back = (Button) findViewById(R.id.back_detail);
        save = (Button) findViewById(R.id.save_detail);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

        //接收listView中点击item传来的note_id,
        Intent intent = getIntent();
        note_id = intent.getIntExtra("note_id", 0);
        NoteOperator noteOperator = new NoteOperator(this);
        Note note = noteOperator.getNoteById(note_id);

        title.setText(String.valueOf(note.title));
        time1.setText(String.valueOf(note.time));
        context.setText(String.valueOf(note.context));

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
                DatePickerDialog dDialog = new DatePickerDialog(DetailActivity.this,
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
                TimePickerDialog tDialog = new TimePickerDialog(DetailActivity.this,
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
        if (view == findViewById(R.id.back_detail)) {
            Intent intent = new Intent(DetailActivity.this, MyFragment1.class);
            startActivity(intent);
        }else if(view == findViewById(R.id.save_detail)){
            get_title=title.getText().toString().trim();
            get_time=time1.getText().toString().trim();
            get_context=context.getText().toString().trim();

            if(TextUtils.isEmpty(get_title)||TextUtils.isEmpty(get_context)||TextUtils.isEmpty(get_context)){
                Toast.makeText(this,"修改内容不能为空",Toast.LENGTH_SHORT).show();
            }else{
                Note note=new Note();
                note.note_id=note_id;
                note.title=get_title;
                note.time=get_time;
                note.context=get_context;

                NoteOperator noteOperator=new NoteOperator(DetailActivity.this);
                noteOperator.update(note);

                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(DetailActivity.this,MyFragment1.class);
                startActivity(intent);
                finish();

            }
        }
    }
}

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.myapplication.R;
import com.example.myapplication.data.Note;
import com.example.myapplication.data.NoteOperator;

public class Time extends Activity {
    private Chronometer timer;
    private Button start, stop, reset,ting;
    private long mRecordTime;
    private String time;
    private String get_cost;
    private int note_id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
        timer=(Chronometer)findViewById(R.id.chronometer);
        start = (Button) findViewById(R.id.timer_start);
        stop = (Button) findViewById(R.id.timer_stop);
        reset = (Button) findViewById(R.id.timer_reset);
        ting = (Button) findViewById(R.id.timer_ting);
        Intent intent = getIntent();
        note_id = intent.getIntExtra("note_id", 0);
        NoteOperator noteOperator = new NoteOperator(this);
        Note note = noteOperator.getNoteById(note_id);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timer.setBase(SystemClock.elapsedRealtime());
                //开始计时
                if(mRecordTime != 0){
                    timer.setBase(timer.getBase() + (SystemClock.elapsedRealtime() - mRecordTime));
                }else{
                    timer.setBase(SystemClock.elapsedRealtime());
                }
                timer.start();

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                time= timer.getText().toString();
                Note note=new Note();
                note.note_id=note_id;

                note.cost = time;
                NoteOperator noteOperator=new NoteOperator(Time.this);
                noteOperator.update1(note);
                Intent intent=new Intent();
                intent.setClass(Time.this,MyFragment1.class);
                startActivity(intent);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setBase(SystemClock.elapsedRealtime());

            }
        });
        ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                mRecordTime = SystemClock.elapsedRealtime();

            }
        });
    }


}
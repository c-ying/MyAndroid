package com.example.timer;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class StopWatch extends LinearLayout {

    private Chronometer myChronometer;
    private Button butStart;//开始
    private Button butStop;//暂停
    private Button butResume;//继续
    private Button butReset;//重置
    public StopWatch(Context context , @Nullable AttributeSet attrs) {
        super ( context , attrs );

    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate ( );

        myChronometer = (Chronometer) super.findViewById ( R.id.myChronometer );
        butStart = (Button) super.findViewById ( R.id.butStart );
        butStop = (Button) super.findViewById ( R.id.butPause );
        butResume=(Button) super.findViewById ( R.id.butResume );
        butReset = (Button) super.findViewById ( R.id.butReset );

        butStart.setVisibility ( View.VISIBLE );
        butStop.setVisibility ( View.GONE);
        butResume.setVisibility ( View.GONE);
        butReset.setVisibility ( View.GONE );

        butStart.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                myChronometer.setBase ( (long) ((long) (SystemClock.elapsedRealtime ()-
                        (long)Double.parseDouble (myChronometer.getText ().toString ().split ( ":")[0])*60*1000)-
                        (long)Double.parseDouble ( myChronometer.getText ().toString ().split (":" )[1] )*1000) );
                myChronometer.start ();

                butStart.setVisibility ( View.GONE );
                butResume.setVisibility ( View.VISIBLE );
                butStop.setVisibility ( View.VISIBLE);
                butReset.setVisibility ( View.GONE );
            }
        } );

        butStop.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                myChronometer.stop ();

                butStop.setVisibility ( View.GONE );
                butResume.setVisibility ( View.GONE);
                butStart.setVisibility ( View.VISIBLE );
                butReset.setVisibility ( View.VISIBLE );

            }
        } );

        butReset.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                myChronometer.setBase ( SystemClock.elapsedRealtime ( ) );

                butReset.setVisibility ( View.GONE );
                butResume.setVisibility ( View.GONE );
                butStart.setVisibility ( View.VISIBLE );
                butStop.setVisibility ( View.GONE);

            }
        } );

        butResume.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                myChronometer.setFormat ( "%s" );
                myChronometer.setBase ( SystemClock.elapsedRealtime ( ) );
                butStop.setVisibility ( View.VISIBLE );
                butReset.setVisibility ( View.GONE );
                butResume.setVisibility ( View.VISIBLE);
                butStart.setVisibility ( View.GONE );
            }
        } );


    }
}


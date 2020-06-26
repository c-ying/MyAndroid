package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.data.NoteOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends Fragment {


    TextView showtime;
    String NowTime,NowTime2;
    ListView listView;
    List list1;
    Button add;//添加按钮
    Button ceshi;
    TextView note_id;//向其他界面传值
    ArrayList<HashMap<String, String>> list;
    /*public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long nowTime = System.currentTimeMillis();//获取系统时间
                    CharSequence sysTime = DateFormat.format("hh:mm:ss", nowTime);//时间显示格式
                    //showtime.setText(sysTime); //更新时间
                    NowTime = sysTime.toString();
                    for(int i=0;i<list1.size();i++){
                        iteminclude item = (iteminclude) list1.get(i);
                        NowTime2 = item.getTime();
                        if(NowTime.equals(NowTime2)){
                            Log.i("Sos","成功");
                            Toast.makeText(getActivity(), "时间到了！", Toast.LENGTH_LONG).show();
                            //zhendong();
                            //soundplay();
                        }
                    }
                    /*Log.i("Nowtime",NowTime);
                    /*Log.i("Nowtime",NowTime);
                    Log.i("Nowtime2",NowTime2);
                    break;
                default:
                    break;
            }
        }
    };*/
    //设置震动
    /*public void zhendong(){
        Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        long[] patter = {1000, 1000, 1000, 1000};
        vibrator.vibrate(patter,0);
    }
    //关闭震动,响铃
    public void close(){
        Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.cancel();
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);
    }
    //设置响铃
    public void soundplay(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);
        rt.play();
    }*/


    public MyFragment1() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        add = (Button) view.findViewById(R.id.add);


        //通过list获取数据库表中的所有id和title，通过ListAdapter给listView赋值
        final NoteOperator noteOperator = new NoteOperator(getActivity());
        list = noteOperator.getNoteList();
        final ListAdapter listAdapter = new SimpleAdapter(getActivity(), list, R.layout.item,
                new String[]{"id", "title","context","time"}, new int[]{R.id.note_id, R.id.note_title,R.id.note_content,R.id.note_time});
        listView.setAdapter(listAdapter);

        //通过添加界面传来的值判断是否要刷新listView
        /*Intent intent = getIntent();
        int flag = intent.getIntExtra("Insert", 0);
        if (flag == 1) {
            list = noteOperator.getNoteList();
            listView.setAdapter(listAdapter);
        }*/

        if (list.size() != 0) {
            //点击listView的任何一项跳到详情页面
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long i) {

                    String id = list.get(position).get("id");
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DetailActivity.class);
                    intent.putExtra("note_id", Integer.parseInt(id));
                    startActivity(intent);


                }
            });

            //长按实现对列表的删除
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("确定删除？");
                    builder.setTitle("提示");

                    //添加AlterDialog.Builder对象的setPositiveButton()方法
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String id = list.get(position).get("id");
                            noteOperator.delete(Integer.parseInt(id));
                            list.remove(position);
                            //listAdapter.notify();
                            listView.setAdapter(listAdapter);
                        }
                    });

                    //添加AlterDialog.Builder对象的setNegativeButton()方法
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                    return true;
                }
            });
        } else {
            Toast.makeText(getActivity(), "暂无待办事项，请添加", Toast.LENGTH_SHORT).show();
        }


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AddActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }



}

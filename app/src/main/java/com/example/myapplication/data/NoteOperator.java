package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteOperator {
    private DBHelper dbHelper;


    public NoteOperator(Context context) {

        dbHelper = new DBHelper(context);
    }

    /**
     * 插入数据
     *
     * @param note
     * @return
     */
    public boolean insert(Note note) {
        //与数据库建立连接
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.KEY_username,note.username1);
        contentValues.put(Note.KEY_title, note.title);
        contentValues.put(Note.KEY_context, note.context);
        contentValues.put(Note.KEY_time, note.time);
        contentValues.put(Note.KEY_cost, note.cost);

        //插入每一行数据
        long note_id = db.insert(Note.TABLE, null, contentValues);
        db.close();
        if (note_id != -1)
            return true;
        else
            return false;
    }

    /**
     * 删除数据
     *
     * @param note_id
     */
    public void delete(int note_id) {
        //与数据库建立连接
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Note.TABLE, Note.KEY_id + "=?", new String[]{String.valueOf(note_id)});
        db.close();
    }

    /**
     * 从数据库中查找 id，title，context
     *
     * @return ArrayList
     */
    public ArrayList<HashMap<String, String>> getNoteList(String username1) {
        //与数据库建立连接
        SQLiteDatabase db = dbHelper.getReadableDatabase();
       /* String sql = "select " + Note.KEY_id + "," + Note.KEY_title + "," + Note.KEY_context + "," + Note.KEY_time +
        " from " + Note.TABLE ;*/
        String sql = "select " + Note.KEY_id  + ","+ Note.KEY_title + "," + Note.KEY_context + "," + Note.KEY_time +"," + Note.KEY_cost +
                " from " + Note.TABLE+ " where " + Note.KEY_username + "=?";
        //通过游标将每一条数据放进ArrayList中
        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();
        //Cursor cursor = db.rawQuery(sql, null);
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(username1)});
        while (cursor.moveToNext()) {
            HashMap<String, String> note = new HashMap<String, String>();
            note.put("id", cursor.getString(cursor.getColumnIndex(Note.KEY_id)));
            note.put("title", cursor.getString(cursor.getColumnIndex(Note.KEY_title)));
            note.put("context", cursor.getString(cursor.getColumnIndex(Note.KEY_context)));
            note.put("time", cursor.getString(cursor.getColumnIndex(Note.KEY_time)));
            note.put("cost", cursor.getString(cursor.getColumnIndex(Note.KEY_cost)));
            noteList.add(note);
        }
        cursor.close();
        db.close();
        return noteList;
    }



    /**
     * 通过id查找，返回一个Note对象
     *
     * @param id
     * @return
     */
    public Note getNoteById(int id) {
        //与数据库建立连接
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + Note.KEY_title + "," + Note.KEY_context +","+Note.KEY_time+","+Note.KEY_cost+
                " from " + Note.TABLE + " where " + Note.KEY_id + "=?";
        Note note = new Note();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            note.title = cursor.getString(cursor.getColumnIndex(Note.KEY_title));
            note.context = cursor.getString(cursor.getColumnIndex(Note.KEY_context));
            note.time = cursor.getString(cursor.getColumnIndex(Note.KEY_time));
            note.cost = cursor.getString(cursor.getColumnIndex(Note.KEY_cost));
        }
        cursor.close();
        db.close();
        return note;
    }

    /**
     * 更新数据
     *
     * @param note
     */
    public void update(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Note.KEY_title, note.title);
        contentValues.put(Note.KEY_context, note.context);
        contentValues.put(Note.KEY_time, note.time);
        contentValues.put(Note.KEY_cost, note.cost);

        db.update(Note.TABLE, contentValues, Note.KEY_id + "=?", new String[]{String.valueOf(note.note_id)});
        db.close();
    }
    public void update1(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Note.KEY_cost, note.cost);

        db.update(Note.TABLE, contentValues, Note.KEY_id + "=?", new String[]{String.valueOf(note.note_id)});
        db.close();
    }


}
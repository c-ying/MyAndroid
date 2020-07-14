package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserService {
    private DataBaseHelper dbHelper;
    public UserService(Context context)
    {
        dbHelper=new DataBaseHelper(context);
    }



    public boolean login(String username,String password) {
        //DataBaseHelper
        // SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();
        //String sql="select * from user9 where username=? and password=?";
        Cursor cursor=sdb.rawQuery("select * from user5 where username=? and password=?", new String[]{username,password});
        // Cursor c = sdb.query("user9", null, "username=? and password=?", new String[]{username, password});
        if(cursor.moveToFirst()==true){
            if(cursor!=null&&cursor.isClosed()){
                cursor.close();}
            return true;
        }
        else {
            return false;
        }
    }
    public boolean register(User user){

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql = "select " + "*" +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        if(cursor.moveToFirst()==true){
            if(cursor!=null&&cursor.isClosed()){
                cursor.close();}
                return  false;
           }
        else{
            ContentValues contentValues=new ContentValues();
            contentValues.put(User.K_username,user.username);
            contentValues.put(User.K_password,user.password);
            contentValues.put(User.K_email,user.email);
            db.insert(User.TABLE,null,contentValues);
            db.close();

            return true;
        }
    }

    public boolean update(User user){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User.K_nicheng,user.nicheng);
        contentValues.put(User.K_email,user.email);
        contentValues.put(User.K_sex,user.sex);
        contentValues.put(User.K_birth,user.birth);
        contentValues.put(User.K_signature,user.signature);
        contentValues.put(User.K_job,user.job);
        db.update(User.TABLE,contentValues,User.K_username + "=?",new String[]{String.valueOf(user.username)});
        db.close();
        return true;
    }


    public boolean updatepass(User user){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User.K_password,user.password);
        db.update(User.TABLE,contentValues,User.K_username + "=?",new String[]{String.valueOf(user.username)});
        db.close();
        return true;
    }
    public String getNicheng(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_nicheng +
                " from " + User.TABLE + " where " + User.K_username + "=?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.nicheng = cursor.getString(cursor.getColumnIndex(User.K_nicheng));

        }
        cursor.close();
        db.close();
        return user.nicheng;
    }
    public String getEmail(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_email +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.email = cursor.getString(cursor.getColumnIndex(User.K_email));
        }
        cursor.close();
        db.close();
        return user.email;
    }
    public String getSign(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_signature +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.signature = cursor.getString(cursor.getColumnIndex(User.K_signature));
        }
        cursor.close();
        db.close();
        return user.signature;
    }

    public String getSex(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_sex +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.sex = cursor.getString(cursor.getColumnIndex(User.K_sex));
        }
        cursor.close();
        db.close();
        return user.sex;
    }

    public String getBirth(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_birth +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.birth = cursor.getString(cursor.getColumnIndex(User.K_birth));
        }
        cursor.close();
        db.close();
        return user.birth;
    }

    public String getJob(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_job +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.job = cursor.getString(cursor.getColumnIndex(User.K_job));
        }
        cursor.close();
        db.close();
        return user.job;
    }

}
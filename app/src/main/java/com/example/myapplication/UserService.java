package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;


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
        Cursor cursor=sdb.rawQuery("select * from user2 where username=? and password=?", new String[]{username,password});
        // Cursor c = sdb.query("user9", null, "username=? and password=?", new String[]{username, password});
        if(cursor.moveToFirst()==true){
            if(cursor!=null&&cursor.isClosed()){
                cursor.close();}
            return true;
        }
        /*if (c != null && c.getCount() >= 1) {
            c.close();
            sdb.close();
            return true;
        }
        */else {
            return false;
        }
    }
    public boolean register(User user){
        /*SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user9(username,password,email) values(?,?,?)";
        Object obj[]={User.K_username,User.K_password,User.K_email};
        sdb.execSQL(sql, obj);
        sdb.close();
        return true;*/
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User.K_username,user.username);
        contentValues.put(User.K_password,user.password);
        contentValues.put(User.K_email,user.email);
        db.insert(User.TABLE,null,contentValues);
        db.close();
        return true;
    }

    public boolean update(User user){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User.K_nicheng,user.nicheng);
        contentValues.put(User.K_email,user.email);
        contentValues.put(User.K_sex,user.sex);
        contentValues.put(User.K_birth,user.birth);
        contentValues.put(User.K_signature,user.signature);
        db.update(User.TABLE,contentValues,User.K_username + "=?",new String[]{String.valueOf(user.username)});
       /* String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        String sex=user.getSex();
        String birth=user.getBirth();
        String signature=user.getSignature();
        String sql="update user9 set password=password ,email='email',sex='sex',birth='birth',signature='signature'  where username='username'";
        db.execSQL(sql);*/
        //Object obj[]={user.getPassword(),,user.getSex(),user.getBirth(),user.getSignature(),user.getUsername()};
        //sdb.execSQL("update user2 set password=?,email=?,sex=?,birth=?,signature=? where username=?",new Object[]{user.getPassword(),user.getEmail(),user.getSex(),user.getBirth(),user.getSignature(),user.getUsername()});

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
        //String nicheng;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + User.K_nicheng +
                " from " + User.TABLE + " where " + User.K_username + "=?";
        //String sql = "select " + Note.KEY_title + "," + Note.KEY_context +
        // " from " + Note.TABLE + " where " + Note.KEY_id + "=?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(user.username)});
        while(cursor.moveToNext()) {
            user.nicheng = cursor.getString(cursor.getColumnIndex(User.K_nicheng));

        }
        cursor.close();
        db.close();
        return user.nicheng;
    }
}
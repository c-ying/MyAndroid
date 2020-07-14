package com.example.myapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper  extends SQLiteOpenHelper {
    static String D_NAME="user5.db";
    /*static String TABLE_NAME="user9";
    static String username="username";
    static String password="password";
    static String email="email";
    static  String sex="sex";
    static String birth="birth";
    static String signature="signature";
    static String IMAGE="image";*/
    static int version=4;
    public DataBaseHelper( Context context) {
        super(context, D_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "+User.TABLE +"("
                +User.K_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +User.K_nicheng +" text,"
                +User.K_username +" text,"
                +User.K_password +" text,"
                +User.K_email+" text,"
                +User.K_sex+" text,"
                +User.K_birth+" text,"
                +User.K_signature+" text,"
                +User.K_job+" text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists "+User.TABLE);
        onCreate(sqLiteDatabase);
    }
}

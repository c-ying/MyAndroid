package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable{

    static String TABLE="user5";
    static String K_id="id";
    static String K_nicheng="nicheng";
    static String K_username="username";
    static String K_password="password";
    static String K_email="email";
    static  String K_sex="sex";
    static String K_birth="birth";
    static String K_signature="signature";
    static String K_job="job";

    public String nicheng;
    public String username;
    public String password;
    public String email;
    public String sex;
    public String birth;
    public String signature;
    public String job;
}

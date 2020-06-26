package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable{

    static String TABLE="user2";
    static String K_id="id";
    static String K_nicheng="nicheng";
    static String K_username="username";
    static String K_password="password";
    static String K_email="email";
    static  String K_sex="sex";
    static String K_birth="birth";
    static String K_signature="signature";

    public String nicheng;
    public String username;
    public String password;
    public String email;
    public String sex;
    public String birth;
    public String signature;
   /* public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String email,String sex,String birth,String signature) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.birth=birth;
        this.signature=signature;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth =birth; }
    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", email=" + email + ",sex="+sex+",birth="+birth+",signature="+signature+"]";
    }*/

}

package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private String email;
    //private String sex;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String email, String sex) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
       // this.sex = sex;
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
    /*public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }*/
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", email=" + email + "]";
    }

}

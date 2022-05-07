package com.rajendra.onlinedailygroceries.model;

import java.io.Serializable;

public class User implements Serializable {
    String user_name;
    String password;
    String avatar;
    String date;

    public User(String user_name, String password, String avatar, String date) {
        this.user_name = user_name;
        this.password = password;
        this.avatar = avatar;
        this.date = date;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}

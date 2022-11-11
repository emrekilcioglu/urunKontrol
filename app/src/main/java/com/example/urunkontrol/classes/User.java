package com.example.urunkontrol.classes;

public class User {
    private int userId;
    private String name,userName,password;
    private boolean loginStatus;

    public User() {
    }

    public User(int userId, String name, String userName, String password, boolean loginStatus) {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.loginStatus = loginStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}

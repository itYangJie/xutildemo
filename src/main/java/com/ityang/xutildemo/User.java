package com.ityang.xutildemo;

/**
 * Created by Administrator on 2016/2/10.
 */
public class User {

    private int id;
    private String userName;

    public User(int id, String userName, String password, boolean isVerify) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isVerify = isVerify;
    }
    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isVerify() {
        return isVerify;
    }

    public void setIsVerify(boolean isVerify) {
        this.isVerify = isVerify;
    }

    private String password;
    private boolean isVerify;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isVerify=" + isVerify +
                '}';
    }
}

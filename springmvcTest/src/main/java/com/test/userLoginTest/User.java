package com.test.userLoginTest;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
public class User {
    private String username;
    private String password;
    private int grade;

    public User(String name, String pwd, int gd) {
        username = name;
        password = pwd;
        grade = gd;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
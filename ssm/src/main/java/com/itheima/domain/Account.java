package com.itheima.domain;

public class Account {

    private String email;
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return "账号信息：E-mail：" + email + "，密码：" + password + "。\n";
    }
}
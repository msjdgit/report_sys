package com.jxd.report.model;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @ClassName User
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/13 17:13
 * @Version 1.0
 */

public class User {
    @TableId
    private int uid;
    private String uname;
    private String password;
    private int role;

    public User() {
    }

    public User(int uid, String uname, String password, int role) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.role = role;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

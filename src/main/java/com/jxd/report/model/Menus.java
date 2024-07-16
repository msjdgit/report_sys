package com.jxd.report.model;

/**
 * @ClassName Menus
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/1 10:29
 * @Version 1.0
 */

public class Menus {
    private int menuid;
    private String menuname;
    private String menupath;
    private String prepath;
    private String role;

    public Menus() {
    }

    public Menus(int menuid, String menuname, String menupath, String prepath, String role) {
        this.menuid = menuid;
        this.menuname = menuname;
        this.menupath = menupath;
        this.prepath = prepath;
        this.role = role;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenupath() {
        return menupath;
    }

    public void setMenupath(String menupath) {
        this.menupath = menupath;
    }

    public String getPrepath() {
        return prepath;
    }

    public void setPrepath(String prepath) {
        this.prepath = prepath;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

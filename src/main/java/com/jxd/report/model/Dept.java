package com.jxd.report.model;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @ClassName Dept
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 20:52
 * @Version 1.0
 */

public class Dept {
    @TableId
    private int deptno;
    private String dname;

    public Dept() {
    }

    public Dept(int deptno, String dname) {
        this.deptno = deptno;
        this.dname = dname;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}

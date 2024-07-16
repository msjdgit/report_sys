package com.jxd.report.model;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @ClassName Project
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 20:53
 * @Version 1.0
 */

public class Project {
    @TableId
    private int pno;
    private String pname;
    private int deptno;

    public Project() {
    }

    public Project(int pno, String pname, int deptno) {
        this.pno = pno;
        this.pname = pname;
        this.deptno = deptno;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}

package com.jxd.report.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName Emp
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 20:49
 * @Version 1.0
 */

public class Emp {
    @TableId
    private int empno;
    private String ename;
    private String sex;
    private String telephone;
    private String hiredate;
    private int deptno;
    private String position;
    private String password;
    private int role;
    public Emp() {
    }

    public Emp(int empno, String ename, String sex, String telephone, String hiredate, int deptno, String position, String password, int role) {
        this.empno = empno;
        this.ename = ename;
        this.sex = sex;
        this.telephone = telephone;
        this.hiredate = hiredate;
        this.deptno = deptno;
        this.position = position;
        this.password = password;
        this.role = role;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

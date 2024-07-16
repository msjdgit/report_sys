package com.jxd.report.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName JobReport
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 20:55
 * @Version 1.0
 */
@TableName("job_report")
public class JobReport {
    @TableId
    private int id;
    private int empno;
    private String date;
    private String time;
    private String attendance;
    private double workload;
    private double overtime;
    private String description;
    private int pno;
    private String reviewstatus;
    private int aduitid;
    private String aduittime;
    private String reason;

    public int getAduitid() {
        return aduitid;
    }

    public void setAduitid(int aduitid) {
        this.aduitid = aduitid;
    }

    public String getAduittime() {
        return aduittime;
    }

    public void setAduittime(String aduittime) {
        this.aduittime = aduittime;
    }

    public JobReport() {

    }

    public JobReport(int id, int empno, String date, String time, String attendance, double workload, double overtime, String description, int pno, String reviewstatus, int aduitid, String aduittime, String reason) {
        this.id = id;
        this.empno = empno;
        this.date = date;
        this.time = time;
        this.attendance = attendance;
        this.workload = workload;
        this.overtime = overtime;
        this.description = description;
        this.pno = pno;
        this.reviewstatus = reviewstatus;
        this.aduitid = aduitid;
        this.aduittime = aduittime;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus;
    }
}

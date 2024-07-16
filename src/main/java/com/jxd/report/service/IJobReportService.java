package com.jxd.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.report.model.JobReport;

import java.time.format.SignStyle;
import java.util.List;
import java.util.Map;

public interface IJobReportService extends IService<JobReport> {
    //根据id个人报工
    List<JobReport> getMyReport(int userId);
    //获取报工记录，用于时间限制
    List<String> getCompletedDate(int empno);
    //获取个人所有报工记录
    Map<String,Object> getAllPersonReport(Map<String, String> map);
    //获取近几天的所有人的报工记录的条数
    List<Map<String,Object>> getNewReport(int deptno);
    //获取部门下的报工，实现批量提交或者退回，用于审核
    Map<String,Object> getDeptReport(Map<String,String> map);

    //报工汇总
    Map<String,Object> getDeptAllReport(Map<String,String> map);

    //获取每个人的报工记录
    //将报工日期合到一个字段
    //获取漏报记录
    List<Map<String,Object>> getAllByDeptno(int deptno,Map<String,Object> map);
    //获取员工是否有未完成的报工记录
    List<Map<String,Object>> getEveryJobReportCount(int[] empnos);
}

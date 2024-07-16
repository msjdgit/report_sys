package com.jxd.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.jxd.report.model.JobReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IJobReportDao extends BaseMapper<JobReport> {
    List<JobReport> getReport(int userId);
    //获取员工的未审核的报工记录条数，用于是否删除的校验
    List<Map<String,Object>> getEveryReport(@Param("empnos")int[] empnos);
    List<String> getCompletedDate(int empno);
    IPage<JobReport> getAllReport(IPage<JobReport> page, @Param("map") Map<String,String> map);

    Map<String,Object> getNewReport(@Param("deptno") int deptno,@Param("date") String date);
    IPage<JobReport> selectDeptReport(IPage<JobReport> page, @Param("map") Map<String,String> map);
    IPage<JobReport> selectDeptAllReport(IPage<JobReport> page, @Param("map") Map<String,String> map);
    List<Map<String,Object>> selectAllByDeptno(@Param("deptno") int deptno,@Param("ename") String ename);
}

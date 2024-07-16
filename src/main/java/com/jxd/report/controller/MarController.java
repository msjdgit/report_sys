package com.jxd.report.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.report.model.Emp;
import com.jxd.report.model.JobReport;
import com.jxd.report.service.IEmpService;
import com.jxd.report.service.IJobReportService;
import com.jxd.report.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName MarController
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:39
 * @Version 1.0
 */
@RestController
public class MarController {
    @Autowired
    private IEmpService empService;
    @Autowired
    private IJobReportService jobReportService;

    /**
     * 经理首页
     * @param empno
     * @return
     */
    @RequestMapping("/getNewReport/{empno}")
    public List<Map<String,Object>> getNewReport(@PathVariable int empno){
        //部门经理获取部门所有最近的报工记录的条数
        //前台按时间分类

        int deptno = empService.getById(empno).getDeptno();

        List<Map<String,Object>> listForm = jobReportService.getNewReport(deptno);
        return listForm;
    }

    /**
     *获取部门未审核的报工
     * @param map
     * @return
     */
    @RequestMapping("/getDeptReport")
    public Map<String,Object> getDeptReport(@RequestBody Map<String,String> map){

        //AbstractWrapper wrapper = new QueryWrapper();
        String deptno = "" + empService.getById(Integer.parseInt((String)map.get("empno"))).getDeptno();
        map.put("deptno",deptno);
        Map<String,Object> maps = jobReportService.getDeptReport(map);
        return maps;
    }

    /**
     * 审核，退回
     * @param map
     * @return
     */
    @RequestMapping("/saveAduit")
    public String auditReport(@RequestBody Map<String,Object> map){
        //审核报工记录
        //存储审核状态，可以单条或批量存储审核状态
        Collection<JobReport> entityList = new ArrayList<>();
        List<Integer> list= (List<Integer>) map.get("id");

        String reviewstatus = (String) map.get("reviewstatus");
        String reason = (String) map.get("returnReason");
        int aduitid = Integer.parseInt((String) map.get("aduitid"));
        String time = Tools.getDateTime();
        for (int i = 0; i < list.size(); i++) {
            JobReport report = jobReportService.getById(list.get(i));
            report.setAduitid(aduitid);
            report.setAduittime(time);
            report.setReviewstatus(reviewstatus);
            report.setReason(reason);
            entityList.add(report);
        }
        boolean flag = jobReportService.updateBatchById(entityList);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 报工汇总
     * @param map
     * @return
     */
    @RequestMapping("/getReportList")
    public Map<String,Object> getAllReports(@RequestBody Map<String,String> map){
        //根据部们编号查询部门所有人的报工记录
        //查询条件，姓名，起始日期到终止日期
        String deptno = "" + empService.getById(Integer.parseInt((String)map.get("empno"))).getDeptno();
        map.put("deptno",deptno);
        Map<String,Object> maps = jobReportService.getDeptAllReport(map);


        return maps;
    }

    /**
     * 漏报汇总
     * @param querymap
     * @return
     */
    @RequestMapping("/getUnderReportList")
    public List<Map<String,Object>> getUnderReports(@RequestBody Map<String,Object> querymap){
        //查询部门所有漏报记录
        //部门，漏报日期是查询条件
        int deptno = empService.getById(Integer.parseInt((String)querymap.get("empno"))).getDeptno();

        List<Map<String,Object>> list = jobReportService.getAllByDeptno(deptno,querymap);
        return list;
    }

}

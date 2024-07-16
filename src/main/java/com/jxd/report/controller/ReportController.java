package com.jxd.report.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.report.model.*;
import com.jxd.report.service.*;
import com.jxd.report.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName ReportController
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:14
 * @Version 1.0
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
public class ReportController {
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IJobReportService jobReportService;
    @Autowired
    private IMenusService menusService;
    @Autowired
    private IUserService userService;

    //员工登录
    @RequestMapping("/login")
    public User login(@RequestBody User user){
        //校验密码
        //判断登录人身份
        //登录不同界面,或者在前台通过判断登录人身份，如果是部门经理，设置上半区域组件为显示或true
        //构造查询对象
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",user.getUid());
        wrapper.eq("password",user.getPassword());
        user = userService.getOne(wrapper);
        return user;
    }

    //获取菜单列表
    @RequestMapping("/getMenus/{role}")
    public List<Menus> getMenus(@PathVariable int role){
        List<Menus> list ;
        AbstractWrapper wrapper = new QueryWrapper();
        if (role == 1){
            wrapper.eq("role",1);
            wrapper.or();
            wrapper.eq("role",2);
        } else {
            wrapper.eq("role",role);
        }
        list = menusService.list(wrapper);
        return list;
    }
    /**
     * 获取本人漏报记录
     * @return
     */
    @RequestMapping("/getMyUnderReport/{empno}")
    public List<Map<String,Object>> getMyUnderReport(@PathVariable int  empno){
        //获取员工所有记录
        //判断哪几天没有报工记录
        //下面写了个查询没有报工记录的方法，可以考虑合并

        //查找已有报工记录
        List<JobReport> list = jobReportService.getMyReport(empno);
        List<String> exitTime = new ArrayList<>();
        //获取返回值数组
        List<Map<String,Object>> list1 = new ArrayList<>();
        for (JobReport job:list){
            exitTime.add(job.getDate());
            if ("已退回".equals(job.getReviewstatus())){
                Map<String,Object> map= new HashMap<>();
                map.put("date",job.getDate());
                map.put("msg","报工被退回");
                map.put("operation","立即补报");
                list1.add(map);
            }
        }
        //入职日期，获取没有记录的日期
        String hiredate = empService.getById(empno).getHiredate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(hiredate, formatter);
        List<String> missingDays = Tools.checkDateContinuity(startDate,exitTime);
        //遍历，判断是否是周末
        for (String s : missingDays) {
            if (!Tools.isWeekend(s)){
                Map<String,Object> map= new HashMap<>();
                map.put("date",s);
                map.put("msg","漏报");
                map.put("operation","立即补报");
                list1.add(map);
            }
        }
        return list1;
    }

    @RequestMapping("/getOneReport")
    public JobReport getOneReport(@RequestBody Map<String,Object> map){
        //获取被退回的报工记录，返回前台，用于重新填报
        AbstractWrapper wrapper = new QueryWrapper();

        wrapper.eq("empno",map.get("empno"));
        wrapper.eq("date",map.get("date"));
        JobReport report = jobReportService.getOne(wrapper);
        return report;
    }

    @RequestMapping("/updateReport")
    public String updateReport(@RequestBody JobReport report){
        report.setReviewstatus("未审核");
        if (!report.getAttendance().equals("请假")){
            report.setWorkload(1);
        }else {
            report.setWorkload(0);
        }
        report.setTime(Tools.getDateTime());
        boolean flag;
        if (report.getId()== 0){
            //保存，补报或者填报记录
            flag = jobReportService.save(report);
        }else {
            //重新保存被退回的报工记录
            flag = jobReportService.updateById(report);
        }
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("/getProject/{empno}")
    public List<Project> getProject(@PathVariable int empno){
        //根据员工所在部门编号查找所有项目
        //返回前台动态加载为下拉框
        int deptno = empService.getById(empno).getDeptno();
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("deptno",deptno);
        List<Project> list = projectService.list(wrapper);
        return list;
    }


    @RequestMapping("/getAllPerReport")
    public Map<String,Object> getAllReport(@RequestBody Map<String,String> map){
        //获取所有报工记录，
        return jobReportService.getAllPersonReport(map);
    }

    //获取个人填报记录用于日期框选择
    @RequestMapping("/getCompletedDate/{empno}")
    public List<String> getCompletedDate(@PathVariable int empno){
        List<String> list = jobReportService.getCompletedDate(empno);
        return list;
    }

    //获取入职日期
    @RequestMapping("/getHiredate/{empno}")
    public String getHiredate(@PathVariable int empno){
        return empService.getById(empno).getHiredate();
    }
}

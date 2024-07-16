package com.jxd.report.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jxd.report.model.*;
import com.jxd.report.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/3 9:04
 * @Version 1.0
 */
@RestController
public class AdminController {
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IJobReportService jobReportService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IUserService userService;

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @RequestMapping("/addEmp")
    public String addEmp(@RequestBody Emp emp){
        emp.setPassword("123");
        if ("部门经理".equals(emp.getPosition())){
            emp.setRole(1);
        }else {
            emp.setRole(2);
        }
        boolean flag;
        while (true){
            int empno = ThreadLocalRandom.current().nextInt(1000, 10000);
            Emp emp1 = empService.getById(empno);
            if (emp1 == null){
                emp.setEmpno(empno);
                flag = empService.save(emp);
                User user = new User(empno,emp.getEname(),"123",emp.getRole());
                userService.save(user);
                break;
            }
        }
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 获取单个员工信息用于修改
     * @param empno
     * @return
     */
    @RequestMapping("/getEmp/{empno}")
    public Emp getEmp(@PathVariable int empno){
        return empService.getById(empno);
    }

    /**
     * 获取项目
     * @param pno
     * @return
     */
    @RequestMapping("/getProj/{pno}")
    public Project getProject(@PathVariable int pno){
        return projectService.getById(pno);
    }

    /**
     * 添加项目
     * @param project
     * @return
     */
    @RequestMapping("/addProj")
    public String addProj(@RequestBody Project project){
        boolean flag = projectService.save(project);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 删除员工
     * @param empnos
     * @return
     */
    @RequestMapping("/delEmp")
    public String delEmp(@RequestBody List<Integer> empnos){
        //关联删除jobreport
        //判断是否有未完成的报工记录
        List<Integer> list = new ArrayList<>();

        int[] empno = new int[empnos.size()];
        for (int i = 0; i < empno.length ;i++) {
            empno[i] = empnos.get(i);
        }
        List<Map<String,Object>> queryList = jobReportService.getEveryJobReportCount(empno);
        //关联删除报工记录
        String msg = "";
        String temp = "";
        String temp1 = "";
        for (Map<String,Object> map:queryList){
            if ((Long)map.get("count") == 0){
                list.add(Integer.parseInt ("" + map.get("empno")));
                temp1 += (String) map.get("ename");
                temp1 += "，";
            }else {
                temp += (String) map.get("ename");
                temp += "，";
            }
        }
        if (list.size()!= 0){
            empService.removeByIds(list);
            AbstractWrapper wrapper = new QueryWrapper();
            wrapper.in("empno",list);
            jobReportService.remove(wrapper);
            userService.removeByIds(list);
            msg += temp1.substring(0,temp1.length()-1);
            msg += "等人已被删除";
        }
        if (temp != ""){
            msg += "，";
            msg += temp.substring(0,temp.length()-1);
            msg += "等人尚有未完成程序";
        }
        if (list.size()!= 0){
            return msg;
        }else {
            return "error";
        }

    }

    /**
     * 删除项目
     * @param pnos
     * @return
     */
    @RequestMapping("/delProj")
    public String delProj(@RequestBody List<Integer> pnos){
        //项目表可以直接删除，员工表没有关联外键
        boolean flag = projectService.removeByIds(pnos);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 更新员工
     * @param emp
     * @return
     */
    @RequestMapping("/updateEmp")
    public String updateEmp(@RequestBody Emp emp){
        if ("部门经理".equals(emp.getPosition())){
            emp.setRole(1);
        }else {
            emp.setRole(2);
        }
        boolean flag = empService.updateById(emp);
        User user = new User(emp.getEmpno(),emp.getEname(),"123",emp.getRole());
        userService.save(user);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 更新项目
     * @param project
     * @return
     */
    @RequestMapping("/updateProj")
    public String editProj(@RequestBody Project project){
        boolean flag = projectService.updateById(project);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 获取员工
     * @param map
     * @return
     */
    @RequestMapping("/getEmps")
    public Map<String,Object> getEmps(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = empService.getAllEmpList(map);
        return map1;
    }

    /**
     * 获取部门
     * @return
     */
    @RequestMapping("/getDept")
    public List<Dept> getDept(){
        return deptService.list();
    }

    /**
     * 获取项目
     * @param map
     * @return
     */
    @RequestMapping("/getProjs")
    public Map<String, Object> getCourse(@RequestBody Map<String,String> map){
        Map<String,Object> maps = projectService.getAllProjList(map);
        return maps;
    }
    @RequestMapping("/getDepts")
    public Map<String,Object> getAllDept(@RequestBody Map<String,String> map){
        Map<String,Object> maps = deptService.getAllDept(map);
        return maps;
    }
    @RequestMapping("/getDept/{deptno}")
    public Dept getOneDept(@PathVariable int deptno){
        return deptService.getById(deptno);
    }
    @RequestMapping("updateDept")
    public String updateDept(@RequestBody Dept dept){
        boolean flag = deptService.updateById(dept);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }
    @RequestMapping("/addDept")
    public String addDept(@RequestBody Dept dept){
        boolean flag = deptService.save(dept);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("/delDept")
    public String delDept(@RequestBody List<Integer> deptnos){
        List<Integer> list = new ArrayList<>();

        //将部门下没有项目的部门删除
        for (int deptno:deptnos){
            AbstractWrapper wrapper = new QueryWrapper();
            wrapper.eq("deptno",deptno);
            List<Project> projects = projectService.list(wrapper);
            List<Emp> emps = empService.list(wrapper);
            if (projects.size() == 0 && emps.size() == 0){
                list.add(deptno);
            }
        }
        Boolean flag = deptService.removeByIds(list);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }
}

package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IJobReportDao;
import com.jxd.report.model.Emp;
import com.jxd.report.model.JobReport;
import com.jxd.report.service.IEmpService;
import com.jxd.report.service.IJobReportService;
import com.jxd.report.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName JobReportServiceImpl
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:11
 * @Version 1.0
 */
@Service
public class JobReportServiceImpl extends ServiceImpl<IJobReportDao, JobReport> implements IJobReportService {
   @Autowired
   private IJobReportDao jobReportDao;
   @Autowired
   private IEmpService empService;
    @Override
    public List<JobReport> getMyReport(int userId) {
        return jobReportDao.getReport(userId);
    }

    @Override
    public List<String> getCompletedDate(int empno) {
        return jobReportDao.getCompletedDate(empno);
    }

    /**
     * 获取个人所有的报工记录
     * @param queryMap
     * @return
     */
    @Override
    public Map<String, Object> getAllPersonReport(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<JobReport> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<JobReport> pageResult = jobReportDao.getAllReport(pages,queryMap);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }

    @Override
    public List<Map<String,Object>> getNewReport(int deptno) {
        List<Map<String,Object>> listForm = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int daysAgo = 3; // 要获取几天前的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < daysAgo; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);//从前一天开始，每次循环向前进一天
            String date = sdf.format(calendar.getTime());
            if (Tools.isWeekend(date)){//判断是不是周末
                i--;
                continue;
            }
            AbstractWrapper wrapper = new QueryWrapper();
            wrapper.eq("deptno",deptno);
            wrapper.lt("hiredate",date);
            List<Emp> list = empService.list(wrapper);
            //存到list
            Map<String,Object> map = jobReportDao.getNewReport(deptno,date);
            /*if (list.size() <= Integer.parseInt((String)map.get("checkedReports"))){
                continue;
            }*/
            String msg = date + "应报" + list.size() + "人，已报" + map.get("actuallyReports") +
                    "人，提交" + map.get("checkedReports") + "人，未审" + map.get("uncheckedReports") +
                    "人（退回" + map.get("returnReports") + "人）";
            Map<String,Object> map1 = new HashMap<>();
            map1.put("date",date);
            map1.put("msg",msg);
            listForm.add(map1);
        }
        return listForm;
    }

    @Override
    public Map<String, Object> getDeptReport(Map<String,String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<JobReport> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<JobReport> pageResult =
                    jobReportDao.selectDeptReport(pages,queryMap);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }

    @Override
    public Map<String, Object> getDeptAllReport(Map<String,String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<JobReport> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<JobReport> pageResult =
                             jobReportDao.selectDeptAllReport(pages,queryMap);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }

    @Override
    public List<Map<String, Object>> getAllByDeptno(int deptno,Map<String,Object> querymap) {
        String date = "";
        if (querymap.get("date") != null){
            date = ((String) querymap.get("date")).split("T")[0];
        }
        String ename = (String) querymap.get("ename");

        List<Map<String,Object>> list = jobReportDao.selectAllByDeptno(deptno,ename);
        List<Map<String,Object>> list1 = new ArrayList<>();
        for (Map<String,Object> map1:list){//遍历每个员工的记录
            String str = (String) map1.get("dates");
            String[] dates = str.split(",");
            List<String> exitTime = new ArrayList<>();
            for (int i = 0; i < dates.length; i++) {//已经存在的记录
                exitTime.add(dates[i]);
            }
            //入职日期
            String hiredate = map1.get("hiredate").toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(hiredate, formatter);
            List<String> missingDays = Tools.checkDateContinuity(startDate,exitTime);
            //如果日期不为null
            if (date != null && date != "" && !Tools.isWeekend(date)){
                for (String s : missingDays){
                    if (date.equals(s)){
                        Map<String,Object> map= new HashMap<>();
                        map.put("ename",map1.get("ename"));
                        map.put("empno",map1.get("empno"));
                        map.put("dname",map1.get("dname"));
                        map.put("date",s);
                        list1.add(map);
                        break;
                    }
                }
                continue;
            }
            //如果日期为null
            for (String s : missingDays) {
                if (!Tools.isWeekend(s)){
                    Map<String,Object> map= new HashMap<>();
                    map.put("ename",map1.get("ename"));
                    map.put("empno",map1.get("empno"));
                    map.put("dname",map1.get("dname"));
                    map.put("date",s);
                    list1.add(map);
                }
            }
        }
        return list1;
    }

    @Override
    public List<Map<String, Object>> getEveryJobReportCount(int[] empnos) {
        return jobReportDao.getEveryReport(empnos);
    }
}

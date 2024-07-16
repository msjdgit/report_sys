package com.jxd.report.util;

import com.jxd.report.model.JobReport;
import com.jxd.report.service.IJobReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName ScheduledTasks
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/8 11:05
 * @Version 1.0
 */

@Component
public class ScheduledTasks {
    @Autowired
    private IJobReportService jobReportService;

    @Scheduled(cron = "0 0 12 ? * MON-FRI")// 每天上午12点（周一到周五）
    public void insertData(){
        //每天0时添加一条数据,
        JobReport report = new JobReport();
        //将所有人的数据添加进去，存入数据库

    }
}

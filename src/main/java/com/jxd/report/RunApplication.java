package com.jxd.report;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName RunApplication
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/26 9:13
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.jxd.report.dao")
//@EnableScheduling//定时任务注解
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class,args);
    }
}

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.report.RunApplication;
import com.jxd.report.controller.MarController;
import com.jxd.report.model.Dept;
import com.jxd.report.model.JobReport;
import com.jxd.report.service.IDeptService;
import com.jxd.report.service.IJobReportService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestJUnit
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/26 9:22
 * @Version 1.0
 */
@SpringBootTest(classes = RunApplication.class)
public class TestJUnit {
    @Autowired
    private IJobReportService jobReportService;
    @Autowired
    private IDeptService deptService;

    @Test
    public void test1(){
        Map<String,String > map = new HashMap<>();
        map.put("empno","1002");
        map.put("pno","1");
        map.put("date","");
        map.put("page","1");
        map.put("limit","5");
        Map<String,Object> map1 = jobReportService.getAllPersonReport(map);
        System.out.println(map1.get("count"));
    }
    @Test
    public void test2(){
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.like("dname",null);
        String dname = null;
        List<Dept> list;
        if (null == dname){
            list = deptService.list();
        }else {
            list = deptService.list(wrapper);
        }
        System.out.println(list.size());
    }

    @Test
    public void test3(){

    }

    @Autowired
    private MarController controller;

    @Test
    public void test5(){
        Map<String,String > map = new HashMap<>();
        map.put("empno","1002");
        map.put("date","2024-07-01");
        map.put("param","");
        map.put("limit","5");
        map.put("page","1");
        Map<String,Object> k = controller.getDeptReport(map);
        System.out.println(k.get("count"));
    }

    @Test
    public void test6(){
        Map<String,String> map = new HashMap<>();
        map.put("empno","1001");
        map.put("ename","");
        map.put("startdate","");
        map.put("enddate","");
        map.put("limit","1");
        map.put("page","5");
        Map<String,Object> k = controller.getAllReports(map);
        System.out.println(k.size());
    }
}

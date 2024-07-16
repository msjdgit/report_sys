package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IEmpDao;
import com.jxd.report.model.Emp;
import com.jxd.report.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EmpServiceImpl
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:05
 * @Version 1.0
 */
@Service
public class EmpServiceImpl extends ServiceImpl<IEmpDao, Emp> implements IEmpService {
    @Autowired
    private IEmpDao empDao;
    @Override
    public Map<String, Object> getAllEmpList(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String ename = queryMap.get("ename");
        String deptnos = queryMap.get("deptno");
        int deptno = 0;
        if((!"".equals(deptnos))&&(deptnos != null)){
            deptno = Integer.parseInt(deptnos);
        }
        System.out.println(ename);
        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Emp> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Emp> pageResult = empDao.getAllEmpList(pages,ename,deptno);
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
}

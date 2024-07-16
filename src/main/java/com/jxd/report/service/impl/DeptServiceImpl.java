package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IDeptDao;
import com.jxd.report.model.Dept;
import com.jxd.report.model.Project;
import com.jxd.report.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeptServiceImpl
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:08
 * @Version 1.0
 */
@Service
public class DeptServiceImpl extends ServiceImpl<IDeptDao, Dept> implements IDeptService {
    @Autowired
    private IDeptDao deptDao;
    @Override
    public Map<String, Object> getAllDept(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String dname = queryMap.get("dname");

        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Dept> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Dept> pageResult = deptDao.getAllDept(pages,dname);
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

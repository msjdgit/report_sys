package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IProjectDao;
import com.jxd.report.model.Emp;
import com.jxd.report.model.Project;
import com.jxd.report.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Project
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/29 21:09
 * @Version 1.0
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<IProjectDao, Project> implements IProjectService {
    @Autowired
    private IProjectDao projectDao;
    @Override
    public Map<String, Object> getAllProjList(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String pname = queryMap.get("pname");
        String deptnos = queryMap.get("deptno");
        int deptno = 0;
        if(!"".equals(deptnos)){
            deptno = Integer.parseInt(deptnos);
        }

        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Project> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Project> pageResult = projectDao.getAllProjList(pages,pname,deptno);
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

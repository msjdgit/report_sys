package com.jxd.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.report.model.Dept;

import java.util.List;
import java.util.Map;

public interface IDeptService extends IService<Dept> {
    Map<String,Object> getAllDept(Map<String,String> map);
}

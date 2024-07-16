package com.jxd.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.report.model.Emp;

import java.util.Map;

public interface IEmpService extends IService<Emp> {
    Map<String, Object> getAllEmpList(Map<String,String> map);
}

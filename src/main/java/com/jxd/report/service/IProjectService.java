package com.jxd.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.report.model.Project;

import java.util.Map;

public interface IProjectService extends IService<Project> {
    Map<String, Object> getAllProjList(Map<String,String> map);
}

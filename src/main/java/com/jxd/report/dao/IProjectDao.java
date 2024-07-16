package com.jxd.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.report.model.Emp;
import com.jxd.report.model.Project;
import org.apache.ibatis.annotations.Param;

public interface IProjectDao extends BaseMapper<Project> {
    IPage<Project> getAllProjList(IPage<Project> page, @Param("pname") String pname, @Param("deptno") int deptno);
}

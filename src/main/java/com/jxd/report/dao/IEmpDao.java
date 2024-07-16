package com.jxd.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.report.model.Emp;
import org.apache.ibatis.annotations.Param;

public interface IEmpDao extends BaseMapper<Emp> {
    IPage<Emp> getAllEmpList(IPage<Emp> page, @Param("ename") String ename, @Param("deptno") int deptno);
}

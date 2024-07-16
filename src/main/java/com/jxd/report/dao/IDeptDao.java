package com.jxd.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.report.model.Dept;
import org.apache.ibatis.annotations.Param;

public interface IDeptDao extends BaseMapper<Dept> {
    IPage<Dept> getAllDept(IPage<Dept> page, @Param("dname") String dname);
}

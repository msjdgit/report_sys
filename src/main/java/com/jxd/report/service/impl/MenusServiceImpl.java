package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IMenusDao;
import com.jxd.report.model.Menus;
import com.jxd.report.service.IMenusService;
import org.springframework.stereotype.Service;

/**
 * @ClassName MenusServiceImpl
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/1 18:49
 * @Version 1.0
 */

@Service
public class MenusServiceImpl extends ServiceImpl<IMenusDao, Menus> implements IMenusService {
}

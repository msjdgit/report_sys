package com.jxd.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.report.dao.IUserDao;
import com.jxd.report.model.Dept;
import com.jxd.report.model.User;
import com.jxd.report.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/13 17:16
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, User> implements IUserService {

}

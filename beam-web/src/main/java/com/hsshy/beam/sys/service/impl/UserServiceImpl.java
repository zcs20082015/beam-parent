package com.hsshy.beam.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsshy.beam.sys.dao.UserMapper;
import com.hsshy.beam.sys.entity.User;
import com.hsshy.beam.sys.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 管理员表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}

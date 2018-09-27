package com.hsshy.beam.modular.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsshy.beam.modular.user.dao.UserMapper;
import com.hsshy.beam.modular.user.entity.User;
import com.hsshy.beam.modular.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}

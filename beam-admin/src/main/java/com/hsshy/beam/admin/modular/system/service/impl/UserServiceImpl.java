package com.hsshy.beam.admin.modular.system.service.impl;
import com.hsshy.beam.admin.modular.system.dao.UserMapper;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.hsshy.beam.admin.modular.system.service.IUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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

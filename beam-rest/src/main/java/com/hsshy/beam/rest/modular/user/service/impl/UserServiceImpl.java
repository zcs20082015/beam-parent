package com.hsshy.beam.rest.modular.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsshy.beam.rest.modular.user.dao.UserMapper;
import com.hsshy.beam.rest.modular.user.entity.User;
import com.hsshy.beam.rest.modular.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author hs
 * @date 2019-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}

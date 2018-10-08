package com.hsshy.beam.admin.modular.system.dao;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 管理员表
 * 
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
	
}

package com.hsshy.beam.sys.service;


import com.hsshy.beam.common.base.service.ICommonService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.sys.dto.ChangePassowdForm;
import com.hsshy.beam.sys.entity.User;

/**
 * 管理员表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
public interface IUserService extends ICommonService<User> {

    R selectPageList(User user);

    R saveUser(User user);

    R changeStatus(Long userId, Integer flag);

    R deleteUser(Long userIds[]);

    R resetPassword(Long userIds[]);

    R changePassword(ChangePassowdForm changePassowdForm);

}

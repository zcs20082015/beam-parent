package com.hsshy.beam.sys.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsshy.beam.common.constant.Constant;
import com.hsshy.beam.common.shiro.ShiroUtils;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.common.utils.ToolUtil;
import com.hsshy.beam.sys.dao.UserMapper;
import com.hsshy.beam.sys.dto.ChangePassowdForm;
import com.hsshy.beam.sys.entity.User;
import com.hsshy.beam.sys.service.IUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Arrays;
import java.util.List;

/**
 * 管理员表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public R selectPageList(User user) {
        return R.ok(baseMapper.selectPageList(new Page(user.getCurrentPage(),user.getPageSize()),user));
    }

    @Override
    public R saveUser(User user) {

        Assert.notNull(user.getAccount(),"登陆账号不能为空");
        Assert.notNull(user.getName(),"真实姓名不能为空");
        if(ToolUtil.isNotEmpty(user.getId())){
            if(user.getId()==Constant.SUPER_ADMIN&&ShiroUtils.getUserId().longValue()!=Constant.SUPER_ADMIN){
                return R.fail("不能修改超级管理员信息");
            }
            User oldUser = this.getById(user.getId());
            Assert.notNull(oldUser,"找不到该用户");
            if(this.updateById(user)){
                return R.ok();
            }
            else {
                return R.fail("未知原因，保存失败");
            }
        }
        else {

            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setPassword(ShiroUtils.sha256("123456", salt));
            if(this.save(user)){
                return R.ok();

            }
            else {
                return R.fail("未知原因，保存失败");
            }
        }



    }

    @Override
    public R changeStatus(Long userId, Integer flag) {
        if(userId == Constant.SUPER_ADMIN){
            return R.fail("不能修改超级管理员的状态");
        }
        User user = this.getById(userId);
        Assert.notNull(user,"找不到用户");
        user.setStatus(flag);
        this.updateById(user);
        return R.ok();
    }

    @Override
    public R deleteUser(Long[] userIds) {
        for(Long userId:userIds){
            if(userId == Constant.SUPER_ADMIN){
                return R.fail("管理员不能删除");
            }
        }
        this.removeByIds(Arrays.asList(userIds));
        return R.ok();
    }

    @Override
    public R resetPassword(Long[] userIds) {
        for(Long userId:userIds){
            if(userId == Constant.SUPER_ADMIN){
                return R.fail("管理员密码不能重置");
            }
        }
        List<User> userList = this.list(new QueryWrapper<User>().lambda().in(User::getId,userIds));
        Assert.notNull(userList,"找不到用户");
        userList.forEach(user -> {
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setPassword(ShiroUtils.sha256("123456", salt));
        });
        this.updateBatchById(userList);
        return R.ok();
    }

    @Override
    public R changePassword(ChangePassowdForm changePassowdForm) {
        if(!changePassowdForm.getNewPwd().equals(changePassowdForm.getPassword_confirm())){
            return R.fail("两次密码不一致");
        }

        User user = this.getById(ShiroUtils.getUserId());
        Assert.notNull(user,"找不到该用户");
        String old = ShiroUtils.sha256(changePassowdForm.getOldPwd(),user.getSalt());
        if(user.getPassword().equals(old)){
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setPassword(ShiroUtils.sha256("123456", salt));
            this.updateById(user);
            return R.ok("修改成功");
        }
        else {
            return R.fail("密码有误");
        }
    }


}

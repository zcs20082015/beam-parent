package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.common.annotion.Permission;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.sys.dto.ChangePassowdForm;
import com.hsshy.beam.sys.entity.User;
import com.hsshy.beam.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
@Api(value="UserController",tags={"User接口"})
@RequestMapping("/sys/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/page/list")
    public Object pageList(User user)  {

        return  R.ok(userService.selectPageList(user));
    }

    @ApiOperation("改变状态,是否可用")
    @PostMapping(value = "/change/status/{flag}")
    public Object changeStatus(@RequestBody Long userId,@PathVariable Integer flag){
       return userService.changeStatus(userId,flag);
    }

    @ApiOperation("保存用户")
    @PostMapping(value = "/save")
    public Object save(@RequestBody User user){
        return userService.saveUser(user);
    }

    @ApiOperation("批量删除用户")
    @PostMapping(value = "/delete")
    public Object delete(@RequestBody Long userIds[]){
        return userService.deleteUser(userIds);
    }

    @ApiOperation("用户详情")
    @PostMapping(value = "/info}")
    public Object info(@RequestBody Long userId){

        return R.ok(userService.getById(userId));
    }

    @ApiOperation("重置用户密码")
    @PostMapping(value = "/reset/password")
    public Object resetPassword(@RequestBody Long userIds[]){
        return userService.resetPassword(userIds);
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/change/password")
    public Object changePassword(@RequestBody ChangePassowdForm changePassowdForm){

        return userService.changePassword(changePassowdForm);
    }



}
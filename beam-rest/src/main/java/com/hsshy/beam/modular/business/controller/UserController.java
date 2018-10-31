package com.hsshy.beam.modular.business.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.sys.service.IRoleService;
import com.hsshy.beam.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="UserController",tags={"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation("用户列表")
    @GetMapping("userList")
    public Object getUserList(){
        return R.ok(userService.list(new QueryWrapper<>()));
    }


    @ApiOperation("角色列表")
    @GetMapping("roleList")
    public Object getRoleList(){
        return R.ok(roleService.list(new QueryWrapper<>()));
    }


}
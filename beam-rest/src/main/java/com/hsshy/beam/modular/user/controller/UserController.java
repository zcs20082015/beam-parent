package com.hsshy.beam.modular.user.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.modular.auth.util.JwtTokenUtil;
import com.hsshy.beam.modular.user.dto.AuthRequest;
import com.hsshy.beam.modular.user.entity.User;
import com.hsshy.beam.modular.user.service.IUserService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.web.base.controller.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Api(value="UserController",tags={"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController extends BaseRestController<User,Long> {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserController() {
        setCommonService(userService);
    }

    @ApiOperation(value = "获取token")
    @PostMapping(value = "${jwt.auth-path}/token/get")
    public Object getToken(@RequestBody AuthRequest authRequest) {
        Assert.notNull(authRequest,"数据不能为空");
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
        Map map = new HashMap<>();
        map.put("randomKey",randomKey);
        map.put("token",token);
        return R.ok(map);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping(value = "/getByUserNameAndPassword")
    public Object getByUserNameAndPassword(AuthRequest authRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(User::getName,authRequest.getUserName()).eq(User::getPassword,authRequest.getPassword());
        return R.ok(userService.getOne(queryWrapper));
    }

    @ApiOperation(value = "获取用户post")
    @PostMapping(value = "/getByUserNameAndPassword1")
    public Object getByUserNameAndPassword1(@RequestBody AuthRequest authRequest,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(User::getName,authRequest.getUserName()).eq(User::getPassword,password);
        return R.ok(userService.getOne(queryWrapper));
    }





    @ApiOperation(value = "查询")
    @GetMapping(value = "/getByIdAndName")
    public Object getOne(@RequestParam  Long id,@RequestParam  String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(User::getName,name).eq(User::getId,id);
        return R.ok(userService.getOne(queryWrapper));
    }



}
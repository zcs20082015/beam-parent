package com.hsshy.beam.modular.user.controller;
import com.hsshy.beam.base.controller.CommonController;
import com.hsshy.beam.modular.user.entity.User;
import com.hsshy.beam.modular.user.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value="UserController",tags={"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController extends CommonController<User> {

    @Autowired
    private IUserService userService;

    public UserController() {
        setCommonService(userService);
    }

}
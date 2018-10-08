package com.hsshy.beam.admin.modular.system.controller;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.hsshy.beam.admin.modular.system.service.IUserService;
import com.hsshy.beam.web.base.controller.beam.BaseAdminController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
@Api(value="UserController",tags={"User接口"})
@RequestMapping("/system/user")
@Controller
public class UserController extends BaseAdminController<User,Long> {

    @Autowired
    private IUserService userService;



    public UserController() {
        setCommonService(userService);
    }





}
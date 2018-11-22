package com.hsshy.beam.modular.system.controller;

import com.hsshy.beam.sys.entity.Role;
import com.hsshy.beam.sys.service.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 角色
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:13:03
 */
@Api(value="RoleController",tags={"Role接口"})
@Controller
@RequestMapping("/system/role")
public class RoleController  {

    @Autowired
    private IRoleService roleService;








}
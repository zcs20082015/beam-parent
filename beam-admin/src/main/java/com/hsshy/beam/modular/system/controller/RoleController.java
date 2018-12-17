package com.hsshy.beam.modular.system.controller;

import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.sys.entity.Role;
import com.hsshy.beam.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:13:03
 */
@Api(value="RoleController",tags={"Role接口"})
@RestController
@RequestMapping("/sys/role")
public class RoleController  {

    @Autowired
    private IRoleService roleService;


    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/page/list")
    public Object pageList(Role role)  {

        return  R.ok(roleService.selectPageList(role));
    }

    @ApiOperation("保存角色")
    @PostMapping(value = "/save")
    public Object save(@RequestBody Role role){

        return roleService.saveOrUpdate(role);
    }

    @ApiOperation("批量删除用户")
    @PostMapping(value = "/delete")
    public Object delete(@RequestBody Long roleIds[]){

        return roleService.deleteRole(roleIds);
    }

    @ApiOperation("用户详情")
    @PostMapping(value = "/info}")
    public Object info(@RequestBody Long roleId){

        return R.ok(roleService.getById(roleId));
    }





}
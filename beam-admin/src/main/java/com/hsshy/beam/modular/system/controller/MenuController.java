package com.hsshy.beam.modular.system.controller;

import com.hsshy.beam.modular.system.entity.Menu;
import com.hsshy.beam.modular.system.service.IMenuService;
import com.hsshy.beam.web.base.controller.BaseAdminController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单管理
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-08 16:33:17
 */
@Api(value="MenuController",tags={"Menu接口"})
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseAdminController<Menu,Long> {

    @Autowired
    private IMenuService menuService;



    public MenuController() {
        setCommonService(menuService);
    }





}
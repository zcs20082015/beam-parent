package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.common.base.controller.BaseController;
import com.hsshy.beam.common.shiro.ShiroUtils;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.common.utils.RedisUtil;
import com.hsshy.beam.sys.entity.Menu;
import com.hsshy.beam.sys.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-08 16:33:17
 */
@Api(value="MenuController",tags={"Menu接口"})
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 导航菜单
     */
    @ApiOperation(value = "导航菜单")
    @GetMapping("/nav")
    public R nav(){
        List<Menu> menuList = menuService.getUserMenuList(ShiroUtils.getUserId());
        return R.ok(menuList);
    }










}
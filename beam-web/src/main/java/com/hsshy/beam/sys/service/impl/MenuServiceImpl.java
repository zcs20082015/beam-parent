package com.hsshy.beam.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsshy.beam.common.constant.Constant;
import com.hsshy.beam.common.constant.cache.Cache;
import com.hsshy.beam.common.constant.cache.CacheKey;
import com.hsshy.beam.sys.dao.MenuMapper;
import com.hsshy.beam.sys.entity.Menu;
import com.hsshy.beam.sys.service.IMenuService;
import com.hsshy.beam.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-08 16:33:17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IUserService userService;


    @Override
    public List<Menu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<Menu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<Menu> userMenuList = new ArrayList<>();
        for(Menu menu : menuList){
            if(menuIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<Menu> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }


    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.USER_ID + "'+#userId")
    public List<Menu> getUserMenuList(Long userId) {

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<Menu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<Menu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<Menu> getMenuTreeList(List<Menu> menuList, List<Long> menuIdList){
        List<Menu> subMenuList = new ArrayList<Menu>();

        for(Menu entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}

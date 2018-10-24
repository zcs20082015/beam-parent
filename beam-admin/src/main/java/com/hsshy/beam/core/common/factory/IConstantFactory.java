package com.hsshy.beam.core.common.factory;


import com.hsshy.beam.modular.system.entity.Dict;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Long userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Long userId);

    /**
     * @method
     * @description 根据用户id获取角色id
     * @date: 2018/10/10 22:06
     * @author: hs
     * @param  * @param null
     * @return
     */
    List<Long> getRoleIdsById(Long userId);


    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Long roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Long roleId);




    /**
     * 获取部门名称
     */
    String getDeptName(Long deptId);



    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);


    /**
     * 获取字典名称
     */
    String getDictName(Long dictId);


    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);



    /**
     * 查询字典
     */
    List<Dict> findInDict(Long id);



    /**
     * 获取子部门id
     */
    List<Long> getSubDeptId(Long deptid);



}

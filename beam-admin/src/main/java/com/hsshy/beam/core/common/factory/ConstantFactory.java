package com.hsshy.beam.core.common.factory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.common.utils.SpringContextHolder;
import com.hsshy.beam.common.utils.ToolUtil;
import com.hsshy.beam.modular.system.dao.*;
import com.hsshy.beam.modular.system.entity.*;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }


    @Override
    public List<Long> getRoleIdsById(Long userId) {
        return userMapper.getRoleIdsById(userId);
    }


    /**
     * 通过角色id获取角色名称
     */
    @Override
//    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getRoleName())) {
            return roleObj.getRoleName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
//    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getRoleName())) {
            return roleObj.getRemark();
        }
        return "";
    }

    /**
     * 获取部门名称
     */
    @Override
//    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getName())) {
            return dept.getName();
        }
        return "";
    }


    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }



    /**
     * 获取字典名称
     */
    @Override
    public String getDictName(Long dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dict dict = dictMapper.selectById(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }



    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, Integer val) {
        QueryWrapper<Dict> temp = new QueryWrapper<>();
        temp.lambda().eq(Dict::getName,name);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return "";
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Dict::getPid, dict.getId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
            return "";
        }
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }


    /**
     * 查询字典
     */
    @Override
    public List<Dict> findInDict(Long id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            List<Dict> dicts = dictMapper.selectList(wrapper.eq("pid", id));
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }



    /**
     * 获取子部门id
     */
    @Override
    public List<Long> getSubDeptId(Long deptid) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("pids", "%[" + deptid + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<Long> deptids = new ArrayList<>();

        if(depts != null && depts.size() > 0){
            for (Dept dept : depts) {
                deptids.add(dept.getId());
            }
        }

        return deptids;
    }




}

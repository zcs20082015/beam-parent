package com.hsshy.beam.common.factory;

import java.util.List;

public interface IConstantFactory {

    //根据用户ID获取其所有角色ID
    List<Long> getRoleIdsById(Long userId);

    //通过角色id获取角色名称
    String getSingleRoleName(Long roleId);

    //获取部门名称
    String getDeptName(Long deptId);


}

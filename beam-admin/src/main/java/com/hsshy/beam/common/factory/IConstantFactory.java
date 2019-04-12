package com.hsshy.beam.common.factory;

import java.util.List;

public interface IConstantFactory {

    //根据用户ID获取其所有角色ID
    List<Long> getRoleIdsById(Long userId);

    //通过角色id获取角色名称
    String getSingleRoleName(Long roleId);

    //获取部门名称
    String getDeptName(Long deptId);

    //根据父级字典名称和code获取字典名称
    String getDictsByName(String name,String code);

    //根据父级字典code和获取字典名称
    String getDictsByCode(String pcode,String code);


}

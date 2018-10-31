package com.hsshy.beam.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.common.base.entity.AbstractEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * 
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:13:03
 */
@Data
@TableName("sys_role")
public class Role extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}


	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}

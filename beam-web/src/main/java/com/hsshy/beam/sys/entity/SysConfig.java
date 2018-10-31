package com.hsshy.beam.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.common.base.entity.AbstractEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置信息
 * 
 * @author rachel.li
 */
@Data
@TableName("sys_config")
public class SysConfig extends AbstractEntity<Long> {

	@TableId
	private Long id;

	private String paramKey;

	private String paramValue;

	private Integer status;

	private String remark;

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

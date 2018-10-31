package com.hsshy.beam.common.base.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * 数据Entity类
 * 
 * @author hs
 * @date 2018-09-27
 *
 */
public abstract class RestEntity<ID> extends AbstractEntity<ID> {


	@TableField(value = "create_date", fill = FieldFill.INSERT)
	private Date createDate; // 创建日期
	@TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
	private Date updateDate; // 更新日期

	public RestEntity() {
		super();

	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




}

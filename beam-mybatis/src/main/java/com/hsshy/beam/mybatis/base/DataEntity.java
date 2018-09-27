package com.hsshy.beam.mybatis.base;



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hsshy.beam.mybatis.constant.DataBaseConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据Entity类
 * 
 * @author hs
 * @date 2018-09-27
 *
 */
public abstract class DataEntity<ID> extends Model {


	public abstract ID getId();

	public abstract void setId(ID id);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.domain.Persistable#isNew()
	 *
	 */
	public boolean isNew() {

		return null == getId();
	}

	@TableId
	private Long id;

	@TableField(value = "remarks")
	private String remarks; // 备注
	@TableField(value = "create_by", el = "createBy.id", fill = FieldFill.INSERT)
	private Long createBy; // 创建者
	@TableField(value = "create_date", fill = FieldFill.INSERT)
	private Date createDate; // 创建日期
	@TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.UPDATE)
	private Long updateBy; // 更新者
	@TableField(value = "update_date", fill = FieldFill.UPDATE)
	private Date updateDate; // 更新日期
	@TableField(value = "del_flag", fill = FieldFill.INSERT)
	private Integer delFlag; // 删除标记（0：正常；1：删除 ）

	public DataEntity() {
		super();
		this.delFlag = DataBaseConstant.DEL_FLAG_NORMAL;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

package com.hsshy.beam.modular.system.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.hsshy.beam.web.base.entity.AbstractEntity;
import com.hsshy.beam.web.base.entity.DataEntity;

/**
 * 字典表
 * 
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:39:07
 */
@TableName("sys_dict")
public class Dict extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 排序
	 */
	private Integer num;
	/**
	 * 父级字典
	 */
	private Long pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 提示
	 */
	private String tips;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置：排序
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：排序
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：父级字典
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父级字典
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：提示
	 */
	public void setTips(String tips) {
		this.tips = tips;
	}
	/**
	 * 获取：提示
	 */
	public String getTips() {
		return tips;
	}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}

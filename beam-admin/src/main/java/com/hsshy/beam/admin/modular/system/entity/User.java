package com.hsshy.beam.admin.modular.system.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import com.hsshy.beam.web.base.entity.DataEntity;

/**
 * 管理员表
 * 
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-07 18:03:20
 */
@TableName("sys_user")
public class User extends DataEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * md5密码盐
	 */
	private String salt;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 性别（1：男 2：女）
	 */
	private Integer sex;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 状态(1：启用  2：冻结  3：删除）
	 */
	private Integer status;
	/**
	 * 保留字段
	 */
	private Integer version;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：头像
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：md5密码盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：md5密码盐
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：性别（1：男 2：女）
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别（1：男 2：女）
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮件
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：状态(1：启用  2：冻结  3：删除）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态(1：启用  2：冻结  3：删除）
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：保留字段
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：保留字段
	 */
	public Integer getVersion() {
		return version;
	}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}

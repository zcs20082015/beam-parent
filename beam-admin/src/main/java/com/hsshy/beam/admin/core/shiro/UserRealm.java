/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.hsshy.beam.admin.core.shiro;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.admin.core.util.Constant;
import com.hsshy.beam.admin.modular.system.dao.MenuMapper;
import com.hsshy.beam.admin.modular.system.dao.UserMapper;
import com.hsshy.beam.admin.modular.system.entity.Menu;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.hsshy.beam.admin.modular.system.service.IUserService;
import com.hsshy.beam.common.utils.ToolUtil;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 认证
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 上午11:55:49
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    
    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		Long userId = user.getId();
		
		List<String> permsList;
		
		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN){
			List<Menu> menuList = menuMapper.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			for(Menu menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = userMapper.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for(String perms : permsList){
			if(ToolUtil.isEmpty(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

		//查询用户信息
		User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getAccount,token.getUsername()));


		//账号不存在
		if(user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		return info;
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}
}

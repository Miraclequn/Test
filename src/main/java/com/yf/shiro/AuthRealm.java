
package com.yf.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.yf.entity.Permission;
import com.yf.entity.Role;
import com.yf.entity.User;
import com.yf.mapper.PermissionMapper;
import com.yf.mapper.RoleMapper;
import com.yf.mapper.UserMapper;


public class AuthRealm extends AuthorizingRealm{
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RoleMapper roleMapper;
  @Autowired
  private PermissionMapper permissionMapper;
  
	//认证.登录
	  @Override
	  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("身份认证");
	    UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
	    String userName = utoken.getUsername();
	    User user = userMapper.getUserByName(userName);
	    if(user != null){
	    return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
	  }else {
		return null;
	  }
	  }
	  
	  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限认证");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		User user = (User)principals.getPrimaryPrincipal();
		Role role = roleMapper.getUserRole(user.getUid());
		simpleAuthorizationInfo.addRole(role.getRole());
		List<Permission> list=permissionMapper.getUserPermission(role.getId());
		System.out.println(list.size());
		for (Permission permission : list) {
			simpleAuthorizationInfo.addStringPermission(permission.getPermission());
		}
        return simpleAuthorizationInfo;
	}

}
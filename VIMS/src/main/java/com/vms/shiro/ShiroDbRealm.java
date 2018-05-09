package com.vms.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vms.model.ShiroUser;
import com.vms.model.User;
import com.vms.service.RoleService;
import com.vms.service.UserService;

/**
 * @description：shiro权限认证
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    public ShiroDbRealm()
    {
        super();
        setAuthenticationTokenClass(AuthenticationToken.class);
        LOGGER.debug("Initial Resources-Realm: {}, set authenticationTokenClass = {}", this, getAuthenticationTokenClass());
    }
    
    /**
     * Shiro登录认证(原理：用户提交 用户名和密码 --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ----
     * shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        String loginname = null;
        Object credentials = null;
        boolean passwdCredentials = false;
        
        UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
        loginname = (String) upToken.getUsername();
        passwdCredentials = true;
        LOGGER.info("Shiro开始登录认证");
        User user = userService.findUserByLoginName(loginname);
        // 账号不存在
        if(user == null)
        {
            return null;
        }
        
        // 后面根据用户id查询
        List<Long> roleList = roleService.findRoleIdListByUserId(user.getId());
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginname(), user.getName(), roleList);
        
        if(passwdCredentials)
        {
            credentials = user.getPassword().toCharArray();
        }
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, credentials, getName());
        
    }
    
    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();
        
        Set<String> urlSet = new HashSet<String>();
        for(Long roleId : roleList)
        {
            List<String> roleResourceList = roleService.findResourcesByRoleId(roleId);
            if(CollectionUtils.isNotEmpty(roleResourceList))
            {
                urlSet.addAll(roleResourceList);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
}

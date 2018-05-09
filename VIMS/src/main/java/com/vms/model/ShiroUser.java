/**
 *
 */
package com.vms.model;

import java.io.Serializable;
import java.util.List;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroUser implements Serializable
{
    
    private static final long serialVersionUID = -1373760761780840081L;
    
    public String id;
    
    public String loginName;
    
    public String name;
    
    public boolean isAuth;
    
    /**
     * 供电单位
     */
    private String pOrgNo;
    
    private List <Long> roleList;
    
    public ShiroUser (String id, String loginName, String name, List <Long> roleList)
    {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.roleList = roleList;
    }
    
    public String getName ()
    {
        return name;
    }
    
    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString ()
    {
        return loginName;
    }
    
    public String getpOrgNo ()
    {
        return pOrgNo;
    }
    
    public void setpOrgNo (String pOrgNo)
    {
        this.pOrgNo = pOrgNo;
    }
    
    public boolean isAuth ()
    {
        return isAuth;
    }
    
    public void setAuth (boolean isAuth)
    {
        this.isAuth = isAuth;
    }
    
    public List <Long> getRoleList ()
    {
        return roleList;
    }
    
    public void setRoleList (List <Long> roleList)
    {
        this.roleList = roleList;
    }
    
}
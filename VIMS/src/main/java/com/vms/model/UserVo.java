package com.vms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description：UserVo
 */
public class UserVo implements Serializable
{
    /**
     *用户类型-管理员
     */
    public static final int USER_TYPE_ADMINISTRATOR = 0;
    
    /**
     *用户类型-用户
     */
    public static final int USER_TYPE_USER = 1;
    
    /**
     * 
     */
    private static final long serialVersionUID = -6777958953004126087L;
    
    private String id;
    
    @Size (min = 5, max = 16, message = "loginname::validate.loginName")
    private String loginname;
    
    @Size (min = 1, max = 32, message = "name::validate.checkName")
    private String name;
    
    @Size (min = 6, max = 20, message = "password::validate.size")
    private String password;
    
    @NotNull (message = "sex::validate.NotBlank")
    private Integer sex;
    
    @NotNull (message = "status::validate.NotBlank")
    private Integer status;
    
    @NotBlank (message = "department::validate.NotBlank")
    private String organizationId;
    
    @NotBlank (message = "roles::validate.NotBlank")
    private String roleIds;
    
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdate;
    
    private String createdateShow;
    
    private String roleName;
    
    private Integer age;
    
    private Integer usertype;
    
    private String pOrganizationId;
    
    private String loginnameOld;
    
    private String phone;
    
    private List<Role> rolesList;
    
    private String organizationName;
    
    private String pOrganizationName;
    
    private Date createdateStart;
    
    private Date createdateEnd;
    
    private String webGuideFirst;
    
    private Date webGuideUpdateTime;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getLoginname()
    {
        return loginname;
    }
    
    public void setLoginname(String loginname)
    {
        this.loginname = loginname == null ? null : loginname.trim();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }
    
    public Integer getSex()
    {
        return sex;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public Integer getAge()
    {
        return age;
    }
    
    public void setAge(Integer age)
    {
        this.age = age;
    }
    
    public Integer getUsertype()
    {
        return usertype;
    }
    
    public void setUsertype(Integer usertype)
    {
        this.usertype = usertype;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getOrganizationId()
    {
        return organizationId;
    }
    
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
    
    public Date getCreatedate()
    {
        if(createdate == null)
        {
            return null;
        }
        return (Date) createdate.clone();
    }
    
    public void setCreatedate(Date createdate)
    {
        if(createdate == null)
        {
            this.createdate = null;
        }
        else
        {
            this.createdate = (Date) createdate.clone();
        }
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone == null ? null : phone.trim();
    }
    
    public List<Role> getRolesList()
    {
        return rolesList;
    }
    
    public void setRolesList(List<Role> rolesList)
    {
        this.rolesList = rolesList;
    }
    
    public String getOrganizationName()
    {
        return organizationName;
    }
    
    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }
    
    public String getRoleIds()
    {
        return roleIds;
    }
    
    public void setRoleIds(String roleIds)
    {
        this.roleIds = roleIds;
    }
    
    public Date getCreatedateStart()
    {
        if(createdateStart == null)
        {
            return null;
        }
        return (Date) createdateStart.clone();
    }
    
    public void setCreatedateStart(Date createdateStart)
    {
        if(createdateStart == null)
        {
            this.createdateStart = null;
        }
        else
        {
            this.createdateStart = (Date) createdateStart.clone();
        }
    }
    
    public Date getCreatedateEnd()
    {
        if(createdateEnd == null)
        {
            return null;
        }
        return (Date) createdateEnd.clone();
    }
    
    public void setCreatedateEnd(Date createdateEnd)
    {
        if(createdateEnd == null)
        {
            this.createdateEnd = null;
        }
        else
        {
            this.createdateEnd = (Date) createdateEnd.clone();
        }
    }
    
    public String getLoginnameOld()
    {
        return loginnameOld;
    }
    
    public void setLoginnameOld(String loginnameOld)
    {
        this.loginnameOld = loginnameOld;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    public String getpOrganizationId()
    {
        return pOrganizationId;
    }
    
    public void setpOrganizationId(String pOrganizationId)
    {
        this.pOrganizationId = pOrganizationId;
    }
    
    public String getpOrganizationName()
    {
        return pOrganizationName;
    }
    
    public void setpOrganizationName(String pOrganizationName)
    {
        this.pOrganizationName = pOrganizationName;
    }
    
    public String getWebGuideFirst()
    {
        return webGuideFirst;
    }
    
    public void setWebGuideFirst(String webGuideFirst)
    {
        this.webGuideFirst = webGuideFirst;
    }
    
    public Date getWebGuideUpdateTime()
    {
        return webGuideUpdateTime;
    }
    
    public void setWebGuideUpdateTime(Date webGuideUpdateTime)
    {
        this.webGuideUpdateTime = webGuideUpdateTime;
    }
    
    public String getCreatedateShow()
    {
        return createdateShow;
    }
    
    public void setCreatedateShow(String createdateShow)
    {
        this.createdateShow = createdateShow;
    }
    
}
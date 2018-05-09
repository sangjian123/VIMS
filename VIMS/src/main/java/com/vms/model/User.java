package com.vms.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description：用户
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public class User implements Serializable
{
    
    private static final long serialVersionUID = 6700813629656881143L;
    
    private String id;
    
    private String loginname;
    
    private String username;
    
    private String name;
    
    private String password;
    
    private Integer sex;
    
    private Integer age;
    
    private Integer usertype;
    
    private Integer status;
    
    private String organizationId;
    
    private String pOrganizationId;
    
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdate;
    
    private String phone;
    
    private String pwdFirstUpdate;
    
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwdUpdateTime;
    
    private String deptLeader;
    
    private String webGuideFirst;
    
    private Date webGuideUpdateTime;
    
    private String code;
    
    private String validateCode;
    
    public String getCode ()
    {
        return code;
    }
    
    public void setCode (String code)
    {
        this.code = code;
    }
    
    public String getValidateCode ()
    {
        return validateCode;
    }
    
    public void setValidateCode (String validateCode)
    {
        this.validateCode = validateCode;
    }
    
    public String getId ()
    {
        return id;
    }
    
    public void setId (String id)
    {
        this.id = id;
    }
    
    public String getUsername ()
    {
        return username;
    }
    
    public void setUsername (String username)
    {
        this.username = username;
    }
    
    public String getLoginname ()
    {
        return loginname;
    }
    
    public void setLoginname (String loginname)
    {
        this.loginname = loginname == null ? null : loginname.trim ();
    }
    
    public String getName ()
    {
        return name;
    }
    
    public void setName (String name)
    {
        this.name = name == null ? null : name.trim ();
    }
    
    public String getPassword ()
    {
        return password;
    }
    
    public void setPassword (String password)
    {
        this.password = password == null ? null : password.trim ();
    }
    
    public Integer getSex ()
    {
        return sex;
    }
    
    public void setSex (Integer sex)
    {
        this.sex = sex;
    }
    
    public Integer getAge ()
    {
        return age;
    }
    
    public void setAge (Integer age)
    {
        this.age = age;
    }
    
    public Integer getUsertype ()
    {
        return usertype;
    }
    
    public void setUsertype (Integer usertype)
    {
        this.usertype = usertype;
    }
    
    public Integer getStatus ()
    {
        return status;
    }
    
    public void setStatus (Integer status)
    {
        this.status = status;
    }
    
    public String getOrganizationId ()
    {
        return organizationId;
    }
    
    public void setOrganizationId (String organizationId)
    {
        this.organizationId = organizationId;
    }
    
    public Date getCreatedate ()
    {
        if (createdate == null)
        {
            return null;
        }
        return (Date) createdate.clone ();
    }
    
    public void setCreatedate (Date createdate)
    {
        if (createdate == null)
        {
            this.createdate = null;
        }
        else
        {
            this.createdate = (Date) createdate.clone ();
        }
    }
    
    public String getPhone ()
    {
        return phone;
    }
    
    public void setPhone (String phone)
    {
        this.phone = phone == null ? null : phone.trim ();
    }
    
    public String getpOrganizationId ()
    {
        return pOrganizationId;
    }
    
    public void setpOrganizationId (String pOrganizationId)
    {
        this.pOrganizationId = pOrganizationId;
    }
    
    public String getPwdFirstUpdate ()
    {
        return pwdFirstUpdate;
    }
    
    public void setPwdFirstUpdate (String pwdFirstUpdate)
    {
        this.pwdFirstUpdate = pwdFirstUpdate;
    }
    
    public Date getPwdUpdateTime ()
    {
        if (pwdUpdateTime == null)
        {
            return null;
        }
        return (Date) pwdUpdateTime.clone ();
    }
    
    public void setPwdUpdateTime (Date pwdUpdateTime)
    {
        if (pwdUpdateTime == null)
        {
            this.pwdUpdateTime = null;
        }
        else
        {
            this.pwdUpdateTime = (Date) pwdUpdateTime.clone ();
        }
    }
    
    public String getDeptLeader ()
    {
        return deptLeader;
    }
    
    public void setDeptLeader (String deptLeader)
    {
        this.deptLeader = deptLeader;
    }
    
    public String getWebGuideFirst ()
    {
        return webGuideFirst;
    }
    
    public void setWebGuideFirst (String webGuideFirst)
    {
        this.webGuideFirst = webGuideFirst;
    }
    
    public Date getWebGuideUpdateTime ()
    {
        return webGuideUpdateTime;
    }
    
    public void setWebGuideUpdateTime (Date webGuideUpdateTime)
    {
        this.webGuideUpdateTime = webGuideUpdateTime;
    }
    
    @Override
    public String toString ()
    {
        return "User{ id=" + id + ", loginname='" + loginname + "', name='" + name + ", sex=" + sex + ", age=" + age
            + ", usertype=" + usertype + ", status=" + status + ", organizationId=" + organizationId + ", pOrganizationId="
            + pOrganizationId + ", createdate=" + createdate + ", pwdFirstUpdate=" + pwdFirstUpdate + ", pwdUpdateTime="
            + pwdUpdateTime + ", phone='" + phone + "\'}";
    }
    
}
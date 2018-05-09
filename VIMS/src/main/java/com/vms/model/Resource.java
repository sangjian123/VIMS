package com.vms.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vms.utils.I18nUtils;

/**
 * @description：资源
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class Resource implements Serializable
{
    
    private static final long serialVersionUID = -5321613594382537470L;
    
    private String id;
    
    private String name;
    
    private String nameFmt;
    
    private String url;
    
    private String description;
    
    @JsonProperty ("iconCls")
    private String icon;
    
    private String pid;
    
    private Integer seq;
    
    private Integer status;
    
    private Integer resourcetype;
    
    private Date createdate;
    
    private String userId;
    
    private String roleId;
    
    private String resourceId;
    
    private String topResourceId;
    
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private int childrenNum;
    
    private String menuCode;
    
    private boolean checked = false;
    
    public boolean isChecked()
    {
        return checked;
    }
    
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
    
    public String getMenuCode()
    {
        return menuCode;
    }
    
    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public String getResourceId()
    {
        return resourceId;
    }
    
    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }
    
    public String getTopResourceId()
    {
        return topResourceId;
    }
    
    public void setTopResourceId(String topResourceId)
    {
        this.topResourceId = topResourceId;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url == null ? null : url.trim();
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description == null ? null : description.trim();
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon == null ? null : icon.trim();
    }
    
    public String getPid()
    {
        return pid;
    }
    
    public void setPid(String pid)
    {
        this.pid = pid;
    }
    
    public Integer getSeq()
    {
        return seq;
    }
    
    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Integer getResourcetype()
    {
        return resourcetype;
    }
    
    public void setResourcetype(Integer resourcetype)
    {
        this.resourcetype = resourcetype;
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
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }
    
    public String getNameFmt()
    {
        nameFmt = I18nUtils.getI18nMsg(getName());
        return nameFmt;
    }
    
    public void setNameFmt(String nameFmt)
    {
        this.nameFmt = nameFmt;
    }
    
    public int getChildrenNum()
    {
        return childrenNum;
    }
    
    public void setChildrenNum(int childrenNum)
    {
        this.childrenNum = childrenNum;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(null == obj)
        {
            return false;
        }
        if(this.getClass() != obj.getClass())
        {
            
            return false;
        }
        Resource s = (Resource) obj;
        return id.equals(s.id) && name.equals(s.name);
    }
    
    @Override
    public int hashCode()
    {
        String in = id + name;
        return in.hashCode();
    }
    
    @Override
    public String toString()
    {
        return "Resource{" + "id=" + id + ", name='" + name + '\'' + ", url='" + url + '\'' + ", description='" + description
            + '\'' + ", icon='" + icon + '\'' + ", pid=" + pid + ", seq=" + seq + ", status=" + status + ", resourcetype="
            + resourcetype + ", createdate=" + createdate + ", userId=" + userId + ", resourceId=" + resourceId
            + ", topResourceId=" + topResourceId + '}';
    }
}
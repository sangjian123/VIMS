package com.vms.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.vms.utils.I18nUtils;

/**
 * @description：角色
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class Role implements Serializable
{
    
    /**
     * 默认排序
     */
    public static final Integer DEFAULT_SEQ = 0;
    
    /**
     * 角色状态-不可用
     */
    public static final Integer STATUS_DISABLED = 1;
    
    private static final long serialVersionUID = -1756241579303707517L;
    
    private Long id;
    
    @Size (min = 1, max = 64, message = "name::validate.size")
    private String name;
    
    private Integer seq;
    
    @Length (min = 0, max = 255, message = "description::validate.size")
    private String description;
    
    @NotNull (message = "status::validate.NotBlank")
    private Integer status;
    
    //角色管理页面 新增
    //个性化首页  0是  1否
    @NotNull (message = "oprIsIndex::validate.NotBlank")
    private Integer isIndex;
    
    //操作页 资源id
    private String resourceId;
    
    //操作页菜单名称国际化
    private String nameFmt;
    
    /**
     * 用于页面排序用
     */
    private String sort;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
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
    
    public Integer getSeq()
    {
        return seq;
    }
    
    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description == null ? null : description.trim();
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Integer getIsIndex()
    {
        return isIndex;
    }
    
    public void setIsIndex(Integer isIndex)
    {
        this.isIndex = isIndex;
    }
    
    public String getResourceId()
    {
        return resourceId;
    }
    
    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }
    
    public String getNameFmt()
    {
        if(StringUtils.isNotEmpty(getResourceId()))
        {
            nameFmt = I18nUtils.getI18nMsg("resource_id_" + getResourceId());
            return nameFmt;
        }
        else
        {
            return null;
        }
    }
    
    public void setNameFmt(String nameFmt)
    {
        this.nameFmt = nameFmt;
    }
    
    public String getSort()
    {
        return sort;
    }
    
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    
    @Override
    public String toString()
    {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + ", seq=" + seq + ", description='" + description + '\''
            + ", status=" + status + '}';
    }
}
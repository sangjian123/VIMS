package com.vms.model;

import java.util.List;

/**
 * @description：TreeVO
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class Tree implements java.io.Serializable
{
    
    public static final String STATE_OPEN = "open";
    
    public static final String STATE_CLOSE = "closed";
    
    private static final long serialVersionUID = 980682543891282923L;
    
    private Long id;
    
    private String text;
    
    private String name;
    
    private String url;
    
    private String state = "open";// open,closed
    
    private boolean checked = false;
    
    private Object attributes;
    
    private List <Tree> children;
    
    private String iconCls;
    
    private String type;
    
    private String typeName;
    
    private String pid;
    
    private String orgNo;
    
    public Long getId ()
    {
        return id;
    }
    
    public void setId (Long id)
    {
        this.id = id;
    }
    
    public String getText ()
    {
        return text;
    }
    
    public void setText (String text)
    {
        this.text = text;
    }
    
    public String getState ()
    {
        return state;
    }
    
    public void setState (String state)
    {
        this.state = state;
    }
    
    public boolean isChecked ()
    {
        return checked;
    }
    
    public void setChecked (boolean checked)
    {
        this.checked = checked;
    }
    
    public Object getAttributes ()
    {
        return attributes;
    }
    
    public void setAttributes (Object attributes)
    {
        this.attributes = attributes;
    }
    
    public List <Tree> getChildren ()
    {
        return children;
    }
    
    public void setChildren (List <Tree> children)
    {
        this.children = children;
    }
    
    public String getIconCls ()
    {
        return iconCls;
    }
    
    public void setIconCls (String iconCls)
    {
        this.iconCls = iconCls;
    }
    
    public String getPid ()
    {
        return pid;
    }
    
    public void setPid (String pid)
    {
        this.pid = pid;
    }
    
    public String getType ()
    {
        return type;
    }
    
    public void setType (String type)
    {
        this.type = type;
    }
    
    public String getTypeName ()
    {
        return typeName;
    }
    
    public void setTypeName (String typeName)
    {
        this.typeName = typeName;
    }
    
    public String getName ()
    {
        return name;
    }
    
    public void setName (String name)
    {
        this.name = name;
    }
    
    public String getUrl ()
    {
        return url;
    }
    
    public void setUrl (String url)
    {
        this.url = url;
    }
    
    public String getOrgNo ()
    {
        return orgNo;
    }
    
    public void setOrgNo (String orgNo)
    {
        this.orgNo = orgNo;
    }
}

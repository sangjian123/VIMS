package com.vms.model;

import java.io.Serializable;

/**
 * 用户密码
 * @author lvzhengtao
 *
 */
public class UserPassword implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1253378012566130499L;
    
    private String id;
    
    private String oldPwd;
    
    private String newPwd;
    
    public String getId ()
    {
        return id;
    }
    
    public void setId (String id)
    {
        this.id = id;
    }
    
    public String getOldPwd ()
    {
        return oldPwd;
    }
    
    public void setOldPwd (String oldPwd)
    {
        this.oldPwd = oldPwd;
    }
    
    public String getNewPwd ()
    {
        return newPwd;
    }
    
    public void setNewPwd (String newPwd)
    {
        this.newPwd = newPwd;
    }
    
}

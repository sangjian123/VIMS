package com.vms.model;

import java.io.Serializable;

public class DeptAdmin implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4692702430901863061L;
    
    /**
     * 
     */
    private String id;
    
    /**
     * 部门编号
     */
    private String deptNo;
    
    /**
     * 用户编号
     */
    private String userId;
    
    /**
     * @return the id
     */
    public String getId ()
    {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId (String id)
    {
        this.id = id;
    }
    
    /**
     * @return the deptNo
     */
    public String getDeptNo ()
    {
        return deptNo;
    }
    
    /**
     * @param deptNo the deptNo to set
     */
    public void setDeptNo (String deptNo)
    {
        this.deptNo = deptNo;
    }
    
    /**
     * @return the userId
     */
    public String getUserId ()
    {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId (String userId)
    {
        this.userId = userId;
    }
}

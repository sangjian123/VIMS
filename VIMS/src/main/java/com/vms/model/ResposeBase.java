package com.vms.model;

import java.io.Serializable;

/**
 * Respose基类
 * 状态码 {@link com.icss.ebu.ami.commons.constants.ConstantCode}
 * @author wanlilong
 * @date 2018.02.05
 * @version v1.0
 */
public class ResposeBase implements Serializable
{
    /**
     * 序列化一致性验证
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 返回状态码  1->成功  0失败
     */
    private Integer statusCode;
    
    /**
     * 返回状态描述
     */
    private String statusMsg;
    
    public Integer getStatusCode()
    {
        return statusCode;
    }
    
    public void setStatusCode(Integer statusCode)
    {
        this.statusCode = statusCode;
    }
    
    public String getStatusMsg()
    {
        
        return statusMsg;
    }
    
    public void setStatusMsg(String statusMsg)
    {
        this.statusMsg = statusMsg;
    }
}

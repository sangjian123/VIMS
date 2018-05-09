package com.vms.model;

import java.io.Serializable;

import com.vms.utils.KfaTopic;

public class QueueBase implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 主题
     */
    private KfaTopic kfaTopic;
    
    /**
     * 消息消费失败重试次数
     */
    private int tryTimes = 5;
    
    /**
     * 已重试失败次数
     */
    private int failTimes;
    
    public KfaTopic getKfaTopic()
    {
        return kfaTopic;
    }
    
    public void setKfaTopic(KfaTopic kfaTopic)
    {
        this.kfaTopic = kfaTopic;
    }
    
    public int getTryTimes()
    {
        return tryTimes;
    }
    
    public void setTryTimes(int tryTimes)
    {
        this.tryTimes = tryTimes;
    }
    
    public int getFailTimes()
    {
        return failTimes;
    }
    
    public void setFailTimes(int failTimes)
    {
        this.failTimes = failTimes;
    }
    
}

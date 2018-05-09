package com.vms.model;

public class UserPwdInput
{
    private int errorTimes;
    
    private long lastTime;
    
    public int getErrorTimes ()
    {
        return errorTimes;
    }
    
    public void setErrorTimes (int errorTimes)
    {
        this.errorTimes = errorTimes;
    }
    
    public long getLastTime ()
    {
        return lastTime;
    }
    
    public void setLastTime (long lastTime)
    {
        this.lastTime = lastTime;
    }
    
}

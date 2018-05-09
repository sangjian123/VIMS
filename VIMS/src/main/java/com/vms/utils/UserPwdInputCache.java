package com.vms.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.vms.model.UserPwdInput;

/**
 * @date 2017.3.23
 * @version v1.0
 */
public final class UserPwdInputCache
{
    
    // 主机状态集合
    private static ConcurrentHashMap<String, UserPwdInput> cache = new ConcurrentHashMap<String, UserPwdInput>();
    
    private UserPwdInputCache()
    {
        
    }
    
    /**
     * 获取数据
     */
    public static UserPwdInput getCache(String loginName)
    {
        return cache.get(loginName);
    }
    
    /**
     * 获取数据
     */
    public static ConcurrentHashMap<String, UserPwdInput> getCache()
    {
        return cache;
    }
    
    /**
     * 设置数据
     */
    public static void setCache(String loginName, UserPwdInput value)
    {
        cache.put(loginName, value);
    }
    
    /**
     * 删除数据
     */
    public static UserPwdInput removeCache(String loginName)
    {
        return cache.remove(loginName);
    }
    
    /**
     * 清空
     */
    public static void clear()
    {
        cache.clear();
    }
}
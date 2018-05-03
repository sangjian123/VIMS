package com.vms.utils;

import java.util.HashMap;
import java.util.Map;

public final class ConfigHolder {
    
    // 配置文件
    private static Map<String, String> cfg = new HashMap<String, String>(0);
    
    private ConfigHolder()
    {
        
    }
    
    /**
     * 获取配置项
     */
    public static String getCfg(String key)
    {
        return cfg.get(key);
    }
    
    /**
     * 设置配置项
     */
    public static void setCfg(String key, String value)
    {
        cfg.put(key, value);
    }
    
}

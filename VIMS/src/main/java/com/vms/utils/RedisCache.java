package com.vms.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis cache注解
 * @author wanlilong
 */

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
public @interface RedisCache
{
    String key();
    
    // 缓存时间(秒)
    int expire() default 300;
    
    String fieldKey();
    
    String nameSpace() default "";
}

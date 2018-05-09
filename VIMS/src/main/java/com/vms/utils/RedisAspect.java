package com.vms.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * redis 注解aop实现类
 * @author wanlilong
 */

@Component
public class RedisAspect
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAspect.class);
    
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    
    /**
     * redis注解实现方法
     */
    public Object cache(ProceedingJoinPoint pjp)
    {
        Object result = null;
        Method method = getMethod(pjp);
        RedisCache rediscache = method.getAnnotation(RedisCache.class);
        
        if(rediscache == null)
        {
            try
            {
                return pjp.proceed();
            }
            catch (Throwable e)
            {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        
        String key = parseKey(rediscache.nameSpace(), rediscache.fieldKey(), rediscache.key(), method, pjp.getArgs());
        
        try
        {
            result = redisCacheUtil.getDataFromRedis(key);
        }
        catch (Exception e)
        {
            result = null;
        }
        
        if(result != null)
        {
            LOGGER.info("get from redis, method=" + method.getName());
            return result;
        }
        
        if(LOGGER.isInfoEnabled())
        {
            LOGGER.info("get from db, method=" + method.getName());
        }
        
        try
        {
            result = pjp.proceed();
        }
        catch (Throwable e)
        {
            throw new ServiceException(e.getMessage(), e);
        }
        if(result != null)
        {
            int cacheTime = rediscache.expire();
            setRedisCache(key, result, cacheTime);
        }
        return result;
    }
    
    /**
     * 保存数据到redis
     */
    private void setRedisCache(String key, Object result, int cacheTime)
    {
        redisCacheUtil.saveDataInRedis(key, result, cacheTime);
        if(LOGGER.isInfoEnabled())
        {
            LOGGER.info("set redis,key=" + key);
        }
    }
    
    /**
     * 根据 SPEL表达式 获取缓存 key
     * 
     * @param pjp
     * @return
     */
    private String parseKey(String nameSpace, String fieldKey, String key, Method method, Object[] args)
    {
        StringBuilder sb = new StringBuilder();
        int index = key.indexOf('#');
        if(index <= -1)
        {
            sb.append(nameSpace).append("_").append(fieldKey).append("_").append(key);
            return sb.toString();
        }
        
        Annotation[][] annotations = method.getParameterAnnotations();
        // SPEL key
        ExpressionParser parser = new SpelExpressionParser();
        // SPEL context
        StandardEvaluationContext context = new StandardEvaluationContext();
        
        Param p = null;
        for(int i = 0; i < annotations.length; i++)
        {
            p = getParam(annotations[i]);
            if(p != null)
            {
                context.setVariable(p.value(), args[i]);
            }
        }
        String parseKey = parser.parseExpression(key).getValue(context, String.class);
        parseKey = parseKey.replaceAll(" ", "");
        
        sb.append(nameSpace).append("_").append(fieldKey).append("_").append(parseKey);
        return sb.toString();
    }
    
    private Param getParam(Annotation[] annos)
    {
        for(Annotation a : annos)
        {
            if(a instanceof Param)
            {
                return (Param) a;
            }
        }
        return null;
    }
    
    /**
     * 获取注解的内容
     * 
     * @param pjp
     * @return
     */
    public Method getMethod(JoinPoint pjp)
    {
        
        Method method = null;
        try
        {
            Object target = pjp.getTarget();
            String methodName = pjp.getSignature().getName();
            
            Class<?>[] classz = target.getClass().getInterfaces();
            
            Class<?> cls = classz.length > 0 ? classz[0] : target.getClass();
            
            Class<?>[] parameterTypes = ((MethodSignature) pjp.getSignature()).getMethod().getParameterTypes();
            method = cls.getMethod(methodName, parameterTypes);
        }
        catch (Exception e)
        {
            LOGGER.error("NoSuchMethodException", e);
        }
        
        return method;
    }
    
}

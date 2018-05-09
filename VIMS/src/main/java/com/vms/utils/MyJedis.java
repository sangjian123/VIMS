package com.vms.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 创建Jedis连接池
 * @author wanlilong
 *
 */
@Component
public class MyJedis
{
    
    private static JedisSentinelPool jedisSentinelPool = null;
    
    /**
     * 初始化Jedis
     * @param ip
     * @param port
     */
    public static void init(String[] ipAndPortArr, String requirepass)
    {
        if(jedisSentinelPool == null)
        {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
            config.setMaxTotal(-1);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
            config.setMaxIdle(10);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
            config.setMaxWaitMillis(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
            config.setTestOnBorrow(true);
            
            Set<String> sentinels = new HashSet<String>();
            for(String ipAndPort : ipAndPortArr)
            {
                sentinels.add(ipAndPort);
            }
            
            jedisSentinelPool = new JedisSentinelPool("epmsMaster", sentinels, config, requirepass);
        }
        
    }
    
    /**
     * 获取Jedis连接池
     * @return
     */
    public static Jedis getJedisResource()
    {
        return jedisSentinelPool.getResource();
    }
    
    /** 
     * 返还到连接池 
     */
    public static void returnResource(Jedis jedis)
    {
        if(jedis != null)
        {
            jedis.close();
        }
    }
    
}

package com.vms.utils;

import redis.clients.jedis.Jedis;

/** 
* @author  wangyulong 
* @date    2017年8月24日 上午9:09:06 
* @version 1.0  
*/
public class RedisDisLock
{
    /**锁100秒后自动删除,释放锁*/
    private static final int expired = 100;
    
    /**默认超时时间（毫秒），本地超时,10秒内完成操作，否则锁重置*/
    public static final long DEFAULT_TIME_OUT = 10 * 1000L;
    
    /**
     * 加锁
     * @param jedis
     * @param lockKey
     * @return
     */
    public static boolean tryLock(Jedis jedis, String lockKey)
    {
        return tryLock(jedis, lockKey, DEFAULT_TIME_OUT);
    }
    
    /**
     * 加锁
     * @param jedis
     * @param lockKey
     * @param timeOut
     * @return
     */
    public static boolean tryLock(Jedis jedis, String lockKey, long timeOut)
    {
        // 1. 通过SETNX试图获取一个lock
        boolean success = false;
        long value = System.currentTimeMillis() + timeOut + 1;
        long acquired = jedis.setnx(lockKey, String.valueOf(value));
        //SETNX成功，则成功获取一个锁
        if(acquired == 1)
        {
            jedis.expire(lockKey, expired);//设置100秒超时自动删除
            success = true;
        }
        //SETNX失败，说明锁被其他客户端保持，检查其是否已经超时
        else
        {
            String oldValue = jedis.get(lockKey);
            if(oldValue != null && isTimeExpired(oldValue))
            {//超时
                 //获取上一个锁到期时间，并设置现在的锁到期时间，
             //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                String getValue = jedis.getSet(lockKey, String.valueOf(value));
                if(getValue != null)
                {
                    if(getValue.equals(oldValue))
                    {
                        success = true;
                    }
                    else
                        success = false;// 已被其他进程捷足先登了
                }
            }
            else //未超时，则直接返回失败
                success = false;
        }
        return success;
    }
    
    /**
     * 解锁
     * @param jedis
     * @param lockKey
     */
    public static void release(Jedis jedis, String lockKey)
    {
        long current = System.currentTimeMillis();
        // 避免删除非自己获取得到的锁
        String value = jedis.get(lockKey);
        if(value != null && current < Long.valueOf(value))
            jedis.del(lockKey);
    }
    
    //上个锁超时判断
    private static boolean isTimeExpired(String value)
    {
        //和当前时间比较，小于当前时间则算超时及过期了
        return Long.parseLong(value) < System.currentTimeMillis();
    }
}

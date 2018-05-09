package com.vms.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.alibaba.druid.util.StringUtils;

/**
 * redis数据操作工具类
 * @author wanlilong
 */
@Component
public class RedisCacheUtil
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheUtil.class);
    
    private static String CHARSET_UTF = "UTF-8";
    
    /**
     * 将数据保存到redis中
     * @param key 数据主键
     * @param object 数据
     * @param cacheTime 缓存时间  （秒）
     */
    public boolean saveDataInRedis(String key, Object object, int cacheTime)
    {
        if(StringUtils.isEmpty(key) || object == null)
        {
            LOGGER.error("redis key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        deleteKey(key);
        try
        {
            byte[] value = SerializationUtil.serialize(object);
            if(value == null)
            {
                return false;
            }
            
            byte[] byteKey = key.getBytes(CHARSET_UTF);
            // 保存至redis
            String status = jedis.set(byteKey, value);
            // 设置缓存时间
            if(cacheTime != -1)
            {
                jedis.expire(byteKey, cacheTime);
            }
            
            if("OK".equals(status))
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量数据保存
     * @param DataMap
     * @param cacheTime
     * @return
     */
    public <T> boolean saveDataInRedis(Map<String, T> dataMap, int cacheTime)
    {
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        try
        {
            for(Map.Entry<String, T> entry : dataMap.entrySet())
            {
                String key = entry.getKey().toString();
                Object object = entry.getValue();
                if(StringUtils.isEmpty(key) || object == null)
                {
                    continue;
                }
                
                byte[] value = SerializationUtil.serialize(object);
                if(value == null)
                {
                    continue;
                }
                
                byte[] byteKey = key.getBytes(CHARSET_UTF);
                // 保存至redis
                jedis.set(byteKey, value);
                // 设置缓存时间
                if(cacheTime != -1)
                {
                    jedis.expire(byteKey, cacheTime);
                }
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 设置key的有效期
     * @param key
     * @param cacheTime
     * @return
     */
    public boolean setKeyexpire(String key, int cacheTime)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        try
        {
            byte[] byteKey = key.getBytes(CHARSET_UTF);
            // 设置缓存时间
            if(cacheTime != -1)
            {
                jedis.expire(byteKey, cacheTime);
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 从redis获取数据
     * @param key 数据主键
     * @return Object 数据
     */
    public Object getDataFromRedis(String key)
    {
        return getDataFromRedis(key, Object.class);
    }
    
    /**
     * 获取redis数据
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> T getDataFromRedis(String key, Class<T> clazz)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return null;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return null;
        }
        
        try
        {
            byte[] byteKey = key.getBytes(CHARSET_UTF);
            if(jedis.exists(byteKey))
            {
                byte[] bs = jedis.get(byteKey);
                if(bs == null)
                {
                    return null;
                }
                Object obj = SerializationUtil.deserialize(bs);
                return obj == null ? null : (T) obj;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            LOGGER.error("jedis get Data From Redis Fail.", e);
            return null;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量获取数据
     * @param keyList
     * @param clazz
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> Set<T> getDataFromRedis(Set<String> keyList, Class<T> clazz)
    {
        HashSet<T> hashSet = new HashSet<T>();
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return hashSet;
        }
        
        try
        {
            for(String key : keyList)
            {
                if(StringUtils.isEmpty(key))
                {
                    continue;
                }
                
                byte[] byteKey = key.getBytes(CHARSET_UTF);
                if(jedis.exists(byteKey))
                {
                    byte[] bs = jedis.get(byteKey);
                    if(bs == null)
                    {
                        continue;
                    }
                    Object obj = SerializationUtil.deserialize(bs);
                    if(obj != null)
                    {
                        hashSet.add((T) obj);
                    }
                }
            }
            return hashSet;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis get Data From Redis Fail.", e);
            return hashSet;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量获取数据
     * @param keyList
     * @param clazz
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> Map<String, T> getDataMapFromRedis(Set<String> keyList, Class<T> clazz)
    {
        Map<String, T> hashMap = new HashMap<String, T>();
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return hashMap;
        }
        
        try
        {
            for(String key : keyList)
            {
                if(StringUtils.isEmpty(key))
                {
                    continue;
                }
                
                byte[] byteKey = key.getBytes(CHARSET_UTF);
                if(jedis.exists(byteKey))
                {
                    byte[] bs = jedis.get(byteKey);
                    if(bs == null)
                    {
                        continue;
                    }
                    Object obj = SerializationUtil.deserialize(bs);
                    if(obj != null)
                    {
                        hashMap.put(key, (T) obj);
                    }
                }
            }
            return hashMap;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis get Data From Redis Fail.", e);
            return hashMap;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 检测主键key是否存在 若存在删除
     * @param key 数据主键
     */
    public boolean deleteKey(String key)
    {
        if(key == null)
        {
            LOGGER.error("redis key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        try
        {
            byte[] byteKey = key.getBytes(CHARSET_UTF);
            if(jedis.exists(byteKey))
            {
                jedis.del(byteKey);
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量删除以pre_key开头的key
     * @param pre_key
     * @return
     * 
     */
    public boolean batchDeleteKey(String pre_key)
    {
        if(pre_key == null)
        {
            LOGGER.error("param pre_key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        try
        {
            Set<byte[]> set = getBatchKey(pre_key, jedis);
            for(byte[] key : set)
            {
                jedis.del(key);
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量查询以pre_key开头的key对应的值
     * @param <T>
     * @param pre_key
     * @return
     * <T> T getDataFromRedis(String key, Class<T> clazz)
     */
    @SuppressWarnings ("unchecked")
    public <T> Set<T> getBatchKey(String pre_key, Class<T> clazz)
    {
        HashSet<T> hashSet = new HashSet<T>();
        if(pre_key == null)
        {
            LOGGER.error("param pre_key is Empty.");
            return hashSet;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return hashSet;
        }
        try
        {
            
            Set<byte[]> set = getBatchKey(pre_key, jedis);
            for(byte[] key : set)
            {
                byte[] bs = jedis.get(key);
                if(bs == null)
                {
                    continue;
                }
                Object obj = SerializationUtil.deserialize(bs);
                if(obj != null)
                {
                    hashSet.add((T) obj);
                }
            }
            return hashSet;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return hashSet;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量查询以pre_key开头的key
     * @param pre_key
     * @return
     */
    private Set<byte[]> getBatchKey(String pre_key, Jedis jedis)
    {
        try
        {
            String keyStr = pre_key + "*";
            byte[] byteKey = keyStr.getBytes(CHARSET_UTF);
            Set<byte[]> set = jedis.keys(byteKey);
            return set;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return new HashSet<byte[]>();
        }
    }
    
    /**
     * 获取所有pre——key为前缀的key值
     * @param pre_key
     * @param clazz
     * @return
     */
    public Set<byte[]> getBatchKeys(String pre_key)
    {
        Set<byte[]> hashSet = new HashSet<byte[]>();
        if(pre_key == null)
        {
            LOGGER.error("param pre_key is Empty.");
            return hashSet;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return hashSet;
        }
        try
        {
            
            hashSet = getBatchKey(pre_key, jedis);
            return hashSet;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return hashSet;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 获取缓存队列数据总个数
     * @param key
     * @param object
     * @return
     */
    public long getCacheCount(String key)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return 0L;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return 0L;
        }
        
        try
        {
            return jedis.llen(key);
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return 0L;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 数据右侧压入链表
     * @param key
     * @param object
     * @return
     */
    public boolean rightPushCache(String key, int cacheTime, String... strValues)
    {
        if(StringUtils.isEmpty(key) || strValues == null)
        {
            LOGGER.error("redis key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        try
        {
            jedis.rpush(key, strValues);
            if(cacheTime != -1)
            {
                jedis.expire(key, cacheTime);
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 移除列表中与参数 value 相等的所有元素
     * @return
     */
    public boolean removeValue(String key, String value)
    {
        if(StringUtils.isEmpty(key) || value == null)
        {
            LOGGER.error("redis key is Empty.");
            return false;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return false;
        }
        
        try
        {
            jedis.lrem(key, 0, value);
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return false;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 分页取出数据
     * @param <T>
     * @param key
     * @param object
     * @return
     */
    public List<String> getCacheDataByPage(String key, int begin, int end)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return null;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return null;
        }
        
        try
        {
            return jedis.lrange(key, begin, end);
        }
        catch (Exception e)
        {
            LOGGER.error("jedis lrange In Redis Fail.", e);
            return null;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 移出并获取列表的第一个元素
     * @param <T>
     * @param key
     * @param object
     * @return
     */
    public String leftPopAndGet(String key)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return null;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return null;
        }
        
        try
        {
            return jedis.lpop(key);
        }
        catch (Exception e)
        {
            LOGGER.error("jedis lpop fail.", e);
            return null;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 移除并获取列表最后一个元素
     * @param <T>
     * @param key
     * @param object
     * @return
     */
    public String rightPopAndGet(String key)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return null;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return null;
        }
        
        try
        {
            return jedis.rpop(key);
        }
        catch (Exception e)
        {
            LOGGER.error("jedis lpop fail.", e);
            return null;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * Redis来生成唯一ID
     * @param key
     * @return 返回分配的ID
     */
    public Long incrementAndGet(String key)
    {
        return incrementAndGet(key, -1);
    }
    
    /**
     * Redis来生成唯一ID
     * @param key
     * @return 返回分配的ID
     */
    public Long incrementAndGet(String key, int cacheTime)
    {
        if(StringUtils.isEmpty(key))
        {
            LOGGER.error("redis key is Empty.");
            return null;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return null;
        }
        try
        {
            byte[] byteKey = key.getBytes(CHARSET_UTF);
            Long num = jedis.incr(byteKey);
            if(cacheTime != -1)
            {
                jedis.expire(byteKey, cacheTime);
            }
            return num;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis save Data In Redis Fail.", e);
            return null;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
    /**
     * 批量查询以suffex_key开头的key
     * @param suffex_key
     * @return
     */
    private Set<byte[]> getBatchSuffexKey(String suffex_key, Jedis jedis)
    {
        try
        {
            String keyStr = "*" + suffex_key;
            byte[] byteKey = keyStr.getBytes(CHARSET_UTF);
            Set<byte[]> set = jedis.keys(byteKey);
            return set;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return new HashSet<byte[]>();
        }
    }
    
    /**
     * 获取所有pre——key为前缀的key值
     * @param pre_key
     * @param clazz
     * @return
     */
    public Set<byte[]> getBatchSuffexKeys(String suffex)
    {
        Set<byte[]> hashSet = new HashSet<byte[]>();
        if(suffex == null)
        {
            LOGGER.error("param suffex is Empty.");
            return hashSet;
        }
        
        Jedis jedis = MyJedis.getJedisResource();
        if(jedis == null)
        {
            LOGGER.error("get Jedis from JedisPool Fail. jedis is null");
            return hashSet;
        }
        try
        {
            
            hashSet = getBatchSuffexKey(suffex, jedis);
            return hashSet;
        }
        catch (Exception e)
        {
            LOGGER.error("jedis delete Key Fail.", e);
            return hashSet;
        }
        finally
        {
            MyJedis.returnResource(jedis);
        }
    }
    
}

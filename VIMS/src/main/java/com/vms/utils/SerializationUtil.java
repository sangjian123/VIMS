package com.vms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * reids 序列化 与 反序列化
 * @author wanlilong
 */
public class SerializationUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationUtil.class);
    
    /**
     * 序列化
     * @param object
     * @return bytes
     */
    public static byte[] serialize(Object object)
    {
        if(object == null)
        {
            return null;
        }
        
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch (Exception e)
        {
            LOGGER.error("save data in redis serialize fail銆�", e);
        }
        finally
        {
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(baos);
        }
        return null;
    }
    
    /**
     * 反序列化
     * 
     * @param bytes
     * @return object
     */
    public static Object deserialize(byte[] bytes)
    {
        if(bytes == null)
        {
            return null;
        }
        
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e)
        {
            LOGGER.error("get data from redis deserialize fail銆�", e);
        }
        finally
        {
            IOUtils.closeQuietly(bais);
            IOUtils.closeQuietly(ois);
        }
        return null;
    }
}

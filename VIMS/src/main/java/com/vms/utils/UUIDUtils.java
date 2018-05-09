package com.vms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vms.constant.CommonConstants;

/**
 * UUID工具类
 * 
 * @ClassName: UUIDUtils
 * @Description:
 * @author 朱云凯
 * @date 2016年10月20日 下午6:53:08
 *
 */
public class UUIDUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDUtils.class);
    
    private static final String MIN = "000000";
    
    private static final int MAX = 999990;
    
    private static int DIVISOR = 60000;
    
    private static String PROJECT_INDEX = "PROJECT_INDEX";
    
    /** This Format for format the number to special format. */
    private static final NumberFormat numberFormat = new DecimalFormat(MIN);
    
    /** This int is the sequence number ,the default value is 0. */
    private static AtomicInteger seq = new AtomicInteger(1);
    
    private static final int RANDOM_PRE = new SecureRandom().nextInt(900) + 100;
    
    private static String INDEX = "";
    
    private static final Lock LOCK = new ReentrantLock();
    
    private static long lastTime = System.currentTimeMillis();
    
    private static final long BATCH = 10;
    
    /**
     * 获取UUID @Title: generateUUID @Description: @param @return @return
     * Long @throws
     */
    public static Long generateUUID()
    {
        
        return generate16Long();
    }
    
    public static synchronized Long generate16Long()
    {
        
        LOCK.lock();
        long rev = 0;
        if(INDEX.length() == 0)
        {
            initIndex();
        }
        int curSeq = seq.get();
        StringBuffer sb = new StringBuffer();
        if(curSeq == MAX || curSeq % BATCH == 0)
        {
            boolean done = false;
            while(!done)
            {
                long now = System.currentTimeMillis();
                if(now == lastTime)
                {
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException e)
                    {
                        LOGGER.error("generate16Long interruptedException:", e);
                    }
                    continue;
                }
                else
                {
                    
                    if(curSeq == MAX)
                    {
                        seq.set(0);
                    }
                    lastTime = now;
                    done = true;
                }
            }
        }
        sb.append(lastTime / DIVISOR);
        sb.append(INDEX);
        sb.append(numberFormat.format(seq.getAndIncrement()));
        rev = Long.valueOf(sb.toString());
        LOCK.unlock();
        return rev;
    }
    
    private static void initIndex()
    {
        Properties p = new Properties();
        FileInputStream inputStream = null;
        try
        {
            String filePath = Thread.currentThread().getContextClassLoader().getResource(File.separator).getPath();
            inputStream = new FileInputStream(new File(filePath + "/configuration.properties"));
            p.load(inputStream);
            for(Entry<Object, Object> entry : p.entrySet())
            {
                
                if(PROJECT_INDEX.equals(entry.getKey()))
                {
                    INDEX =
                        entry.getValue() == null ? String.valueOf(new SecureRandom().nextInt(9)) : String.valueOf(entry
                            .getValue());
                }
            }
        }
        catch (Exception e)
        {
            INDEX = String.valueOf(new SecureRandom().nextInt(9));
        }
        finally
        {
            p.clear();
            IOUtils.closeQuietly(inputStream);
        }
        INDEX += String.valueOf(new SecureRandom().nextInt(9));
    }
    
    public synchronized static String generate16Str()
    {
        LOCK.lock();
        String rev;
        if(INDEX.length() == 0)
        {
            initIndex();
        }
        int curSeq = seq.get();
        StringBuffer sb = new StringBuffer();
        if(curSeq == MAX || curSeq % BATCH == 0)
        {
            boolean done = false;
            while(!done)
            {
                long now = System.currentTimeMillis();
                if(now == lastTime)
                {
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException e)
                    {
                        LOGGER.error("generate16Long interruptedException:", e);
                    }
                    continue;
                }
                else
                {
                    
                    if(curSeq == MAX)
                    {
                        seq.set(0);
                    }
                    lastTime = now;
                    done = true;
                }
            }
        }
        sb.append(lastTime / DIVISOR);
        sb.append(INDEX);
        sb.append(numberFormat.format(seq.getAndIncrement()));
        rev = sb.toString();
        LOCK.unlock();
        return rev;
    }
    
    public static Long generate19Long()
    {
        return Long.parseLong(generate19Str());
    }
    
    public static String generate19Str()
    {
        
        return generate16Str() + RANDOM_PRE;
    }
    
    public static Long generateDefault16(long defTime)
    {
        return Long.parseLong(generateDefault16Str(defTime));
    }
    
    public static String generateDefault16Str(long defTime)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(defTime / DIVISOR);
        sb.append(MIN);
        sb.append(CommonConstants.STRING_00);
        return sb.toString();
    }
    
}

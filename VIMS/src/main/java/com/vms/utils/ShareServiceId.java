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

import com.vms.constant.GeneralConstant;

/**
 * 解决ShareService私用ID
 * 
 * @author tfl
 *
 */
public class ShareServiceId
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShareServiceId.class);
    
    private static final String MIN = "00000";
    
    private static final int MAX = 99992;
    
    private static int DIVISOR = 60000;
    
    private static long MINUS_NUM = 21024000;
    
    /** This Format for format the number to special format. */
    private static final NumberFormat numberFormat = new DecimalFormat(MIN);
    
    /** This int is the sequence number ,the default value is 0. */
    private static AtomicInteger seq = new AtomicInteger(1);
    
    private static String INDEX = "";
    
    private static final Lock LOCK = new ReentrantLock();
    
    private static long lastTime = System.currentTimeMillis();
    
    private static final long BATCH = 10;
    
    public static synchronized Long generateUUID()
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
                        LOGGER.error("generateUUID interruptedException:", e);
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
        sb.append(lastTime / DIVISOR - MINUS_NUM);
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
                
                if(GeneralConstant.PROJECT_INDEX.equals(entry.getKey()))
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
    
}

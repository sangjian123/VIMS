package com.vms.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vms.utils.AesKeyUtils;
import com.vms.utils.ConfigHolder;
import com.vms.utils.MyJedis;

public class SystemInit extends ContextLoaderListener
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemInit.class);
    
    private static Logger sysLogger = LoggerFactory.getLogger("sysLoger");
    
    /**
     * 空字符串
     */
    private static String BLANK = "";
    
    /**
     * WebApplicationContext对象
     */
    private static WebApplicationContext ctx = null;
    
    private static void setCtx(WebApplicationContext context)
    {
        ctx = context;
    }
    
    public void contextInitialized(ServletContextEvent contextEvent)
    {
        LOGGER.info("System initializing...");
        sysLogger.info("System initializing...");
        try
        {
            super.contextInitialized(contextEvent);
            setCtx(WebApplicationContextUtils.getRequiredWebApplicationContext(contextEvent.getServletContext()));
            
            LOGGER.info("Load spring configurations successful.");
            sysLogger.info("Load spring configurations successful.");
            // 初始化缓存
            LOGGER.info("Start to load init data...");
            sysLogger.info("Start to load init data...");
            // 初始化
            init();
        }
        catch (Exception e)
        {
            LOGGER.error("Initialized error", e);
        }
    }
    
    /**
     * 根据对象名获取对象
     *
     * @param name
     *            对象名
     * @return 返回对象信息
     * @author xugangfeng
     * @see [类、类#方法、类#成员]
     */
    public static Object getBean(String name)
    {
        return ctx.getBean(name);
    }
    
    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent contextEvent)
    {
        sysLogger.info("System destroyed...");
        destroy();
        super.contextDestroyed(contextEvent);
    }
    
    /**
     * 获得当前系统的绝对路径
     *
     * @return 当前系统的绝对路径
     * @see [类、类#方法、类#成员]
     */
    public static String getServletContextRealPath()
    {
        return getWebApplicationContext().getServletContext().getRealPath(BLANK);
    }
    
    /**
     * 返回WebApplicationContext对象
     *
     * @return WebApplicationContext - WebApplicationContext
     * @see [类、类#方法、类#成员]
     */
    public static WebApplicationContext getWebApplicationContext()
    {
        return ctx;
    }
    
    /**
     * 初始化
     */
    private void init()
    {
        // 初始化系统配置项
        try
        {
            getConfiguration("dubbo-config.properties");
            getConfiguration("configuration.properties");
            getConfiguration("redisConfig.properties");
        }
        catch (Exception e)
        {
            LOGGER.error("Initialized configuration。properties Fail.", e);
        }
        
        try
        {
            String ipAndPorts = ConfigHolder.getCfg("redisCluster");
            String[] ipAndPortArr = ipAndPorts.split(",");
            String requirepass = AesKeyUtils.decrypt(ConfigHolder.getCfg("REDIS_PASSWORD"), ConfigHolder.getCfg("REDIS_PWD_KEY"));
            MyJedis.init(ipAndPortArr, requirepass);
        }
        catch (Exception e)
        {
            //redis 异常
            LOGGER.error("Redis Error!!!", e);
            if(e.getMessage().contains("All"))
            {
                //所有redis都配置错误或者redis连接不上
                LOGGER.error("All Redis Error!!!", e);
                //发送告警短信
            }
        }
        
    }
    
    /**
     * 读取系统配置文件 并缓存到系统内存中去
     * @throws IOException 
     */
    private void getConfiguration(String fileName) throws IOException
    {
        InputStream inputStream = null;
        try
        {
            Properties p = new Properties();
            inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            p.load(inputStream);
            for(Entry<Object, Object> entry : p.entrySet())
            {
                ConfigHolder.setCfg((String) entry.getKey(), (String) entry.getValue());
            }
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
    }
    
    /**
     * 定时器销毁
     */
    private void destroy()
    {
        
    }
    
    /**
     * 当前0点时间戳
     * 
     * @return
     */
    public long getDayBegin()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 3);
        cal.set(Calendar.MILLISECOND, 1);
        return cal.getTimeInMillis();
    }
}

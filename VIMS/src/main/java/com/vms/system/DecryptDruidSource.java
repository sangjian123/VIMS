package com.vms.system;

import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;


@SuppressWarnings ("serial")
public class DecryptDruidSource extends DruidDataSource
{
    private static final Logger LOGGER = LoggerFactory.getLogger (DecryptDruidSource.class);
    String PUBLIC_KEY =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALQ8yl+oxXGPJgHOopPWatnTwVvc2zwWiVsIBbqLhdqMVioeQu2hAFooLVNUhDJmc/DAGzG5lRWQtJYN5NLVOQECAwEAAQ==";
    @Override
    public void setUsername (String username)
    {
        String name = username;
        try
        {
            name = ConfigTools.decrypt (PUBLIC_KEY, name);
        }
        catch (Exception e)
        {
            LOGGER.info ("DecryptDruidSource username decrypt error : ", e);
        }
        super.setUsername (name);
    }
    
    @Override
    public void setConnectProperties (Properties properties)
    {
        Properties newProp = new Properties ();
        newProp.setProperty ("config.decrypt", "true");
        newProp.setProperty ("config.decrypt.key", PUBLIC_KEY);
        super.setConnectProperties (newProp);
    }
    
    @Override
    public void setConnectionProperties (String connectionProperties)
    {
        String connProp = connectionProperties;
        if (StringUtils.isBlank (connProp))
        {
            connProp = "config.decrypt=true;config.decrypt.key=" + PUBLIC_KEY;
        }
        else
        {
            connProp += ";config.decrypt.key=";
            connProp += PUBLIC_KEY;
        }
        super.setConnectionProperties (connProp);
    }
}

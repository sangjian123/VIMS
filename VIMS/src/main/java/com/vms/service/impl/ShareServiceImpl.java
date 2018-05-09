package com.vms.service.impl;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.vms.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService
{
    
    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
    
    /** This Format for format the data to special format. */
    private static final Format dateFormat = new SimpleDateFormat("HHmmss");
    
    /** This Format for format the number to special format. */
    private static final NumberFormat numberFormat = new DecimalFormat("00");
    
    /** This int is the sequence number ,the default value is 0. */
    private int seq = 0;
    
    private static final int MAX = 99;
    
    @Override
    public Long generateUUID()
    {
        Calendar rightNow = Calendar.getInstance();
        
        StringBuffer sb = new StringBuffer();
        
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        
        numberFormat.format(seq, sb, HELPER_POSITION);
        
        if(seq == MAX)
        {
            seq = 0;
        }
        else
        {
            seq++;
        }
        return Long.parseLong(sb.toString());
    }
    
    //	@Override
    //	public void test() {
    //		String basename = "com.icss.ebu.ami.portal.system.properties.message";
    //		Locale cn = Locale.CHINA;// 中文
    //		Locale us = Locale.US;// 英文
    //		// 根据基名和语言环境加载对应的语言资源文件
    //		ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename, cn);// 加载myproperties_zh.properties
    //		ResourceBundle myResourcesUS = ResourceBundle.getBundle(basename, us);// 加载myproperties_en.properties
    //
    //		// 加载资源文件后， 程序就可以调用ResourceBundle实例对象的 getString方法获取指定的资源信息名称所对应的值。
    //		// String value = myResources.getString(“key");
    //		String usernameCN = myResourcesCN.getString("username");
    //		String passwordCN = myResourcesCN.getString("password");
    //
    //		String usernameUS = myResourcesUS.getString("username");
    //		String passwordUS = myResourcesUS.getString("password");
    //		System.out.println(usernameCN + "--" + passwordCN);
    //		System.out.println(usernameUS + "--" + passwordUS);
    //	}
    
}

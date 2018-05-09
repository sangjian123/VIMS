/**
 * @Title: ResourceLoader.java
 * @Package com.icss.ebu.ami.portal.system.commons.utils
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Comsys-lvzhengtao
 * @date 2016年12月5日 上午10:12:14
 * @version V1.0
 */

package com.vms.utils;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.vms.constant.GeneralConstant;

/**
 * @ClassName: PropertiesReader
 * @Description: 读取配置属性工具类
 * @author tfl
 * @date 2016年12月8日
 */

public final class PropertiesReader
{
    
    private static final String FONT_CODE = "PDF_EXPORT_FONT_CODE";
    
    private static final String FONT = "PDF_EXPORT_FONT";
    
    private static final String PWD_KEY = "USER_PWD_KEY";
    
    private static String userPwdKey = "";
    
    private static String pdfExportFont = null;
    
    private static String pdfExportFontCode = null;
    
    public static String getPdfExportFontCode() throws IOException
    {
        if(StringUtils.isBlank(pdfExportFontCode))
        {
            pdfExportFontCode = ConfigHolder.getCfg(FONT_CODE);
        }
        return pdfExportFontCode;
    }
    
    public static String getPdfExportFont() throws IOException
    {
        if(StringUtils.isBlank(pdfExportFont))
        {
            pdfExportFont = ConfigHolder.getCfg(FONT);
        }
        return pdfExportFont;
    }
    
    public static String getUserPwdKey() throws IOException
    {
        if(StringUtils.isBlank(userPwdKey))
        {
            String pwdKey = ConfigHolder.getCfg(PWD_KEY);
            if(StringUtils.isNotBlank(pwdKey))
            {
                userPwdKey =
                    new String(Base64.decodeBase64(pwdKey.getBytes(GeneralConstant.CHARACTER_CODING)),
                        GeneralConstant.CHARACTER_CODING);
            }
        }
        return userPwdKey;
    }
}

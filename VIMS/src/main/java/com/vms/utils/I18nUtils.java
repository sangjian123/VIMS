package com.vms.utils;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化工具类
 *
 * @author jihonghai
 * @date 2016年11月16日下午5:10:42
 */
public class I18nUtils
{
    
    /**
     * 获取key对应的国际化值
     *
     * @param code 国际化对应key值
     * @return 国际化值
     */
    public static String getI18nMsg(String code)
    {
        
        return I18nUtils.getI18nMsg(code, null);
    }
    
    /**
     * 获取key对应的国际化值
     *
     * @param code 国际化对应key值
     * @param args array of arguments that will be filled in for params within
     * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     * or {@code null} if none.
     * @return 国际化值
     */
    public static String getI18nMsg(String code, Object[] args)
    {
        
        return ContextHelper.getContext().getMessage(code, args, code, getCurrentLocale());
    }
    
    /**
     * 获取当前环境的语言
     *
     * @return Locale 当前环境的语言
     */
    public static Locale getCurrentLocale()
    {
        
        return LocaleContextHolder.getLocale();
    }
}

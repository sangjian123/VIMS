package com.vms.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.util.ObjectUtils;

import com.vms.constant.GeneralConstant;

/**
 * 请求或效应相关工具类
 *
 * @author tfl
 * @date 2016年12月14日 下午3:10:42
 */
public class ServiceUtils
{
    
    /**
     * 获取请求会话标识
     * 
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request)
    {
        if(request == null)
        {
            return null;
        }
        HttpSession s = request.getSession();
        if(s == null)
            return null;
        return s.getId();
    }
    
    /**
     * 获取模糊字符串
     * 
     * @param sourceStr
     * @return
     */
    public static String getLikeString(String sourceStr)
    {
        if(StringUtils.isNotBlank(sourceStr) && sourceStr.length() > 2)
        {
            StringBuilder sb = new StringBuilder();
            int a = sourceStr.length() / 4;
            if(a < 1)
            {
                a = 1;
            }
            sb.append(sourceStr.substring(0, a));
            sb.append(GeneralConstant.ASTERISK).append(GeneralConstant.ASTERISK);
            sb.append(GeneralConstant.ASTERISK);
            sb.append(sourceStr.substring(sourceStr.length() - a, sourceStr.length()));
            return sb.toString();
        }
        else
        {
            return GeneralConstant.ASTERISK;
        }
    }
    
    /**
     * 获取请求客户端IP
     * 
     * @param request
     * @return
     */
    public static String getLikeClientIp(HttpServletRequest request)
    {
        String temp = getClientIp(request);
        if(StringUtils.isNotBlank(temp))
        {
            try
            {
                temp = getLikeString(temp);
            }
            catch (NullPointerException e)
            {
                temp = GeneralConstant.ASTERISK;
            }
            return temp;
        }
        else
        {
            return GeneralConstant.ASTERISK;
        }
    }
    
    /**
     * 获取请求客户端IP
     * 
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request)
    {
        if(request == null)
        {
            return null;
        }
        return request.getServerName().toLowerCase().equals("localhost") ? GeneralConstant.LOCAL_HOST : getIpAddr(request);
    }
    
    /**
     * 获取客户端IP
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Real-IP");
        if(!StringUtils.isEmpty(ip))
        {
            return ip;
        }
        if(request.getHeader("X-Forwarded-For") != null)
        {
            for(String singleIP : request.getHeader("X-Forwarded-For").split(","))
            {
                if(singleIP != null && !singleIP.equals("unknown"))
                {
                    return singleIP.trim();
                }
            }
        }
        return request.getRemoteAddr();
    }
    
    /**
     * 按特定的字符编码获取固定长度的字符串
     * 
     * @param str
     * @param charset
     *            字符编码
     * @param len
     *            字符长度
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getSubString(String str, String charset, int len) throws UnsupportedEncodingException
    {
        if(StringUtils.isBlank(str) || getStringLength(str, charset) <= len)
        {
            return str;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int tempLen = 0;
        for(char c : chars)
        {
            tempLen += getStringLength(String.valueOf(c), charset);
            if(tempLen > len)
            {
                break;
            }
            index++;
        }
        return str.substring(0, index);
    }
    
    /**
     * 按特定的字符编码获取长度-中文默认长度是2
     * 
     * @param str
     * @return
     */
    public static int getStringLength(String s)
    {
        int length = 0;
        int len = s.length();
        for(int i = 0; i < len; i++)
        {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;
            
        }
        return length;
    }
    
    /**
     * 按特定的字符编码获取长度
     * 
     * @param str
     * @param charset
     *            字符编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static int getStringLength(String str, String charset) throws UnsupportedEncodingException
    {
        if(StringUtils.isBlank(str))
        {
            return 0;
        }
        return str.getBytes(charset).length;
    }
    
    /**
     * json字符串转换成Map对象
     * 
     * @param jsonStr
     * @return map
     * @throws JSONException
     */
    @SuppressWarnings ("unchecked")
    public static Map<String, String> jsonStrToMap(String jsonStr) throws JSONException
    {
        JSONObject jsonObj = new JSONObject(jsonStr);
        Iterator<String> nameItr = jsonObj.keys();
        String name;
        Map<String, String> outMap = new HashMap<String, String>();
        while(nameItr.hasNext())
        {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }
    
    /**
     * 比较值的更新
     * 
     * @param properties
     * @param oldValue
     * @param newValue
     * @param updateSb
     */
    public static void compareChange(String properties, Object oldValue, Object newValue, StringBuilder updateSb)
    {
        if(!ObjectUtils.isEmpty(newValue) && !ObjectUtils.isEmpty(oldValue) && !oldValue.equals(newValue))
        {
            updateSb.append(properties).append(": ").append(oldValue).append(" -> ").append(newValue == null ? "" : newValue)
                .append(" ,");
        }
        else if(ObjectUtils.isEmpty(oldValue) && !ObjectUtils.isEmpty(newValue))
        {
            updateSb.append(properties).append(": ").append(" -> ").append(newValue).append(" ,");
        }
    }
    
    /**
     * 追加字段内容
     * 
     * @param properties
     * @param newValue
     * @param updateSb
     */
    public static void addPropContent(String properties, Object value, StringBuilder addSb)
    {
        if(value != null)
        {
            addSb.append(properties).append(": ").append(value).append(" ,");
        }
    }
    
    /**
     * 去除末尾逗号
     * @param sb
     * @return String
     */
    public static String getContent(StringBuilder sb)
    {
        String content = sb.toString();
        if(content.endsWith(","))
        {
            content = content.substring(0, content.length() - 1);
        }
        return content;
    }
    
    /**
     * 分析修改信息
     *
     * @param oldObj
     * @param newObj
     * @return 修改的内容明细
     */
    public static String analyseEditContent(Object oldObj, Object newObj)
    {
        if(oldObj == null)
        {
            return "old object is null";
        }
        else if(newObj == null)
        {
            return "new object is null";
        }
        Class o = oldObj.getClass();
        Class n = newObj.getClass();
        if(!o.getName().equals(n.getName()))
        {
            return "the cast of compare object is defferent";
        }
        StringBuilder updateSb = new StringBuilder().append(" edit[ ");
        Field[] perps = o.getDeclaredFields();
        Field[] newPerps = n.getDeclaredFields();
        if(perps != null)
        {
            String name;
            Field newField;
            for(Field f : perps)
            {
                f.setAccessible(true);
                
                name = f.getName();
                try
                {
                    newField = findFieldByName(newPerps, name);
                    if(newField != null)
                    {
                        newField.setAccessible(true);
                        compareChange(name, f.get(oldObj), newField.get(newObj), updateSb);
                    }
                }
                catch (Exception e)
                {
                    throw new ServiceException(e);
                }
            }
        }
        if(updateSb.length() > 7)
        {
            updateSb.setLength(updateSb.length() - 1);
        }
        updateSb.append("]");
        return updateSb.toString();
    }
    
    private static Field findFieldByName(Field[] newPerps, String name)
    {
        if(newPerps != null)
        {
            for(Field field : newPerps)
            {
                if(name.equals(field.getName()))
                {
                    return field;
                }
            }
        }
        return null;
    }
}

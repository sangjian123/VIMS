/*
 * 文 件 名: GeneralUtils.java
 * 描 述: XML文件操作工具类
 * 修改内容: 新增
 */
package com.vms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vms.constant.CommonConstants;
import com.vms.constant.GeneralConstant;
import com.vms.model.ResposeBase;

/**
 * 通用的工具类
 *
 * @author xugangfeng
 * @version [版本号, 2016-11-11]
 * @see [相关类/方法]
 * @since [V100R001]
 */
public final class GeneralUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AesKeyUtils.class);
    
    private static final int SIZE = 100;
    
    /**
     * <默认构造函数>
     */
    private GeneralUtils()
    {
        
    }
    
    /**
     * 判断对象是否为null , 为null返回true,否则返回false
     *
     * @param obj
     *            被判断的对象
     * @return boolean
     */
    public static boolean isNull(Object obj)
    {
        return null == obj;
    }
    
    /**
     * 判断对象是否为null , 为null返回true,否则返回false
     * 
     * @param obj
     *            被判断的对象
     * @param exact
     *            是否严密的判断
     * @return boolean
     * @author wangwei-nj
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(Object obj, boolean exact)
    {
        if(exact)
        {
            if(obj instanceof String)
            {
                return GeneralUtils.isNullOrZeroLength((String) obj);
            }
            else if(obj instanceof Collection)
            {
                return GeneralUtils.isNullOrZeroSize((Collection) obj);
            }
        }
        
        return isNull(obj);
    }
    
    /**
     * 判断对象是否为null , 为null返回false,否则返回true
     *
     * @param obj
     *            被判断的对象
     * @return boolean
     */
    public static boolean isNotNull(Object obj)
    {
        return !isNull(obj);
    }
    
    /**
     * 判断对象是否为null , 为null返回false,否则返回true
     * 
     * @param obj
     *            被判断的对象
     * @param exact
     *            是否严密的判断
     * @return boolean
     * @author wangwei-nj
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNull(Object obj, boolean exact)
    {
        return !isNull(obj, exact);
    }
    
    /**
     * 判断集合对象是否为null或者0大小 , 为空或0大小返回true ,否则返回false
     *
     * @param c
     *            collection 集合接口
     * @return boolean 布尔值
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNullOrZeroSize(Collection c)
    {
        return isNull(c) || c.isEmpty();
    }
    
    /**
     * 判断集合对象是否为null或者0大小 , 为空或0大小返回false, 否则返回true
     *
     * @param c
     *            collection 集合接口
     * @return boolean 布尔值
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNullOrZeroSize(Collection c)
    {
        return !isNullOrZeroSize(c);
    }
    
    /**
     * 判断集合对象是否为null或者0大小 , 为空或0大小返回true ,否则返回false
     *
     * @param iterable
     *            collection 集合接口
     * @return boolean 布尔值
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNullOrZeroSize(Iterable iterable)
    {
        return !isNotNullOrZeroSize(iterable);
    }
    
    /**
     * 判断集合对象是否为null或者0大小 , 为空或0大小返回false, 否则返回true
     *
     * @param iterable
     *            collection 集合接口
     * @return boolean 布尔值
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNullOrZeroSize(Iterable iterable)
    {
        boolean result = false;
        
        if(isNotNull(iterable))
        {
            result = iterable.iterator().hasNext();
        }
        
        return result;
    }
    
    /**
     * 判断数字类型是否为null或者0，如果是返回true，否则返回false
     *
     * @param number
     *            被判断的数字
     * @return boolean
     */
    public static boolean isNullOrZero(Number number)
    {
        if(GeneralUtils.isNotNull(number))
        {
            return number.intValue() == 0;
        }
        
        return true;
    }
    
    /**
     * 判断数字类型是否为null或者0 true: n != null && n != 0
     *
     * @param number
     *            - 被判断的数字
     * @return boolean - true,false
     * @author wangkai
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNullOrZero(Number number)
    {
        return !isNullOrZero(number);
    }
    
    /**
     * 判断字符串是否为null或者0长度，字符串在判断长度时， 先去除前后的空格,空或者0长度返回true,否则返回false
     *
     * @param str
     *            被判断的字符串
     * @return boolean
     */
    public static boolean isNullOrZeroLength(String str)
    {
        return StringUtils.isEmpty(str);
    }
    
    /**
     * 判断字符串是否为null或者0长度，字符串在判断长度时， 先去除前后的空格,空或者0长度返回false,否则返回true
     *
     * @param str
     *            被判断的字符串
     * @return boolean
     */
    public static boolean isNotNullOrZeroLength(String str)
    {
        return !isNullOrZeroLength(str);
    }
    
    /**
     * 判断str数组是否为null或者0长度，只要有一个空或者0长度返回false， 否则返回true
     *
     * @param str
     *            String 字符数组
     * @return boolean
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNullOrZeroLength(String... str)
    {
        for(String s : str)
        {
            if(isNullOrZeroLength(s))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断str数组是否为null或者0长度，只要有一个空或者0长度返回true， 否则返回false
     *
     * @param str
     *            String 字符数组
     * @return boolean
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNullOrZeroLength(String... str)
    {
        return !isNotNullOrZeroLength(str);
    }
    
    /**
     * 判断数组是否为空
     * 
     * @param objects
     *            objects
     * @return boolean 布尔值
     * @author fuhaiyan
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNullOrZeroSizeArray(String[] objects)
    {
        boolean result = true;
        if(GeneralUtils.isNull(objects) || GeneralConstant.NUM_0 >= objects.length)
        {
            result = false;
        }
        return result;
    }
    
    /**
     * <判断邮箱格式是否正确>
     * 
     * @param email
     *            email
     * @return 格式正确返回false；不正确返回true
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmailRule(String email)
    {
        boolean result = true;
        if(isNotNullOrZeroLength(email) && isRegexMatch(email, GeneralConstant.EMAIL))
        {
            result = false;
        }
        return result;
    }
    
    /**
     * <判断邮箱格式是否正确>
     * 
     * @param email
     *            email
     * @return 格式不正确返回true；正确返回false
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmailRule(String email)
    {
        return !isEmailRule(email);
    }
    
    /**
     * 判断电话传真手机格式
     * 
     * @param mfp
     *            mfp
     * @return 匹配返回false 否则返回true
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMobileOrFaxOrPhone(String mfp)
    {
        return isNotRegexMatch(mfp, GeneralConstant.MOBILEL_FAX_PHONE);
    }
    
    /**
     * ip匹配返回false 否则返回true
     * 
     * @param ip
     *            ip
     * @return 匹配返回false 否则返回true
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isIP(String ip)
    {
        return isNotRegexMatch(GeneralConstant.IS_IP, ip);
    }
    
    /**
     * url匹配返回false 否则返回true
     * 
     * @param url
     *            url
     * @return 匹配返回false 否则返回true
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isURL(String url)
    {
        boolean result = true;
        if(isNotNullOrZeroLength(url) && !isRegexMatch(GeneralConstant.URL, url))
        {
            result = false;
        }
        return result;
    }
    
    /**
     * <正则校验法> <功能详细描述>
     * 
     * @param expression
     *            expression
     * @param value
     *            value
     * @return 匹配成功返回true 否则false
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isRegexMatch(String value, String expression)
    {
        String regex = StringUtils.trim(value);
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    
    /**
     * <正则校验法> <功能详细描述>
     * 
     * @param expression
     *            expression
     * @param value
     *            value
     * @return 匹配成功返回false 否则true
     * @author chenhui0216
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotRegexMatch(String value, String expression)
    {
        return !isRegexMatch(value, expression);
    }
    
    /**
     * byte[]转换为String，默认为UTF-8
     *
     * @param bts
     *            bts
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public static String bytes2String(byte[] bts)
    {
        return bytes2String(bts, GeneralConstant.CHARACTER_CODING);
    }
    
    /**
     * byte[]转换为String， 如果没有编码默认为UTF-8
     *
     * @param bts
     *            - byte数组
     * @param code
     *            - 编码
     * @return 转换后的字符串
     */
    public static String bytes2String(byte[] bts, String code)
    {
        try
        {
            if(isNotNullOrZeroLength(code))
            {
                return new String(bts, code);
            }
            
            return new String(bts, GeneralConstant.CHARACTER_CODING);
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error("bytes to String error: ", e);
            return "";
        }
    }
    
    /**
     * 拼装文件路径
     *
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名或带部分路径的文件名
     * @return 返回处理后的字符串
     */
    public static String filePathConcat(String filePath, String fileName)
    {
        String basePath = FilenameUtils.separatorsToUnix(GeneralUtils.isNull(filePath) ? "" : filePath);
        String fullFileName = FilenameUtils.separatorsToUnix(GeneralUtils.isNull(fileName) ? "" : fileName);
        
        if(StringUtils.isBlank(basePath))
        {
            return fullFileName;
        }
        
        if(basePath.length() - 1 == StringUtils.lastIndexOf(basePath, File.separator))
        {
            basePath = StringUtils.substring(basePath, 0, basePath.length() - 1);
        }
        
        if(0 == StringUtils.indexOf(fullFileName, File.separator))
        {
            fullFileName = StringUtils.substring(fullFileName, 1);
        }
        
        return basePath + File.separator + fullFileName;
    }
    
    /**
     * 将大List分拆成小长度为500的list用于分批插入数据
     * 
     * @param <T>
     * @param list
     * @return
     */
    public static <T> List<List<T>> createList(List<T> list)
    {
        return createList(list, SIZE);
    }
    
    /**
     * 将大List分拆成小list用于分批插入数据
     * 
     * @param <T>
     * @param list
     *            需要拆分的list
     * @param size
     *            每个list的大小
     * @return 拆分后List集合
     */
    public static <T> List<List<T>> createList(List<T> list, int size)
    {
        List<List<T>> listArr = new ArrayList<List<T>>();
        // 获取被拆分的List个数
        int length = list.size();
        int listSize = length % size == 0 ? length / size : length / size + 1;
        for(int i = 0; i < listSize; i++)
        {
            // 把指定索引数据放入到list中
            List<T> subList = new ArrayList<T>();
            for(int j = i * size; j <= size * (i + 1) - 1; j++)
            {
                if(j <= length - 1)
                {
                    subList.add(list.get(j));
                }
            }
            listArr.add(subList);
        }
        return listArr;
    }
    
    /**
     * 对象转Byte数组
     * 
     * @param obj
     * @return 字符数组
     * @author tfl
     * @see [类、类#方法、类#成员]
     */
    public static byte[] objectToByteArray(Object obj)
    {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            
        }
        catch (IOException e)
        {
            LOGGER.error("Object to bytes error: ", e);
        }
        finally
        {
            IOUtils.closeQuietly(objectOutputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);
        }
        return bytes;
    }
    
    /**
     * Byte数组转对象
     * 
     * @param bytes
     * @return 对象
     * @author tfl
     * @see [类、类#方法、类#成员]
     */
    public static Object byteArrayToObject(byte[] bytes)
    {
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try
        {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        }
        catch (Exception e)
        {
            LOGGER.error("bytes to Object error: ", e);
        }
        finally
        {
            IOUtils.closeQuietly(byteArrayInputStream);
            IOUtils.closeQuietly(objectInputStream);
        }
        return obj;
    }
    
    /**
     * 判断是数字字符串
     * 
     * @param String
     * @return 对象
     * @author tfl
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNumeric(String str)
    {
        if(null == str || str.trim().length() < 1)
        {
            return false;
        }
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断Float类型是否为0
     * @param float1
     * @return
     */
    public static boolean floatIsZero(float float1)
    {
        return floatIsEquals(float1, 0L);
    }
    
    /**
     * 判断2个Float类型是否相等
     * @param float1
     * @param float2
     * @return
     */
    public static boolean floatIsEquals(float float1, float float2)
    {
        if(float1 - float2 < 0.00000001f && float1 - float2 > -0.00000001f)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 判断Double类型是否为0
     * @param double1
     * @return
     */
    public static boolean doubleIsZero(double double1)
    {
        return doubleIsEquals(double1, 0D);
    }
    
    /**
     * 判断2个Double类型是否相等
     * @param float1
     * @param float2
     * @return
     */
    public static boolean doubleIsEquals(double double1, double double2)
    {
        if(double1 - double2 < 0.00000001f && double1 - double2 > -0.00000001f)
        {
            return true;
        }
        return false;
    }
    
    public static void analysisResults(ResposeBase rs)
    {
        if(rs.getStatusCode() != CommonConstants.SUCCESS_CODE)
        {
            throw new ServiceException(rs.getStatusMsg(), rs.getStatusCode());
        }
    }
    
    /**
     * 字符模糊匹配
     * 支持:/druid*, *druid, *druid*
     * @param patternStr
     * @param sourceStr
     * @return
     */
    public static boolean fuzzyMatches(String patternStr, String sourceStr)
    {
        if(patternStr == null || sourceStr == null)
        {
            return false;
        }
        String pattern = patternStr.trim();
        String source = sourceStr.trim();
        if(pattern.startsWith("*") && pattern.endsWith("*"))
        {
            // pattern: /druid* source:/druid/index.html
            int length = pattern.length() - 2;
            if(source.length() >= length)
            {
                if(source.indexOf(pattern.substring(1, length)) > 1)
                {
                    return true;
                }
            }
        }
        if(pattern.endsWith("*"))
        {
            // pattern: /druid* source:/druid/index.html
            int length = pattern.length() - 1;
            if(source.length() >= length)
            {
                if(pattern.substring(0, length).equals(source.substring(0, length)))
                {
                    return true;
                }
            }
        }
        else if(pattern.startsWith("*"))
        {
            // pattern: *.html source:/xx/xx.html
            int length = pattern.length() - 1;
            if(source.length() >= length && source.endsWith(pattern.substring(1)))
            {
                return true;
            }
        }
        else if(pattern.contains("*"))
        {
            // pattern:  /druid/*/index.html source:/druid/admin/index.html
            int start = pattern.indexOf("*");
            int end = pattern.lastIndexOf("*");
            if(source.startsWith(pattern.substring(0, start)) && source.endsWith(pattern.substring(end + 1)))
            {
                return true;
            }
        }
        else
        {
            // pattern: /druid/index.html source:/druid/index.html
            if(pattern.equals(source))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     *   把长度超过 size的list 的数组的前 size 个对象抽取出来组成新的list
     */
    public static <T> List<T> splitList(List<T> list, int size)
    {
        if(list == null || list.size() <= size)
            return list;
        List<T> newList = new ArrayList<T>(size);
        for(int i = 0; i < size; i++)
        {
            newList.add(list.get(i));
        }
        return newList;
    }
    
}

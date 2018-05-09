package com.vms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.vms.constant.GeneralConstant;

/**
 * 日期工具类
 *
 * @author tfl
 * @date 2016年11月22日下午3:10:42
 */
public class DateUtils
{
    
    /**
     * yyyy-MM-dd
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    
    /**
     * yyyyMM
     */
    private static final String DATE_YYYY_MM_PATTERN = "yyyyMM";
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * yyyyMMddHHmmss
     */
    public static final String DATE_PATTERN_YMDHMS = "yyyyMMddHHmmss";
    
    /**
     * 预设Format的当前日期字符串
     */
    public static String getToday()
    {
        Date today = new Date();
        return formatDate(today);
    }
    
    /**
     * 预设Format的当前日期字符串
     */
    public static String getToday(String pattern)
    {
        Date today = new Date();
        return formatDate(today, pattern);
    }
    
    /**
     * 获取日期的字符串
     *
     * @param date
     *            转换的日期
     * @return 时间格式(yyyy-MM-dd)
     */
    public static String formatDate(Date date)
    {
        return date == null ? null : formatDate(date, DEFAULT_PATTERN);
    }
    
    /**
     * 获取指定格式的日期时间字符串
     *
     * @param date
     *            转换的日期
     * @param pattern
     *            转换的指定格式
     * @return 转换后的日期字符串
     */
    public static String formatDate(Date date, String pattern)
    {
        return date == null ? null : new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 获取当前月之前/之后的月份
     *
     * @param increment
     *            增加量/减少量
     * @return 指定月份的Date对象
     */
    public static Date getMonthBeforeOrAfterCurrentMonth(int increment)
    {
        Calendar c = Calendar.getInstance();
        // c.set(Calendar.MONTH, c.get(Calendar.MONTH) + increment);
        c.add(c.MONTH, increment);
        return c.getTime();
    }
    
    /**
     * 获取最近的N个月（包含当前月）
     *
     * @param number
     *            月份数(实际值比预期值小1)。如：取最近12个月，number传入值为11
     * @return List<String> 年月从小到大
     */
    public static List<String> getLatestMonth(int number)
    {
        List<String> list = new ArrayList<String>(number);
        
        if(number > 0)
        {
            for(int i = number; i >= 0; i--)
            {
                list.add(formatDate(getMonthBeforeOrAfterCurrentMonth(-i), DATE_YYYY_MM_PATTERN));
            }
        }
        return list;
    }
    
    public static Date getMonthBeforeOrAfterSomeMonth(Date date, int increment)
    {
        Calendar c = Calendar.getInstance();
        /*c.set(Calendar.YEAR, date.getYear() + 1900);
        c.set(Calendar.MONTH, date.getMonth());
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + increment);*/
        c.setTime(date);
        c.add(c.MONTH, increment);
        return c.getTime();
    }
    
    /**
     * 获取指定时间的近 number 个月
     * 
     * @param someDate
     * @param number
     * @return
     */
    public static List<String> getLatestMonthForSomeDate(Date someDate, int number)
    {
        List<String> list = new ArrayList<String>(number);
        
        if(number > 0)
        {
            for(int i = number; i >= 0; i--)
            {
                list.add(formatDate(getMonthBeforeOrAfterSomeMonth(someDate, -i), DATE_YYYY_MM_PATTERN));
            }
        }
        return list;
    }
    
    /**
     * 按默认yyy-MM-dd格式转化成String
     * 
     * @param dateStr
     * @return
     */
    public static String date2String(Date date)
    {
        return date2String(date, DEFAULT_PATTERN);
    }
    
    /**
     * 将java.util.Date类型转化位String类型 (如果传入时间为系统当前时间，
     * 则应该调用getCurrentTimeStamp()方法。)
     *
     * @param date
     *            要转换的时间
     * @param format
     *            时间格式
     * @return 如果转换成功，返回指定格式字符串，如果转换失败，返回null
     */
    public static String date2String(Date date, String format)
    {
        if(GeneralUtils.isNull(date) || GeneralUtils.isNull(format))
        {
            return null;
        }
        
        return DateFormatUtils.format(date, format);
    }
    
    /**
     * 按默认yyy-MM-dd格式化时间
     * 
     * @param dateStr
     * @return
     */
    public static Date string2Date(String dateStr)
    {
        return string2Date(dateStr, DEFAULT_PATTERN);
    }
    
    /**
     * 将字符串时间转换成java.util.Date类型
     *
     * @param str
     *            要转换的字符串
     * @param format
     *            时间格式
     * @return 如果转换失败，返回null
     */
    public static Date string2Date(String str, String format)
    {
        if(GeneralUtils.isNull(str) || GeneralUtils.isNull(format))
        {
            return null;
        }
        
        // 定义日期/时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        
        try
        {
            // 转换日期/时间格式
            date = sdf.parse(str);
        }
        catch (ParseException e)
        {
            date = null;
        }
        
        return date;
    }
    
    /**
    * 按默认yyyy-MM-dd格式化时间为yyyy-MM-dd 23:59:59
    * 
    * @param dateStr
    * @return
    */
    public static Date string2MaxDate(String dateStr)
    {
        Date date = string2Date(dateStr);
        if(null != date)
        {
            Calendar para = Calendar.getInstance();
            para.setTime(date);
            para.set(Calendar.HOUR_OF_DAY, 23);
            para.set(Calendar.MINUTE, 59);
            para.set(Calendar.SECOND, 59);
            return para.getTime();
        }
        return date;
    }
    
    /**
     * 将yyyyMMddHHmmss 转换成 指定formatStr的时间字符串格式 默认转换为yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     *            - 待转换的时间字符串
     * @param formatStr
     *            - 转换格式
     * @return String - 转换后的时间字符串
     * @author wangkai
     * @see [类、类#方法、类#成员]
     */
    public static String switchDateStr(String dateStr, String formatStr)
    {
        if(GeneralUtils.isNullOrZeroLength(formatStr))
        {
            formatStr = GeneralConstant.DATETIME_EN_14;
        }
        return date2String(string2Date(dateStr, GeneralConstant.DATETIME_14), formatStr);
    }
    
    /**
     * 转换时间MM-dd-yyyy HH:mm:ss成yyyyMMddHHmmss
     *
     * @param dateStr
     *            参数
     * @return String
     * @author xiongguofu
     * @see [类、类#方法、类#成员]
     */
    public static String switchDate18To14(String dateStr)
    {
        Date date = string2Date(dateStr, GeneralConstant.DATETIME_EN_14);
        
        return date2String(date, GeneralConstant.DATETIME_14);
    }
    
    /**
     * 转换时间yyyy-MM-dd'T'HH:mm:ss成yyyyMMdd'T'HHmmss'Z'
     * 
     * @param dateStr
     * @param reverse
     *            为true时 反转
     * @return
     */
    public static String switchDate15To16(String dateStr, boolean reverse)
    {
        Date date = string2Date(dateStr, reverse ? GeneralConstant.DATETIME_16 : GeneralConstant.DATETIME_15);
        return date2String(date, reverse ? GeneralConstant.DATETIME_15 : GeneralConstant.DATETIME_16);
    }
    
    /**
     * 返回当前时间戳
     *
     * @return string 时间字符串
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentTimeStamp()
    {
        return date2String(new Date(), GeneralConstant.DATETIME_14);
    }
    
    /**
     * 返回当前时间戳
     *
     * @param pattern
     *            默认为：yyyyMMddHHmmss
     * @return string 时间字符串
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentTimeStamp(String pattern)
    {
        if(StringUtils.isBlank(pattern))
        {
            pattern = GeneralConstant.DATETIME_14;
        }
        
        return date2String(new Date(), pattern);
    }
    
    /**
     * 返回时间格式为yyyy-MM-dd HH:mm:ss的字符串
     *
     * @param date
     *            默认为：yyyyMMddHHmmss
     * @return string 时间字符串
     * @author tfl
     * @see [类、类#方法、类#成员]
     */
    public static String formatDateTime(Date date)
    {
        return date2String(date, GeneralConstant.DATETIME_14_COMMON);
    }
    
    /**
     * 返回当前时间
     *
     * @return Date 时间
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static Date getCurrentDate()
    {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param value
     *            value
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean isDateType(String value)
    {
        return GeneralUtils.isNotNull(string2Date(value, GeneralConstant.DATETIME_EN_14));
    }
    
    /**
     * 按指定的单位返回指定时间的时间间隔<br>
     * 本方法默认按照"yyyyMMddHHmmss" 格式化时间串
     *
     * @param currentTime
     *            - 当前时间
     * @param compareTime
     *            - 比较时间
     * @param timeUnit
     *            - 时间单位
     * @return float - 返回指定时间的时间间隔
     * @author wangkai
     * @see [类、类#方法、类#成员]
     */
    public static float getIntervalBySpecifiedUnit(String currentTime, String compareTime, int timeUnit)
    {
        
        Date currentDate = string2Date(currentTime, GeneralConstant.DATETIME_14);
        
        Date compareDate = string2Date(compareTime, GeneralConstant.DATETIME_14);
        
        if(GeneralUtils.isNull(currentDate) || GeneralUtils.isNull(compareDate))
        {
            throw new IllegalArgumentException();
        }
        
        // 本方法目前仅仅支持日、时、分、秒的比较，其余比较方式将会抛出IllegalArgumentException异常。
        switch(timeUnit)
        {
            case Calendar.DATE:
                return (org.apache.commons.lang3.time.DateUtils.truncate(currentDate, Calendar.HOUR).getTime() - org.apache.commons.lang3.time.DateUtils
                    .truncate(compareDate, Calendar.HOUR).getTime())
                    / (GeneralConstant.TWENTY_FOUR * GeneralConstant.SIXTY * GeneralConstant.SIXTY * GeneralConstant.THOUSAND);
            case Calendar.HOUR:
                return (org.apache.commons.lang3.time.DateUtils.truncate(currentDate, Calendar.MINUTE).getTime() - org.apache.commons.lang3.time.DateUtils
                    .truncate(compareDate, Calendar.MINUTE).getTime())
                    / (GeneralConstant.SIXTY * GeneralConstant.SIXTY * GeneralConstant.THOUSAND);
            case Calendar.MINUTE:
                return (org.apache.commons.lang3.time.DateUtils.truncate(currentDate, Calendar.SECOND).getTime() - org.apache.commons.lang3.time.DateUtils
                    .truncate(compareDate, Calendar.SECOND).getTime()) / (GeneralConstant.SIXTY * GeneralConstant.THOUSAND);
            case Calendar.SECOND:
                return (org.apache.commons.lang3.time.DateUtils.truncate(currentDate, Calendar.MILLISECOND).getTime() - org.apache.commons.lang3.time.DateUtils
                    .truncate(compareDate, Calendar.MILLISECOND).getTime()) / GeneralConstant.THOUSAND;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    /**
     * <计算周期任务下次执行时间> <功能详细描述>
     *
     * @param currentStartTime
     *            当前执行时间
     * @param intervalType
     *            周期任务间隔类型
     * @param interval
     *            周期任务间隔数值
     * @return "yyyyMMddHHmmss"格式时间字符串
     * @author jiangtianyang
     * @see [类、类#方法、类#成员]
     */
    public static String calculateNextStartTime(Date currentStartTime, int intervalType, int interval)
    {
        Calendar calendar = Calendar.getInstance();
        switch(intervalType)
        {
            case 0:
                calendar.add(Calendar.HOUR, interval);
                break;
            case 1:
                calendar.add(Calendar.DAY_OF_MONTH, interval);
                break;
            case GeneralConstant.NUM_2:
                calendar.add(Calendar.MONTH, interval);
                break;
            default:
                break;
        }
        
        return date2String(calendar.getTime(), GeneralConstant.DATETIME_14);
    }
    
    /**
     * 将6位字符串转换成时间)
     *
     * @param str
     *            要转换的时间
     * @return 如果转换成功，返回指定格式字符串，如果转换失败，返回null
     */
    public static String time2String(String str)
    {
        String time = null;
        if(GeneralUtils.isNotNullOrZeroLength(str) && str.length() == GeneralConstant.NUM_6)
        {
            time =
                str.substring(GeneralConstant.NUM_0, GeneralConstant.NUM_2) + GeneralConstant.COLON
                    + str.substring(GeneralConstant.NUM_2, GeneralConstant.NUM_4) + GeneralConstant.COLON
                    + str.substring(GeneralConstant.NUM_4, GeneralConstant.NUM_6);
        }
        
        return time;
    }
    
    /**
     * 判断当前时间距离某开始时间 间隔是否大于多少秒
     *
     * @param beginTime
     *            开始的时间
     * @second second 秒
     * @return
     */
    public static boolean isBelongTime(long beginTime, long second)
    {
        boolean rev = false;
        if((System.currentTimeMillis() - beginTime) <= second * 1000)
        {
            rev = true;
        }
        return rev;
    }
    
    /**
     * 判断当前时间距离某开始时间 间隔是否大于多少秒
     *
     * @param lessSec
     *            小于当前时间最小秒数
     * @param greaterSec
     *            大于当前时间最大秒数
     * @param timeValue
     *            某时间值
     * @return
     */
    public static boolean isMiddleTime(long timeValue, long lessSec, long greaterSec)
    {
        boolean rev = false;
        long curTime = System.currentTimeMillis();
        if(curTime >= timeValue)
        {
            if((curTime - timeValue) <= lessSec * 1000)
            {
                rev = true;
            }
        }
        else
        {
            if((timeValue - curTime) <= greaterSec * 1000)
            {
                rev = true;
            }
        }
        return rev;
    }
    
    /**
     * 判断当前时间与开始时间 是否同一天
     *
     * @param beginTime
     *            开始的时间
     * @return
     */
    public static boolean isSameDay(long beginTime)
    {
        boolean rev = false;
        Date date = new Date(beginTime);
        if(formatDate(new Date()).equals(formatDate(date)))
        {
            rev = true;
        }
        return rev;
    }
    
    /**
     * 判断是否合法日期
     * 
     * @param date
     *            日期字符串
     * @param format
     *            日期格式：yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate(String dt, String format)
    {
        int[] DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if("yyyyMM".equals(format))
        { // 校验数值是否超过上下限
            int month = Integer.parseInt(dt.substring(4, 6));
            if(month > 12 || month < 1)
            {
                return false;
            }
        }
        if("yyyy-MM-dd".equals(format))
        { // 校验数值是否超过上下限
            int year = Integer.parseInt(dt.substring(0, 4));// 年份
            int month = Integer.parseInt(dt.substring(5, 7));
            int day = Integer.parseInt(dt.substring(8, 10));
            if(month > 12 || month < 1)
            {
                return false;
            }
            if(day < 1 || day > DAYS[month - 1])
            {
                return false;
            }
            if(month == 2)
            {
                Calendar c = Calendar.getInstance();
                c.set(year, 2, 1);// year年的3月1日
                c.add(Calendar.DAY_OF_MONTH, -1);// 将3月1日往左偏移一天结果是2月的天数
                int day2 = c.get(Calendar.DAY_OF_MONTH);
                if(day < 1 || day > day2)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * @Title: isValidateFormat @Description: 判断当前日期是否格式正确 @param dt @param
     * format @return boolean @throws
     */
    public static boolean isValidateFormat(String dt, String format)
    {
        boolean convertSuccess = true;
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try
        {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            fmt.setLenient(false);
            fmt.parse(dt);
        }
        catch (ParseException e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
    
    /**
     * 获取与当前世界currTime间隔interval天数后的日期
     * @param CurrTime
     * @param interval
     * @return
     */
    public static Date getDate(Date currTime, int interval)
    {
        if(null == currTime)
        {
            currTime = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(currTime);
        c.add(c.DATE, interval);
        return c.getTime();
    }
    
    /**
     * 返回指定时间的前n天
     * @param date
     * @param addDayCount ,>0指定时间之后, <0指定时间之前
     * @return
     */
    public static Date getSpecifiedDateByAddDay(Date date, int addDayCount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + addDayCount);
        return c.getTime();
    }
    
}

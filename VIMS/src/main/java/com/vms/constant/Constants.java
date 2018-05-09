package com.vms.constant;

/**
 * 系统常量类(定义字符串、数字等常量)
 * @author Administrator
 *
 */
public class Constants
{
    /**
     * 数字常量类定义区域
     *
     */
    public interface Number
    {
        public static int ONE = 1;// 数字1
        
        public static int TWO = 2;// 数字2
        
        public static int THERR = 3;// 数字3
        
        public static int FOUR = 4;// 数字4
        
        public static int FIVE = 5;// 数字5
        
        public static int SIX = 6;// 数字6
        
        public static int SEVEN = 7;// 数字7
        
        public static int EIGHT = 8;// 数字8
        
        public static int NINE = 9;// 数字9
        
        public static int TEN = 10;// 数字10
    }
    
    public interface Strings
    {
        public static String USER = "user";
        
        public static String TIMEOUT = "timeout";
        
        public static String HISTORYUSERS = "historyusers";
        
        public static String JSESSIONID = "JSESSIONID";
        
        public static String ERRORINFO = "ERRORINFO";
        
        public static String EXCEPTION = "exception";
    }
    
    public interface Exception
    {
        public static String EXCEPTION_1000001 = "Exception_1000001";
        
        public static String EXCEPTION_1000002 = "Exception_1000002";
        
        public static String EXCEPTION_1000003 = "Exception_1000003";
    }
}

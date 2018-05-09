package com.vms.utils;

import org.apache.commons.lang3.StringUtils;

import com.vms.model.UserPwdInput;

/**
 * @description：建议密码输入次数
 * @author：tfl @date：2017/5/15 14:51
 */
public class CheckPwdInput
{
    private static final String ERROR_TIMES = "password_error_times";
    
    private static final String DISABLED_TIME = "password_disabled_time";
    
    public static final String EXIST_INPUT_FALG = "isExist";
    
    public static final String CHECK_ERROR = "checkPwdErrorInput";
    
    /**
     * 验证密码输入错误次数
     * 
     * @param username
     * @return
     */
    public static String checkPwdInput(String username)
    {
        int errorTimes = getPwdErrorSet(ERROR_TIMES, 3);
        UserPwdInput userInput = UserPwdInputCache.getCache(username);
        if(userInput == null)
        {
            return null;
        }
        
        int failTimes = userInput.getErrorTimes();
        
        if(failTimes >= errorTimes)
        {
            int spaceTime = getPwdErrorSet(DISABLED_TIME, 10);
            
            if(DateUtils.isBelongTime(userInput.getLastTime(), spaceTime * 60))
            {
                return String.format(I18nUtils.getI18nMsg("system_user_login_error_pwd"), errorTimes, spaceTime);
            }
            else
            {
                UserPwdInputCache.removeCache(username);
            }
        }
        else
        {
            return EXIST_INPUT_FALG;
        }
        return null;
    }
    
    /**
     * 增加密码输入错误次数
     * 
     * @param userName
     * @return
     */
    public static void addPwdErrorTimes(String userName)
    {
        // 时间间隔
        UserPwdInput userInput = UserPwdInputCache.getCache(userName);
        if(null != userInput)
        {
            int errorTimes = getPwdErrorSet(ERROR_TIMES, 3);
            
            int havedErrorTimes = userInput.getErrorTimes();
            userInput.setErrorTimes(++havedErrorTimes);
            if(havedErrorTimes == errorTimes)
            {
                userInput.setLastTime(System.currentTimeMillis());
            }
        }
        else
        {
            userInput = new UserPwdInput();
            userInput.setErrorTimes(1);
            userInput.setLastTime(System.currentTimeMillis());
            UserPwdInputCache.setCache(userName, userInput);
        }
    }
    
    /**
     * 获取密码输入错误设置
     * 
     * @param key
     * @param defInt
     * @return
     */
    public static int getPwdErrorSet(String key, int defInt)
    {
        String value = ConfigHolder.getCfg(key);
        int rev = defInt;
        if(StringUtils.isNotEmpty(value))
        {
            try
            {
                rev = Integer.parseInt(value);
            }
            catch (NumberFormatException e)
            {
                rev = defInt;
            }
        }
        return rev;
    }
}

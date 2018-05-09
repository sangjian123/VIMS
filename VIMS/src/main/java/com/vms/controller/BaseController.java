package com.vms.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vms.constant.CommonConstant;
import com.vms.constant.GeneralConstant;
import com.vms.model.DateEditor;
import com.vms.model.Page;
import com.vms.model.Result;
import com.vms.model.User;
import com.vms.utils.I18nUtils;
import com.vms.utils.ServiceUtils;
import com.vms.utils.StringEscapeEditor;

/**
 * @description：基础 controller
 */
public abstract class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    //成功状态码
    protected static final int SUCCESS_CODE = 1;
    
    //失败码
    protected static final int FAIL_CODE = 0;
    
    /** This int is the sequence number ,the default value is 0. */
    private static int DIVISOR = 60000;
    
    private static AtomicInteger seq = new AtomicInteger(0);
    
    private static final int MAX = 9999999;
    
    /** This Format for format the number to special format. */
    private static final NumberFormat numberFormat = new DecimalFormat("0000000");
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder)
    {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateEditor());
        
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }
    
    /**
     * 获取session
     *
     * @return
     */
    public Session getSession()
    {
        Subject user = SecurityUtils.getSubject();
        return user.getSession();
    }
    
    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public User getCurrentUser()
    {
        User currentUser = (User) getSession().getAttribute(CommonConstant.CURRENT_LOGIN_USER);
        return currentUser;
    }
    
    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public User getCurrentUser(HttpSession session)
    {
        User currentUser = (User) session.getAttribute(CommonConstant.CURRENT_LOGIN_USER);
        return currentUser;
    }
    
    /**
     * 根据登录用户IP
     */
    public String getRemoteAddr()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return ServiceUtils.getClientIp(request);
    }
    
    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public String getUserId()
    {
        return this.getCurrentUser().getId();
    }
    
    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public String getStaffName()
    {
        return this.getCurrentUser().getName();
    }
    
    /**
     * 封装返回的结果集
     * @param page 查询返回的结果
     * @param isSuccess 是否查询成功
     * @return Map<String,Object>
     */
    protected Map<String, Object> ajaxResult(Page<?> page, boolean isSuccess)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(CommonConstant.PAGE_RESULT_MESSAGE, FAIL_CODE);
        resultMap.put(CommonConstant.PAGE_RESULT_ROWS, new ArrayList<>());
        resultMap.put(CommonConstant.PAGE_RESULT_TOTAL, 0);
        if(isSuccess)
        {
            resultMap.put(CommonConstant.PAGE_RESULT_ROWS, page.getResults());
            resultMap.put(CommonConstant.PAGE_RESULT_TOTAL, page.getTotalRecord());
            resultMap.put(CommonConstant.PAGE_RESULT_MESSAGE, SUCCESS_CODE);
        }
        return resultMap;
    }
    
    /**
     * ajax失败
     *
     * @param msg
     *            失败的消息
     * @return {Object}
     */
    public Object renderError(String msg)
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
    
    /**
     * ajax成功
     *
     * @return {Object}
     */
    public Object renderSuccess()
    {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
    
    /**
     * ajax成功
     *
     * @param msg
     *            消息
     * @return {Object}
     */
    public Object renderSuccess(String msg)
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    
    /**
     * ajax成功
     *
     * @param msg
     *            消息
     * @return {Object}
     */
    public Object renderSuccess(String msg, String id)
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        result.setId(id);
        return result;
    }
    
    /**
     * ajax成功
     *
     * @param obj
     *            成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj)
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
    public int getCurrentPage(HttpServletRequest request)
    {
        
        return NumberUtils.toInt(request.getParameter(CommonConstant.PAGE_PARAM_PAGE), 1);
    }
    
    public int getPageSize(HttpServletRequest request)
    {
        
        return NumberUtils.toInt(request.getParameter(CommonConstant.PAGE_PARAM_ROWS), 10);
    }
    
    //排序字段
    protected String sortableSort(HttpServletRequest request)
    {
        return request.getParameter("sort");
    }
    
    //排序 desc asc
    protected String sortableOrder(HttpServletRequest request)
    {
        return request.getParameter("order");
    }
    
    /**
     * 获取国际化配置文件中属性值
     *
     * @param key
     *            属性的key
     * @return 属性的值
     */
    public String getProperty(String key)
    {
        return I18nUtils.getI18nMsg(key);
    }
    
    /**
     * 获取客户端请求
     */
    public HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    /**
     * 获取校验错误信息
     * @param result
     * @return
     */
    public Map<String, String> getErrors(BindingResult result)
    {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for(FieldError error : list)
        {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
    
    public synchronized static Long generateUUID()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("5");
        sb.append(System.currentTimeMillis() / DIVISOR);
        
        sb.append(numberFormat.format(seq));
        
        seq.compareAndSet(MAX, 0);
        seq.incrementAndGet();
        return Long.parseLong(sb.toString());
    }
    
    public Map<String, String> getValidateMap(String message)
    {
        Map<String, String> map = new HashMap<String, String>(2);
        if(StringUtils.isNotBlank(message))
        {
            String[] mess = message.split(GeneralConstant.DOUBLE_COLON);
            if(mess.length > 1)
            {
                map.put(GeneralConstant.FIELD_NAME, mess[0]);
                map.put(GeneralConstant.MESSAGE, mess[1]);
            }
            else
            {
                map.put(GeneralConstant.FIELD_NAME, GeneralConstant.FIELD_NAME);
                map.put(GeneralConstant.MESSAGE, message);
            }
        }
        else
        {
            map.put(GeneralConstant.FIELD_NAME, GeneralConstant.BLANK);
            map.put(GeneralConstant.MESSAGE, GeneralConstant.BLANK);
        }
        return map;
    }
    
    protected String getToken(HttpServletRequest request)
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String tokenId = "";
        if(cookies != null)
        {
            for(int i = 0; i < cookies.length; i++)
            {
                if("access_token".equals(cookies[i].getName()))
                {
                    tokenId = cookies[i].getValue();
                    break;
                }
            }
        }
        
        return tokenId;
    }
}

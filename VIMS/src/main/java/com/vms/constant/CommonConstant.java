package com.vms.constant;

/**
 * portal内通用的常量 Created by jihonghai on 2016/12/1.
 */
public interface CommonConstant
{
    
    /**
     * 页面跳转的url
     */
    String REDIRECT_URL = "redirectURL";
    
    String SESSION_LOCALE = "locale";
    
    String LATEST_URL = "latest_url";
    
    String ACCESS_TOKEN = "access_token";
    
    /**
     * 当前登录用户的session名称
     */
    String CURRENT_LOGIN_USER = "currentUser";
    
    /**
     * 当前登录用户所属供电单位的session名称
     */
    String CURRENT_ORGANIZATION = "currentOrganization";
    
    /**
     * 当前登录用户所属部门的session名称
     */
    String CURRENT_DEPT = "currentDept";
    
    String CURRENT_TODO_TASK_COUNT = "todoTaskCount";
    
    /**
     * 分页参数 page
     */
    String PAGE_PARAM_PAGE = "page";
    
    /**
     * 分页参数 rows
     */
    String PAGE_PARAM_ROWS = "rows";
    
    /**
     * 列表排序 升序、降序 参数名
     */
    String PAGE_PARAM_ORDER = "order";
    
    /**
     * 列表排序 升序
     */
    String PAGE_PARAM_ORDER_ASC = "asc";
    
    /**
     * 列表排序 降序
     */
    String PAGE_PARAM_ORDER_DESC = "desc";
    
    /**
     * 列表排序 排序字段
     */
    String PAGE_PARAM_ORDER_COL = "sort";
    
    /**
     * 分页查询返回的参数名 total
     */
    String PAGE_RESULT_TOTAL = "total";
    
    /**
     * 分页查询返回的参数名 rows
     */
    String PAGE_RESULT_ROWS = "rows";
    
    /**
     * 分页查询返回的状态码参数名 message
     */
    String PAGE_RESULT_MESSAGE = "message";
    
    /**
     * 当前货币单位
     */
    String MONETARY_UNIT = "monetaryUnit";
}

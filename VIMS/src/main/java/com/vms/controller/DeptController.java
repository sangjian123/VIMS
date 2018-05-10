package com.vms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vms.constant.CommonConstant;
import com.vms.constant.GeneralConstant;
import com.vms.model.Dept;
import com.vms.model.Page;
import com.vms.service.DeptService;
import com.vms.utils.SecurityLogUtils;
import com.vms.utils.ServiceUtils;

@Controller
@RequestMapping("/json")
public class DeptController extends BaseController {
    private static Logger securityLoger = LoggerFactory.getLogger ("securityLoger");
    
    
    @Autowired
    private DeptService deptService;
    
    
    /**
     * 部門管理页
     *
     * @return
     */
    @RequestMapping (value = "/dept", method = RequestMethod.GET)
    public String manager ()
    {
        return "/admin/dept";
    }
    
    
    /**
     * 权限列表
     *
     * @param request
     * @return
     */
    @RequestMapping (value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid (Dept dept, HttpServletRequest request)
    {
        Map <String, Object> map = new HashMap <String, Object> ();
        
        Page <Dept> page = new Page <Dept> (dept);
        
        String sortCol = request.getParameter (CommonConstant.PAGE_PARAM_ORDER_COL);
        String order = request.getParameter (CommonConstant.PAGE_PARAM_ORDER);
        
        if (StringUtils.isNotEmpty (sortCol) && StringUtils.isNotEmpty (order))
        {
            Map <String, Object> params = new HashMap <String, Object> ();
            params.put ("order", sortCol + GeneralConstant.SPACE + order);
            page.setConditions (params);
        }
        
        page.setPageNo (Integer.parseInt (request.getParameter (CommonConstant.PAGE_PARAM_PAGE)));
        page.setPageSize (Integer.parseInt (request.getParameter (CommonConstant.PAGE_PARAM_ROWS)));
        page = deptService.findDeptByPage (page);
        map.put ("rows", page.getResults ());
        map.put ("total", page.getTotalRecord ());
        return map;
    }
    
    /**
     * 添加部門页
     *
     * @return
     */
    @RequestMapping (value = "/addPage", method = RequestMethod.GET)
    public String addPage ()
    {
        return "/dept/deptAdd";
    }
    
    
    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add (@Valid Dept dept, HttpServletRequest request)
    {

        deptService.addDept (dept);
        
        String deptStr = dept.toString ();
        
        String className = this.getClass ().getSimpleName ();
        SecurityLogUtils.loggerLog (className, getCurrentUser (), ServiceUtils.getLikeClientIp (request),
            SecurityLogUtils.EVENT_ADD_ROLE, deptStr, SecurityLogUtils.OPER_SUCCESS, deptStr, securityLoger);
        return renderSuccess (super.getProperty ("oper_success"));

    }
    

}

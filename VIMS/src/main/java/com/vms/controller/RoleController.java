package com.vms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vms.constant.CommonConstant;
import com.vms.constant.GeneralConstant;
import com.vms.model.Page;
import com.vms.model.Resource;
import com.vms.model.Role;
import com.vms.model.Tree;
import com.vms.model.UserVo;
import com.vms.service.ResourceService;
import com.vms.service.RoleService;

/** 
* @author  zhangkaining 
* @date 2017年11月13日 下午2:39:06 
* @version 1.0   
*/
@Controller
@RequestMapping ("/role")
public class RoleController extends BaseController
{
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ResourceService resourceService;
    
    @RequestMapping ("")
    public String role()
    {
        return "/service/role/role";
    }
    
    @RequestMapping ("model")
    public String model()
    {
        return "/service/role/roleModel";
    }
    
    @RequestMapping ("grant")
    public String grant()
    {
        return "/service/role/roleGrant";
    }
    
    @RequestMapping ("/query")
    @ResponseBody
    public Object query(Role role, HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        Page<Role> page = new Page<Role>(role);
        
        String sortCol = request.getParameter(CommonConstant.PAGE_PARAM_ORDER_COL);
        String order = request.getParameter(CommonConstant.PAGE_PARAM_ORDER);
        
        if(StringUtils.isNotEmpty(sortCol) && StringUtils.isNotEmpty(order))
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("order", sortCol + GeneralConstant.SPACE + order);
            page.setConditions(params);
        }
        
        page.setPageNo(Integer.parseInt(request.getParameter(CommonConstant.PAGE_PARAM_PAGE)));
        page.setPageSize(Integer.parseInt(request.getParameter(CommonConstant.PAGE_PARAM_ROWS)));
        page = roleService.findRoleByPage(page);
        map.put("rows", page.getResults());
        map.put("total", page.getTotalRecord());
        return map;
        
    }
    
    @RequestMapping ("/editRole")
    @ResponseBody
    public Object editRole(HttpServletRequest request, @RequestBody Role role)
    {
        //规则校验
        if(role.getId() == null)
        {
            role.setSeq(Role.DEFAULT_SEQ);
            roleService.addRole(role);
        }
        else
        {
            roleService.updateRole(role);
        }
        
        return renderSuccess("success");
        
    }
    
    @RequestMapping ("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestBody Role role)
    {
        role = roleService.findRoleById(role.getId());
        if(role != null)
        {
            List<String> user = roleService.findUserIdListByRoleId(role.getId());
            if(user != null && user.size() > 0)
            {
                return renderError("角色已关联用户");
            }
            roleService.deleteRoleById(role.getId());
            roleService.deleteRoleResource(role.getId());
            return renderSuccess("success");
        }
        else
        {
            return renderError("操作失败");
        }
    }
    
    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping (value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVo userVo)
    {
        String[] roleIds = null;
        List<String> ids = null;
        if(null != userVo && StringUtils.isNotBlank(userVo.getRoleIds()))
        {
            roleIds = userVo.getRoleIds().split(",");
            ids = Arrays.asList(roleIds);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Tree> trees = roleService.findTree();
        if(null != ids && !ids.isEmpty())
        {
            for(Tree tree : trees)
            {
                if(ids.contains(String.valueOf(tree.getId())))
                {
                    tree.setChecked(true);
                }
            }
            
        }
        result.put("list", trees);
        return renderSuccess(result);
    }
    
    @RequestMapping ("/getTreeListByRoleId")
    @ResponseBody
    public Object getTreeListByDsId(HttpServletRequest request, HttpServletResponse response, @RequestBody Role role)
    {
        List<Resource> list = new ArrayList<Resource>();
        Map<String, Object> result = new HashMap<String, Object>();
        list = resourceService.allResource();
        List<Long> idList = roleService.findResourceIdListByRoleId(role.getId());
        for(Resource resource : list)
        {
            if(idList.contains(Long.parseLong(resource.getId())))
            {
                resource.setChecked(true);
            }
        }
        result.put("list", list);
        return renderSuccess(result);
    }
    
    @RequestMapping ("/saveTree")
    @ResponseBody
    public Object saveTree(HttpServletRequest request, HttpServletResponse response, @RequestBody Role role)
    {
        
        roleService.updateRoleResource(role.getId(), role.getResourceId());
        
        return renderSuccess("success");
    }
    
}

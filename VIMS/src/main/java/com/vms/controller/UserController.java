package com.vms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vms.constant.CommonConstant;
import com.vms.model.Page;
import com.vms.model.User;
import com.vms.model.UserPassword;
import com.vms.model.UserVo;
import com.vms.service.UserService;
import com.vms.utils.AesKeyUtils;
import com.vms.utils.CheckPwdInput;
import com.vms.utils.ConfigHolder;
import com.vms.utils.PasswordCheckUtils;
import com.vms.utils.PropertiesReader;
import com.vms.utils.ServiceUtils;
import com.vms.utils.UserPwdInputCache;

/**
 * @description：用户管理
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
@RequestMapping ("/user")
public class UserController extends BaseController
{
    
    private static Logger securityLoger = LoggerFactory.getLogger("securityLoger");
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping (value = "/manager")
    public String manager()
    {
        return "service/user/user";
    }
    
    @RequestMapping ("grant")
    public String grant()
    {
        return "/service/user/userGrant";
    }
    
    /**
     * 用户管理列表
     *
     * @param userVo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping (value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(UserVo userVo, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<UserVo> page = new Page<UserVo>(userVo);
        page.setPageNo(Integer.parseInt(request.getParameter("page")));
        page.setPageSize(Integer.parseInt(request.getParameter("rows")));
        
        page = userService.findUserByPage(page);
        map.put("rows", page.getResults());
        map.put("total", page.getTotalRecord());
        return map;
    }
    
    /**
     * 添加用户页
     *
     * @return
     */
    @RequestMapping (value = "/addPage")
    public String addPage(HttpServletRequest request)
    {
        return "service/user/userAdd";
    }
    
    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody UserVo userVo, HttpServletRequest request)
    {
        User u = userService.findUserByLoginName(userVo.getLoginname());
        if(u != null)
        {
            return renderError(super.getProperty("username_exist"));
        }
        String chkStr = PasswordCheckUtils.checkPassword(userVo.getLoginname(), userVo.getPassword());
        if(StringUtils.isNoneBlank(chkStr))
        {
            return renderError(chkStr);
        }
        try
        {
            String pwd = AesKeyUtils.encrypt(userVo.getPassword(), PropertiesReader.getUserPwdKey());
            userVo.setPassword(pwd);
        }
        catch (IOException e)
        {
            logger.error(super.getProperty("enPassword_fail") + "：{}", e);
            return renderError(super.getProperty("enPassword_fail"));
        }
        
        userVo.setUsertype(UserVo.USER_TYPE_USER);
        try
        {
            userService.addUser(userVo, getCurrentUser(), ServiceUtils.getLikeClientIp(request), securityLoger);
        }
        catch (Exception e)
        {
            logger.error(super.getProperty("oper_fail"), e);
            return renderError(super.getProperty("oper_fail"));
        }
        
        return renderSuccess(super.getProperty("oper_success"));
    }
    
    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping ("/edit")
    @ResponseBody
    public Object edit(@RequestBody UserVo userVo, HttpServletRequest request)
    {
        if(StringUtils.isNotBlank(userVo.getPassword()))
        {
            String chkStr = PasswordCheckUtils.checkPassword(userVo.getLoginname(), userVo.getPassword());
            if(StringUtils.isNoneBlank(chkStr))
            {
                return renderError(chkStr);
            }
            try
            {
                String pwd = AesKeyUtils.encrypt(userVo.getPassword(), PropertiesReader.getUserPwdKey());
                userVo.setPassword(pwd);
            }
            catch (IOException e)
            {
                logger.error(super.getProperty("enPassword_fail") + "：{}", e);
                return renderError(super.getProperty("enPassword_fail"));
            }
        }
        UserVo oldUserVo = userService.findUserVoById(userVo.getId());
        try
        {
            userService.updateUser(userVo, getCurrentUser(), ServiceUtils.getLikeClientIp(request), securityLoger);
        }
        catch (Exception e)
        {
            logger.error("updateUser error:", e);
            return renderError(super.getProperty("oper_fail"));
        }
        
        return renderSuccess(super.getProperty("edit_success"));
    }
    
    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping (value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage()
    {
        return "service/user/userEditPwd";
    }
    
    @RequestMapping ("/getUserName")
    @ResponseBody
    public Object getUserName()
    {
        return renderSuccess(getCurrentUser().getName());
    }
    
    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping ("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(@RequestBody UserPassword userPassword, HttpServletRequest request)
    {
        String oldPwd = userPassword.getOldPwd();
        String pwd = userPassword.getNewPwd();
        User curUser = getCurrentUser();
        String checkPwdError = ConfigHolder.getCfg(CheckPwdInput.CHECK_ERROR);
        String pwdInput = CheckPwdInput.checkPwdInput(curUser.getLoginname());
        if("true".equals(checkPwdError))
        {
            if(StringUtils.isNotBlank(pwdInput) && !CheckPwdInput.EXIST_INPUT_FALG.equals(pwdInput))
            {
                return renderError(pwdInput);
            }
        }
        String tempStr = "";
        String newStr = "";
        try
        {
            tempStr = AesKeyUtils.encrypt(oldPwd, PropertiesReader.getUserPwdKey());
            newStr = AesKeyUtils.encrypt(pwd, PropertiesReader.getUserPwdKey());
        }
        catch (IOException e)
        {
            logger.error(super.getProperty("enPassword_fail") + "：{}", e);
            return renderError(super.getProperty("enPassword_fail"));
        }
        
        if(!curUser.getPassword().equals(tempStr))
        {
            CheckPwdInput.addPwdErrorTimes(curUser.getLoginname());
            return renderError(super.getProperty("old_password_error"));
        }
        else
        {
            if("true".equals(checkPwdError))
            {
                if(CheckPwdInput.EXIST_INPUT_FALG.equals(pwdInput))
                {
                    UserPwdInputCache.removeCache(curUser.getLoginname());
                }
            }
        }
        if(pwd.equals(oldPwd))
        {
            return renderError(super.getProperty("new_password_error"));
        }
        String chkStr = PasswordCheckUtils.checkPassword(curUser.getLoginname(), pwd);
        if(StringUtils.isNoneBlank(chkStr))
        {
            return renderError(chkStr);
        }
        /*if (userService.checkLastSamePwd (curUser.getId (), newStr))
        {
            return renderError (super.getProperty ("new_password_last_same"));
        }*/
        userService.updateUserPwdById(curUser.getId(), newStr, curUser.getPassword());
        curUser.setPwdFirstUpdate("1");
        getSession().setAttribute(CommonConstant.CURRENT_LOGIN_USER, curUser);
        String className = this.getClass().getSimpleName();
        return renderSuccess(super.getProperty("update_password_success"));
    }
    
    /**
     * 选择用户页
     *
     * @return
     */
    @RequestMapping ("/choseUser")
    public String choseUser()
    {
        return "service/user/userChose";
    }
    
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping ("/delete")
    @ResponseBody
    public Object delete(@RequestBody UserVo userVo, HttpServletRequest request)
    {
        String id = userVo.getId();
        User curUser = this.getCurrentUser();
        if(curUser != null && curUser.getId() != null && curUser.getId().equals(id))
        {
            return renderError(super.getProperty("system_user_del_tip"));
        }
        UserVo user = userService.findUserVoById(id);
        userService.deleteUserById(id);
        
        String userStr = user == null ? id : user.toString();
        String className = this.getClass().getSimpleName();
        return renderSuccess(super.getProperty("delete_dept_success"));
    }
    
    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping ("/editPage")
    public String editPage(HttpServletRequest request)
    {
        return "service/user/userEdit";
    }
    
}

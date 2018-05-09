package com.vms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vms.constant.CommonConstant;
import com.vms.constant.Constants;
import com.vms.model.Result;
import com.vms.model.ShiroUser;
import com.vms.model.User;
import com.vms.service.ResourceService;
import com.vms.service.RoleService;
import com.vms.service.UserService;
import com.vms.utils.AesKeyUtils;
import com.vms.utils.CheckPwdInput;
import com.vms.utils.ConfigHolder;
import com.vms.utils.I18nUtils;
import com.vms.utils.PropertiesReader;
import com.vms.utils.UserPwdInputCache;

/**
 * @description：登录退出
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
public class LoginController extends BaseController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    private static final String VARIF_CODE = "verificationCodeType";
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ResourceService resourceService;
    
    /**
     * 登陆跳转（登陆超时返回首页并给出提示信息）
     * @param request 请求
     * @param response 响应
     * @return 登陆页面
     */
    @RequestMapping (value = "/login")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mod = new ModelAndView("login/login");
        boolean timeout = Boolean.parseBoolean(request.getParameter(Constants.Strings.TIMEOUT));
        //        mod.addObject (Constants.Strings.TIMEOUT, timeout ? new Result (Constants.Exception.EXCEPTION_1000002) : null);
        //        mod.addObject (Constants.Strings.HISTORYUSERS, CookieUtils.getCookieList (request));
        if(SecurityUtils.getSubject().isAuthenticated())
        {
            return new ModelAndView("home/home");
        }
        return mod;
    }
    
    /**
     * 登陆请求跳转主页
     * @param request 请求
     * @param response 响应
     * @param user 用户信息
     * @return Result 
     */
    @RequestMapping (value = "/doLogin")
    @ResponseBody
    public Object doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user)
    {
        String username = user.getLoginname();
        String password = user.getPassword();
        
        Result result = null;
        LOGGER.info("POST请求登录");
        if(StringUtils.isBlank(username))
        {
            return renderError(super.getProperty("username_empty"));
        }
        if(StringUtils.isBlank(password))
        {
            return renderError(super.getProperty("password_empty"));
        }
        
        if(StringUtils.isBlank(user.getValidateCode()) && !StringUtils.isBlank(user.getCode()))
        {
            return renderError(super.getProperty("verifiCode_empty"));
        }
        
        if(!StringUtils.isBlank(user.getCode()) && !user.getCode().equalsIgnoreCase(user.getValidateCode()))
        {
            return renderError(super.getProperty("verifiCode_error"));
        }
        
        String checkError = ConfigHolder.getCfg(CheckPwdInput.CHECK_ERROR);
        String pwdInput = CheckPwdInput.checkPwdInput(username);
        if("true".equals(checkError))
        {
            if(StringUtils.isNotBlank(pwdInput) && !CheckPwdInput.EXIST_INPUT_FALG.equals(pwdInput))
            {
                return renderError(pwdInput);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        String input = "";
        try
        {
            input = AesKeyUtils.encrypt(password, PropertiesReader.getUserPwdKey());
        }
        catch (IOException e)
        {
            logger.error(super.getProperty("enPassword_fail") + "：{}", e.getMessage());
            return renderError(super.getProperty("enPassword_fail"));
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, input.toCharArray());
        token.setRememberMe(true);
        try
        {
            subject.login(token);
            ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
            
            // 登录校验成功后，将用户存到session中
            User currentUser = userService.findUserById(shiroUser.id);
            userService.checkPwdValidTime(currentUser);
            subject.getSession().setAttribute(CommonConstant.CURRENT_LOGIN_USER, currentUser);
            subject.getSession().setAttribute(CommonConstant.SESSION_LOCALE, I18nUtils.getCurrentLocale());
            
            if("true".equals(checkError))
            {
                if(CheckPwdInput.EXIST_INPUT_FALG.equals(pwdInput))
                {
                    UserPwdInputCache.removeCache(username);
                }
            }
            request.getSession().setAttribute(Constants.Strings.USER, user);
            
        }
        catch (UnknownAccountException e)
        {
            LOGGER.error("账号不存在：{}", e);
            return renderError(super.getProperty("account_pwd_error"));
        }
        catch (DisabledAccountException e)
        {
            LOGGER.error("账号未启用：{}", e);
            return renderError(super.getProperty("account_limited"));
        }
        catch (IncorrectCredentialsException e)
        {
            LOGGER.error("error password：{}", e);
            CheckPwdInput.addPwdErrorTimes(username);
            return renderError(super.getProperty("account_pwd_error"));
        }
        catch (RuntimeException e)
        {
            LOGGER.error("未知错误,请联系管理员：{}", e);
            return renderError(super.getProperty("unknown_error"));
        }
        return renderSuccess();
        
    }
    
    /**
     * 登出系统
     * @param request 请求
     * @param response 响应
     */
    @RequestMapping (value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().removeAttribute(Constants.Strings.USER);
        SecurityUtils.getSubject().logout();
        
        return "login/login";
    }
    
    /**
     * 跳转主页
     * @param request 请求
     * @param response 响应
     * @return 主页
     */
    @RequestMapping (value = "/home")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        return "home/home";
    }
    
    /**
     * 跳转修改密码页面
     * @param request 请求
     * @param response 响应
     * @return 主页
     */
    @RequestMapping (value = "/editPassWord")
    public String editPassWord(HttpServletRequest request, HttpServletResponse response)
    {
        return "service/user/editPassWord";
    }
    
    /**
     * 测试页面
     * @param request 请求
     * @param response 响应
     * @return 主页
     */
    @RequestMapping (value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response)
    {
        return "service/test";
    }
    
    /**
     * 流程图页面
     * @param request 请求
     * @param response 响应
     * @return 主页
     */
    @RequestMapping (value = "/jsPlumb")
    public String jsPlumb(HttpServletRequest request, HttpServletResponse response)
    {
        return "service/jsplumb";
    }
    
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping (value = "/")
    public String index()
    {
        return "redirect:/home";
    }
    
    /**
     * "client_id=test&client_secret=test&grant_type=password&scope=read+write&username=xunan&password=!QAZ2wsx";
     * @param username
     * @param password
     * @return
     */
    public String assemblePostDataJsonStr(String username, String password)
    {
        // fixMe.
        String clientId = ConfigHolder.getCfg("AUTH_CENTER_CLIENT");
        String clientSecret = ConfigHolder.getCfg("AUTH_CENTER_SECERT");
        ;
        StringBuffer sb = new StringBuffer();
        sb.append("client_id=").append(clientId).append("&client_secret=").append(clientSecret).append("&grant_type=password")
            .append("&scope=read+write").append("&username=").append(username).append("&password=").append(password);
        return sb.toString().trim();
    }
}

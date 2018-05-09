package com.vms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.constant.GeneralConstant;
import com.vms.mapper.DeptAdminMapper;
import com.vms.mapper.UserMapper;
import com.vms.mapper.UserRoleMapper;
import com.vms.model.Page;
import com.vms.model.User;
import com.vms.model.UserPasswordHis;
import com.vms.model.UserRole;
import com.vms.model.UserVo;
import com.vms.service.ShareService;
import com.vms.service.UserPasswordHisService;
import com.vms.service.UserService;
import com.vms.utils.ConfigHolder;
import com.vms.utils.DateUtils;
import com.vms.utils.ServiceException;
import com.vms.utils.ShareServiceId;

@Service
public class UserServiceImpl implements UserService
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private ShareService shareService;
    
    @Autowired
    private DeptAdminMapper deptAdminMapper;
    
    @Autowired
    private UserPasswordHisService userPasswordHisService;
    
    @Override
    public User findUserByLoginName(String username)
    {
        return userMapper.findUserByLoginName(username);
    }
    
    @Override
    public User findUserById(String id)
    {
        User user = userMapper.findUserById(id);
        return user;
    }
    
    @Override
    public Page<UserVo> findUserByPage(Page<UserVo> page)
    {
        try
        {
            page.setResults(userMapper.findUserByPage(page));
        }
        catch (Exception e)
        {
            LOGGER.error("error", e);
        }
        
        return page;
    }
    
    @Override
    public UserVo findUserVoById(String id)
    {
        return userMapper.findUserVoById(id);
    }
    
    @Override
    public boolean checkLastSamePwd(String userId, String password)
    {
        List<UserPasswordHis> hisList = userPasswordHisService.findUserPasswordByUserId(userId);
        if(CollectionUtils.isNotEmpty(hisList))
        {
            int size = hisList.size();
            int i = 0;
            for(; i < size; i++)
            {
                if(i < 5 && hisList.get(i).getPassword().equals(password))
                {
                    return true;
                }
                else if(i >= 5)
                {
                    userPasswordHisService.deleteById(hisList.get(i).getId());
                }
            }
        }
        return false;
    }
    
    @Override
    public void checkPwdValidTime(User user)
    {
        int days = 90;
        try
        {
            days = Integer.valueOf(ConfigHolder.getCfg("PASSWORD_VALID_DAYS"));
        }
        catch (Exception e)
        {
            days = 90;
        }
        if(StringUtils.isBlank(user.getPwdFirstUpdate()))
        {
            user.setPwdFirstUpdate("0");
            return;
        }
        if("0".equals(user.getPwdFirstUpdate()))
        {
            return;
        }
        if(user.getPwdUpdateTime() == null || !DateUtils.isBelongTime(user.getPwdUpdateTime().getTime(), days * 86400l))
        {
            user.setPwdFirstUpdate("2");
        }
    }
    
    @Override
    public User finActiduserbyUserName(String Name)
    {
        return userMapper.finActiduserbyUserName(Name);
    }
    
    private void copyUserProperties(User user, UserVo userVo)
    {
        user.setId(userVo.getId());
        user.setUsertype(userVo.getUsertype());
        user.setLoginname(userVo.getLoginname());
        user.setName(userVo.getName());
        user.setPassword(userVo.getPassword());
        user.setSex(userVo.getSex());
        user.setStatus(userVo.getStatus());
        user.setPhone(userVo.getPhone());
        user.setOrganizationId(userVo.getOrganizationId());
        user.setpOrganizationId(userVo.getpOrganizationId());
        user.setCreatedate(userVo.getCreatedate());
    }
    
    /**
     * 查询用户数量
     *
     * @param user
     * @return
     */
    public Integer countUser(User user)
    {
        return userMapper.countUser(user);
    }
    
    @Override
    public void editPersonInfo(UserVo userVo)
    {
        userMapper.editPersonInfo(userVo);
        
    }
    
    @Override
    public void addUser(UserVo userVo, User curUser, String client, Logger logger)
    {
        userVo.setId(ShareServiceId.generateUUID() + "");
        userVo.setCreatedate(new Date());
        User user = new User();
        try
        {
            copyUserProperties(user, userVo);
        }
        catch (Exception e)
        {
            LOGGER.error("类转换异常：{}", e);
            throw new ServiceException("copyProperties error ：", e);
        }
        user.setPwdFirstUpdate("0");
        user.setPwdUpdateTime(userVo.getCreatedate());
        userMapper.insert(user);
        
        String id = userVo.getId();
        String[] roles = {};
        if(StringUtils.isNotEmpty(userVo.getRoleIds()))
        {
            roles = userVo.getRoleIds().split(GeneralConstant.COMMA);
        }
        UserRole userRole = new UserRole();
        for(String string : roles)
        {
            userRole.setId(ShareServiceId.generateUUID());
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
        
    }
    
    @Override
    public void updateUser(UserVo userVo, User curUser, String client, Logger logger)
    {
        
        User user = new User();
        try
        {
            copyUserProperties(user, userVo);
        }
        catch (Exception e)
        {
            LOGGER.error("类转换异常：{}", e);
            throw new ServiceException("copyProperties error ：", e);
        }
        User oldUser = findUserById(userVo.getId());
        if(StringUtils.isNotBlank(userVo.getPassword()) && !oldUser.getPassword().equals(userVo.getPassword()))
        {
            user.setPwdFirstUpdate("0");
            user.setPwdUpdateTime(new Date());
        }
        userMapper.updateUser(user);
        String id = userVo.getId();
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if(CollectionUtils.isNotEmpty(userRoles))
        {
            for(UserRole userRole : userRoles)
            {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
        
        String[] roles = {};
        if(StringUtils.isNotEmpty(userVo.getRoleIds()))
        {
            roles = userVo.getRoleIds().split(GeneralConstant.COMMA);
        }
        UserRole userRole = new UserRole();
        for(String string : roles)
        {
            userRole.setId(ShareServiceId.generateUUID());
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
        
    }
    
    @Override
    public void updateUserPwdById(String userId, String pwd, String oldPwd)
    {
        User user = new User();
        user.setId(userId);
        user.setPassword(pwd);
        user.setPwdFirstUpdate("1");
        user.setPwdUpdateTime(new Date());
        userMapper.updateUserPwdById(user);
        /*if (!checkLastSamePwd (userId, oldPwd))
        {
            userPasswordHisService.insert (userId, oldPwd);
        }
        userPasswordHisService.insert (userId, pwd);*/
    }
    
    @Override
    public void deleteUserById(String id)
    {
        userMapper.deleteById(id);
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if(CollectionUtils.isNotEmpty(userRoles))
        {
            for(UserRole userRole : userRoles)
            {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
        //userPasswordHisService.deleteByUserId (id);
    }
    
}

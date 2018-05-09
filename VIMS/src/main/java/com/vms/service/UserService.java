package com.vms.service;

import org.slf4j.Logger;

import com.vms.model.Page;
import com.vms.model.User;
import com.vms.model.UserVo;

/**
 * @description：用户管理
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public interface UserService
{
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findUserByLoginName(String username);
    
    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    User findUserById(String id);
    
    User finActiduserbyUserName(String Name);
    
    /**
     * 用户列表
     *
     * @param page
     */
    Page<UserVo> findUserByPage(Page<UserVo> page);
    
    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(String id);
    
    /**
     * 查询用户数量
     *
     * @param user
     */
    Integer countUser(User user);
    
    /**
     * 验证最近密码是否相同
     *
     * @param userId
     * @param password
     */
    boolean checkLastSamePwd(String userId, String password);
    
    /**
     * 验证密码是否超期
     *
     * @param user
     */
    void checkPwdValidTime(User user);
    
    /**
     * 维护个人信息页面
     * @param userVo
     */
    void editPersonInfo(UserVo userVo);
    
    /**
     * 添加用户
     *
     * @param userVo
     */
    void addUser(UserVo userVo, User curUser, String client, Logger logger);
    
    /**
     * 修改用户
     *
     * @param userVo
     */
    void updateUser(UserVo userVo, User curUser, String client, Logger logger);
    
    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(String userId, String pwd, String oldPwd);
    
    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(String id);
    
}

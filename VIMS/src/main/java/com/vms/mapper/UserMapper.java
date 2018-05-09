package com.vms.mapper;

import java.util.List;

import com.vms.model.Page;
import com.vms.model.PageInfo;
import com.vms.model.User;
import com.vms.model.UserVo;

public interface UserMapper
{
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(String id);
    
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(User user);
    
    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);
    
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
    
    /**
     * 用户列表
     *
     * @param pageInfo
     * @return
     */
    List findUserPageCondition(PageInfo pageInfo);
    
    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);
    
    /**
     * 修改用户密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(User user);
    
    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(String id);
    
    /**
     * 分页
     * 
     * @param name
     * @return
     */
    
    List<UserVo> findUserByPage(Page<UserVo> page);
    
    User finActiduserbyUserName(String name);
    
    /**
     * 统计用户数量
     *
     * @return 统计数量
     */
    Integer countUser(User user);
    
    /**
     * 统计用户数量
     *
     * @return 统计数量
     */
    long countLongUser();
    
    /**
     * 分页查询系统用户
     * 
     * @param page
     * @return
     */
    List<User> findSysUserByPage(Page<User> page);
    
    void editPersonInfo(UserVo userVo);
    
    List<String> findDataSourceIdByUserId(String id);
    
    List<String> findCubeIdByUserId(String id);
    
    void deleteUserCubeByUserId(String id);
    
    void deleteUserDataSourceByUserId(String id);
    
}
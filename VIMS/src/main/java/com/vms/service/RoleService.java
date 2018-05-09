package com.vms.service;

import java.util.List;

import com.vms.model.Page;
import com.vms.model.Resource;
import com.vms.model.Role;
import com.vms.model.Tree;

/**
 * @description：权限管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public interface RoleService
{
    /**
     * 查询权限列表
     *
     * @param page 查询参数
     */
    Page<Role> findRoleByPage(Page page);
    
    /**
     * 查询权限树
     *
     * @return
     */
    List<Tree> findTree();
    
    /**
     * 根据id查询权限
     *
     * @param id
     * @return
     */
    Role findRoleById(Long id);
    
    /**
     * 根据权限id查询资源集合
     *
     * @param id
     * @return
     */
    List<Long> findResourceIdListByRoleId(Long id);
    
    /**
     * 更新权限和资源的关联关系
     *
     * @param id
     * @param resourceIds
     */
    void updateRoleResource(Long id, String resourceIds);
    
    /**
     * 根据用户查询id查询权限集合
     *
     * @param userId
     * @return
     */
    List<Long> findRoleIdListByUserId(String userId);
    
    /**
     * 根据权限查询id查询资源路径集合
     *
     * @param roleId
     * @return
     */
    List<String> findResourcesByRoleId(Long roleId);
    
    /**
     * 根据用户ID查询该用户的所有角色
     * @param userId
     * @return
     */
    List<Role> queryRolesByUserId(String userId);
    
    /**
     * 根据用户查询id查询权限集合
     *
     * @param userId
     * @return
     */
    List<String> findUserIdListByRoleId(Long roleId);
    
    /**
     * 根据权限查询id查询资源路径集合
     *
     * @param roleId
     * @return
     */
    List<Resource> findResourcesByRole(Long roleId);
    
    /**
     * 根据权限查询id查询资源路径集合  限定首页小面板展示
     *
     * @param roleId
     * @return
     */
    List<Resource> findResourcesByRoleAndPanel(Long roleId, String panelIds);
    
    /**
     * 限定首页小面板展示
     *
     * @param panelIds
     * @return
     */
    List<Resource> findResourcesByPanel(String panelIds);
    
    /**
     * 获取用户关联角色中 是否进入个性化首页字符串集合
     * @param userId
     * @return
     */
    String getRoleIsIndexList(String userId);
    
    /**
     * 添加权限
     *
     * @param role
     */
    void addRole(Role role);
    
    void updateRole(Role role);
    
    /**
     * 根据id删除权限
     *
     * @param id
     */
    void deleteRoleById(Long id);
    
    void deleteRoleResource(Long id);
}

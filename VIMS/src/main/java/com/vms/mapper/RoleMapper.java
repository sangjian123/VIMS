package com.vms.mapper;

import java.util.List;

import com.vms.model.Page;
import com.vms.model.PageInfo;
import com.vms.model.Resource;
import com.vms.model.Role;

public interface RoleMapper
{
    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    int insert(Role role);
    
    /**
     * 查询角色列表
     *
     * @param pageInfo
     * @return
     */
    List<Role> findRolePageCondition(PageInfo pageInfo);
    
    /**
     * 查询角色列表
     *
     * @param pageInfo
     * @return
     */
    List<Role> findRoleByPage(Page<Role> page);
    
    /**
     * 角色统计
     *
     * @param pageInfo
     * @return
     */
    int findRolePageCount(PageInfo pageInfo);
    
    /**
     * 角色列表
     *
     * @return
     */
    List<Role> findRoleAll();
    
    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    Role findRoleById(Long id);
    
    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    int updateRole(Role role);
    
    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    int deleteRoleById(Long id);
    
    /**
     * 根据角色查询资源id列表
     *
     * @param id
     * @return
     */
    List<Long> findResourceIdListByRoleId(Long id);
    
    /**
     * 根据角色id查询资源角色关联id列表
     *
     * @param
     * @return
     */
    List<Long> findRoleResourceIdListByRoleId(Long id);
    
    /**
     * 根据角色id查询资源id、链接列表
     *
     * @param id
     * @return
     */
    List<String> findResourcesByRoleId(Long id);
    
    /**
     * 查询用户角色列表
     * 
     * @param userId
     *            用戶Id
     * @return List<Role>
     * @author tfl
     */
    List<Role> findRoleByUserId(String userId);
    
    /**
     * 查询用户角色列表
     * 
     * @param userId
     *            用戶Id
     * @return List<Role>
     * @author tfl
     */
    List<Role> queryRolesByUserId(String userId);
    
    /**
     * 角色统计
     *
     * @return 角色数量
     */
    long countRole();
    
    /**
     * 通过角色名查询角色
     * 
     * @param roleName
     *            角色名称
     * @return List<Role>
     * @author tfl
     */
    List<Role> findRoleByName(String roleName);
    
    String getRoleIsIndexString(String userId);
    
    /**
     * 查询角色下的菜单列表
     *
     * @param i
     * @return
     */
    List<Resource> findResourceIdListByRoleIdAndType(Long i);
}
package com.vms.mapper;

import java.util.List;
import java.util.Map;

import com.vms.model.Resource;
import com.vms.model.RoleResource;

public interface RoleResourceMapper
{
    /**
     * 添加角色资源关联
     *
     * @param roleResource
     * @return
     */
    int insert(RoleResource roleResource);
    
    /**
     * 根据角色id查询角色资源关联列表
     *
     * @param id
     * @return
     */
    List<RoleResource> findRoleResourceIdListByRoleId(Long id);
    
    /**
     * 删除角色资源关联关系
     *
     * @param roleResourceId
     * @return
     */
    int deleteById(Long roleResourceId);
    
    /**
     * 删除角色资源关联关系
     *
     * @param roleResourceId
     * @return
     */
    int deleteByRoleId(Long roleId);
    
    /**
     * 根据角色id查询角色资源关联列表
     *
     * @param id
     * @return
     */
    List<Resource> findRoleResourceByRoleId(Long id);
    
    /**
     * 根据角色id查询角色资源关联列表
     *
     * @param id
     * @return
     */
    List<Resource> findRoleResourceByRoleIdAndPanel(Map<String, Object> param);
    
    /**
     * 小面板查询
     *
     * @param id
     * @return
     */
    List<Resource> findRoleResourceByPanel(Map<String, Object> param);
}
package com.vms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.mapper.RoleMapper;
import com.vms.mapper.RoleResourceMapper;
import com.vms.mapper.UserRoleMapper;
import com.vms.model.Page;
import com.vms.model.Resource;
import com.vms.model.Role;
import com.vms.model.RoleResource;
import com.vms.model.Tree;
import com.vms.service.RoleService;
import com.vms.service.ShareService;
import com.vms.utils.ServiceException;
import com.vms.utils.UUIDUtils;

@Service
public class RoleServiceImpl implements RoleService
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private ShareService shareService;
    
    @Override
    public Page<Role> findRoleByPage(Page page)
    {
        page.setResults(roleMapper.findRoleByPage(page));
        return page;
    }
    
    @Override
    public List<Tree> findTree()
    {
        List<Tree> trees = new ArrayList<Tree>();
        List<Role> roles = roleMapper.findRoleAll();
        for(Role role : roles)
        {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());
            
            trees.add(tree);
        }
        return trees;
    }
    
    @Override
    public Role findRoleById(Long id)
    {
        return roleMapper.findRoleById(id);
    }
    
    @Override
    public List<Long> findResourceIdListByRoleId(Long id)
    {
        return roleMapper.findResourceIdListByRoleId(id);
    }
    
    @Override
    public void updateRoleResource(Long id, String resourceIds)
    {
        // 先删除后添加,有点爆力
        roleResourceMapper.deleteByRoleId(id);
        String[] resources = resourceIds.split(",");
        RoleResource roleResource = new RoleResource();
        for(String string : resources)
        {
            if(!string.isEmpty())
            {
                roleResource.setId(UUIDUtils.generate16Long());
                roleResource.setRoleId(id);
                roleResource.setResourceId(Long.parseLong(string));
                roleResourceMapper.insert(roleResource);
            }
        }
    }
    
    @Override
    public void deleteRoleResource(Long id)
    {
        // 先删除后添加,有点爆力
        roleResourceMapper.deleteByRoleId(id);
        
    }
    
    @Override
    public List<Long> findRoleIdListByUserId(String userId)
    {
        return userRoleMapper.findRoleIdListByUserId(userId);
    }
    
    @Override
    public List<String> findResourcesByRoleId(Long roleId)
    {
        return roleMapper.findResourcesByRoleId(roleId);
    }
    
    @Override
    public List<Role> queryRolesByUserId(String userId)
    {
        return roleMapper.queryRolesByUserId(userId);
    }
    
    @Override
    public List<String> findUserIdListByRoleId(Long roleId)
    {
        return userRoleMapper.findUserIdListByRoleId(roleId);
    }
    
    @Override
    public List<Resource> findResourcesByRole(Long roleId)
    {
        return roleResourceMapper.findRoleResourceByRoleId(roleId);
    }
    
    @Override
    public List<Resource> findResourcesByRoleAndPanel(Long roleId, String panelIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ROLEID", roleId);
        param.put("PANELIDS", panelIds);
        return roleResourceMapper.findRoleResourceByRoleIdAndPanel(param);
    }
    
    @Override
    public String getRoleIsIndexList(String userId)
    {
        return roleMapper.getRoleIsIndexString(userId);
    }
    
    @Override
    public List<Resource> findResourcesByPanel(String panelIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("PANELIDS", panelIds);
        return roleResourceMapper.findRoleResourceByPanel(param);
    }
    
    @Override
    public void addRole(Role role)
    {
        role.setId(UUIDUtils.generate16Long());
        int insert = roleMapper.insert(role);
        if(insert != 1)
        {
            LOGGER.warn("插入失败，参数：{}", role.toString());
            throw new ServiceException("插入失败");
        }
    }
    
    @Override
    public void updateRole(Role role)
    {
        int update = roleMapper.updateRole(role);
        if(update != 1)
        {
            LOGGER.warn("更新失败，参数：{}", role.toString());
            throw new ServiceException("更新失败");
        }
    }
    
    @Override
    public void deleteRoleById(Long id)
    {
        int update = roleMapper.deleteRoleById(id);
        if(update != 1)
        {
            LOGGER.warn("删除失败，id：{}", id);
            throw new ServiceException("删除失败");
        }
    }
}

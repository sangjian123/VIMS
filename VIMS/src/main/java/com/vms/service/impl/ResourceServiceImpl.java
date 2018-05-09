package com.vms.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.mapper.ResourceMapper;
import com.vms.mapper.RoleMapper;
import com.vms.mapper.UserRoleMapper;
import com.vms.model.Resource;
import com.vms.model.ResourceTree;
import com.vms.model.User;
import com.vms.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService
{
    @Autowired
    private ResourceMapper resourceMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public List<ResourceTree> queryResourceTree(User user)
    {
        
        Set<Resource> list = new HashSet<Resource>();
        List<Long> roleIdList = userRoleMapper.findRoleIdListByUserId(user.getId());
        for(Long i : roleIdList)
        {
            List<Resource> resourceIdLists = roleMapper.findResourceIdListByRoleIdAndType(i);
            for(Resource resource : resourceIdLists)
            {
                list.add(resource);
            }
        }
        
        return parseResourceList(list);
    }
    
    private List<ResourceTree> parseResourceList(Set<Resource> list)
    {
        
        List<ResourceTree> trees = new ArrayList<ResourceTree>();
        for(Resource resource : list)
        {
            if(resource != null && StringUtils.isEmpty(resource.getPid()))
            {
                ResourceTree treeOne = new ResourceTree();
                treeOne.setId(resource.getId());
                treeOne.setName(resource.getName());
                List<ResourceTree> tree = new ArrayList<ResourceTree>();
                for(Resource resourceTwo : list)
                {
                    if(StringUtils.isNotEmpty(resourceTwo.getPid()) && resource.getId().equals(resourceTwo.getPid()))
                    {
                        ResourceTree treeTwo = new ResourceTree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setName(resourceTwo.getName());
                        treeTwo.setUrl(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                }
                treeOne.setList(tree);
                trees.add(treeOne);
            }
        }
        Collections.sort(trees);
        return trees;
    }
    
    @Override
    public List<Resource> allResource()
    {
        return resourceMapper.findResourceAll();
    }
}

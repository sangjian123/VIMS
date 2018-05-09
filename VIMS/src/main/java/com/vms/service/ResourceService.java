package com.vms.service;

import java.util.List;

import com.vms.model.Resource;
import com.vms.model.ResourceTree;
import com.vms.model.User;

public interface ResourceService
{
    
    /**
     * 查询菜单列表
     *
     * @return
     */
    List<ResourceTree> queryResourceTree(User user);
    
    List<Resource> allResource();
    
}

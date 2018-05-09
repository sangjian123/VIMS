package com.vms.mapper;

import java.util.List;

import com.vms.model.UserRole;

public interface UserRoleMapper
{
    
    int insert(UserRole userRole);
    
    int updateByPrimaryKeySelective(UserRole userRole);
    
    int deleteById(Long id);
    
    List<UserRole> findUserRoleByUserId(String userId);
    
    List<Long> findRoleIdListByUserId(String userId);
    
    List<String> findUserIdListByRoleId(Long userId);
}
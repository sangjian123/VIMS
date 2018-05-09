package com.vms.mapper;

import java.util.List;

import com.vms.model.UserPasswordHis;

public interface UserPasswordHisMapper
{
    
    int insert(UserPasswordHis userRole);
    
    int deleteById(Long id);
    
    int deleteByUserId(String userId);
    
    List<UserPasswordHis> findUserPasswordByUserId(String userId);
    
}
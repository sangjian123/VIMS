package com.vms.service;

import java.util.List;

import com.vms.model.UserPasswordHis;

public interface UserPasswordHisService
{
    
    int insert(String userId, String password);
    
    int deleteById(Long id);
    
    int deleteByUserId(String userId);
    
    List<UserPasswordHis> findUserPasswordByUserId(String userId);
    
}
package com.vms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.mapper.UserPasswordHisMapper;
import com.vms.model.UserPasswordHis;
import com.vms.service.UserPasswordHisService;
import com.vms.utils.UUIDUtils;

@Service
public class UserPasswordHisServiceImpl implements UserPasswordHisService
{
    
    @Autowired
    private UserPasswordHisMapper userPasswordHisMapper;
    
    @Override
    public int insert(String userId, String password)
    {
        UserPasswordHis userPwd = new UserPasswordHis();
        userPwd.setId(UUIDUtils.generate16Long());
        userPwd.setUserId(userId);
        userPwd.setPassword(password);
        userPwd.setUpdateTime(new Date());
        return userPasswordHisMapper.insert(userPwd);
    }
    
    @Override
    public int deleteById(Long id)
    {
        return userPasswordHisMapper.deleteById(id);
    }
    
    @Override
    public int deleteByUserId(String userId)
    {
        return userPasswordHisMapper.deleteByUserId(userId);
    }
    
    @Override
    public List<UserPasswordHis> findUserPasswordByUserId(String userId)
    {
        return userPasswordHisMapper.findUserPasswordByUserId(userId);
    }
    
}

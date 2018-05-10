package com.vms.service;

import com.vms.model.Dept;
import com.vms.model.Page;

public interface DeptService
{
    /**
     * 查询部門列表
     *
     * @param page 查询参数
     */
    Page<Dept> findDeptByPage(Page<Dept> page);
    
    /**
     * 添加部門
     *
     * @param dept
     */
    void addDept(Dept dept);
    
    /**
     * 根据deptCode删除部門
     *
     * @param deptCode
     */
    void deleteDeptByDeptCode(String deptCode);
    
    /**
     * 根据deptName删除部門
     *
     * @param deptName
     */
    void deleteDeptByDeptName(String deptName);
    
    /**
     * 根据Deptcode查询部門
     *
     * @param Deptcode
     * @return
     */
    Dept findDeptByDeptCode(Long Deptcode);
    
    /**
     * 更新权限
     *
     * @param Dept
     */
    void updateDept(Dept dept);
    
    
}

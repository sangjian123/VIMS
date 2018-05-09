package com.vms.mapper;

import java.util.List;

import com.vms.model.DeptAdmin;

public interface DeptAdminMapper
{
    /**
     * 查询部门负责人信息
     * 
     * @param DeptAdmin
     * @return DeptAdmin
     */
    List<DeptAdmin> queryDeptAdmin(DeptAdmin deptAdmin);
    
    /**
     * 新增部门负责人信息
     * 
     * @param DeptAdmin
     * @return int
     */
    int insertDtpeAdmin(DeptAdmin deptAdmin);
    
    /**
     * 更新部门负责人
     * 
     * @param DeptAdmin
     * @return int
     */
    int updateDtpeAdmin(DeptAdmin deptAdmin);
    
    /**
     * 查询部门负责人数量
     * 
     * @param DeptAdmin
     * @return int
     */
    int countDeptAdmin(DeptAdmin deptAdmin);
}

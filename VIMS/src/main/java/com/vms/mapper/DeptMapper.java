package com.vms.mapper;

import java.util.List;

import com.vms.model.Dept;
import com.vms.model.Page;

public interface DeptMapper {

	public List<Dept> findDeptByPage(Page page);

	public int addDept(Dept dept);

	public int updateDept(Dept dept);

	public int deleteDeptByDeptCode(String deptCode);

	public int deleteDeptByDeptName(String deptName);

	public Dept findDeptByDeptCode(Long Deptcode);

}

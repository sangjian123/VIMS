package com.vms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vms.mapper.DeptMapper;
import com.vms.model.Dept;
import com.vms.model.Page;
import com.vms.service.DeptService;
import com.vms.utils.ServiceException;

public class DeptServiceImpl implements DeptService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RoleServiceImpl.class);

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public Page<Dept> findDeptByPage(Page page) {
		page.setResults(deptMapper.findDeptByPage(page));
		return page;
	}

	@Override
	public void addDept(Dept dept) {
		int insert = deptMapper.addDept(dept);
		if (insert != 1) {
			LOGGER.warn("插入失败，参数：{}", dept.toString());
			throw new ServiceException("插入失败");
		}
	}

	@Override
	public void updateDept(Dept dept) {
		int update = deptMapper.updateDept(dept);
		if (update != 1) {
			LOGGER.warn("更新失败，参数：{}", dept.toString());
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public void deleteDeptByDeptCode(String deptCode) {
		int update = deptMapper.deleteDeptByDeptCode(deptCode);
		if (update != 1) {
			LOGGER.warn("删除失败，code：{}", deptCode);
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public void deleteDeptByDeptName(String deptName) {
		int update = deptMapper.deleteDeptByDeptName(deptName);
		if (update != 1) {
			LOGGER.warn("删除失败，deptName：{}", deptName);
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Dept findDeptByDeptCode(Long Deptcode) {
		Dept dept = deptMapper.findDeptByDeptCode(Deptcode);
		if (null == dept) {
			//
		}
		
		return dept;
	}

}

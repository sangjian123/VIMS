package com.vms.model;

import java.io.Serializable;

public class Dept implements Serializable
{

	private static final long serialVersionUID = 4692702430901863061L;

	private String deptNo;
	
	private String deptName;

	
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Dept [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}


}

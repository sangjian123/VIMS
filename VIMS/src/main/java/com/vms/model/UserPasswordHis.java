package com.vms.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description：用户密码
 * @author：tfl @date：2017/5/10 14:51
 */
public class UserPasswordHis implements Serializable {

	private static final long serialVersionUID = -8332992884983317378L;

	private Long id;

	private String userId;

	private String password;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserPasswordHis{" + "id=" + id + ", userId=" + userId + ", updateTime=" + updateTime + '}';
	}
}
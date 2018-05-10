package com.vms.utils;

import org.slf4j.Logger;

import com.vms.model.User;

/**
 * 安全日志工具类
 *
 * @author tfl
 * @date 2016年12月13日 下午3:10:42
 */
public final class SecurityLogUtils {
	/**
	 * 登录
	 */
	public static final String EVENT_LOGIN = "login";
	/**
	 * 注销
	 */
	public static final String EVENT_LOGOUT = "logout";
	/**
	 * 新增用户
	 */
	public static final String EVENT_ADD_USER = "addUser";
	/**
	 * 编辑用户
	 */
	public static final String EVENT_EDIT_USER = "editUser";
	/**
	 * 删除用户
	 */
	public static final String EVENT_DEL_USER = "delUser";
	/**
	 * 锁定用户
	 */
	public static final String EVENT_LOCK_USER = "lockUser";
	/**
	 * 解锁用户
	 */
	public static final String EVENT_UNLOCK_USER = "unLockUser";
	/**
	 * 更改密碼
	 */
	public static final String EVENT_EDIT_PASSWOD = "editPassword";
	/**
	 * 新增角色
	 */
	public static final String EVENT_ADD_ROLE = "addRole";
	/**
	 * 编辑角色
	 */
	public static final String EVENT_EDIT_ROLE = "editRole";
	/**
	 * 删除角色
	 */
	public static final String EVENT_DEL_ROLE = "delRole";

	/**
	 * 角色权限
	 */
	public static final String EVENT_ADD_ROLE_GRANT = "addRoleGrants";

	/**
	 * 编辑角色权限
	 */
	public static final String EVENT_EDIT_ROLE_GRANT = "editRoleGrants";

	/**
	 * 添加用户角色
	 */
	public static final String EVENT_ADD_USER_ROLE = "addUserRoles";
	/**
	 * 编辑用户角色
	 */
	public static final String EVENT_EDIT_USER_ROLE = "editUserRoles";

	/**
	 * 添加系统参数
	 */
	public static final String EVENT_ADD_SYS_PARAM = "addSysParam";

	/**
	 * 编辑系统参数
	 */
	public static final String EVENT_EDIT_SYS_PARAM = "editSysParam";

	/**
	 * 删除系统参数
	 */
	public static final String EVENT_DEL_SYS_PARAM = "delSysParam";

	/**
	 * 添加码表
	 */
	public static final String EVENT_ADD_PCODE = "addPcode";

	/**
	 * 编辑码表
	 */
	public static final String EVENT_EDIT_PCODE = "editPcode";

	/**
	 * 删除码表
	 */
	public static final String EVENT_DEL_PCODE = "delPcode";

	/**
	 * 系统
	 */
	public static final String OPER_PORTAL = "portal";

	/**
	 * 操作结果-成功
	 */
	public static final String OPER_SUCCESS = "success";
	/**
	 * 操作结果-失败
	 */
	public static final String OPER_FAILURE = "failure";

	/**
	 * 安全日志记录
	 * 
	 * @param user
	 * @param clientId
	 * @param eventType
	 * @param operObj
	 * @param result
	 * @param params
	 * @param logger
	 * @return
	 */
	public static void loggerLog(String className, User user, String clientId, String eventType, String operObj,
			String result, String params, Logger logger) {
		StringBuilder sb = new StringBuilder();
		sb.append(className).append(" - [ eventType:").append(eventType);
		sb.append(", userId:").append(user == null ? "" : user.getId());
		sb.append(", name:").append(user == null ? "" : user.getLoginname());
		sb.append(", client:").append(clientId);
		sb.append(", operObj:").append(operObj);
		sb.append(", result:").append(result);
		sb.append(", params:").append(params).append(" ]");
		logger.info(sb.toString());
	}

}
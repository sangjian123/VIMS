package com.vms.utils;

import org.apache.commons.lang3.StringUtils;

public final class PasswordCheckUtils {
	/**
	 * 密码验证
	 * 
	 * @param password
	 * @return
	 */
	public static String checkPassword(String username, String password) {
		if (StringUtils.isBlank(password) || password.length() < 5 || password.length() > 20) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req3") + "!";
		} else if (password.equals(username)) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req1") + "!";
		}
		String[] num = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		String[] capital = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		String[] lowerCase = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		String[] symbol = { "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "\\", "|",
				"[", "{", "}", "]", ";", ":", "\'", "\"", ",", "<", ".", ">", "/", "?", " " };
		int i = 0;
		for (String s : num) {
			if (password.indexOf(s) > -1) {
				i += 1;
				break;
			}
		}
		if (i == 0) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req4") + "!";
		}
		for (String s : capital) {
			if (password.indexOf(s) > -1) {
				i += 1;
				break;
			}
		}
		if (i == 1) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req6") + "!";
		}
		for (String s : lowerCase) {
			if (password.indexOf(s) > -1) {
				i += 1;
				break;
			}
		}
		if (i == 2) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req5") + "!";
		}
		for (String s : symbol) {
			if (password.indexOf(s) > -1) {
				i += 1;
				break;
			}
		}
		if (i == 3) {
			return I18nUtils.getI18nMsg("system_user_edit_pwd_req7") + "!";
		}
		return null;
	}
}

package com.simple.admin.util;

import javax.servlet.http.HttpServletRequest;

import com.simple.constant.Constant;
import com.simple.model.LoginUser;


public class LoginUserUtil {

	public static LoginUser getCurrentUser(HttpServletRequest request) {
		return (LoginUser) request.getSession().getAttribute(Constant.CURRENT_USER);
	}
	public static void setCurrentUser(HttpServletRequest request,LoginUser user) {
		request.getSession().setAttribute(Constant.CURRENT_USER,user);
	}
	public static void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.CURRENT_USER);
	}
}

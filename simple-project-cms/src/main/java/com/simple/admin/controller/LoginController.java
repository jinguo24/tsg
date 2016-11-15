package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.constant.Constant;
import com.simple.model.LoginUser;
import com.simple.model.User;
import com.simple.service.UserService;
import com.simple.weixin.util.MD5Util;
/**
 * 
 * @author zhenglong.wei
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	/**
	 * 登录
	 * @param sqzh
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "doLogin",method=RequestMethod.POST)
	@ResponseBody
	public String doLogin(String account,String password,HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(account)) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"请输入帐号", null);
		}
		
		if (StringUtils.isEmpty(password)) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"请输入密码", null);
		}
		
		User user = userService.findByCode(account);
		if (null == user ) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"用户不存在", null);
		}
		
		if (user.getIsAdmin() != 1 ) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"该工号没有后台权限", null);
		}
		
		if (!user.getPassword().equals(getMD5Password(password)) ) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"密码错误", null);
		}
		
		LoginUser lu = new LoginUser();
		lu.setCode(account);
		lu.setName(user.getName());
		lu.setIsSuperUser(lu.getIsSuperUser());
		lu.setIsAdmin(user.getIsAdmin());
		lu.setIsAdminSuper(user.getIsAdminSuper());
		LoginUserUtil.setCurrentUser(request, lu);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"登录成功", lu);
	}
	
	@RequestMapping(value = "logout",method=RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		LoginUserUtil.removeCurrentUser(request);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"登出成功", null);
	}
	
	public static String getMD5Password(String password) {
		return MD5Util.MD5Encode(password, Constant.MD5_KEY);
	}
	
}

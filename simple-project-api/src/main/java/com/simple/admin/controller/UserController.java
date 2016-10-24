package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.util.AjaxWebUtil;
import com.simple.constant.Constant;
import com.simple.model.User;
import com.simple.service.UserService;
import com.simple.weixin.util.MD5Util;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response,User user,String oldPassword){
		try{
			User u = userService.findByCode(user.getCode());
			if(!getMD5Password(oldPassword).equals(u.getPassword())){
				return AjaxWebUtil.sendAjaxResponse(request, response, true, "原密码错误", user);
			}
			u.setPassword(getMD5Password(user.getPassword()));
			userService.updateByParams(u);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "更新成功", user);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "更新失败", e.getMessage());
		}
	}

	
	public static String getMD5Password(String password) {
		return MD5Util.MD5Encode(password, Constant.MD5_KEY);
	}
}

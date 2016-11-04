package com.simple.admin.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.constant.Constant;
import com.simple.model.PageResult;
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
	 * 查询用户列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findList", method = RequestMethod.GET)
	@ResponseBody
	public String findList(HttpServletRequest request, HttpServletResponse response, String code,String name, Integer isSuperUser,
			int pageIndex, int pageSize) {
		try {
			PageResult pr = userService.queryUsers(code,name, isSuperUser, pageIndex, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", pr);
		} catch (Exception e) {
			log.error("查询失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}

	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response, User user) {
		try {
			
			user.setPassword(getMD5Password(EnvPropertiesConfiger.getValue("initPassword")));
			userService.add(user);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "新增成功", user);
		} catch (Exception e) {
			log.error("新增用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "新增失败", e.getMessage());
		}
	}

	/**
	 * 批量上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		try {
			//CommonsMultipartFile cf= (CommonsMultipartFile)files[0]; //这个myfile是MultipartFile的
	        //DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        String fileName = file.getOriginalFilename();
	        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
			ResponseInfo ri  = userService.validateFile(file.getInputStream(),suffix);
			List<User> users = new ArrayList<>();
			if ( null != ri && ri.getStatus().getState()) {
				users =(List<User>)ri.getData();
			}else if ( null != ri && (!ri.getStatus().getState())) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,ri.getStatus().getCode(),"创建失败", ri.getData());
			}
			for (User u : users) {
				u.setPassword(getMD5Password(EnvPropertiesConfiger.getValue("initPassword")));
			}
			userService.addList(users);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "上传成功", users);
		} catch (Exception e) {
			log.error("上传用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "新增失败", e.getMessage());
		}
	}

	/**
	 * 通过id查询用户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "findById", method = RequestMethod.GET)
	@ResponseBody
	public String findById(HttpServletRequest request, HttpServletResponse response, Integer id) {
		try {
			User user = userService.findById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", user);
		} catch (Exception e) {
			log.error("查询失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}
	}

	/**
	 * 更新用戶
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response,User user){
		try {
			User u = userService.findByCode(user.getCode());
			if(u!=null && u.getId() == user.getId()){
				return AjaxWebUtil.sendAjaxResponse(request, response, false, "工号已经存在", user);
			}
			userService.updateByParams(user);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "更新成功", user);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "更新失败", e.getMessage());
		}
	}
	
	/**
	 * 校验工号是否存在
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "vaildCode", method = RequestMethod.POST)
	@ResponseBody
	public String vaildCode(HttpServletRequest request, HttpServletResponse response,User user){
		try {
			User u = userService.findByCode(user.getCode());
			if(u!=null){
				return AjaxWebUtil.sendAjaxResponse(request, response, false, "工号已经存在", user);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "成功", user);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "失败", e.getMessage());
		}
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response,Integer id){
		try{
			User user = new User();
			user.setId(id);
			user.setPassword(getMD5Password(EnvPropertiesConfiger.getValue("initPassword")));
			userService.updatePwd(user);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "重置密码成功", id);
		}catch(Exception e){
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "重置密码失败", id);
		}
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response,Integer id){
		try {
			userService.delete(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "刪除成功", id);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "刪除失败", e.getMessage());
		}
	}
	
	public static String getMD5Password(String password) {
		return MD5Util.MD5Encode(password, Constant.MD5_KEY);
	}
	
}

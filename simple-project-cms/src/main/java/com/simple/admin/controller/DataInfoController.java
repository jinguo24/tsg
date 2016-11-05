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
import com.simple.model.DataInfo;
import com.simple.model.PageResult;
import com.simple.service.DataInfoService;

@Controller
@RequestMapping("dataInfo")
public class DataInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(DataInfoController.class);
	
	@Autowired
	private DataInfoService dataInfoService;
	
	
	/**
	 * 查询资料列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findList", method = RequestMethod.GET)
	@ResponseBody
	public String findList(HttpServletRequest request, HttpServletResponse response, String bookName,String name, Integer type,
			int pageIndex, int pageSize) {
		try {
			PageResult pr = dataInfoService.findListByParams(bookName, name, type, pageIndex, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", pr);
		} catch (Exception e) {
			log.error("查询失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}

	}

	/**
	 * 新增资料
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response, DataInfo dataInfo) {
		try {
			dataInfoService.add(dataInfo);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "新增成功", dataInfo);
		} catch (Exception e) {
			log.error("新增资料失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "新增失败", e.getMessage());
		}
	}
	
	
	/**
	 * 通过id查询资料
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
			DataInfo dataInfo = dataInfoService.findById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", dataInfo);
		} catch (Exception e) {
			log.error("查询失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}
	}

	/**
	 * 更新资料
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response,DataInfo dataInfo){
		try {
			dataInfoService.update(dataInfo);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "更新成功", dataInfo);
		} catch (Exception e) {
			log.error("更新资料失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "更新失败", e.getMessage());
		}
	}
	
	/**
	 * 删除资料
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response,Integer id){
		try {
			dataInfoService.delete(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "刪除成功", id);
		} catch (Exception e) {
			log.error("更新资料失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "刪除失败", e.getMessage());
		}
	}
	
}

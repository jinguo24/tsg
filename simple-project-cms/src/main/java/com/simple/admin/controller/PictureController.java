package com.simple.admin.controller;

import java.util.List;

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
import com.simple.model.Picture;
import com.simple.service.PictureService;

@Controller
@RequestMapping("picture")
public class PictureController {
	
	private static final Logger log = LoggerFactory.getLogger(PictureController.class);
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 查询轮播图列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findList",method=RequestMethod.GET)
	@ResponseBody
	public String findList(HttpServletRequest request, HttpServletResponse response){
		try{
			List<Picture> pics = pictureService.findList();
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", pics);
		}catch(Exception e){
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}
	 
	}
	
	/**
	 * 查询轮播图详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findById",method=RequestMethod.GET)
	@ResponseBody
	public String findById(Integer id,HttpServletRequest request, HttpServletResponse response){
		try{
			Picture pic = pictureService.findById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", pic);
		}catch(Exception e){
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败", e.getMessage());
		}
	}
	
	/**
	 * 新增轮播图
	 * @param request
	 * @param response
	 * @param pic
	 * @return
	 */
	@RequestMapping(value = "add",method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response,Picture pic){
		
		try {
			pictureService.add(pic);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "新增成功", pic);
		} catch (Exception e) {
			log.error("新增轮播图失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "新增失败", e.getMessage());
		}
	}
	
	/**
	 * 更新轮播图
	 * @param request
	 * @param response
	 * @param pic
	 * @return
	 */
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response,Picture pic){
		
		try {
			pictureService.update(pic);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "更新成功", pic);
		} catch (Exception e) {
			log.error("新增轮播图失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "更新失败", e.getMessage());
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(Integer id,HttpServletRequest request, HttpServletResponse response){
		try{
			pictureService.delete(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "删除成功", id);
		}catch(Exception e){
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "删除失败", e.getMessage());
		}
	}
	
}

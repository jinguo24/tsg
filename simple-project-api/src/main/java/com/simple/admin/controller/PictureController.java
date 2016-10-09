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
	
	
	
}

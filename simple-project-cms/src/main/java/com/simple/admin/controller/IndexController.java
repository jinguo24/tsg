package com.simple.admin.controller;

import java.util.ArrayList;
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
import com.simple.constant.Constant;
import com.simple.model.DataInfo;
import com.simple.service.EsIndexService;

@Controller
@RequestMapping("index")
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private EsIndexService esIndexService;
	
	
	@RequestMapping(value = "createIndex", method = RequestMethod.GET)
	@ResponseBody
	public String createIndex(HttpServletRequest request, HttpServletResponse response){
		try {
			esIndexService.createIndex(Constant.INDEX_DATA_NAME);
			List<DataInfo> infos = new ArrayList<DataInfo>(); 
			for (int i = 10 ; i < 20 ; i ++) {
				DataInfo di = new DataInfo();
				di.setId(i+1);
				di.setName("阿萨德发"+i);
				di.setBookName("balk激动死阿的士速递"+i);
				di.setDesc("撒旦发射点发生的阿啊电风扇地方"+i);
				di.setAuthors("阿斯兰多夫空军偶"+i);
				di.setTags("阿斯兰的开房间啊松岛枫iu"+i);
				infos.add(di);
			}
			esIndexService.batchInsertOrUpdateDoc(Constant.INDEX_DATA_NAME, Constant.INDEX_DATA_TYPE_BOOK, infos);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "刪除成功", null);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "刪除失败", e.getMessage());
		}
	}
	
	@RequestMapping(value = "books", method = RequestMethod.GET)
	@ResponseBody
	public String queryIndex(Integer id,String text,Integer type,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response){
		try {
			List<DataInfo> rl = esIndexService.queryDataInfo(id, text, type,pageIndex,pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "刪除成功", rl);
		} catch (Exception e) {
			log.error("更新用户失败", e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "刪除失败", e.getMessage());
		}
	}
}

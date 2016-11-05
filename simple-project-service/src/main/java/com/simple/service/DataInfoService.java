package com.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.DataInfoDao;
import com.simple.model.DataInfo;
import com.simple.model.PageResult;
import com.simple.model.User;

@Service
public class DataInfoService {
	
	@Autowired
	private DataInfoDao dao;
	
	public PageResult findListByParams(String bookName ,String name,Integer type,int pageIndex ,int pageSize){
		int total = getCount(bookName,name,type);
		List<DataInfo> dataInfos = new ArrayList<>();
		if(total>0){
			dataInfos = dao.findListByParams(bookName, name, type, pageIndex, pageSize);
		}
		PageResult p = new PageResult(total, pageSize, pageIndex, dataInfos);
		return p;
	}
	
	public int getCount(String bookName ,String name,Integer type) {
		 
		return dao.getCount(bookName, name, type);
	}
	
	public void add(DataInfo  dataInfo){
		dao.add(dataInfo);
	}
	
	public DataInfo findById(Integer id){
   
	return dao.findById(id);
	}
	
	public List<DataInfo> findList(){
		return dao.findList();
	}
	
	public void update(DataInfo dataInfo){
		dao.update(dataInfo);
	}
	
	public void delete(Integer id){
		dao.delete(id);
	}
	

}

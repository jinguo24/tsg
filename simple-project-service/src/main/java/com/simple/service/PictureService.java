package com.simple.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.PictureDao;
import com.simple.model.Picture;

@Service
public class PictureService {
	
	@Autowired
	private PictureDao dao;
	
	
	public void add(Picture pic){
		pic.setCreateTime(new Date());
		dao.add(pic);
	}
	
	public Picture findById(Integer id){
		return dao.findById(id);
	}
	
	public List<Picture> findList(){
		return dao.findList();
	}
	
	public void update(Picture pic){
		dao.update(pic);
	}
	
	public void delete(Integer id){
		dao.delete(id);
	}

}

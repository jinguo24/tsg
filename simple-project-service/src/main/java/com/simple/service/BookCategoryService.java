package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.BookCategoryDao;
import com.simple.model.BookCategory;

@Service
public class BookCategoryService {
	
	@Autowired
	private BookCategoryDao dao;
	
	public List<BookCategory> findRootList(){
		return dao.findRootList();
	}
	
	public List<BookCategory> findChildren(String parentCode){
		return dao.findChildren(parentCode);
	}
	
	public BookCategory findByCode(String code) {
		return dao.findByCode(code);
	}
}

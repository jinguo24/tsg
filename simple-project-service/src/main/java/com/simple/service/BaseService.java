package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.BaseDao;
import com.simple.model.Bank;

@Service
public class BaseService {
	
	@Autowired
	private BaseDao baseDao;
	
	public List<Bank> getBankList(){
		return baseDao.getBankList();
	}
	
	public Bank queryBank(String code) {
		return baseDao.queryBank(code);
	}
}

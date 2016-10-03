package com.simple.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Bank;

@Repository
@DatabaseTemplate("st_all")
public class BaseDao extends BaseIbatisDao{

	private String scope = "base.";
	
	public List<Bank> getBankList(){
		return super.sqlSession.selectList(scope + "getBankList");
	}
	
	public Bank queryBank(String code) {
		return super.sqlSession.selectOne(scope + "getBankByCode",code);
	}
}

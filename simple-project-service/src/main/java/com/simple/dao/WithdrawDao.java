package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Account;

@Repository
@DatabaseTemplate("st_all")
public class WithdrawDao extends BaseIbatisDao{

	public void insert(Account account){
		this.sqlSession.insert("account.insert", account);
	}
	
	public Account queryById(String id){
		return this.sqlSession.selectOne("account.getById", id);
	}
	
	public void deleteByApplyPhone(String applyPhone) {
		this.sqlSession.delete("account.deleteByApplyPhone",applyPhone);
	}
	
	public List<Account> queryList(int pageIndex,int pageSize,String applyPhone) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("applyPhone", applyPhone);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("account.queryList",param);
	}
	
	public List<Account> queryAllList(int pageIndex,int pageSize,String applyPhone,int status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("applyPhone", applyPhone);
		param.put("cashStatus", status);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("account.queryAllList",param);
	}
	
	public Integer queryAllCount(int pageIndex,int pageSize,String applyPhone,int status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("applyPhone", applyPhone);
		param.put("cashStatus", status);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		return this.sqlSession.selectOne("account.queryAllCount",param);
	}
	
	public void updateFinished(String applyPhone) {
		Map param = new HashMap();
		param.put("applyPhone", applyPhone);
		this.sqlSession.update("");
	}
	
	public void updateStatus(Account account){
		this.sqlSession.update("account.updateStatus", account);
	}
	
	public void updatePhone(Account account){
		this.sqlSession.update("account.updatePhone", account);
	}
	
}

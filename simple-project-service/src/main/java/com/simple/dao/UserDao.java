package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.constant.Constant;
import com.simple.model.User;

@Repository
@DatabaseTemplate("st_all")
public class UserDao extends BaseIbatisDao{
	
	public User getById(String statement, int id){
		return (User) super.getById(statement, id);
	}
	
	public List<User> getList(String phone,int userStatus,int pageIndex,int pageSize) {
		if (pageIndex < 1) {
			pageIndex  =1;
		}
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("phone", phone);
		param.put("userStatus", userStatus);
		param.put("startnum", (pageIndex-1)*pageSize);
		param.put("pageSize", pageSize);
		return this.sqlSession.selectList("user.getList",param);
	}
	
	public int getCount(String phone,int userStatus) {
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("phone", phone);
		param.put("userStatus", userStatus);
		return this.sqlSession.selectOne("user.getCount",param);
	}
	
	public User getByPhone(String phone,boolean valid) {
		Map param = new HashMap();
		param.put("userPhone", phone);
		if (valid) {
			param.put("status", Constant.USER_STATUS_VALID);
		}else {
			param.put("status", 0);
		}
		return this.sqlSession.selectOne("user.selectOne",param);
	}
	
	public void updateUserAllow(String phone,boolean allow) {
		Map param = new HashMap();
		param.put("userPhone", phone);
		if (allow) {
			param.put("allow", Constant.ALLOW_SELL);
		}else {
			param.put("allow", Constant.NOT_ALLOW_SELL);
		}
		this.sqlSession.update("user.updateAllow",param);
	}
	
	public void updatePercent(String phone,double percent) {
		Map param = new HashMap();
		param.put("userPhone", phone);
		param.put("percent", percent);
		this.sqlSession.update("user.updatePercent",param);
	}
	
	public void increseCharge(String phone,double charge) {
		Map param = new HashMap();
		param.put("userPhone", phone);
		param.put("charge", charge);
		this.sqlSession.update("user.increseCharge",param);
	}
	
	public void reduceBlance(String phone,double amount) {
		Map param = new HashMap();
		param.put("userPhone", phone);
		param.put("amount", amount);
		this.sqlSession.update("user.reduceBlance",param);
	}
	
	public void updateStatus(String phone,int status){
		Map param = new HashMap();
		param.put("userPhone", phone);
		param.put("status", status);
		this.sqlSession.update("user.updateStatus",param);
	}

}

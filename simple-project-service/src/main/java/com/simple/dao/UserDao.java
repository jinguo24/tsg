package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.User;
@Repository
@DatabaseTemplate("st_all")
public class UserDao extends BaseIbatisDao{
	
	public List<User> findListByParams(String code ,String name,Integer isSuperUser,int pageIndex ,int pageSize){
		if (pageIndex < 1) {
			pageIndex  =1;
		}
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("code", code);
		param.put("name", name);
		param.put("isSuperUser", isSuperUser);
		param.put("startnum", (pageIndex-1)*pageSize);
		param.put("pageSize", pageSize);
		return this.sqlSession.selectList("user.selectList",param);
	}
	
	public int getCount(String code,String name,Integer isSuperUser) {
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("code", code);
		param.put("name", name);
		param.put("isSuperUser", isSuperUser);
		return this.sqlSession.selectOne("user.queryCount",param);
	}
    
	public void deleteByPrimaryKey(Integer id){
		this.sqlSession.delete("user.deleteByPrimaryKey",id);
    }

	public void insert(User user){
    	this.sqlSession.insert("user.insert",user);
    }
	
	public void insertList(List<User> users){
		this.sqlSession.insert("user.insertBatch", users);
	}

	public void insertSelective(User user){
    	this.sqlSession.insert("user.insertSelective",user);
    }

	public User findById(Integer id){
    	return this.sqlSession.selectOne("user.selectByPrimaryKey",id);
    }
	
	public User findByCode(String code){
		return this.sqlSession.selectOne("user.selectByCode",code);
	}

	public void updateByPrimaryKeySelective(User user){
    	 this.sqlSession.update("user.updateByPrimaryKeySelective", user);
    }

	public void updateByPrimaryKey(User user){
    	this.sqlSession.update("user.updateByPrimaryKey", user);
    }
	
	public void updatePwd(User user){
		this.sqlSession.update("user.updatePwd",user);
	}

}
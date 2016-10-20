package com.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.UserDao;
import com.simple.model.PageResult;
import com.simple.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void add(User user){
		
		userDao.insert(user);
	}
	
	public void addList(List<User> users){
		userDao.insertList(users);
	}
	
	
	public void update(User user){
		userDao.updateByPrimaryKey(user);
	}
	
	public void updateByParams(User user){
		userDao.updateByPrimaryKeySelective(user);
	}
	
	
	public User findById(Integer id){
		
		return userDao.findById(id);
	}
	
	public User findByCode(String code){
		return userDao.findByCode(code);
	}
	
	public void delete(Integer id){
		userDao.deleteByPrimaryKey(id);
	}
	
	public PageResult queryUsers(String code,String name,Integer isSuperUser ,int pageIndex,int pageSize){
		int total = queryCount(code,name,isSuperUser);
		List<User> users = new ArrayList<>();
		if(total>0){
			users = queryList(code,name, isSuperUser, pageIndex, pageSize);
		}
		PageResult p = new PageResult(total, pageSize, pageIndex, users);
		return p;
	}
	public List<User> queryList(String code,String name,Integer isSuperUser ,int pageIndex,int pageSize) {
		return userDao.findListByParams(code,name,isSuperUser, pageIndex, pageSize);
	}
	
	public int queryCount(String code,String name,Integer isSuperUser) {
		return userDao.getCount(code,name, isSuperUser);
	}
	
	public void updatePwd(User user){
		userDao.updatePwd(user);
	}
}

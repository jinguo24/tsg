package com.simple.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.dao.OrderDao;
import com.simple.dao.UserDao;
import com.simple.model.PageResult;
import com.simple.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired 
	private OrderDao orderDao;
	
	public PageResult queryPage(String phone,int userStatus,int pageIndex,int pageSize) {
		List<User> products = queryList(phone,userStatus,pageIndex, pageSize);
		int total = queryCount(phone,userStatus);
		PageResult p = new PageResult(total, pageSize, pageIndex, products);
		return p;
	}
	
	public List<User> queryList(String phone,int userStatus,int pageIndex,int pageSize) {
		return dao.getList(phone,userStatus, pageIndex, pageSize);
	}
	
	public int queryCount(String phone,int userStatus) {
		return dao.getCount(phone,userStatus);
	}
	
	public User getById(String statement, int id){
		return dao.getById(statement, id);
	}
	
	public User queryByPhone(String phone,boolean valid) {
		return dao.getByPhone(phone,valid);
	}
	
	public int insert(User user) {
		return dao.createObject("user.createRegister", user);
	}
	
	public int update(User user){
		return dao.updateObject("user.modify",user);
	}
	
	public void updateStatus(String phone,int status){
		 dao.updateStatus(phone, status);
	}

}

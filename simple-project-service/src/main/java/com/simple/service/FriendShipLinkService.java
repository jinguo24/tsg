package com.simple.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.FriendShipLinkDao;
import com.simple.model.FriendShipLink;

@Service
public class FriendShipLinkService {
	
	@Autowired
	private FriendShipLinkDao dao;
	
	public void add(FriendShipLink link){
		link.setCreateTime(new Date());
		dao.add(link);
	}
	
	public FriendShipLink findById(Integer id){
		return dao.findById(id);
	}
	
	public List<FriendShipLink> findList(){
		return dao.findList();
	}
	
	public void update(FriendShipLink link){
		dao.update(link);
	}
	
	public void delete(Integer id){
		dao.delete(id);
	}
	

    
}
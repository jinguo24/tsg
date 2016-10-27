package com.simple.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.FriendShipLink;

@Repository
@DatabaseTemplate("st_all")
public class FriendShipLinkDao extends BaseIbatisDao{

	public void add(FriendShipLink  link){
		this.sqlSession.insert("friendshipLink.insert",link);
	}
	
	public FriendShipLink findById(Integer id){
   
	return this.sqlSession.selectOne("friendshipLink.selectByPrimaryKey", id);	
	}
	
	public List<FriendShipLink> findList(){
		return this.sqlSession.selectList("friendshipLink.selectList");
	}
	
	public void update(FriendShipLink link){
		this.sqlSession.update("friendshipLink.updateByPrimaryKeySelective", link);
	}
	
	public void delete(Integer id){
		this.sqlSession.delete("friendshipLink.deleteByPrimaryKey",id);
	}
	

   
}
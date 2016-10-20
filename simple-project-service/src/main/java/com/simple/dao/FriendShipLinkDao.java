package com.simple.dao;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.FriendShipLink;

@Repository
@DatabaseTemplate("st_all")
public class FriendShipLinkDao extends BaseIbatisDao{
    public void deleteByPrimaryKey(Integer id){
    	this.sqlSession.delete("friendshipLink.deleteByPrimaryKey",id);
    }

   public void insert(FriendShipLink record){
		this.sqlSession.insert("friendshipLink.insert",record);
    }

    public FriendShipLink selectByPrimaryKey(Integer id){
    	return this.sqlSession.selectOne("friendshipLink.selectByPrimaryKey", id);	
    }

    public void updateByPrimaryKeySelective(FriendShipLink record){
    	this.sqlSession.update("friendshipLink.updateByPrimaryKeySelective", record);
    }

   
}
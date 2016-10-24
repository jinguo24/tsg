package com.simple.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Picture;

@Repository
@DatabaseTemplate("st_all")
public class PictureDao extends BaseIbatisDao{
	
	public void add(Picture  pic){
		this.sqlSession.insert("picture.insert",pic);
	}
	
	public Picture findById(Integer id){
   
	return this.sqlSession.selectOne("picture.selectByPrimaryKey", id);	
	}
	
	public List<Picture> findList(){
		return this.sqlSession.selectList("picture.selectList");
	}
	
	public void update(Picture pic){
		this.sqlSession.update("picture.updateByPrimaryKeySelective", pic);
	}
	
	public void delete(Integer id){
		this.sqlSession.delete("picture.deleteByPrimaryKey",id);
	}
	
	

}

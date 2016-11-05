package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.DataInfo;

@Repository
@DatabaseTemplate("st_all")
public class DataInfoDao  extends BaseIbatisDao{
	
	public List<DataInfo> findListByParams(String bookName ,String name,Integer type,int pageIndex ,int pageSize){
		if (pageIndex < 1) {
			pageIndex  =1;
		}
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("bookName", bookName);
		param.put("name", name);
		param.put("type", type);
		param.put("startnum", (pageIndex-1)*pageSize);
		param.put("pageSize", pageSize);
		return this.sqlSession.selectList("dataInfo.selectList",param);
	}
	
	public int getCount(String bookName ,String name,Integer type) {
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("bookName", bookName);
		param.put("name", name);
		param.put("type", type);
		return this.sqlSession.selectOne("dataInfo.queryCount",param);
	}
	
	public void add(DataInfo  dataInfo){
		this.sqlSession.insert("dataInfo.insert",dataInfo);
	}
	
	public DataInfo findById(Integer id){
   
	return this.sqlSession.selectOne("dataInfo.selectByPrimaryKey", id);	
	}
	
	public List<DataInfo> findList(){
		return this.sqlSession.selectList("dataInfo.selectList");
	}
	
	public void update(DataInfo dataInfo){
		this.sqlSession.update("dataInfo.updateByPrimaryKeySelective", dataInfo);
	}
	
	public void delete(Integer id){
		this.sqlSession.delete("dataInfo.deleteByPrimaryKey",id);
	}
	
	

	

}

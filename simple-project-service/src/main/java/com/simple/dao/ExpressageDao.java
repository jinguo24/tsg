package com.simple.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Expressage;

@Repository
@DatabaseTemplate("st_all")
public class ExpressageDao extends BaseIbatisDao{
	
	public List<Expressage> query(){
		return this.sqlSession.selectList("expressage.query");
	}
}

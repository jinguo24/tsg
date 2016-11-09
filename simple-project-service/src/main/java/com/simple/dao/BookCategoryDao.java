package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.BookCategory;

@Repository
@DatabaseTemplate("st_all")
public class BookCategoryDao  extends BaseIbatisDao{
	
	public List<BookCategory> findRootList(){
		return this.sqlSession.selectList("bookCategory.selectRootList");
	}
	
	public List<BookCategory> findChildren(String parentCode){
		Map param = new HashMap();
		param.put("parentCode", parentCode);
		return this.sqlSession.selectList("bookCategory.selectChildren",param);
	}
	
	public BookCategory findByCode(String code) {
		Map param = new HashMap();
		param.put("code", code);
		return this.sqlSession.selectOne("bookCategory.selectByCode",param);
	}
}

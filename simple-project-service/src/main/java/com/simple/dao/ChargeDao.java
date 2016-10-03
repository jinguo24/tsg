package com.simple.dao;

import org.springframework.stereotype.Repository;
import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Charge;

@Repository
@DatabaseTemplate("st_all")
public class ChargeDao extends BaseIbatisDao{

	public Charge getCharge(){
		return super.sqlSession.selectOne("charge.query");
	}
}

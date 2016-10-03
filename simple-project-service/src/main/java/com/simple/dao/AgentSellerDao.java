package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.constant.Constant;
import com.simple.model.AgentSeller;
import com.simple.model.SellerJoinProductVO;
import com.simple.model.SellerJoinVO;

@Repository
@DatabaseTemplate("st_all")
public class AgentSellerDao extends BaseIbatisDao{
	
	public List<AgentSeller> queryByAgent(String phone){
		return this.sqlSession.selectList("agentSeller.queryByAgent",phone);
	}
	
	public List<AgentSeller> queryBySeller(String phone) {
		return this.sqlSession.selectList("agentSeller.queryBySeller",phone);
	}
	
	public int queryCountByPhone(String owner,String seller) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("seller", seller);
		return this.sqlSession.selectOne("agentSeller.queryCountByPhone",param);
	}
	
	public List<AgentSeller> queryListByPhone(String owner,String seller,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("seller", seller);
		if ( pageIndex <= 0 ) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("end", pageSize);
		return this.sqlSession.selectList("agentSeller.queryByPhone",param);
	}
	
	public Integer querySumWatchCount(String owner) {
		return this.sqlSession.selectOne("agentSeller.querySumWatchCount",owner);
	}
	
	
	public void add(AgentSeller agentSeller) {
		this.sqlSession.insert("agentSeller.insert",agentSeller);
	}
	
	public void delete(AgentSeller agentSeller) {
		this.sqlSession.insert("agentSeller.delete",agentSeller);
	}
	
	public void updatePercent(String agent,String seller,double percent) {
		Map param = new HashMap();
		param.put("chargePercent", percent);
		param.put("agentPhone", agent);
		param.put("sellerPhone", seller);
		this.sqlSession.update("agentSeller.updatePercent",param);
	}
	
	public void updateAllow(String agent,String seller,boolean allow) {
		Map param = new HashMap();
		if (allow) {
			param.put("allow", Constant.ALLOW_SELL);
		}else {
			param.put("allow", Constant.NOT_ALLOW_SELL);
		}
		param.put("agentPhone", agent);
		param.put("sellerPhone", seller);
		this.sqlSession.update("agentSeller.updateAllow",param);
	}
	
	public List<SellerJoinVO> getSellerJoinHeadList(String userPhone){
		return this.sqlSession.selectList("agentSeller.getSellerJoinList", userPhone);
	}

	public List<SellerJoinProductVO> getSellerJoinProductVO(String sql) {
		return this.sqlSession.selectList("agentSeller.getSellerJoinProductList", sql);
	}
	
	public void increaseWatchCount(String agent,String seller) {
		Map param = new HashMap();
		param.put("agentPhone", agent);
		param.put("sellerPhone", seller);
		this.sqlSession.update("agentSeller.increaseWatchCount",param);
	}
}

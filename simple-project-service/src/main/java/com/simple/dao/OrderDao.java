package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Order;
import com.simple.model.SellerListVO;
import com.simple.model.SellerMainVO;
import com.simple.model.User;

@Repository
@DatabaseTemplate("st_all")
public class OrderDao extends BaseIbatisDao{
	
	public List<Order> getOrdersByUserPhone(Map<String, Object> param){
		return this.sqlSession.selectList("order.queryByUserPhone",param);
	}
	
	public Integer getOrdersCount(Map<String, Object> param){
		return this.sqlSession.selectOne("order.queryOrderCount",param);
	}
	
	public Order getByCode(String code){
		return this.sqlSession.selectOne("order.getByCode",code);
	}
	
	public Double queryAgentTotalPrice(String owner,String begin,String end) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("beginTime", begin);
		param.put("endTime", end);
		return this.sqlSession.selectOne("order.queryAgentTotalPrice",param);
	}
	
	public Double queryAgentTotalCharge(String owner,String begin,String end) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("beginTime", begin);
		param.put("endTime", end);
		return this.sqlSession.selectOne("order.queryAgentTotalCharge",param);
	}
	
	public Double querySellerTotalPrice(String owner,String seller,String begin,String end) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		return this.sqlSession.selectOne("order.querySellerTotalPrice",param);
	}
	
	public Double querySellerTotalCharge(String owner,String seller) {
		Map param = new HashMap();
		param.put("owner", owner);
		param.put("seller", seller);
		return this.sqlSession.selectOne("order.querySellerTotalCharge",param);
	}
	
	public Integer queryCountByStatus(String owner,String seller,List<Integer> orderStatus,String begin,String end,boolean hasPay) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_status_list", orderStatus);
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (hasPay) {
			param.put("hasPay", 1);
		}else {
			param.put("hasPay", 0);
		}
		return this.sqlSession.selectOne("order.queryCountByStatus",param);
	}
	
	public List<Order> queryListByStatus(String owner,String seller,int orderStatus,
			String begin,String end,int pageIndex,int pageSize,boolean hasPay) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_status", orderStatus);
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (hasPay) {
			param.put("hasPay", 1);
		}else {
			param.put("hasPay", 0);
		}
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.queryListByStatus",param);
	}
	
	public List<Order> queryListByStatus(String owner,String seller,List<Integer> orderStatus,
			String begin,String end,int pageIndex,int pageSize,boolean hasPay) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_status", orderStatus);
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (hasPay) {
			param.put("hasPay", 1);
		}else {
			param.put("hasPay", 0);
		}
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.queryListByStatusList",param);
	}
	
	public List<Order> querySendList(String owner,String seller,
			String begin,String end,int pageIndex,int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.querySendList",param);
	}
	
	public List<Order> queryToFinishList(String owner,String seller,
			String begin,String end,int pageIndex,int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.queryToFinishList",param);
	}
	
	public List<Order> queryToDoList(String owner,String seller,String begin,String end,int pageIndex,int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("owner", owner);
		param.put("seller", seller);
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.queryToDoList",param);
	}
	
	public Integer queryProductCount(String owner,String seller,int orderStatus) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_status", orderStatus);
		param.put("owner", owner);
		param.put("seller", seller);
		return this.sqlSession.selectOne("order.queryCountByStatus",param);
	}
	
	public void addOrder(Order order) {
		this.sqlSession.insert("order.insert",order);
	}
	
	public int sendOrder(Order order) {
		return this.sqlSession.update("order.sendOrder",order);
	}
	
	public int reject(Order order) {
		return this.sqlSession.update("order.updateReject",order);
	}
	
	public int rejectSuccess(Order order) {
		return this.sqlSession.update("order.updateRejectSuccess",order);
	}
	
	public int rejectRefuse(Order order) {
		return this.sqlSession.update("order.updateRejectRefuse",order);
	}
	
	public int finish(Order order) {
		return this.sqlSession.update("order.updateFinish",order);
	}
	
	public int cancel(Order order) {
		return this.sqlSession.update("order.updateCancel",order);
	}
	
	public void payOrder(Order order) {
		this.sqlSession.update("order.payOrder",order);
	}

	public List<Order> queryMyOrders(String userPhone,int pageIndex,int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userPhone", userPhone);
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("order.queryMyOrders",param);
	}
	
	public void updateOrderWeixinNo(Order order) {
		this.sqlSession.update("order.setWeixinNo",order);
	}
}

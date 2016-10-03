package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.simple.dao.AgentSellerDao;
import com.simple.dao.ChargeDao;
import com.simple.dao.UserDao;
import com.simple.model.AgentSeller;
//import com.simple.model.SellerJoinProductVO;
//import com.simple.model.SellerJoinVO;
import com.simple.model.Charge;


@Service
public class AgentSellerService {
	
	@Autowired
	private AgentSellerDao dao;
	@Autowired
	private ChargeDao chargeDao;
	@Autowired
	private UserDao userDao;
	
	public List<AgentSeller> queryByAgent(String phone){
		return dao.queryByAgent(phone);
	}
	
	public List<AgentSeller> queryBySeller(String phone) {
		return dao.queryBySeller(phone);
	}
	
	public int queryCountByPhone(String owner,String seller) {
		return dao.queryCountByPhone(owner,seller);
	}
	
	public List<AgentSeller> queryListByPhone(String owner,String seller,int pageIndex,int pageSize) {
		return dao.queryListByPhone(owner, seller,pageIndex,pageSize);
	}
	
	public Integer querySumWatchCount(String owner) {
		return dao.querySumWatchCount(owner);
	}
	
	public void add(AgentSeller agentSeller) {
		dao.add(agentSeller);
	}
	
	public void delete(AgentSeller agentSeller) {
		dao.delete(agentSeller);
	}
	
	public void updatePercent(String agent,String seller,double percent) {
		dao.updatePercent(agent, seller, percent);
	}
	
	public void updatePercent(String owner,double percent) {
		List<AgentSeller> ass = queryListByPhone(owner,null,1,10000);
		if ( null != ass && ass.size() > 0 ) {
			for (int i = 0 ; i < ass.size() ; i ++ ) {
				AgentSeller as = ass.get(i);
				updatePercent(as.getAgentPhone(),as.getSellerPhone(),percent);
			}
		}
		userDao.updatePercent(owner, percent);
	}
	
	public void updateAllow(String owner,String seller,boolean allow) {
		dao.updateAllow(owner, seller, allow);
		userDao.updateUserAllow(owner, allow);
	}
	
	public double queryCharge() {
		Charge charge = chargeDao.getCharge();
		if ( null ==charge ) {
			return 0d;
		}
		if ( null == charge.getCharge()) {
			return 0d;
		}
		return charge.getCharge();
	}
	
	public void increaseWatchCount(String owner,String seller) {
		dao.increaseWatchCount(owner, seller);
	}
	
	
//	public PageInfo<SellerJoinVO> getSellerJoinList(String userPhone, Integer pageIndex, Integer pageSize){
//		PageHelper.startPage(pageIndex, pageSize);
//		List<SellerJoinVO> headLists = dao.getSellerJoinHeadList(userPhone);
//		String sqlPre = "(select p.owner userPhone, p.name productName, p.price, ROUND(p.price*";
//		String sqlMid = " ,2) chargePrice, p.thumbnail from product p where p.owner = ";
//		String sqlNext = " limit 2)";
//		Integer headListSize = headLists.size();
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < headListSize; i++) {
//			SellerJoinVO sjhVO = headLists.get(i);
//			sb.append(sqlPre).append(sjhVO.getChargePst()).append(sqlMid).append(sjhVO.getUserPhone()).append(sqlNext);
//			if(i != headListSize -1){
//				sb.append(" union ");
//			}
//		}
//		List<SellerJoinProductVO> productLists = dao.getSellerJoinProductVO(sb.toString());
//		for (SellerJoinVO headList : headLists) {
//			List<SellerJoinProductVO> sjpList= new ArrayList<SellerJoinProductVO>();
//			String phoneHead = headList.getUserPhone();
//			for (SellerJoinProductVO productVO : productLists) {
//				String phoneProduct = productVO.getUserPhone();
//				if(phoneHead.equals(phoneProduct)){
//					sjpList.add(productVO);
//				}
//			}
//			headList.setLists(sjpList);
//		}
//		PageInfo<SellerJoinVO> page = new PageInfo<>(headLists);
//		return page;
//	}
}

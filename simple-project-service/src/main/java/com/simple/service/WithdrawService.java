package com.simple.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.simple.constant.Constant;
import com.simple.dao.UserDao;
import com.simple.dao.WithdrawDao;
import com.simple.model.Account;
import com.simple.model.PageResult;

@Service
public class WithdrawService {

	@Autowired
	private WithdrawDao withdrawDao;
	
	@Autowired
	private UserDao userDao;
	
	public void addAccount(Account account){
		//先删除当前用户之前申请的未完成的提现
		withdrawDao.deleteByApplyPhone(account.getApplyPhone());
		withdrawDao.insert(account);
	}
	
	public Account queryById(String id) {
		return withdrawDao.queryById(id);
	}
	
	public void updateAccountPhone(Account account) {
		withdrawDao.updatePhone(account);
	}
	
	public List<Account> queryList(int pageIndex,int pageSize,String applyPhone) {
		if (StringUtils.isEmpty(applyPhone)) {
			return null;
		}
		return withdrawDao.queryList(pageIndex, pageSize, applyPhone);
	}
	
	public PageResult queryAll(int pageIndex,int pageSize,String applyPhone,int status) {
		List<Account> accounts = withdrawDao.queryAllList(pageIndex, pageSize, applyPhone,status);
		Integer total = withdrawDao.queryAllCount(pageIndex, pageSize, applyPhone, status);
		PageResult p = new PageResult(total, pageSize, pageIndex, accounts);
		return p;
	}
	
	public Account updateAccountFinised(Account a) {
		a.setOperateTime(new Timestamp(new Date().getTime()));
		a.setStatus(Constant.CASH_STATUS_FINISHED);
		withdrawDao.updateStatus(a);
		//扣掉余额
		userDao.reduceBlance(a.getApplyPhone(), a.getCashAmount());
		return a;
	}
	
	public Account updateAccountCancel(String id,String remark) {
		Account a = queryById(id);
		a.setOperateTime(new Timestamp(new Date().getTime()));
		a.setRemark(remark);
		a.setStatus(Constant.CASH_STATUS_CANCEL);
		withdrawDao.updateStatus(a);
		return a;
	}

}

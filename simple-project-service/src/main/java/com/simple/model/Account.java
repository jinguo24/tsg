package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.simple.common.util.DateUtil;

/**提款表
 * tmall_account
 * @author kaixiang
 *
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 3087624468698518327L;
	private String id;
	private String userPhone;
	private String realName;
	private String bankCode;
	private String bankName;
	private String bankAccount;
	private String bankAccountNo;
	private double cashAmount;
	private Timestamp cashTime;
	private String showCashTime;
	private String bankPhone;
	private String weiChatNo;
	private int status;
	private String remark;
	private Timestamp operateTime;
	private String showOperateTime;
	private String applyPhone;
	
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public Timestamp getCashTime() {
		return cashTime;
	}
	public void setCashTime(Timestamp cashTime) {
		this.cashTime = cashTime;
		if ( null != this.cashTime) {
			this.showCashTime = DateUtil.date2AllString(this.cashTime);
		}
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	public String getWeiChatNo() {
		return weiChatNo;
	}
	public void setWeiChatNo(String weiChatNo) {
		this.weiChatNo = weiChatNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
		if ( null != this.operateTime) {
			this.showOperateTime = DateUtil.date2AllString(this.operateTime);
		}
	}
	public String getShowCashTime() {
		return showCashTime;
	}
	public void setShowCashTime(String showCashTime) {
		this.showCashTime = showCashTime;
	}
	public String getShowOperateTime() {
		return showOperateTime;
	}
	public void setShowOperateTime(String showOperateTime) {
		this.showOperateTime = showOperateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplyPhone() {
		return applyPhone;
	}
	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}

package org.ssm.model;

import java.io.Serializable;

public class Bill implements Serializable{
	
	private static final long serialVersionUID = 4321999465120822573L;
	private String appNo;//投保单号
	private String money;//要改成的金额
	private String environment;//要修改的环境
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
}

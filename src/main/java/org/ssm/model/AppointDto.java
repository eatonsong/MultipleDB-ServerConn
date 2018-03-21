package org.ssm.model;

import java.io.Serializable;
import java.util.List;

public class AppointDto implements Serializable{
	private static final long serialVersionUID = -2654615221339886632L;
	private String areaCode;//	区域机构代码
	private List<String> environments;
	private List<String> appbaoliuList;
	private List<Appoint> appointList;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public List<String> getEnvironments() {
		return environments;
	}
	public void setEnvironments(List<String> environments) {
		this.environments = environments;
	}
	public List<String> getAppbaoliuList() {
		return appbaoliuList;
	}
	public void setAppbaoliuList(List<String> appbaoliuList) {
		this.appbaoliuList = appbaoliuList;
	}
	public List<Appoint> getAppointList() {
		return appointList;
	}
	public void setAppointList(List<Appoint> appointList) {
		this.appointList = appointList;
	}
	

}

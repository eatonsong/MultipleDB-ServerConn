package org.ssm.model;

import java.io.Serializable;
/**\
 * 特别约定
 * @author lenovo
 *
 */
public class Appoint implements Serializable {
	private static final long serialVersionUID = 6663456179101764899L;
	private String appjigou;//适用机构
	private String appchanpin;//适用产品
	private String appdaima;//特约代码；
	private String appneirong;//特约内容
	public String getAppjigou() {
		return appjigou;
	}
	public void setAppjigou(String appjigou) {
		this.appjigou = appjigou;
	}
	public String getAppchanpin() {
		return appchanpin;
	}
	public void setAppchanpin(String appchanpin) {
		this.appchanpin = appchanpin;
	}
	public String getAppdaima() {
		return appdaima;
	}
	public void setAppdaima(String appdaima) {
		this.appdaima = appdaima;
	}
	public String getAppneirong() {
		return appneirong;
	}
	public void setAppneirong(String appneirong) {
		this.appneirong = appneirong;
	}
	
}

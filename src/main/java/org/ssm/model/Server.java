package org.ssm.model;

import java.io.Serializable;

public class Server implements Serializable {
	private static final long serialVersionUID = -222841002287478117L;
	private String name;//环境
	private String ip;//主机IP
	private String user;//主机登陆用户名
	private String psw;//主机登陆密码
	private int port;//主机ssh2登陆端口，如果取默认值，传-1
	private String privateKey;//密钥文件路径
	private String passphrase;// 密钥的密码
	
	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Server(String name, String ip, String user, String psw, int port) {
		super();
		this.name = name;
		this.ip = ip;
		this.user = user;
		this.psw = psw;
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPassphrase() {
		return passphrase;
	}
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
	
}

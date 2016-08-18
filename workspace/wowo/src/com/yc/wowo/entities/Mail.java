package com.yc.wowo.entities;


/**
 * Mail属性实体
 * 
 * @author eden
 * 
 */
public class Mail{

	public static final String ENCODEING = "UTF-8";

	private String host = "smtp.163.com"; // 服务器地址 

	private String sender = "xuda27@163.com"; // 发件人的邮箱

	private String receiver; // 收件人的邮箱

	private String name = "达达软件团队"; // 发件人昵称

	private String username = "xuda27@163.com"; // 账号

	private String password = "zaqwsx13579"; // 密码

	private String subject = "[达达软件]找回您的帐户密码"; // 主题

	private String message = "你的验证码是："; // 信息(支持HTML)

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
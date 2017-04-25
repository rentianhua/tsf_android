package com.dumu.housego.entity;

public class User {
	private String userid;
	private String username;
	private String modelid;
	private String password;
	private String password2;
	private String yzm;

	
	public User(String userid, String username, String modelid, String password, String password2, String yzm) {
		super();
		this.userid = userid;
		this.username = username;
		this.modelid = modelid;
		this.password = password;
		this.password2 = password2;
		this.yzm = yzm;
	}

	public User() {
		super();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", modelid=" + modelid + ", password=" + password
				+ ", password2=" + password2 + ", yzm=" + yzm + "]";
	}

}

package com.dumu.housego.entity;

public class UpLoadHtsfz {
	private String id;
	private String username;
	private String contract;
	private String idcard;
	public UpLoadHtsfz(String id, String username, String contract, String idcard) {
		super();
		this.id = id;
		this.username = username;
		this.contract = contract;
		this.idcard = idcard;
	}
	public UpLoadHtsfz() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "UpLoadHtsfz [id=" + id + ", username=" + username + ", contract=" + contract + ", idcard=" + idcard
				+ "]";
	}
	
	
	
	
}

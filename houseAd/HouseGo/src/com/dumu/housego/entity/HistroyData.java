package com.dumu.housego.entity;

public class HistroyData {
	private String title;
	private String username;
	private String inputtime;
	private String action;
	private String type;
	public HistroyData(String title, String username, String inputtime, String action, String type) {
		super();
		this.title = title;
		this.username = username;
		this.inputtime = inputtime;
		this.action = action;
		this.type = type;
	}
	public HistroyData() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "HistroyData [title=" + title + ", username=" + username + ", inputtime=" + inputtime + ", action="
				+ action + ", type=" + type + "]";
	}
	
	
	
}

package com.dumu.housego.entity;

public class UpLoadPic {
	private String aid;
	private String url;
	private String picname;
	public UpLoadPic(String aid, String url, String picname) {
		super();
		this.aid = aid;
		this.url = url;
		this.picname = picname;
	}
	public UpLoadPic() {
		super();
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	@Override
	public String toString() {
		return "UpLoadPic [aid=" + aid + ", url=" + url + ", picname=" + picname + "]";
	}
	
	
	
	
	
}

package com.dumu.housego.entity;

import java.io.Serializable;

public class Pics implements Serializable{
	private String url;
	private String alt;
	private String imagepath;
	
	public Pics(String url, String alt,String imagepath) {
		super();
		this.url = url;
		this.alt = alt;
		this.imagepath=imagepath;
	}
	public Pics() {
		super();
	}
	
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	@Override
	public String toString() {
		return "Pics [url=" + url + ", alt=" + alt + ", imagepath=" + imagepath + "]";
	}
	
	
	
}

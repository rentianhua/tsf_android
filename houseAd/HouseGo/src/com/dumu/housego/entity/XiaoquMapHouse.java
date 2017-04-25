package com.dumu.housego.entity;

public class XiaoquMapHouse {
	private String id;
	private String catid;
	private String title;
	private String house_count;
	private String jingweidu;
	public XiaoquMapHouse(String id, String catid, String title, String house_count, String jingweidu) {
		super();
		this.id = id;
		this.catid = catid;
		this.title = title;
		this.house_count = house_count;
		this.jingweidu = jingweidu;
	}
	public XiaoquMapHouse() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHouse_count() {
		return house_count;
	}
	public void setHouse_count(String house_count) {
		this.house_count = house_count;
	}
	public String getJingweidu() {
		return jingweidu;
	}
	public void setJingweidu(String jingweidu) {
		this.jingweidu = jingweidu;
	}
	@Override
	public String toString() {
		return "XiaoquMapHouse [id=" + id + ", catid=" + catid + ", title=" + title + ", house_count=" + house_count
				+ ", jingweidu=" + jingweidu + "]";
	}
	
	
	
	
	
	
	
}


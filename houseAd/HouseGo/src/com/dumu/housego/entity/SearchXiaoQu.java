package com.dumu.housego.entity;

public class SearchXiaoQu {
	private String id;
	private String  title;
	public SearchXiaoQu(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public SearchXiaoQu() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SearchXiaoQu [id=" + id + ", title=" + title + "]";
	}
	
	
	
}

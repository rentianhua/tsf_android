package com.dumu.housego.entity;

public class AssignContentList {
	private String catid;
	private String page;
	private String ct;
	private String ar;
	private String order;

	public AssignContentList(String catid, String page, String ct, String ar, String order) {
		super();
		this.catid = catid;
		this.page = page;
		this.ct = ct;
		this.ar = ar;
		this.order = order;
	}

	public AssignContentList() {
		super();
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "AssignContentList [catid=" + catid + ", page=" + page + ", ct=" + ct + ", ar=" + ar + ", order=" + order
				+ "]";
	}

}

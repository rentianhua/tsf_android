package com.dumu.housego.entity;

public class AreaHouse {
	private String id;
	private String pid;
	private String name;
	private String cid;
	private String house_count;

	public AreaHouse(String id, String pid, String name, String cid, String house_count) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.cid = cid;
		this.house_count = house_count;
	}

	public AreaHouse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getHouse_count() {
		return house_count;
	}

	public void setHouse_count(String house_count) {
		this.house_count = house_count;
	}

	@Override
	public String toString() {
		return "AreaHouse [id=" + id + ", pid=" + pid + ", name=" + name + ", cid=" + cid + ", house_count="
				+ house_count + "]";
	}

}

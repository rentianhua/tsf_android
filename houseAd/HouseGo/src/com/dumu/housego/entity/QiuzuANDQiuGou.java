package com.dumu.housego.entity;

public class QiuzuANDQiuGou {
	private String id;
	private String username;
	private String chenghu;
	private String inputtime;
	private String city_name;
	private String area_name;
	private String zujinrange;
	private String zongjiarange;
	private String lock;
	public QiuzuANDQiuGou(String id,String username, String chenghu, String inputtime, String city_name, String area_name,
			String zujinrange, String zongjiarange, String lock) {
		super();
		this.username = username;
		this.chenghu = chenghu;
		this.inputtime = inputtime;
		this.city_name = city_name;
		this.area_name = area_name;
		this.zujinrange = zujinrange;
		this.zongjiarange = zongjiarange;
		this.lock = lock;
	}
	public QiuzuANDQiuGou() {
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
	public String getChenghu() {
		return chenghu;
	}
	public void setChenghu(String chenghu) {
		this.chenghu = chenghu;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getZujinrange() {
		return zujinrange;
	}
	public void setZujinrange(String zujinrange) {
		this.zujinrange = zujinrange;
	}
	public String getZongjiarange() {
		return zongjiarange;
	}
	public void setZongjiarange(String zongjiarange) {
		this.zongjiarange = zongjiarange;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	@Override
	public String toString() {
		return "QiuzuANDQiuGou [id=" + id + ", username=" + username + ", chenghu=" + chenghu + ", inputtime="
				+ inputtime + ", city_name=" + city_name + ", area_name=" + area_name + ", zujinrange=" + zujinrange
				+ ", zongjiarange=" + zongjiarange + ", lock=" + lock + "]";
	}

	
	
	
}

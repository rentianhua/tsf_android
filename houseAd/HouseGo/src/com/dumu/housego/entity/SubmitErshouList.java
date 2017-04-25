package com.dumu.housego.entity;

import java.io.Serializable;

public class SubmitErshouList implements Serializable {
	private String id;
	private String catid;
	private String thumb;
	private String username;
	private String ceng;
	private String title;
	private String xiaoquname;
	private String cityname;
	private String areaname;
	private String loudong;
	private String menpai;
	private String zongceng;
	private String zongjia;

	public SubmitErshouList(String id, String catid, String thumb, String username, String ceng, String title,
			String xiaoquname, String cityname, String areaname, String loudong, String menpai, String zongceng,
			String zongjia) {
		super();
		this.id = id;
		this.catid = catid;
		this.thumb = thumb;
		this.username = username;
		this.ceng = ceng;
		this.title = title;
		this.xiaoquname = xiaoquname;
		this.cityname = cityname;
		this.areaname = areaname;
		this.loudong = loudong;
		this.menpai = menpai;
		this.zongceng = zongceng;
		this.zongjia = zongjia;
	}

	public SubmitErshouList() {
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

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCeng() {
		return ceng;
	}

	public void setCeng(String ceng) {
		this.ceng = ceng;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXiaoquname() {
		return xiaoquname;
	}

	public void setXiaoquname(String xiaoquname) {
		this.xiaoquname = xiaoquname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getLoudong() {
		return loudong;
	}

	public void setLoudong(String loudong) {
		this.loudong = loudong;
	}

	public String getMenpai() {
		return menpai;
	}

	public void setMenpai(String menpai) {
		this.menpai = menpai;
	}

	public String getZongceng() {
		return zongceng;
	}

	public void setZongceng(String zongceng) {
		this.zongceng = zongceng;
	}

	public String getZongjia() {
		return zongjia;
	}

	public void setZongjia(String zongjia) {
		this.zongjia = zongjia;
	}

	@Override
	public String toString() {
		return "SubmitErshouList [id=" + id + ", catid=" + catid + ", thumb=" + thumb + ", username=" + username
				+ ", ceng=" + ceng + ", title=" + title + ", xiaoquname=" + xiaoquname + ", cityname=" + cityname
				+ ", areaname=" + areaname + ", loudong=" + loudong + ", menpai=" + menpai + ", zongceng=" + zongceng
				+ ", zongjia=" + zongjia + "]";
	}

}

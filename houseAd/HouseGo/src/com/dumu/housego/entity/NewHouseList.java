package com.dumu.housego.entity;

public class NewHouseList {
	private String id;
	private String catid;
	private String typeid;
	private String title;
	private String thumb;
	private String loupandizhi;
	private String junjia;
	private String cityname;
	private String areaname;
	private String zonghe;
	private String kaipandate;
	private String shiarea;
	private String mianjiarea;
	
	public NewHouseList(String zonghe,String id, String catid, String typeid, String title, String thumb, String loupandizhi,
			String junjia, String cityname, String areaname,String kaipandate,String shiarea,String mianjiarea) {
		super();
		this.zonghe=zonghe;
		this.id = id;
		this.catid = catid;
		this.typeid = typeid;
		this.title = title;
		this.thumb = thumb;
		this.loupandizhi = loupandizhi;
		this.junjia = junjia;
		this.cityname = cityname;
		this.areaname = areaname;
		this.kaipandate = kaipandate;
		this.shiarea = shiarea;
		this.mianjiarea = mianjiarea;
	}

	public NewHouseList() {
		super();
	}

	
	
	public String getZonghe() {
		return zonghe;
	}

	public void setZonghe(String zonghe) {
		this.zonghe = zonghe;
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

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getLoupandizhi() {
		return loupandizhi;
	}

	public void setLoupandizhi(String loupandizhi) {
		this.loupandizhi = loupandizhi;
	}

	public String getJunjia() {
		return junjia;
	}

	public void setJunjia(String junjia) {
		this.junjia = junjia;
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

	public String getKaipandate() {
		return kaipandate;
	}

	public void setKaipandate(String kaipandate) {
		this.kaipandate = kaipandate;
	}

	public String getShiarea() {
		return shiarea;
	}

	public void setShiarea(String shiarea) {
		this.shiarea = shiarea;
	}

	public String getMianjiarea() {
		return mianjiarea;
	}

	public void setMianjiarea(String mianjiarea) {
		this.mianjiarea = mianjiarea;
	}

	@Override
	public String toString() {
		return "NewHouseList [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", title=" + title + ", thumb="
				+ thumb + ", loupandizhi=" + loupandizhi + ", junjia=" + junjia + ", cityname=" + cityname
				+ ", areaname=" + areaname + ", zonghe=" + zonghe + ", kaipandate=" + kaipandate + ", shiarea=" + shiarea
				+ ", mianjiarea=" + mianjiarea+ "]";
	}

}

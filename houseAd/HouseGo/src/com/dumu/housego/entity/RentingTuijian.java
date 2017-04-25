package com.dumu.housego.entity;

public class RentingTuijian {
	private String title;
	private String province_name;
	private String city_name;
	private String area_name;
	
	private String thumb;
	private String id;
	private String catid;
	
	private String zujin;

	public RentingTuijian(String title, String province_name, String city_name, String area_name, String thumb,
			String id, String catid, String zujin) {
		super();
		this.title = title;
		this.province_name = province_name;
		this.city_name = city_name;
		this.area_name = area_name;
		this.thumb = thumb;
		this.id = id;
		this.catid = catid;
		this.zujin = zujin;
	}

	public RentingTuijian() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
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

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
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

	public String getZujin() {
		return zujin;
	}

	public void setZujin(String zujin) {
		this.zujin = zujin;
	}

	@Override
	public String toString() {
		return "RentingTuijian [title=" + title + ", province_name=" + province_name + ", city_name=" + city_name
				+ ", area_name=" + area_name + ", thumb=" + thumb + ", id=" + id + ", catid=" + catid + ", zujin="
				+ zujin + "]";
	}

	
	
	
	
	
	
	
	
	
}	

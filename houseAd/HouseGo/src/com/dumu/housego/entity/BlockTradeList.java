package com.dumu.housego.entity;

import java.io.Serializable;

public class BlockTradeList implements Serializable {
	private String id;
	private String catid;
	private String posid;
	private String module;
	private String modelid;

	private String title;
	private String province;
	private String thumb;
	private String zongjia;
	private String area;
	private String city;
	private String style;
	private String url;
	private String provincename;
	private String cityname;
	private String areaname;
	private String hezuofangshi;
	private String wuyetype;
	private String zhandimianji;
	private String shiyongnianxian;
	private String listorder;
	private String expiration;
	private String extention;
	private String synedit;
	
	private String zonghe;
	
	public BlockTradeList(String zonghe,String id, String catid, String posid, String module, String modelid, String title,
			String province, String thumb, String zongjia, String area, String city, String style, String url,
			String provincename, String cityname, String areaname, String hezuofangshi, String wuyetype,
			String zhandimianji, String shiyongnianxian, String listorder, String expiration, String extention,
			String synedit) {
		super();
		this.zonghe=zonghe;
		this.id = id;
		this.catid = catid;
		this.posid = posid;
		this.module = module;
		this.modelid = modelid;
		this.title = title;
		this.province = province;
		this.thumb = thumb;
		this.zongjia = zongjia;
		this.area = area;
		this.city = city;
		this.style = style;
		this.url = url;
		this.provincename = provincename;
		this.cityname = cityname;
		this.areaname = areaname;
		this.hezuofangshi = hezuofangshi;
		this.wuyetype = wuyetype;
		this.zhandimianji = zhandimianji;
		this.shiyongnianxian = shiyongnianxian;
		this.listorder = listorder;
		this.expiration = expiration;
		this.extention = extention;
		this.synedit = synedit;
	}
	public BlockTradeList() {
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
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getZongjia() {
		return zongjia;
	}
	public void setZongjia(String zongjia) {
		this.zongjia = zongjia;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
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
	public String getHezuofangshi() {
		return hezuofangshi;
	}
	public void setHezuofangshi(String hezuofangshi) {
		this.hezuofangshi = hezuofangshi;
	}
	public String getWuyetype() {
		return wuyetype;
	}
	public void setWuyetype(String wuyetype) {
		this.wuyetype = wuyetype;
	}
	public String getZhandimianji() {
		return zhandimianji;
	}
	public void setZhandimianji(String zhandimianji) {
		this.zhandimianji = zhandimianji;
	}
	public String getShiyongnianxian() {
		return shiyongnianxian;
	}
	public void setShiyongnianxian(String shiyongnianxian) {
		this.shiyongnianxian = shiyongnianxian;
	}
	public String getListorder() {
		return listorder;
	}
	public void setListorder(String listorder) {
		this.listorder = listorder;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getSynedit() {
		return synedit;
	}
	public void setSynedit(String synedit) {
		this.synedit = synedit;
	}
	@Override
	public String toString() {
		return "BlockTradeList [id=" + id + ", catid=" + catid + ", posid=" + posid + ", module=" + module
				+ ", modelid=" + modelid + ", title=" + title + ", province=" + province + ", thumb=" + thumb
				+ ", zongjia=" + zongjia + ", area=" + area + ", city=" + city + ", style=" + style + ", url=" + url
				+ ", provincename=" + provincename + ", cityname=" + cityname + ", areaname=" + areaname
				+ ", hezuofangshi=" + hezuofangshi + ", wuyetype=" + wuyetype + ", zhandimianji=" + zhandimianji
				+ ", shiyongnianxian=" + shiyongnianxian + ", listorder=" + listorder + ", expiration=" + expiration
				+ ", extention=" + extention + ", synedit=" + synedit + ", zonghe=" + zonghe + "]";
	}

	
}

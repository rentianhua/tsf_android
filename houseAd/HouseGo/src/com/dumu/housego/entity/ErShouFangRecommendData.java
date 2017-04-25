package com.dumu.housego.entity;

import java.util.List;

public class ErShouFangRecommendData {
	private String id;
	private String catid;
	private String module;
	private String modelid;

	private String listorder;
	private String expiration;
	private String extention;
	private String synedit;

	private String title;
	private String province;
	private String xiaoquname;
	private String zongjia;
	private String jianzhumianji;
	private String ting;
	private String shi;
	private String thumb;
	private String city;
	private String area;
	private String posid;
	private String style;
	private String url;
	private String provincename;
	private String cityname;
	private String areaname;
	private String city_name;
	private String area_name;
	private String chaoxiang;
	private String biaoqian;
	private String wei;
	private String zaishou;
	private String lock;
	private List<Pics>pics;
	
	private String zonghe;
	
	
	public ErShouFangRecommendData(String zonghe,String id, String catid, String module, String modelid, String listorder,
			String expiration, String extention, String synedit, String title, String province, String xiaoquname,
			String zongjia, String jianzhumianji, String ting, String shi, String thumb, String city, String area,
			String posid, String style, String url, String provincename, String cityname, String areaname,
			String chaoxiang, String biaoqian,String city_name,String area_name, String wei, String zaishou, String lock, List<Pics> pics) {
		super();
		this.zonghe=zonghe;
		this.city_name=city_name;
		this.area_name=area_name;
		this.id = id;
		this.catid = catid;
		this.module = module;
		this.modelid = modelid;
		this.listorder = listorder;
		this.expiration = expiration;
		this.extention = extention;
		this.synedit = synedit;
		this.title = title;
		this.province = province;
		this.xiaoquname = xiaoquname;
		this.zongjia = zongjia;
		this.jianzhumianji = jianzhumianji;
		this.ting = ting;
		this.shi = shi;
		this.thumb = thumb;
		this.city = city;
		this.area = area;
		this.posid = posid;
		this.style = style;
		this.url = url;
		this.provincename = provincename;
		this.cityname = cityname;
		this.areaname = areaname;
		this.chaoxiang = chaoxiang;
		this.biaoqian = biaoqian;
		this.wei = wei;
		this.zaishou = zaishou;
		this.lock = lock;
		this.pics = pics;
	}
	public ErShouFangRecommendData() {
		super();
	}
	
	
	public String getZonghe() {
		return zonghe;
	}
	public void setZonghe(String zonghe) {
		this.zonghe = zonghe;
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
	public String getXiaoquname() {
		return xiaoquname;
	}
	public void setXiaoquname(String xiaoquname) {
		this.xiaoquname = xiaoquname;
	}
	public String getZongjia() {
		return zongjia;
	}
	public void setZongjia(String zongjia) {
		this.zongjia = zongjia;
	}
	public String getJianzhumianji() {
		return jianzhumianji;
	}
	public void setJianzhumianji(String jianzhumianji) {
		this.jianzhumianji = jianzhumianji;
	}
	public String getTing() {
		return ting;
	}
	public void setTing(String ting) {
		this.ting = ting;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
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
	public String getChaoxiang() {
		return chaoxiang;
	}
	public void setChaoxiang(String chaoxiang) {
		this.chaoxiang = chaoxiang;
	}
	public String getBiaoqian() {
		return biaoqian;
	}
	public void setBiaoqian(String biaoqian) {
		this.biaoqian = biaoqian;
	}
	public String getWei() {
		return wei;
	}
	public void setWei(String wei) {
		this.wei = wei;
	}
	public String getZaishou() {
		return zaishou;
	}
	public void setZaishou(String zaishou) {
		this.zaishou = zaishou;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public List<Pics> getPics() {
		return pics;
	}
	public void setPics(List<Pics> pics) {
		this.pics = pics;
	}
	
	@Override
	public String toString() {
		return "ErShouFangRecommendData [id=" + id + ", catid=" + catid + ", module=" + module + ", modelid=" + modelid
				+ ", listorder=" + listorder + ", expiration=" + expiration + ", extention=" + extention + ", synedit="
				+ synedit + ", title=" + title + ", province=" + province + ", xiaoquname=" + xiaoquname + ", zongjia="
				+ zongjia + ", jianzhumianji=" + jianzhumianji + ", ting=" + ting + ", shi=" + shi + ", thumb=" + thumb
				+ ", city=" + city + ", area=" + area + ", posid=" + posid + ", style=" + style + ", url=" + url
				+ ", provincename=" + provincename + ", cityname=" + cityname + ", areaname=" + areaname
				+ ", city_name=" + city_name + ", area_name=" + area_name + ", chaoxiang=" + chaoxiang + ", biaoqian="
				+ biaoqian + ", wei=" + wei + ", zaishou=" + zaishou + ", lock=" + lock + ", pics=" + pics + ", zonghe="
				+ zonghe + "]";
	}
	
	
	
	
	

}

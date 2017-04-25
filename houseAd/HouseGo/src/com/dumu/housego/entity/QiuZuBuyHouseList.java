package com.dumu.housego.entity;

public class QiuZuBuyHouseList {
	private String id;
	private String catid;
	private String typeid;
	private String title;
	private String thumb;
	private String keywords;
	private String posid;
	private String listorder;
	private String status;
	private String sysadd;
	private String islink;
	private String username;
	private String inputtime;
	private String updatetime;
	private String viewsupdatetime;
	private String zujinrange;
	private String chenghu;
	private String area;
	private String city;
	private String province;
	private String shi;
	private String zulin;
	private String lock;
	private String zongjiarange;

	public QiuZuBuyHouseList(String id, String catid, String typeid, String title, String thumb, String keywords,
			String posid, String listorder, String status, String sysadd, String islink, String username,
			String inputtime, String updatetime, String viewsupdatetime, String zujinrange, String chenghu, String area,
			String city, String province, String shi, String zulin, String lock, String zongjiarange) {
		super();
		this.id = id;
		this.catid = catid;
		this.typeid = typeid;
		this.title = title;
		this.thumb = thumb;
		this.keywords = keywords;
		this.posid = posid;
		this.listorder = listorder;
		this.status = status;
		this.sysadd = sysadd;
		this.islink = islink;
		this.username = username;
		this.inputtime = inputtime;
		this.updatetime = updatetime;
		this.viewsupdatetime = viewsupdatetime;
		this.zujinrange = zujinrange;
		this.chenghu = chenghu;
		this.area = area;
		this.city = city;
		this.province = province;
		this.shi = shi;
		this.zulin = zulin;
		this.lock = lock;
		this.zongjiarange = zongjiarange;
	}

	public QiuZuBuyHouseList() {
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getListorder() {
		return listorder;
	}

	public void setListorder(String listorder) {
		this.listorder = listorder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSysadd() {
		return sysadd;
	}

	public void setSysadd(String sysadd) {
		this.sysadd = sysadd;
	}

	public String getIslink() {
		return islink;
	}

	public void setIslink(String islink) {
		this.islink = islink;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInputtime() {
		return inputtime;
	}

	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getViewsupdatetime() {
		return viewsupdatetime;
	}

	public void setViewsupdatetime(String viewsupdatetime) {
		this.viewsupdatetime = viewsupdatetime;
	}

	public String getZujinrange() {
		return zujinrange;
	}

	public void setZujinrange(String zujinrange) {
		this.zujinrange = zujinrange;
	}

	public String getChenghu() {
		return chenghu;
	}

	public void setChenghu(String chenghu) {
		this.chenghu = chenghu;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getZulin() {
		return zulin;
	}

	public void setZulin(String zulin) {
		this.zulin = zulin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getZongjiarange() {
		return zongjiarange;
	}

	public void setZongjiarange(String zongjiarange) {
		this.zongjiarange = zongjiarange;
	}

	@Override
	public String toString() {
		return "QiuZuList [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", title=" + title + ", thumb="
				+ thumb + ", keywords=" + keywords + ", posid=" + posid + ", listorder=" + listorder + ", status="
				+ status + ", sysadd=" + sysadd + ", islink=" + islink + ", username=" + username + ", inputtime="
				+ inputtime + ", updatetime=" + updatetime + ", viewsupdatetime=" + viewsupdatetime + ", zujinrange="
				+ zujinrange + ", chenghu=" + chenghu + ", area=" + area + ", city=" + city + ", province=" + province
				+ ", shi=" + shi + ", zulin=" + zulin + ", lock=" + lock + ",zongjiarange=" + zongjiarange + "]";
	}

}

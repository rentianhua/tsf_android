package com.dumu.housego.entity;

public class YuYueData implements Comparable<YuYueData> {
	private String fromuser;
	private String title;
	private String houseid;
	private String type;
	private String id;
	private String username;
	private String yuyuetime;
	private String fromtable;
	private String listorder;
	private String fromid;
	private String yuyuedate;
	private String catid;
	private String lock;
	private String inputtime;
	private String zhuangtai;

	public YuYueData(String fromuser, String title, String type, String id, String username, String yuyuetime,
			String fromtable, String listorder, String fromid, String yuyuedate, String catid, String lock,
			String inputtime, String zhuangtai,String houseid) {
		super();
		this.houseid=houseid;
		this.fromuser = fromuser;
		this.title = title;
		this.type = type;
		this.id = id;
		this.username = username;
		this.yuyuetime = yuyuetime;
		this.fromtable = fromtable;
		this.listorder = listorder;
		this.fromid = fromid;
		this.yuyuedate = yuyuedate;
		this.catid = catid;
		this.lock = lock;
		this.inputtime = inputtime;
		this.zhuangtai = zhuangtai;
	}

	public String getHouseid() {
		return houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}

	public String getFromuser() {
		return fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getYuyuetime() {
		return yuyuetime;
	}

	public void setYuyuetime(String yuyuetime) {
		this.yuyuetime = yuyuetime;
	}

	public String getFromtable() {
		return fromtable;
	}

	public void setFromtable(String fromtable) {
		this.fromtable = fromtable;
	}

	public String getListorder() {
		return listorder;
	}

	public void setListorder(String listorder) {
		this.listorder = listorder;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public String getYuyuedate() {
		return yuyuedate;
	}

	public void setYuyuedate(String yuyuedate) {
		this.yuyuedate = yuyuedate;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getInputtime() {
		return inputtime;
	}

	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public YuYueData() {
		super();
	}

	@Override
	public String toString() {
		return "YuYueData [fromuser=" + fromuser + ", title=" + title + ", houseid=" + houseid + ", type=" + type
				+ ", id=" + id + ", username=" + username + ", yuyuetime=" + yuyuetime + ", fromtable=" + fromtable
				+ ", listorder=" + listorder + ", fromid=" + fromid + ", yuyuedate=" + yuyuedate + ", catid=" + catid
				+ ", lock=" + lock + ", inputtime=" + inputtime + ", zhuangtai=" + zhuangtai + "]";
	}

	@Override
	public int compareTo(YuYueData another) {
		int delta = another.inputtime.compareTo(this.inputtime);
		return delta;
	}

}

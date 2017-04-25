package com.dumu.housego.entity;

public class YHQinfo {

private String id;
private String catid;
private String title;
private String description;
private String new_id;
private String zhuangtai;
private String new_catid;
private String pay;
private String vmoney;
private String maxnum;
private String yigou;
public YHQinfo(String id, String catid, String title, String description, String new_id, String zhuangtai,
		String new_catid, String pay, String vmoney, String maxnum, String yigou) {
	super();
	this.id = id;
	this.catid = catid;
	this.title = title;
	this.description = description;
	this.new_id = new_id;
	this.zhuangtai = zhuangtai;
	this.new_catid = new_catid;
	this.pay = pay;
	this.vmoney = vmoney;
	this.maxnum = maxnum;
	this.yigou = yigou;
}
public YHQinfo() {
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
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getNew_id() {
	return new_id;
}
public void setNew_id(String new_id) {
	this.new_id = new_id;
}
public String getZhuangtai() {
	return zhuangtai;
}
public void setZhuangtai(String zhuangtai) {
	this.zhuangtai = zhuangtai;
}
public String getNew_catid() {
	return new_catid;
}
public void setNew_catid(String new_catid) {
	this.new_catid = new_catid;
}
public String getPay() {
	return pay;
}
public void setPay(String pay) {
	this.pay = pay;
}
public String getVmoney() {
	return vmoney;
}
public void setVmoney(String vmoney) {
	this.vmoney = vmoney;
}
public String getMaxnum() {
	return maxnum;
}
public void setMaxnum(String maxnum) {
	this.maxnum = maxnum;
}
public String getYigou() {
	return yigou;
}
public void setYigou(String yigou) {
	this.yigou = yigou;
}
@Override
public String toString() {
	return "YHQinfo [id=" + id + ", catid=" + catid + ", title=" + title + ", description=" + description + ", new_id="
			+ new_id + ", zhuangtai=" + zhuangtai + ", new_catid=" + new_catid + ", pay=" + pay + ", vmoney=" + vmoney
			+ ", maxnum=" + maxnum + ", yigou=" + yigou + "]";
}




}

package com.dumu.housego.entity;

import java.io.Serializable;

public class NewDongTai implements Serializable{
	 private String  id;
	 private String  catid;
	 private String  typeid;
	 private String  title;
	 private String  style;
	 private String  thumb;
	 private String  keywords;
	 private String  tags;
	 private String  description;
	 private String  posid;
	 private String  url;
	 private String  listorder;
	 private String  status;
	 private String  sysadd;
	 private String  islink;
	 private String  username;
	 private String  inputtime;
	 private String  updatetime;
	 private String  views;
	 private String  yesterdayviews;
	 private String  dayviews;
	 private String  weekviews;
	 private String  monthviews;
	 private String  viewsupdatetime;
	 private String  biaoqian;
	 private String  new_id;
	public NewDongTai(String id, String catid, String typeid, String title, String style, String thumb, String keywords,
			String tags, String description, String posid, String url, String listorder, String status, String sysadd,
			String islink, String username, String inputtime, String updatetime, String views, String yesterdayviews,
			String dayviews, String weekviews, String monthviews, String viewsupdatetime, String biaoqian,
			String new_id) {
		super();
		this.id = id;
		this.catid = catid;
		this.typeid = typeid;
		this.title = title;
		this.style = style;
		this.thumb = thumb;
		this.keywords = keywords;
		this.tags = tags;
		this.description = description;
		this.posid = posid;
		this.url = url;
		this.listorder = listorder;
		this.status = status;
		this.sysadd = sysadd;
		this.islink = islink;
		this.username = username;
		this.inputtime = inputtime;
		this.updatetime = updatetime;
		this.views = views;
		this.yesterdayviews = yesterdayviews;
		this.dayviews = dayviews;
		this.weekviews = weekviews;
		this.monthviews = monthviews;
		this.viewsupdatetime = viewsupdatetime;
		this.biaoqian = biaoqian;
		this.new_id = new_id;
	}
	public NewDongTai() {
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getYesterdayviews() {
		return yesterdayviews;
	}
	public void setYesterdayviews(String yesterdayviews) {
		this.yesterdayviews = yesterdayviews;
	}
	public String getDayviews() {
		return dayviews;
	}
	public void setDayviews(String dayviews) {
		this.dayviews = dayviews;
	}
	public String getWeekviews() {
		return weekviews;
	}
	public void setWeekviews(String weekviews) {
		this.weekviews = weekviews;
	}
	public String getMonthviews() {
		return monthviews;
	}
	public void setMonthviews(String monthviews) {
		this.monthviews = monthviews;
	}
	public String getViewsupdatetime() {
		return viewsupdatetime;
	}
	public void setViewsupdatetime(String viewsupdatetime) {
		this.viewsupdatetime = viewsupdatetime;
	}
	public String getBiaoqian() {
		return biaoqian;
	}
	public void setBiaoqian(String biaoqian) {
		this.biaoqian = biaoqian;
	}
	public String getNew_id() {
		return new_id;
	}
	public void setNew_id(String new_id) {
		this.new_id = new_id;
	}
	@Override
	public String toString() {
		return "NewDongTai [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", title=" + title + ", style="
				+ style + ", thumb=" + thumb + ", keywords=" + keywords + ", tags=" + tags + ", description="
				+ description + ", posid=" + posid + ", url=" + url + ", listorder=" + listorder + ", status=" + status
				+ ", sysadd=" + sysadd + ", islink=" + islink + ", username=" + username + ", inputtime=" + inputtime
				+ ", updatetime=" + updatetime + ", views=" + views + ", yesterdayviews=" + yesterdayviews
				+ ", dayviews=" + dayviews + ", weekviews=" + weekviews + ", monthviews=" + monthviews
				+ ", viewsupdatetime=" + viewsupdatetime + ", biaoqian=" + biaoqian + ", new_id=" + new_id + "]";
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

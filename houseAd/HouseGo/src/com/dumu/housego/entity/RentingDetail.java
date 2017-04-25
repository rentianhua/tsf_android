package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RentingDetail implements Serializable{
	private String id;
	private String catid;
	private String typeid;
	private String modelid;
	private String userid;
	private String title;
	private String style;
	private String thumb;
	private String keywords;
	private String tags;
	private String description;
	private String posid;
	private String url;
	private String listorder;
	private String status;
	private String sysadd;
	private String islink;
	private String username;
	private String inputtime;
	private String updatetime;
	private String views;
	private String yesterdayviews;
	private String dayviews;
	private String weekviews;
	private String monthviews;
	private String viewsupdatetime;
	private String chaoxiang;
	private String louceng;
	private String zhuangxiu;
	private String zongceng;
	private String ceng;
	private String huxing;
	private String yangtai;
	private String wei;
	private String chu;
	private String ting;
	private String shi;
	private String shuxing;
	private String ditiexian;
	private String leixing;
	private String province;
	private String city;
	private String area;
	private String jingweidu;
	private String zuobiaodizhi;
	private String address;
	private String dituzuobiao;
	private String shenghuopeitao;
	private String jiaotong;
	private List<Pics> pics;
	private String mianji;
	private String zujin;
	private String fangwupeitao;
	private String hidetel;
	private String desc;
	private String yezhushuo;
	private String zulin;
	private String fukuan;
	private String biaoqian;
	private String xiaoqu;
	private String liangdian;
	private String huxingjieshao;
	private String zxdesc;
	private String czreason;
	private String xiaoquintro;
	private String fangling;
	private String jianzhutype;
	private String wuyetype;
	private String xiaoqutype;
	private String pub_type;
	private String jjr_id;
	private String fenpei_time;
	private String lock;
	private String zaizu;
	private String bianhao;
	private String cityname;
	private String areaname;
	private String xiaoquname;
	private String contract;
	private String idcard;
	private String apply_state;
	public List<RentingDetail> tongqu;
	
	
	public RentingDetail( String apply_state,String contract,String idcard,String id, String catid, String typeid, String modelid, String userid, String title,
			String style, String thumb, String keywords, String tags, String description, String posid, String url,
			String listorder, String status, String sysadd, String islink, String username, String inputtime,
			String updatetime, String views, String yesterdayviews, String dayviews, String weekviews,
			String monthviews, String viewsupdatetime, String chaoxiang, String louceng, String zhuangxiu,
			String zongceng, String ceng, String huxing, String yangtai, String wei, String chu, String ting,
			String shi, String shuxing, String ditiexian, String leixing, String province, String city, String area,
			String jingweidu, String zuobiaodizhi, String address, String dituzuobiao, String shenghuopeitao,
			String jiaotong, List<Pics> pics, String mianji, String zujin, String fangwupeitao, String hidetel,
			String desc, String yezhushuo, String zulin, String fukuan, String biaoqian, String xiaoqu,
			String liangdian, String huxingjieshao, String zxdesc, String czreason, String xiaoquintro, String fangling,
			String jianzhutype, String wuyetype, String xiaoqutype, String pub_type, String jjr_id, String fenpei_time,
			String lock, String zaizu, String bianhao, String cityname, String areaname, String xiaoquname) {
		super();
		
		this.apply_state=apply_state;
		this.contract=contract;
		this.idcard=idcard;
		this.id = id;
		this.catid = catid;
		this.typeid = typeid;
		this.modelid = modelid;
		this.userid = userid;
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
		this.chaoxiang = chaoxiang;
		this.louceng = louceng;
		this.zhuangxiu = zhuangxiu;
		this.zongceng = zongceng;
		this.ceng = ceng;
		this.huxing = huxing;
		this.yangtai = yangtai;
		this.wei = wei;
		this.chu = chu;
		this.ting = ting;
		this.shi = shi;
		this.shuxing = shuxing;
		this.ditiexian = ditiexian;
		this.leixing = leixing;
		this.province = province;
		this.city = city;
		this.area = area;
		this.jingweidu = jingweidu;
		this.zuobiaodizhi = zuobiaodizhi;
		this.address = address;
		this.dituzuobiao = dituzuobiao;
		this.shenghuopeitao = shenghuopeitao;
		this.jiaotong = jiaotong;
		this.pics = pics;
		this.mianji = mianji;
		this.zujin = zujin;
		this.fangwupeitao = fangwupeitao;
		this.hidetel = hidetel;
		this.desc = desc;
		this.yezhushuo = yezhushuo;
		this.zulin = zulin;
		this.fukuan = fukuan;
		this.biaoqian = biaoqian;
		this.xiaoqu = xiaoqu;
		this.liangdian = liangdian;
		this.huxingjieshao = huxingjieshao;
		this.zxdesc = zxdesc;
		this.czreason = czreason;
		this.xiaoquintro = xiaoquintro;
		this.fangling = fangling;
		this.jianzhutype = jianzhutype;
		this.wuyetype = wuyetype;
		this.xiaoqutype = xiaoqutype;
		this.pub_type = pub_type;
		this.jjr_id = jjr_id;
		this.fenpei_time = fenpei_time;
		this.lock = lock;
		this.zaizu = zaizu;
		this.bianhao = bianhao;
		this.cityname = cityname;
		this.areaname = areaname;
		this.xiaoquname = xiaoquname;
	}
	public RentingDetail() {
		super();
	}
	
	
	public String getApply_state() {
		return apply_state;
	}
	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
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
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getChaoxiang() {
		return chaoxiang;
	}
	public void setChaoxiang(String chaoxiang) {
		this.chaoxiang = chaoxiang;
	}
	public String getLouceng() {
		return louceng;
	}
	public void setLouceng(String louceng) {
		this.louceng = louceng;
	}
	public String getZhuangxiu() {
		return zhuangxiu;
	}
	public void setZhuangxiu(String zhuangxiu) {
		this.zhuangxiu = zhuangxiu;
	}
	public String getZongceng() {
		return zongceng;
	}
	public void setZongceng(String zongceng) {
		this.zongceng = zongceng;
	}
	public String getCeng() {
		return ceng;
	}
	public void setCeng(String ceng) {
		this.ceng = ceng;
	}
	public String getHuxing() {
		return huxing;
	}
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	public String getYangtai() {
		return yangtai;
	}
	public void setYangtai(String yangtai) {
		this.yangtai = yangtai;
	}
	public String getWei() {
		return wei;
	}
	public void setWei(String wei) {
		this.wei = wei;
	}
	public String getChu() {
		return chu;
	}
	public void setChu(String chu) {
		this.chu = chu;
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
	public String getShuxing() {
		return shuxing;
	}
	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}
	public String getDitiexian() {
		return ditiexian;
	}
	public void setDitiexian(String ditiexian) {
		this.ditiexian = ditiexian;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getJingweidu() {
		return jingweidu;
	}
	public void setJingweidu(String jingweidu) {
		this.jingweidu = jingweidu;
	}
	public String getZuobiaodizhi() {
		return zuobiaodizhi;
	}
	public void setZuobiaodizhi(String zuobiaodizhi) {
		this.zuobiaodizhi = zuobiaodizhi;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDituzuobiao() {
		return dituzuobiao;
	}
	public void setDituzuobiao(String dituzuobiao) {
		this.dituzuobiao = dituzuobiao;
	}
	public String getShenghuopeitao() {
		return shenghuopeitao;
	}
	public void setShenghuopeitao(String shenghuopeitao) {
		this.shenghuopeitao = shenghuopeitao;
	}
	public String getJiaotong() {
		return jiaotong;
	}
	public void setJiaotong(String jiaotong) {
		this.jiaotong = jiaotong;
	}
	public List<Pics> getPics() {
		return pics;
	}
	public void setPics(List<Pics> pics) {
		this.pics = pics;
	}
	public String getMianji() {
		return mianji;
	}
	public void setMianji(String mianji) {
		this.mianji = mianji;
	}
	public String getZujin() {
		return zujin;
	}
	public void setZujin(String zujin) {
		this.zujin = zujin;
	}
	public String getFangwupeitao() {
		return fangwupeitao;
	}
	public void setFangwupeitao(String fangwupeitao) {
		this.fangwupeitao = fangwupeitao;
	}
	public String getHidetel() {
		return hidetel;
	}
	public void setHidetel(String hidetel) {
		this.hidetel = hidetel;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getYezhushuo() {
		return yezhushuo;
	}
	public void setYezhushuo(String yezhushuo) {
		this.yezhushuo = yezhushuo;
	}
	public String getZulin() {
		return zulin;
	}
	public void setZulin(String zulin) {
		this.zulin = zulin;
	}
	public String getFukuan() {
		return fukuan;
	}
	public void setFukuan(String fukuan) {
		this.fukuan = fukuan;
	}
	public String getBiaoqian() {
		return biaoqian;
	}
	public void setBiaoqian(String biaoqian) {
		this.biaoqian = biaoqian;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getLiangdian() {
		return liangdian;
	}
	public void setLiangdian(String liangdian) {
		this.liangdian = liangdian;
	}
	public String getHuxingjieshao() {
		return huxingjieshao;
	}
	public void setHuxingjieshao(String huxingjieshao) {
		this.huxingjieshao = huxingjieshao;
	}
	public String getZxdesc() {
		return zxdesc;
	}
	public void setZxdesc(String zxdesc) {
		this.zxdesc = zxdesc;
	}
	public String getCzreason() {
		return czreason;
	}
	public void setCzreason(String czreason) {
		this.czreason = czreason;
	}
	public String getXiaoquintro() {
		return xiaoquintro;
	}
	public void setXiaoquintro(String xiaoquintro) {
		this.xiaoquintro = xiaoquintro;
	}
	public String getFangling() {
		return fangling;
	}
	public void setFangling(String fangling) {
		this.fangling = fangling;
	}
	public String getJianzhutype() {
		return jianzhutype;
	}
	public void setJianzhutype(String jianzhutype) {
		this.jianzhutype = jianzhutype;
	}
	public String getWuyetype() {
		return wuyetype;
	}
	public void setWuyetype(String wuyetype) {
		this.wuyetype = wuyetype;
	}
	public String getXiaoqutype() {
		return xiaoqutype;
	}
	public void setXiaoqutype(String xiaoqutype) {
		this.xiaoqutype = xiaoqutype;
	}
	public String getPub_type() {
		return pub_type;
	}
	public void setPub_type(String pub_type) {
		this.pub_type = pub_type;
	}
	public String getJjr_id() {
		return jjr_id;
	}
	public void setJjr_id(String jjr_id) {
		this.jjr_id = jjr_id;
	}
	public String getFenpei_time() {
		return fenpei_time;
	}
	public void setFenpei_time(String fenpei_time) {
		this.fenpei_time = fenpei_time;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public String getZaizu() {
		return zaizu;
	}
	public void setZaizu(String zaizu) {
		this.zaizu = zaizu;
	}
	public String getBianhao() {
		return bianhao;
	}
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
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
	public String getXiaoquname() {
		return xiaoquname;
	}
	public void setXiaoquname(String xiaoquname) {
		this.xiaoquname = xiaoquname;
	}
	@Override
	public String toString() {
		return "RentingDetail [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", modelid=" + modelid
				+ ", userid=" + userid + ", title=" + title + ", style=" + style + ", thumb=" + thumb + ", keywords="
				+ keywords + ", tags=" + tags + ", description=" + description + ", posid=" + posid + ", url=" + url
				+ ", listorder=" + listorder + ", status=" + status + ", sysadd=" + sysadd + ", islink=" + islink
				+ ", username=" + username + ", inputtime=" + inputtime + ", updatetime=" + updatetime + ", views="
				+ views + ", yesterdayviews=" + yesterdayviews + ", dayviews=" + dayviews + ", weekviews=" + weekviews
				+ ", monthviews=" + monthviews + ", viewsupdatetime=" + viewsupdatetime + ", chaoxiang=" + chaoxiang
				+ ", louceng=" + louceng + ", zhuangxiu=" + zhuangxiu + ", zongceng=" + zongceng + ", ceng=" + ceng
				+ ", huxing=" + huxing + ", yangtai=" + yangtai + ", wei=" + wei + ", chu=" + chu + ", ting=" + ting
				+ ", shi=" + shi + ", shuxing=" + shuxing + ", ditiexian=" + ditiexian + ", leixing=" + leixing
				+ ", province=" + province + ", city=" + city + ", area=" + area + ", jingweidu=" + jingweidu
				+ ", zuobiaodizhi=" + zuobiaodizhi + ", address=" + address + ", dituzuobiao=" + dituzuobiao
				+ ", shenghuopeitao=" + shenghuopeitao + ", jiaotong=" + jiaotong + ", pics=" + pics + ", mianji="
				+ mianji + ", zujin=" + zujin + ", fangwupeitao=" + fangwupeitao + ", hidetel=" + hidetel + ", desc="
				+ desc + ", yezhushuo=" + yezhushuo + ", zulin=" + zulin + ", fukuan=" + fukuan + ", biaoqian="
				+ biaoqian + ", xiaoqu=" + xiaoqu + ", liangdian=" + liangdian + ", huxingjieshao=" + huxingjieshao
				+ ", zxdesc=" + zxdesc + ", czreason=" + czreason + ", xiaoquintro=" + xiaoquintro + ", fangling="
				+ fangling + ", jianzhutype=" + jianzhutype + ", wuyetype=" + wuyetype + ", xiaoqutype=" + xiaoqutype
				+ ", pub_type=" + pub_type + ", jjr_id=" + jjr_id + ", fenpei_time=" + fenpei_time + ", lock=" + lock
				+ ", zaizu=" + zaizu + ", bianhao=" + bianhao + ", cityname=" + cityname + ", areaname=" + areaname
				+ ", xiaoquname=" + xiaoquname + ", contract=" + contract + ", idcard=" + idcard + ", apply_state="
				+ apply_state + "]";
	}


	
	


	
	

}

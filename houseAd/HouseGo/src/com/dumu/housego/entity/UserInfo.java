package com.dumu.housego.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private String userid;
	private String username;
	private String password;
	private String encrypt;
	private String checked;
	private String sex;
	private String about;
	private String heat;
	private String theme;
	private String praise;
	private String attention;
	private String fans;
	private String share;
	private String nickname;
	private String userpic;
	private String regdate;
	private String lastdate;
	private String regip;
	private String lastip;
	private String loginnum;
	private String email;
	private String groupid;
	private String areaid;
	private String amount;
	private String point;
	private String modelid;
	private String message;
	private String islock;
	private String vip;
	private String overduedate;

	private String vtel;
	private String ctel;
	private String zhuanjie;
	private String deptid;
	private String agentid;

	private String realname;
	private String dengji;
	private String worktime;
	private String jiav;
	private String cardnumber;
	private String mainarea;
	private String leixing;
	private String biaoqian;
	private String sfzpic;
	private String coname;

	public UserInfo(String userid, String ctel,String username, String password, String encrypt, String checked, String sex,
			String about, String heat, String theme, String praise, String attention, String fans, String share,
			String nickname, String userpic, String regdate, String lastdate, String regip, String lastip,
			String loginnum, String email, String groupid, String areaid, String amount, String point, String modelid,
			String message, String islock, String vip, String overduedate, String vtel, String zhuanjie, String deptid,
			String agentid, String realname, String dengji, String worktime, String jiav, String cardnumber,
			String mainarea, String leixing, String biaoqian, String sfzpic, String coname) {
		super();
		this.ctel=ctel;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.encrypt = encrypt;
		this.checked = checked;
		this.sex = sex;
		this.about = about;
		this.heat = heat;
		this.theme = theme;
		this.praise = praise;
		this.attention = attention;
		this.fans = fans;
		this.share = share;
		this.nickname = nickname;
		this.userpic = userpic;
		this.regdate = regdate;
		this.lastdate = lastdate;
		this.regip = regip;
		this.lastip = lastip;
		this.loginnum = loginnum;
		this.email = email;
		this.groupid = groupid;
		this.areaid = areaid;
		this.amount = amount;
		this.point = point;
		this.modelid = modelid;
		this.message = message;
		this.islock = islock;
		this.vip = vip;
		this.overduedate = overduedate;
		this.vtel = vtel;
		this.zhuanjie = zhuanjie;
		this.deptid = deptid;
		this.agentid = agentid;
		this.realname = realname;
		this.dengji = dengji;
		this.worktime = worktime;
		this.jiav = jiav;
		this.cardnumber = cardnumber;
		this.mainarea = mainarea;
		this.leixing = leixing;
		this.biaoqian = biaoqian;
		this.sfzpic = sfzpic;
		this.coname = coname;
	}

	public UserInfo() {
		super();
	}

	public String getCtel() {
		return ctel;
	}

	public void setCtel(String ctel) {
		this.ctel = ctel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getHeat() {
		return heat;
	}

	public void setHeat(String heat) {
		this.heat = heat;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getFans() {
		return fans;
	}

	public void setFans(String fans) {
		this.fans = fans;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserpic() {
		return userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public String getLastip() {
		return lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	public String getLoginnum() {
		return loginnum;
	}

	public void setLoginnum(String loginnum) {
		this.loginnum = loginnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getOverduedate() {
		return overduedate;
	}

	public void setOverduedate(String overduedate) {
		this.overduedate = overduedate;
	}

	public String getVtel() {
		return vtel;
	}

	public void setVtel(String vtel) {
		this.vtel = vtel;
	}

	public String getZhuanjie() {
		return zhuanjie;
	}

	public void setZhuanjie(String zhuanjie) {
		this.zhuanjie = zhuanjie;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getDengji() {
		return dengji;
	}

	public void setDengji(String dengji) {
		this.dengji = dengji;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getJiav() {
		return jiav;
	}

	public void setJiav(String jiav) {
		this.jiav = jiav;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getMainarea() {
		return mainarea;
	}

	public void setMainarea(String mainarea) {
		this.mainarea = mainarea;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getBiaoqian() {
		return biaoqian;
	}

	public void setBiaoqian(String biaoqian) {
		this.biaoqian = biaoqian;
	}

	public String getSfzpic() {
		return sfzpic;
	}

	public void setSfzpic(String sfzpic) {
		this.sfzpic = sfzpic;
	}

	public String getConame() {
		return coname;
	}

	public void setConame(String coname) {
		this.coname = coname;
	}

	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", username=" + username + ", password=" + password + ", encrypt="
				+ encrypt + ", checked=" + checked + ", sex=" + sex + ", about=" + about + ", heat=" + heat + ", theme="
				+ theme + ", praise=" + praise + ", attention=" + attention + ", fans=" + fans + ", share=" + share
				+ ", nickname=" + nickname + ", userpic=" + userpic + ", regdate=" + regdate + ", lastdate=" + lastdate
				+ ", regip=" + regip + ", lastip=" + lastip + ", loginnum=" + loginnum + ", email=" + email
				+ ", groupid=" + groupid + ", areaid=" + areaid + ", amount=" + amount + ", point=" + point
				+ ", modelid=" + modelid + ", message=" + message + ", islock=" + islock + ", vip=" + vip
				+ ", overduedate=" + overduedate + ", vtel=" + vtel + ", ctel=" + ctel + ", zhuanjie=" + zhuanjie
				+ ", deptid=" + deptid + ", agentid=" + agentid + ", realname=" + realname + ", dengji=" + dengji
				+ ", worktime=" + worktime + ", jiav=" + jiav + ", cardnumber=" + cardnumber + ", mainarea=" + mainarea
				+ ", leixing=" + leixing + ", biaoqian=" + biaoqian + ", sfzpic=" + sfzpic + ", coname=" + coname + "]";
	}

}

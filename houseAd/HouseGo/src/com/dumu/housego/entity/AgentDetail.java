package com.dumu.housego.entity;

public class AgentDetail {
    private String userid;
    private String cardnumber;
    private String mainarea;
    private String sfzpic;
    private String leixing;
    private String coname;
    private String biaoqian;
    private String dengji;
    private String realname;
    private String worktime;
    private String jiav;
    private String base;
    private String username;
    private String sex;
    private String about;
    private String regdate;
    private String vtel;
    private String ctel;
    private String userpic;
    private String mainareaids;	
    private String chengjiao_count;
    private String weituo_count;
    private String daikan_count;
	public AgentDetail(String userid, String cardnumber, String mainarea, String sfzpic, String leixing, String coname,
			String biaoqian, String dengji, String realname, String worktime, String jiav, String base, String username,
			String sex, String about, String regdate, String vtel, String ctel, String userpic, String mainareaids, String chengjiao_count, String weituo_count, String daikan_count) {
		super();
		this.userid = userid;
		this.cardnumber = cardnumber;
		this.mainarea = mainarea;
		this.sfzpic = sfzpic;
		this.leixing = leixing;
		this.coname = coname;
		this.biaoqian = biaoqian;
		this.dengji = dengji;
		this.realname = realname;
		this.worktime = worktime;
		this.jiav = jiav;
		this.base = base;
		this.username = username;
		this.sex = sex;
		this.about = about;
		this.regdate = regdate;
		this.vtel = vtel;
		this.ctel = ctel;
		this.userpic = userpic;
		this.mainareaids = mainareaids;
		this.chengjiao_count = chengjiao_count;
		this.weituo_count = weituo_count;
		this.daikan_count = daikan_count;
	}
	public AgentDetail() {
		super();
	}
	
	public String getChengjiao_count() {
		return chengjiao_count;
	}
	public void setChengjiao_count(String chengjiao_count) {
		this.chengjiao_count = chengjiao_count;
	}
	public String getWeituo_count() {
		return weituo_count;
	}
	public void setWeituo_count(String weituo_count) {
		this.weituo_count = weituo_count;
	}
	public String getDaikan_count() {
		return daikan_count;
	}
	public void setDaikan_count(String daikan_count) {
		this.daikan_count = daikan_count;
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getSfzpic() {
		return sfzpic;
	}
	public void setSfzpic(String sfzpic) {
		this.sfzpic = sfzpic;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public String getBiaoqian() {
		return biaoqian;
	}
	public void setBiaoqian(String biaoqian) {
		this.biaoqian = biaoqian;
	}
	public String getDengji() {
		return dengji;
	}
	public void setDengji(String dengji) {
		this.dengji = dengji;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getVtel() {
		return vtel;
	}
	public void setVtel(String vtel) {
		this.vtel = vtel;
	}
	public String getCtel() {
		return ctel;
	}
	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	public String getMainareaids() {
		return mainareaids;
	}
	public void setMainareaids(String mainareaids) {
		this.mainareaids = mainareaids;
	}
	@Override
	public String toString() {
		return "AgentDetail [userid=" + userid + ", cardnumber=" + cardnumber + ", mainarea=" + mainarea + ", sfzpic="
				+ sfzpic + ", leixing=" + leixing + ", coname=" + coname + ", biaoqian=" + biaoqian + ", dengji="
				+ dengji + ", realname=" + realname + ", worktime=" + worktime + ", jiav=" + jiav + ", base=" + base
				+ ", username=" + username + ", sex=" + sex + ", about=" + about + ", regdate=" + regdate + ", vtel="
				+ vtel + ", chengjiao_count=" + chengjiao_count+ ", weituo_count=" + weituo_count+ ", daikan_count=" + daikan_count
				+ ", ctel=" + ctel + ", userpic=" + userpic + ", mainareaids=" + mainareaids + "]";
	}
    
    
    
    
}

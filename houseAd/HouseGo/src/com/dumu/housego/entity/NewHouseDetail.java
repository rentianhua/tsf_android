package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.List;

public class NewHouseDetail implements Serializable {
    private String id;
    private String catid;
    private String typeid;
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
    private String kaifashang;
    private String guihuahushu;
    private String kaipandate;
    private String zhandimianji;
    private String jiaofangdate;
    private String jianzhumianji;
    private String wuyeleixing;
    private String guihuachewei;
    private String chanquannianxian;
    private String wuyegongsi;
    private String wuyefei;
    private String shuidianranqi;
    private String jianzhuleixing;
    private String jianzhutype;
    private String lvhualv;
    private String rongjilv;
    private String loupandizhi;
    private String junjia;
    private String province;
    private String city;
    private String area;
    private String jingweidu;
    private String dituzuobiao;
    private String zuobiaodizhi;
    private String contacttel;
    private String bianhao;
    private String fabulaiyuan;
    private String zhuangxiu;
    private String shouloudizhi;
    private String zhulihuxing;
    private String dafen_zbpt;
    private String dafen_jt;
    private String dafen_hj;
    private String dianping;
    private String shiarea;
    private String mianjiarea;
    private String fangwuyongtu;
    private List<Pics> weizhitu;
    private List<Pics> yangbantu;
    private List<Pics> shijingtu;
    private List<Pics> xiaoqutu;
    private List<Pics> loupantupian;
    private String yhquan;
    private String lowzongjia;
    private String highzongjia;
    private String zongjiarange;
    private String zaishou;
    private String xiaoqutype;
    private String yhq_enddate;
    private NewHtml html;
    private List<NewDongTai> dongtais;
    private String hasyhq;
    private String xiaoqu;
    private String cityname;
    private String areaname;

    public NewHouseDetail(String id, String catid, String typeid, String title, String style, String thumb,
                          String keywords, String tags, String description, String posid, String url, String listorder, String status,
                          String sysadd, String islink, String username, String inputtime, String updatetime, String views,
                          String yesterdayviews, String dayviews, String weekviews, String monthviews, String viewsupdatetime,
                          String kaifashang, String guihuahushu, String kaipandate, String zhandimianji, String jiaofangdate,
                          String jianzhumianji, String wuyeleixing, String guihuachewei, String chanquannianxian, String wuyegongsi,
                          String wuyefei, String shuidianranqi, String jianzhuleixing, String lvhualv, String rongjilv,
                          String loupandizhi, String junjia, String province, String city, String area, String jingweidu,
                          String dituzuobiao, String zuobiaodizhi, String contacttel, String bianhao, String fabulaiyuan,
                          String zhuangxiu, String shouloudizhi, String zhulihuxing, String dafen_zbpt, String dafen_jt,
                          String dafen_hj, String dianping, String shiarea, String mianjiarea, String fangwuyongtu,
                          List<Pics> weizhitu, List<Pics> yangbantu, List<Pics> shijingtu, List<Pics> xiaoqutu,
                          List<Pics> loupantupian, String loupandongtai, String yhquan, String lowzongjia, String highzongjia,
                          String zongjiarange, String zaishou, String xiaoqutype, String yhq_enddate, NewHtml html,
                          List<NewDongTai> dongtais, String hasyhq, String xiaoqu, String cityname, String areaname, String jianzhutype) {
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
        this.kaifashang = kaifashang;
        this.guihuahushu = guihuahushu;
        this.kaipandate = kaipandate;
        this.zhandimianji = zhandimianji;
        this.jiaofangdate = jiaofangdate;
        this.jianzhumianji = jianzhumianji;
        this.wuyeleixing = wuyeleixing;
        this.guihuachewei = guihuachewei;
        this.chanquannianxian = chanquannianxian;
        this.wuyegongsi = wuyegongsi;
        this.wuyefei = wuyefei;
        this.shuidianranqi = shuidianranqi;
        this.jianzhuleixing = jianzhuleixing;
        this.jianzhutype = jianzhutype;
        this.lvhualv = lvhualv;
        this.rongjilv = rongjilv;
        this.loupandizhi = loupandizhi;
        this.junjia = junjia;
        this.province = province;
        this.city = city;
        this.area = area;
        this.jingweidu = jingweidu;
        this.dituzuobiao = dituzuobiao;
        this.zuobiaodizhi = zuobiaodizhi;
        this.contacttel = contacttel;
        this.bianhao = bianhao;
        this.fabulaiyuan = fabulaiyuan;
        this.zhuangxiu = zhuangxiu;
        this.shouloudizhi = shouloudizhi;
        this.zhulihuxing = zhulihuxing;
        this.dafen_zbpt = dafen_zbpt;
        this.dafen_jt = dafen_jt;
        this.dafen_hj = dafen_hj;
        this.dianping = dianping;
        this.shiarea = shiarea;
        this.mianjiarea = mianjiarea;
        this.fangwuyongtu = fangwuyongtu;
        this.weizhitu = weizhitu;
        this.yangbantu = yangbantu;
        this.shijingtu = shijingtu;
        this.xiaoqutu = xiaoqutu;
        this.loupantupian = loupantupian;
        this.yhquan = yhquan;
        this.lowzongjia = lowzongjia;
        this.highzongjia = highzongjia;
        this.zongjiarange = zongjiarange;
        this.zaishou = zaishou;
        this.xiaoqutype = xiaoqutype;
        this.yhq_enddate = yhq_enddate;
        this.html = html;
        this.dongtais = dongtais;
        this.hasyhq = hasyhq;
        this.xiaoqu = xiaoqu;
        this.cityname = cityname;
        this.areaname = areaname;
    }

    public NewHouseDetail() {
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

    public String getKaifashang() {
        return kaifashang;
    }

    public void setKaifashang(String kaifashang) {
        this.kaifashang = kaifashang;
    }

    public String getGuihuahushu() {
        return guihuahushu;
    }

    public void setGuihuahushu(String guihuahushu) {
        this.guihuahushu = guihuahushu;
    }

    public String getKaipandate() {
        return kaipandate;
    }

    public void setKaipandate(String kaipandate) {
        this.kaipandate = kaipandate;
    }

    public String getZhandimianji() {
        return zhandimianji;
    }

    public void setZhandimianji(String zhandimianji) {
        this.zhandimianji = zhandimianji;
    }

    public String getJiaofangdate() {
        return jiaofangdate;
    }

    public void setJiaofangdate(String jiaofangdate) {
        this.jiaofangdate = jiaofangdate;
    }

    public String getJianzhumianji() {
        return jianzhumianji;
    }

    public void setJianzhumianji(String jianzhumianji) {
        this.jianzhumianji = jianzhumianji;
    }

    public String getWuyeleixing() {
        return wuyeleixing;
    }

    public void setWuyeleixing(String wuyeleixing) {
        this.wuyeleixing = wuyeleixing;
    }

    public String getGuihuachewei() {
        return guihuachewei;
    }

    public void setGuihuachewei(String guihuachewei) {
        this.guihuachewei = guihuachewei;
    }

    public String getChanquannianxian() {
        return chanquannianxian;
    }

    public void setChanquannianxian(String chanquannianxian) {
        this.chanquannianxian = chanquannianxian;
    }

    public String getWuyegongsi() {
        return wuyegongsi;
    }

    public void setWuyegongsi(String wuyegongsi) {
        this.wuyegongsi = wuyegongsi;
    }

    public String getWuyefei() {
        return wuyefei;
    }

    public void setWuyefei(String wuyefei) {
        this.wuyefei = wuyefei;
    }

    public String getShuidianranqi() {
        return shuidianranqi;
    }

    public void setShuidianranqi(String shuidianranqi) {
        this.shuidianranqi = shuidianranqi;
    }

    public String getJianzhuleixing() {
        if (jianzhuleixing == null || jianzhuleixing.equals("null"))
            return "";
        return jianzhuleixing;
    }

    public void setJianzhuleixing(String jianzhuleixing) {
        this.jianzhuleixing = jianzhuleixing;
    }

    public String getJianzhutype() {
        if (jianzhutype == null || jianzhutype.equals("null"))
            return "";
        return jianzhutype;
    }

    public void setJianzhutype(String jianzhutype) {
        this.jianzhutype = jianzhutype;
    }

    public String getLvhualv() {
        return lvhualv;
    }

    public void setLvhualv(String lvhualv) {
        this.lvhualv = lvhualv;
    }

    public String getRongjilv() {
        return rongjilv;
    }

    public void setRongjilv(String rongjilv) {
        this.rongjilv = rongjilv;
    }

    public String getLoupandizhi() {
        return loupandizhi;
    }

    public void setLoupandizhi(String loupandizhi) {
        this.loupandizhi = loupandizhi;
    }

    public String getJunjia() {
        return junjia;
    }

    public void setJunjia(String junjia) {
        this.junjia = junjia;
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

    public String getDituzuobiao() {
        return dituzuobiao;
    }

    public void setDituzuobiao(String dituzuobiao) {
        this.dituzuobiao = dituzuobiao;
    }

    public String getZuobiaodizhi() {
        return zuobiaodizhi;
    }

    public void setZuobiaodizhi(String zuobiaodizhi) {
        this.zuobiaodizhi = zuobiaodizhi;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getFabulaiyuan() {
        return fabulaiyuan;
    }

    public void setFabulaiyuan(String fabulaiyuan) {
        this.fabulaiyuan = fabulaiyuan;
    }

    public String getZhuangxiu() {
        return zhuangxiu;
    }

    public void setZhuangxiu(String zhuangxiu) {
        this.zhuangxiu = zhuangxiu;
    }

    public String getShouloudizhi() {
        return shouloudizhi;
    }

    public void setShouloudizhi(String shouloudizhi) {
        this.shouloudizhi = shouloudizhi;
    }

    public String getZhulihuxing() {
        return zhulihuxing;
    }

    public void setZhulihuxing(String zhulihuxing) {
        this.zhulihuxing = zhulihuxing;
    }

    public String getDafen_zbpt() {
        return dafen_zbpt;
    }

    public void setDafen_zbpt(String dafen_zbpt) {
        this.dafen_zbpt = dafen_zbpt;
    }

    public String getDafen_jt() {
        return dafen_jt;
    }

    public void setDafen_jt(String dafen_jt) {
        this.dafen_jt = dafen_jt;
    }

    public String getDafen_hj() {
        return dafen_hj;
    }

    public void setDafen_hj(String dafen_hj) {
        this.dafen_hj = dafen_hj;
    }

    public String getDianping() {
        return dianping;
    }

    public void setDianping(String dianping) {
        this.dianping = dianping;
    }

    public String getShiarea() {
        return shiarea;
    }

    public void setShiarea(String shiarea) {
        this.shiarea = shiarea;
    }

    public String getMianjiarea() {
        return mianjiarea;
    }

    public void setMianjiarea(String mianjiarea) {
        this.mianjiarea = mianjiarea;
    }

    public String getFangwuyongtu() {
        return fangwuyongtu;
    }

    public void setFangwuyongtu(String fangwuyongtu) {
        this.fangwuyongtu = fangwuyongtu;
    }

    public List<Pics> getWeizhitu() {
        return weizhitu;
    }

    public void setWeizhitu(List<Pics> weizhitu) {
        this.weizhitu = weizhitu;
    }

    public List<Pics> getYangbantu() {
        return yangbantu;
    }

    public void setYangbantu(List<Pics> yangbantu) {
        this.yangbantu = yangbantu;
    }

    public List<Pics> getShijingtu() {
        return shijingtu;
    }

    public void setShijingtu(List<Pics> shijingtu) {
        this.shijingtu = shijingtu;
    }

    public List<Pics> getXiaoqutu() {
        return xiaoqutu;
    }

    public void setXiaoqutu(List<Pics> xiaoqutu) {
        this.xiaoqutu = xiaoqutu;
    }

    public List<Pics> getLoupantupian() {
        return loupantupian;
    }

    public void setLoupantupian(List<Pics> loupantupian) {
        this.loupantupian = loupantupian;
    }

    public String getYhquan() {
        return yhquan;
    }

    public void setYhquan(String yhquan) {
        this.yhquan = yhquan;
    }

    public String getLowzongjia() {
        return lowzongjia;
    }

    public void setLowzongjia(String lowzongjia) {
        this.lowzongjia = lowzongjia;
    }

    public String getHighzongjia() {
        return highzongjia;
    }

    public void setHighzongjia(String highzongjia) {
        this.highzongjia = highzongjia;
    }

    public String getZongjiarange() {
        return zongjiarange;
    }

    public void setZongjiarange(String zongjiarange) {
        this.zongjiarange = zongjiarange;
    }

    public String getZaishou() {
        return zaishou;
    }

    public void setZaishou(String zaishou) {
        this.zaishou = zaishou;
    }

    public String getXiaoqutype() {
        return xiaoqutype;
    }

    public void setXiaoqutype(String xiaoqutype) {
        this.xiaoqutype = xiaoqutype;
    }

    public String getYhq_enddate() {
        return yhq_enddate;
    }

    public void setYhq_enddate(String yhq_enddate) {
        this.yhq_enddate = yhq_enddate;
    }

    public NewHtml getHtml() {
        return html;
    }

    public void setHtml(NewHtml html) {
        this.html = html;
    }

    public List<NewDongTai> getDongtais() {
        return dongtais;
    }

    public void setDongtais(List<NewDongTai> dongtais) {
        this.dongtais = dongtais;
    }

    public String getHasyhq() {
        return hasyhq;
    }

    public void setHasyhq(String hasyhq) {
        this.hasyhq = hasyhq;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
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

    @Override
    public String toString() {
        return "NewHouseDetail [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", title=" + title + ", style="
                + style + ", thumb=" + thumb + ", keywords=" + keywords + ", tags=" + tags + ", description="
                + description + ", posid=" + posid + ", url=" + url + ", listorder=" + listorder + ", status=" + status
                + ", sysadd=" + sysadd + ", islink=" + islink + ", username=" + username + ", inputtime=" + inputtime
                + ", updatetime=" + updatetime + ", views=" + views + ", yesterdayviews=" + yesterdayviews
                + ", dayviews=" + dayviews + ", weekviews=" + weekviews + ", monthviews=" + monthviews
                + ", viewsupdatetime=" + viewsupdatetime + ", kaifashang=" + kaifashang + ", guihuahushu=" + guihuahushu
                + ", kaipandate=" + kaipandate + ", zhandimianji=" + zhandimianji + ", jiaofangdate=" + jiaofangdate
                + ", jianzhumianji=" + jianzhumianji + ", wuyeleixing=" + wuyeleixing + ", guihuachewei=" + guihuachewei
                + ", chanquannianxian=" + chanquannianxian + ", wuyegongsi=" + wuyegongsi + ", wuyefei=" + wuyefei
                + ", shuidianranqi=" + shuidianranqi + ", jianzhuleixing=" + jianzhuleixing + ", lvhualv=" + lvhualv
                + ", rongjilv=" + rongjilv + ", loupandizhi=" + loupandizhi + ", junjia=" + junjia + ", province="
                + province + ", city=" + city + ", area=" + area + ", jingweidu=" + jingweidu + ", dituzuobiao="
                + dituzuobiao + ", zuobiaodizhi=" + zuobiaodizhi + ", contacttel=" + contacttel + ", bianhao=" + bianhao
                + ", fabulaiyuan=" + fabulaiyuan + ", zhuangxiu=" + zhuangxiu + ", shouloudizhi=" + shouloudizhi
                + ", zhulihuxing=" + zhulihuxing + ", dafen_zbpt=" + dafen_zbpt + ", dafen_jt=" + dafen_jt
                + ", dafen_hj=" + dafen_hj + ", dianping=" + dianping + ", shiarea=" + shiarea + ", mianjiarea="
                + mianjiarea + ", fangwuyongtu=" + fangwuyongtu + ", weizhitu=" + weizhitu + ", yangbantu=" + yangbantu
                + ", shijingtu=" + shijingtu + ", xiaoqutu=" + xiaoqutu + ", loupantupian=" + loupantupian
                + ", yhquan=" + yhquan + ", lowzongjia=" + lowzongjia
                + ", highzongjia=" + highzongjia + ", zongjiarange=" + zongjiarange + ", zaishou=" + zaishou
                + ", xiaoqutype=" + xiaoqutype + ", yhq_enddate=" + yhq_enddate + ", html=" + html + ", dongtais="
                + dongtais + ", hasyhq=" + hasyhq + ", xiaoqu=" + xiaoqu + ", cityname=" + cityname + ", areaname="
                + areaname + "]";
    }


}

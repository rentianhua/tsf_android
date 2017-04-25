package com.dumu.housego.entity;

import java.util.List;

public class ATerShouSubmit {
    private String id;
    private String username;
    private String userid;
    private String modelid;
    private String province;
    private String city;
    private String area;
    private String xiaoquname;
    private String jingweidu;
    private String zongjia;
    private String title;
    private String desc;
    private String fangling;
    private String jianzhumianji;

    private String longdong;
    private String menpai;
    private String taoneimianji;
    private String louceng;
    private String loucengshuxing;
    private String wuyetype;
    private String diyaxinxi;
    private String huxing;
    private String houseshuxing;

    private List<Pics> pic;
    private String jianzhutype;
    private String jianzhujiegou;
    private String tihubili;
    private String houseuse;
    private String chanquansuoshu;
    private String shifoudianti;
    private String weiyizhuzhai;
    private String guapaishijian;
    private String bianqian;
    private String ditieline;

    private String touzifenxi;
    private String huxingjieshao;
    private String xiaoqujieshao;
    private String shuifeijiexi;
    private String zhuangxiumiaoshu;
    private String zhoubianpeitao;
    private String jiaoyupeitao;
    private String jiaotongchuxing;
    private String hexinmaidian;
    private String xiaoquyoushi;
    private String quanshudiya;
    private String yezhushuo;

    private String curceng;
    private String zongceng;

    private String shi;
    private String ting;
    private String wei;

    private String jiegou;
    private String zhuangxiu;
    private String chaoxiang;

    private String contract;
    private String idcard;

    private String danyuan;

    public ATerShouSubmit(String contract, String danyuan,
                          String idcard, String id, String username, String userid, String modelid, String province, String city, String area,
                          String xiaoquname, String jingweidu, String zongjia, String title, String desc, String fangling,
                          String jianzhumianji, String longdong, String menpai, String taoneimianji, String louceng,
                          String loucengshuxing, String wuyetype, String diyaxinxi, String huxing, String houseshuxing, List<Pics> pic,
                          String jianzhutype, String jianzhujiegou, String tihubili, String houseuse, String chanquansuoshu,
                          String shifoudianti, String weiyizhuzhai, String guapaishijian, String bianqian, String ditieline,
                          String touzifenxi, String huxingjieshao, String xiaoqujieshao, String shuifeijiexi, String zhuangxiumiaoshu,
                          String zhoubianpeitao, String jiaoyupeitao, String jiaotongchuxing, String hexinmaidian,
                          String xiaoquyoushi, String quanshudiya, String curceng, String zongceng, String shi,
                          String ting, String wei, String jiegou, String zhuangxiu, String chaoxiang) {
        super();
        this.danyuan = danyuan;
        this.contract = contract;
        this.idcard = idcard;
        this.id = id;
        this.username = username;
        this.userid = userid;
        this.modelid = modelid;
        this.province = province;
        this.city = city;
        this.area = area;
        this.xiaoquname = xiaoquname;
        this.jingweidu = jingweidu;
        this.zongjia = zongjia;
        this.title = title;
        this.desc = desc;
        this.fangling = fangling;
        this.jianzhumianji = jianzhumianji;
        this.longdong = longdong;
        this.menpai = menpai;
        this.taoneimianji = taoneimianji;
        this.louceng = louceng;
        this.loucengshuxing = loucengshuxing;
        this.wuyetype = wuyetype;
        this.diyaxinxi = diyaxinxi;
        this.huxing = huxing;
        this.houseshuxing = houseshuxing;
        this.pic = pic;
        this.jianzhutype = jianzhutype;
        this.jianzhujiegou = jianzhujiegou;
        this.tihubili = tihubili;
        this.houseuse = houseuse;
        this.chanquansuoshu = chanquansuoshu;
        this.shifoudianti = shifoudianti;
        this.weiyizhuzhai = weiyizhuzhai;
        this.guapaishijian = guapaishijian;
        this.bianqian = bianqian;
        this.ditieline = ditieline;
        this.touzifenxi = touzifenxi;
        this.huxingjieshao = huxingjieshao;
        this.xiaoqujieshao = xiaoqujieshao;
        this.shuifeijiexi = shuifeijiexi;
        this.zhuangxiumiaoshu = zhuangxiumiaoshu;
        this.zhoubianpeitao = zhoubianpeitao;
        this.jiaoyupeitao = jiaoyupeitao;
        this.jiaotongchuxing = jiaotongchuxing;
        this.hexinmaidian = hexinmaidian;
        this.xiaoquyoushi = xiaoquyoushi;
        this.quanshudiya = quanshudiya;
        this.curceng = curceng;
        this.zongceng = zongceng;
        this.shi = shi;
        this.ting = ting;
        this.wei = wei;
        this.jiegou = jiegou;
        this.zhuangxiu = zhuangxiu;
        this.chaoxiang = chaoxiang;
    }

    public ATerShouSubmit() {
        super();
    }


    public String getDanyuan() {
        return danyuan;
    }

    public void setDanyuan(String danyuan) {
        this.danyuan = danyuan;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
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

    public String getXiaoquname() {
        return xiaoquname;
    }

    public void setXiaoquname(String xiaoquname) {
        this.xiaoquname = xiaoquname;
    }

    public String getJingweidu() {
        return jingweidu;
    }

    public void setJingweidu(String jingweidu) {
        this.jingweidu = jingweidu;
    }

    public String getZongjia() {
        return zongjia;
    }

    public void setZongjia(String zongjia) {
        this.zongjia = zongjia;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFangling() {
        return fangling;
    }

    public void setFangling(String fangling) {
        this.fangling = fangling;
    }

    public String getJianzhumianji() {
        return jianzhumianji;
    }

    public void setJianzhumianji(String jianzhumianji) {
        this.jianzhumianji = jianzhumianji;
    }

    public String getLongdong() {
        return longdong;
    }

    public void setLongdong(String longdong) {
        this.longdong = longdong;
    }

    public String getMenpai() {
        return menpai;
    }

    public void setMenpai(String menpai) {
        this.menpai = menpai;
    }

    public String getTaoneimianji() {
        return taoneimianji;
    }

    public void setTaoneimianji(String taoneimianji) {
        this.taoneimianji = taoneimianji;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getLoucengshuxing() {
        return loucengshuxing;
    }

    public void setLoucengshuxing(String loucengshuxing) {
        this.loucengshuxing = loucengshuxing;
    }

    public String getWuyetype() {
        return wuyetype;
    }

    public void setWuyetype(String wuyetype) {
        this.wuyetype = wuyetype;
    }

    public String getDiyaxinxi() {
        return diyaxinxi;
    }

    public void setDiyaxinxi(String diyaxinxi) {
        this.diyaxinxi = diyaxinxi;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    public String getHouseshuxing() {
        return houseshuxing;
    }

    public void setHouseshuxing(String houseshuxing) {
        this.houseshuxing = houseshuxing;
    }

    public List<Pics> getPic() {
        return pic;
    }

    public void setPic(List<Pics> pic) {
        this.pic = pic;
    }

    public String getJianzhutype() {
        return jianzhutype;
    }

    public void setJianzhutype(String jianzhutype) {
        this.jianzhutype = jianzhutype;
    }

    public String getJianzhujiegou() {
        return jianzhujiegou;
    }

    public void setJianzhujiegou(String jianzhujiegou) {
        this.jianzhujiegou = jianzhujiegou;
    }

    public String getTihubili() {
        return tihubili;
    }

    public void setTihubili(String tihubili) {
        this.tihubili = tihubili;
    }

    public String getHouseuse() {
        return houseuse;
    }

    public void setHouseuse(String houseuse) {
        this.houseuse = houseuse;
    }

    public String getChanquansuoshu() {
        return chanquansuoshu;
    }

    public void setChanquansuoshu(String chanquansuoshu) {
        this.chanquansuoshu = chanquansuoshu;
    }

    public String getShifoudianti() {
        return shifoudianti;
    }

    public void setShifoudianti(String shifoudianti) {
        this.shifoudianti = shifoudianti;
    }

    public String getWeiyizhuzhai() {
        return weiyizhuzhai;
    }

    public void setWeiyizhuzhai(String weiyizhuzhai) {
        this.weiyizhuzhai = weiyizhuzhai;
    }

    public String getGuapaishijian() {
        return guapaishijian;
    }

    public void setGuapaishijian(String guapaishijian) {
        this.guapaishijian = guapaishijian;
    }

    public String getBianqian() {
        return bianqian;
    }

    public void setBianqian(String bianqian) {
        this.bianqian = bianqian;
    }

    public String getDitieline() {
        return ditieline;
    }

    public void setDitieline(String ditieline) {
        this.ditieline = ditieline;
    }

    public String getTouzifenxi() {
        return touzifenxi;
    }

    public void setTouzifenxi(String touzifenxi) {
        this.touzifenxi = touzifenxi;
    }

    public String getHuxingjieshao() {
        return huxingjieshao;
    }

    public void setHuxingjieshao(String huxingjieshao) {
        this.huxingjieshao = huxingjieshao;
    }

    public String getXiaoqujieshao() {
        return xiaoqujieshao;
    }

    public void setXiaoqujieshao(String xiaoqujieshao) {
        this.xiaoqujieshao = xiaoqujieshao;
    }

    public String getShuifeijiexi() {
        return shuifeijiexi;
    }

    public void setShuifeijiexi(String shuifeijiexi) {
        this.shuifeijiexi = shuifeijiexi;
    }

    public String getZhuangxiumiaoshu() {
        return zhuangxiumiaoshu;
    }

    public void setZhuangxiumiaoshu(String zhuangxiumiaoshu) {
        this.zhuangxiumiaoshu = zhuangxiumiaoshu;
    }

    public String getZhoubianpeitao() {
        return zhoubianpeitao;
    }

    public void setZhoubianpeitao(String zhoubianpeitao) {
        this.zhoubianpeitao = zhoubianpeitao;
    }

    public String getJiaoyupeitao() {
        return jiaoyupeitao;
    }

    public void setJiaoyupeitao(String jiaoyupeitao) {
        this.jiaoyupeitao = jiaoyupeitao;
    }

    public String getJiaotongchuxing() {
        return jiaotongchuxing;
    }

    public void setJiaotongchuxing(String jiaotongchuxing) {
        this.jiaotongchuxing = jiaotongchuxing;
    }

    public String getHexinmaidian() {
        return hexinmaidian;
    }

    public void setHexinmaidian(String hexinmaidian) {
        this.hexinmaidian = hexinmaidian;
    }

    public String getXiaoquyoushi() {
        return xiaoquyoushi;
    }

    public void setXiaoquyoushi(String xiaoquyoushi) {
        this.xiaoquyoushi = xiaoquyoushi;
    }

    public String getQuanshudiya() {
        return quanshudiya;
    }

    public void setQuanshudiya(String quanshudiya) {
        this.quanshudiya = quanshudiya;
    }

    public String getYezhushuo() {
        return yezhushuo;
    }

    public void setYezhushuo(String yezhushuo) {
        this.yezhushuo = yezhushuo;
    }

    public String getCurceng() {
        return curceng;
    }

    public void setCurceng(String curceng) {
        this.curceng = curceng;
    }

    public String getZongceng() {
        return zongceng;
    }

    public void setZongceng(String zongceng) {
        this.zongceng = zongceng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getTing() {
        return ting;
    }

    public void setTing(String ting) {
        this.ting = ting;
    }

    public String getWei() {
        return wei;
    }

    public void setWei(String wei) {
        this.wei = wei;
    }

    public String getJiegou() {
        return jiegou;
    }

    public void setJiegou(String jiegou) {
        this.jiegou = jiegou;
    }

    public String getZhuangxiu() {
        return zhuangxiu;
    }

    public void setZhuangxiu(String zhuangxiu) {
        this.zhuangxiu = zhuangxiu;
    }

    public String getChaoxiang() {
        return chaoxiang;
    }

    public void setChaoxiang(String chaoxiang) {
        this.chaoxiang = chaoxiang;
    }

    @Override
    public String toString() {
        return "ATerShouSubmit [id=" + id + ", username=" + username + ", userid=" + userid + ", modelid=" + modelid
                + ", province=" + province + ", city=" + city + ", area=" + area + ", xiaoquname=" + xiaoquname
                + ", jingweidu=" + jingweidu + ", zongjia=" + zongjia + ", title=" + title + ", desc=" + desc
                + ", fangling=" + fangling + ", jianzhumianji=" + jianzhumianji + ", longdong=" + longdong + ", menpai="
                + menpai + ", taoneimianji=" + taoneimianji + ", louceng=" + louceng + ", loucengshuxing="
                + loucengshuxing + ", wuyetype=" + wuyetype + ", diyaxinxi=" + diyaxinxi + ", huxing=" + huxing
                + ", houseshuxing=" + houseshuxing + ", pic=" + pic + ", jianzhutype=" + jianzhutype
                + ", jianzhujiegou=" + jianzhujiegou + ", tihubili=" + tihubili + ", houseuse=" + houseuse
                + ", chanquansuoshu=" + chanquansuoshu + ", shifoudianti=" + shifoudianti + ", weiyizhuzhai="
                + weiyizhuzhai + ", guapaishijian=" + guapaishijian + ", bianqian=" + bianqian + ", ditieline="
                + ditieline + ", touzifenxi=" + touzifenxi + ", huxingjieshao=" + huxingjieshao + ", xiaoqujieshao="
                + xiaoqujieshao + ", shuifeijiexi=" + shuifeijiexi + ", zhuangxiumiaoshu=" + zhuangxiumiaoshu
                + ", zhoubianpeitao=" + zhoubianpeitao + ", jiaoyupeitao=" + jiaoyupeitao + ", jiaotongchuxing="
                + jiaotongchuxing + ", hexinmaidian=" + hexinmaidian + ", xiaoquyoushi=" + xiaoquyoushi
                + ", quanshudiya=" + quanshudiya + ", curceng=" + curceng
                + ", zongceng=" + zongceng + ", shi=" + shi + ", ting=" + ting + ", wei=" + wei + ", jiegou=" + jiegou
                + ", zhuangxiu=" + zhuangxiu + ", chaoxiang=" + chaoxiang + ", contract=" + contract + ", idcard="
                + idcard + ", danyuan=" + danyuan + "]";
    }


}
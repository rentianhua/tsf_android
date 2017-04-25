package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.List;

public class ErShouFangDetails implements Serializable {
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
    private String zongjia;
    private String jianzhumianji;
    private String shi;
    private String ting;
    private String wei;
    private String chu;
    private String yangtai;
    private String huxing;
    private String ceng;
    private String zongceng;
    private String louceng;
    private String zhuangxiu;
    private String chaoxiang;
    private String jiegou;
    private String dianti;
    private String ditiexian;
    private String fangling;
    private String shuxing;
    private String isweiyi;
    private String province;
    private String city;
    private String area;
    private String jingweidu;
    private String zuobiaodizhi;
    private String dituzuobiao;
    private String xuexiaomingcheng;
    private String shenghuopeitao;
    private String jiaotong;
    private List<Pics> pics;
    private String hidetel;
    private String yezhushuo;
    private String desc;
    private String biaoqian;
    private String bianhao;
    private String jianzhutype;
    private String jianzhujiegou;
    private String taoneimianji;
    private String tihubili;
    private String guapaidate;
    private String jiaoyiquanshu;
    private String shangcijiaoyi;
    private String fangwuyongtu;
    private String chanquansuoshu;
    private String diyaxinxi;
    private String hexinmaidian;
    private String quanshudiya;
    private String xiaoquyoushi;
    private String touzifenxi;
    private String huxingintro;
    private String xiaoquintro;
    private String zxdesc;
    private String shuifeijiexi;
    private String xiaoqu;
    private String loudong;
    private String menpai;
    private String curceng;
    private String pub_type;
    private String jjr_id;
    private String fenpei_time;
    private String lock;
    private String chenghu;
    private String contract;
    private String idcard;
    private String zaishou;
    private String content;
    private String paginationtype;
    private String maxcharperpage;
    private String template;
    private String paytype;
    private String allow_comment;
    private String relation;
    private String cityname;
    private String areaname;
    private String xiaoquName;
    private String xiaoquname;
    private String apply_state;
    private String userid;
    private String danyuan;
    private List<SeeHouse> daikan;
    public List<ErShouFangDetails> tongqu;
    //private String tuijianliyou;

    public ErShouFangDetails(String danyuan, String userid, String id, String catid, String typeid, String title, String style, String thumb,
                             String keywords, String tags, String description, String posid, String url, String listorder, String status,
                             String sysadd, String islink, String username, String inputtime, String updatetime, String views,
                             String yesterdayviews, String dayviews, String weekviews, String monthviews, String viewsupdatetime,
                             String zongjia, String jianzhumianji, String shi, String ting, String wei, String chu, String yangtai,
                             String huxing, String ceng, String zongceng, String louceng, String zhuangxiu, String chaoxiang,
                             String jiegou, String dianti, String ditiexian, String fangling, String shuxing, String isweiyi,
                             String province, String city, String area, String jingweidu, String zuobiaodizhi, String dituzuobiao,
                             String xuexiaomingcheng, String shenghuopeitao, String jiaotong, List<Pics> pics, String hidetel,
                             String yezhushuo, String desc, String biaoqian, String bianhao, String jianzhutype, String jianzhujiegou,
                             String taoneimianji, String tihubili, String guapaidate, String jiaoyiquanshu, String shangcijiaoyi,
                             String fangwuyongtu, String chanquansuoshu, String diyaxinxi, String hexinmaidian, String quanshudiya,
                             String xiaoquyoushi, String touzifenxi, String huxingintro, String xiaoquintro, String zxdesc,
                             String shuifeijiexi, String xiaoqu, String loudong, String menpai, String curceng, String pub_type,
                             String jjr_id, String fenpei_time, String lock, String chenghu, String contract, String idcard,
                             String zaishou, String content, String paginationtype, String maxcharperpage, String template,
                             String paytype, String allow_comment, String relation, String cityname, String areaname, String xiaoquName,
                             String xiaoquname2, String apply_state) {
        super();

        this.danyuan = danyuan;
        this.userid = userid;
        this.apply_state = apply_state;
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
        this.zongjia = zongjia;
        this.jianzhumianji = jianzhumianji;
        this.shi = shi;
        this.ting = ting;
        this.wei = wei;
        this.chu = chu;
        this.yangtai = yangtai;
        this.huxing = huxing;
        this.ceng = ceng;
        this.zongceng = zongceng;
        this.louceng = louceng;
        this.zhuangxiu = zhuangxiu;
        this.chaoxiang = chaoxiang;
        this.jiegou = jiegou;
        this.dianti = dianti;
        this.ditiexian = ditiexian;
        this.fangling = fangling;
        this.shuxing = shuxing;
        this.isweiyi = isweiyi;
        this.province = province;
        this.city = city;
        this.area = area;
        this.jingweidu = jingweidu;
        this.zuobiaodizhi = zuobiaodizhi;
        this.dituzuobiao = dituzuobiao;
        this.xuexiaomingcheng = xuexiaomingcheng;
        this.shenghuopeitao = shenghuopeitao;
        this.jiaotong = jiaotong;
        this.pics = pics;
        this.hidetel = hidetel;
        this.yezhushuo = yezhushuo;
        this.desc = desc;
        this.biaoqian = biaoqian;
        this.bianhao = bianhao;
        this.jianzhutype = jianzhutype;
        this.jianzhujiegou = jianzhujiegou;
        this.taoneimianji = taoneimianji;
        this.tihubili = tihubili;
        this.guapaidate = guapaidate;
        this.jiaoyiquanshu = jiaoyiquanshu;
        this.shangcijiaoyi = shangcijiaoyi;
        this.fangwuyongtu = fangwuyongtu;
        this.chanquansuoshu = chanquansuoshu;
        this.diyaxinxi = diyaxinxi;
        this.hexinmaidian = hexinmaidian;
        this.quanshudiya = quanshudiya;
        this.xiaoquyoushi = xiaoquyoushi;
        this.touzifenxi = touzifenxi;
        this.huxingintro = huxingintro;
        this.xiaoquintro = xiaoquintro;
        this.zxdesc = zxdesc;
        this.shuifeijiexi = shuifeijiexi;
        this.xiaoqu = xiaoqu;
        this.loudong = loudong;
        this.menpai = menpai;
        this.curceng = curceng;
        this.pub_type = pub_type;
        this.jjr_id = jjr_id;
        this.fenpei_time = fenpei_time;
        this.lock = lock;
        this.chenghu = chenghu;
        this.contract = contract;
        this.idcard = idcard;
        this.zaishou = zaishou;
        this.content = content;
        this.paginationtype = paginationtype;
        this.maxcharperpage = maxcharperpage;
        this.template = template;
        this.paytype = paytype;
        this.allow_comment = allow_comment;
        this.relation = relation;
        this.cityname = cityname;
        this.areaname = areaname;
        this.xiaoquName = xiaoquName;
        xiaoquname = xiaoquname2;
    }

    public ErShouFangDetails() {
        super();
    }

    public String getDanyuan() {
        return danyuan;
    }

    public void setDanyuan(String danyuan) {
        this.danyuan = danyuan;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
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

    public String getChu() {
        return chu;
    }

    public void setChu(String chu) {
        this.chu = chu;
    }

    public String getYangtai() {
        return yangtai;
    }

    public void setYangtai(String yangtai) {
        this.yangtai = yangtai;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    public String getCeng() {
        return ceng;
    }

    public void setCeng(String ceng) {
        this.ceng = ceng;
    }

    public String getZongceng() {
        return zongceng;
    }

    public void setZongceng(String zongceng) {
        this.zongceng = zongceng;
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

    public String getChaoxiang() {
        return chaoxiang;
    }

    public void setChaoxiang(String chaoxiang) {
        this.chaoxiang = chaoxiang;
    }

    public String getJiegou() {
        return jiegou;
    }

    public void setJiegou(String jiegou) {
        this.jiegou = jiegou;
    }

    public String getDianti() {
        return dianti;
    }

    public void setDianti(String dianti) {
        this.dianti = dianti;
    }

    public String getDitiexian() {
        return ditiexian;
    }

    public void setDitiexian(String ditiexian) {
        this.ditiexian = ditiexian;
    }

    public String getFangling() {
        return fangling;
    }

    public void setFangling(String fangling) {
        this.fangling = fangling;
    }

    public String getShuxing() {
        return shuxing;
    }

    public void setShuxing(String shuxing) {
        this.shuxing = shuxing;
    }

    public String getIsweiyi() {
        return isweiyi;
    }

    public void setIsweiyi(String isweiyi) {
        this.isweiyi = isweiyi;
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

    public String getDituzuobiao() {
        return dituzuobiao;
    }

    public void setDituzuobiao(String dituzuobiao) {
        this.dituzuobiao = dituzuobiao;
    }

    public String getXuexiaomingcheng() {
        return xuexiaomingcheng;
    }

    public void setXuexiaomingcheng(String xuexiaomingcheng) {
        this.xuexiaomingcheng = xuexiaomingcheng;
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

    public String getHidetel() {
        return hidetel;
    }

    public void setHidetel(String hidetel) {
        this.hidetel = hidetel;
    }

    public String getYezhushuo() {
        return yezhushuo;
    }

    public void setYezhushuo(String yezhushuo) {
        this.yezhushuo = yezhushuo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
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

    public String getTaoneimianji() {
        return taoneimianji;
    }

    public void setTaoneimianji(String taoneimianji) {
        this.taoneimianji = taoneimianji;
    }

    public String getTihubili() {
        return tihubili;
    }

    public void setTihubili(String tihubili) {
        this.tihubili = tihubili;
    }

    public String getGuapaidate() {
        return guapaidate;
    }

    public void setGuapaidate(String guapaidate) {
        this.guapaidate = guapaidate;
    }

    public String getJiaoyiquanshu() {
        return jiaoyiquanshu;
    }

    public void setJiaoyiquanshu(String jiaoyiquanshu) {
        this.jiaoyiquanshu = jiaoyiquanshu;
    }

    public String getShangcijiaoyi() {
        return shangcijiaoyi;
    }

    public void setShangcijiaoyi(String shangcijiaoyi) {
        this.shangcijiaoyi = shangcijiaoyi;
    }

    public String getFangwuyongtu() {
        return fangwuyongtu;
    }

    public void setFangwuyongtu(String fangwuyongtu) {
        this.fangwuyongtu = fangwuyongtu;
    }

    public String getChanquansuoshu() {
        return chanquansuoshu;
    }

    public void setChanquansuoshu(String chanquansuoshu) {
        this.chanquansuoshu = chanquansuoshu;
    }

    public String getDiyaxinxi() {
        return diyaxinxi;
    }

    public void setDiyaxinxi(String diyaxinxi) {
        this.diyaxinxi = diyaxinxi;
    }

    public String getHexinmaidian() {
        return hexinmaidian;
    }

    public void setHexinmaidian(String hexinmaidian) {
        this.hexinmaidian = hexinmaidian;
    }

    public String getQuanshudiya() {
        return quanshudiya;
    }

    public void setQuanshudiya(String quanshudiya) {
        this.quanshudiya = quanshudiya;
    }

    public String getXiaoquyoushi() {
        return xiaoquyoushi;
    }

    public void setXiaoquyoushi(String xiaoquyoushi) {
        this.xiaoquyoushi = xiaoquyoushi;
    }

    public String getTouzifenxi() {
        return touzifenxi;
    }

    public void setTouzifenxi(String touzifenxi) {
        this.touzifenxi = touzifenxi;
    }

    public String getHuxingintro() {
        return huxingintro;
    }

    public void setHuxingintro(String huxingintro) {
        this.huxingintro = huxingintro;
    }

    public String getXiaoquintro() {
        return xiaoquintro;
    }

    public void setXiaoquintro(String xiaoquintro) {
        this.xiaoquintro = xiaoquintro;
    }

    public String getZxdesc() {
        return zxdesc;
    }

    public void setZxdesc(String zxdesc) {
        this.zxdesc = zxdesc;
    }

    public String getShuifeijiexi() {
        return shuifeijiexi;
    }

    public void setShuifeijiexi(String shuifeijiexi) {
        this.shuifeijiexi = shuifeijiexi;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public String getLoudong() {
        return loudong;
    }

    public void setLoudong(String loudong) {
        this.loudong = loudong;
    }

    public String getMenpai() {
        return menpai;
    }

    public void setMenpai(String menpai) {
        this.menpai = menpai;
    }

    public String getCurceng() {
        return curceng;
    }

    public void setCurceng(String curceng) {
        this.curceng = curceng;
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

    public String getChenghu() {
        return chenghu;
    }

    public void setChenghu(String chenghu) {
        this.chenghu = chenghu;
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

    public String getZaishou() {
        return zaishou;
    }

    public void setZaishou(String zaishou) {
        this.zaishou = zaishou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPaginationtype() {
        return paginationtype;
    }

    public void setPaginationtype(String paginationtype) {
        this.paginationtype = paginationtype;
    }

    public String getMaxcharperpage() {
        return maxcharperpage;
    }

    public void setMaxcharperpage(String maxcharperpage) {
        this.maxcharperpage = maxcharperpage;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(String allow_comment) {
        this.allow_comment = allow_comment;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public String getXiaoquName() {
        return xiaoquName;
    }

    public void setXiaoquName(String xiaoquName) {
        this.xiaoquName = xiaoquName;
    }

    public String getXiaoquname() {
        return xiaoquname;
    }

    public void setXiaoquname(String xiaoquname) {
        this.xiaoquname = xiaoquname;
    }

    public List<SeeHouse> getDaikan() {
        return daikan;
    }

    public void setDaikan(List<SeeHouse> daikan) {
        this.daikan = daikan;
    }

    //public String getTuijianliyou() {
    //    return tuijianliyou;
    //}

    //public void setTuijianliyou(String tuijianliyou) {
    //    this.tuijianliyou = tuijianliyou;
    //}

    @Override
    public String toString() {
        return "ErShouFangDetails [id=" + id + ", catid=" + catid + ", typeid=" + typeid + ", title=" + title
                + ", style=" + style + ", thumb=" + thumb + ", keywords=" + keywords + ", tags=" + tags
                + ", description=" + description + ", posid=" + posid + ", url=" + url + ", listorder=" + listorder
                + ", status=" + status + ", sysadd=" + sysadd + ", islink=" + islink + ", username=" + username
                + ", inputtime=" + inputtime + ", updatetime=" + updatetime + ", views=" + views + ", yesterdayviews="
                + yesterdayviews + ", dayviews=" + dayviews + ", weekviews=" + weekviews + ", monthviews=" + monthviews
                + ", viewsupdatetime=" + viewsupdatetime + ", zongjia=" + zongjia + ", jianzhumianji=" + jianzhumianji
                + ", shi=" + shi + ", ting=" + ting + ", wei=" + wei + ", chu=" + chu + ", yangtai=" + yangtai
                + ", huxing=" + huxing + ", ceng=" + ceng + ", zongceng=" + zongceng + ", louceng=" + louceng
                + ", zhuangxiu=" + zhuangxiu + ", chaoxiang=" + chaoxiang + ", jiegou=" + jiegou + ", dianti=" + dianti
                + ", ditiexian=" + ditiexian + ", fangling=" + fangling + ", shuxing=" + shuxing + ", isweiyi="
                + isweiyi + ", province=" + province + ", city=" + city + ", area=" + area + ", jingweidu=" + jingweidu
                + ", zuobiaodizhi=" + zuobiaodizhi + ", dituzuobiao=" + dituzuobiao + ", xuexiaomingcheng="
                + xuexiaomingcheng + ", shenghuopeitao=" + shenghuopeitao + ", jiaotong=" + jiaotong + ", pics=" + pics
                + ", hidetel=" + hidetel + ", yezhushuo=" + yezhushuo + ", desc=" + desc + ", biaoqian=" + biaoqian
                + ", bianhao=" + bianhao + ", jianzhutype=" + jianzhutype + ", jianzhujiegou=" + jianzhujiegou
                + ", taoneimianji=" + taoneimianji + ", tihubili=" + tihubili + ", guapaidate=" + guapaidate
                + ", jiaoyiquanshu=" + jiaoyiquanshu + ", shangcijiaoyi=" + shangcijiaoyi + ", fangwuyongtu="
                + fangwuyongtu + ", chanquansuoshu=" + chanquansuoshu + ", diyaxinxi=" + diyaxinxi + ", hexinmaidian="
                + hexinmaidian + ", quanshudiya=" + quanshudiya + ", xiaoquyoushi=" + xiaoquyoushi + ", touzifenxi="
                + touzifenxi + ", huxingintro=" + huxingintro + ", xiaoquintro=" + xiaoquintro + ", zxdesc=" + zxdesc
                + ", shuifeijiexi=" + shuifeijiexi + ", xiaoqu=" + xiaoqu + ", loudong=" + loudong + ", menpai="
                + menpai + ", curceng=" + curceng + ", pub_type=" + pub_type + ", jjr_id=" + jjr_id + ", fenpei_time="
                + fenpei_time + ", lock=" + lock + ", chenghu=" + chenghu + ", contract=" + contract + ", idcard="
                + idcard + ", zaishou=" + zaishou + ", content=" + content + ", paginationtype=" + paginationtype
                + ", maxcharperpage=" + maxcharperpage + ", template=" + template + ", paytype=" + paytype
                + ", allow_comment=" + allow_comment + ", relation=" + relation + ", cityname=" + cityname
                + ", areaname=" + areaname + ", xiaoquName=" + xiaoquName + ", xiaoquname=" + xiaoquname
                + ", apply_state=" + apply_state + ", userid=" + userid + ", danyuan=" + danyuan + "]";
    }
}

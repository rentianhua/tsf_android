package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewDongTai;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.NewHtml;
import com.dumu.housego.entity.Pics;

public class NewHouseDetialJSONParse {

    public static NewHouseDetail parseSearch(String json) throws JSONException {
        JSONObject j = new JSONObject(json);
        NewHouseDetail n = new NewHouseDetail();

        n.setId(j.getString("id"));
        n.setCatid(j.getString("catid"));
        n.setPosid(j.getString("posid"));
        n.setTitle(j.getString("title"));

        n.setArea(j.getString("area"));
        n.setAreaname(j.getString("areaname"));
        n.setBianhao(j.getString("bianhao"));
        n.setChanquannianxian(j.getString("chanquannianxian"));
        n.setCity(j.getString("city"));
        n.setCityname(j.getString("cityname"));
        n.setContacttel(j.getString("contacttel"));
        n.setDafen_hj(j.getString("dafen_hj"));
        n.setDafen_jt(j.getString("dafen_jt"));
        n.setDafen_zbpt(j.getString("dafen_zbpt"));
        n.setDayviews(j.getString("dayviews"));
        n.setDescription(j.getString("description"));
        n.setDianping(j.getString("dianping"));
        n.setDituzuobiao(j.getString("dituzuobiao"));
        n.setFabulaiyuan(j.getString("fabulaiyuan"));
        n.setFangwuyongtu(j.getString("fangwuyongtu"));
        n.setGuihuachewei(j.getString("guihuachewei"));
        n.setGuihuahushu(j.getString("guihuahushu"));
        n.setHasyhq(j.getString("hasyhq"));
        n.setHighzongjia(j.getString("highzongjia"));
        n.setInputtime(j.getString("inputtime"));
        n.setIslink(j.getString("islink"));
        if (j.has("jianzhuleixing"))
            n.setJianzhuleixing(j.getString("jianzhuleixing"));
        if (j.has("jianzhutype"))
            n.setJianzhutype(j.getString("jianzhutype"));
        n.setJianzhumianji(j.getString("jianzhumianji"));
        n.setJiaofangdate(j.getString("jiaofangdate"));
        n.setJingweidu(j.getString("jingweidu"));
        n.setJunjia(j.getString("junjia"));
        n.setKaifashang(j.getString("kaifashang"));
        n.setKaipandate(j.getString("kaipandate"));
        n.setKeywords(j.getString("keywords"));
        n.setListorder(j.getString("listorder"));
        n.setLoupandizhi(j.getString("loupandizhi"));

        n.setLowzongjia(j.getString("lowzongjia"));
        n.setLvhualv(j.getString("lvhualv"));
        n.setMianjiarea(j.getString("mianjiarea"));
        n.setMonthviews(j.getString("monthviews"));
        n.setPosid(j.getString("posid"));
        n.setProvince(j.getString("province"));
        n.setRongjilv(j.getString("rongjilv"));
        n.setShiarea(j.getString("shiarea"));
        n.setShouloudizhi(j.getString("shouloudizhi"));
        n.setShuidianranqi(j.getString("shuidianranqi"));
        n.setStatus(j.getString("status"));
        n.setStyle(j.getString("style"));
        n.setSysadd(j.getString("sysadd"));
        n.setTags(j.getString("tags"));
        n.setTypeid(j.getString("typeid"));
        n.setThumb(j.getString("thumb"));
        n.setUpdatetime(j.getString("updatetime"));
        n.setUrl(j.getString("url"));
        n.setUsername(j.getString("username"));
        n.setViews(j.getString("views"));
        n.setViewsupdatetime(j.getString("viewsupdatetime"));
        n.setWeekviews(j.getString("weekviews"));
        n.setWuyefei(j.getString("wuyefei"));
        n.setWuyegongsi(j.getString("wuyegongsi"));
        n.setWuyeleixing(j.getString("wuyeleixing"));
        n.setXiaoqu(j.getString("xiaoqu"));
        n.setXiaoqutype(j.getString("xiaoqutype"));
        n.setYesterdayviews(j.getString("yesterdayviews"));
        n.setYhq_enddate(j.getString("yhq_enddate"));
        n.setYhquan(j.getString("yhquan"));
        n.setZaishou(j.getString("zaishou"));
        n.setZhandimianji(j.getString("zhandimianji"));
        n.setZhuangxiu(j.getString("zhuangxiu"));
        n.setZhulihuxing(j.getString("zhulihuxing"));
        n.setZongjiarange(j.getString("zongjiarange"));
        n.setZuobiaodizhi(j.getString("zuobiaodizhi"));

        if (j.get("html") != "") {
            JSONObject h = j.getJSONObject("html");
            NewHtml html = new NewHtml();
            html.setAllow_comment(h.getString("allow_comment"));
            html.setContent(h.getString("content"));
            html.setFukuanfangshi(h.getString("fukuanfangshi"));
            html.setHuxingintro(h.getString("huxingintro"));
            html.setId(h.getString("id"));
            html.setMaxcharperpage(h.getString("maxcharperpage"));
            html.setPaginationtype(h.getString("paginationtype"));
            html.setPaytype(h.getString("paytype"));
            html.setRelation(h.getString("relation"));
            html.setTemplate(h.getString("template"));
            n.setHtml(html);
            ;
        } else {
            n.setHtml(null);
        }


        if (!j.get("dongtai").equals("") && !j.get("dongtai").equals(null)) {
            JSONArray arr = j.getJSONArray("dongtai");
            List<NewDongTai> loupans = new ArrayList<NewDongTai>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                NewDongTai p = new NewDongTai();

                p.setBiaoqian(o.getString("biaoqian"));
                p.setThumb(o.getString("thumb"));
                p.setTitle(o.getString("title"));
                p.setDescription(o.getString("description"));

                loupans.add(p);
            }
            n.setDongtais(loupans);
            ;
        } else {
            n.setDongtais(null);
        }


        if (j.get("loupantupian") != "") {
            JSONArray arr = j.getJSONArray("loupantupian");
            List<Pics> loupans = new ArrayList<Pics>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                Pics p = new Pics();

                p.setAlt(o.getString("alt"));
                p.setUrl("http://www.taoshenfang.com" + o.getString("url"));

                loupans.add(p);
            }
            n.setLoupantupian(loupans);
        } else {
            n.setLoupantupian(null);
        }

        if (j.get("weizhitu") != "") {
            JSONArray arr1 = j.getJSONArray("weizhitu");
            List<Pics> weizhis = new ArrayList<Pics>();
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject o = arr1.getJSONObject(i);
                Pics p = new Pics();

                p.setAlt(o.getString("alt"));
                p.setUrl("http://www.taoshenfang.com" + o.getString("url"));

                weizhis.add(p);
            }
            n.setWeizhitu(weizhis);
        } else {
            n.setWeizhitu(null);
        }


        if (j.get("yangbantu") != "") {
            JSONArray arr2 = j.getJSONArray("yangbantu");
            List<Pics> yangbans = new ArrayList<Pics>();
            for (int i = 0; i < arr2.length(); i++) {
                JSONObject o = arr2.getJSONObject(i);
                Pics p = new Pics();

                p.setAlt(o.getString("alt"));
                p.setUrl("http://www.taoshenfang.com" + o.getString("url"));

                yangbans.add(p);
            }
            n.setYangbantu(yangbans);
        } else {
            n.setYangbantu(null);
        }


        if (j.get("shijingtu") != "") {
            JSONArray arr3 = j.getJSONArray("shijingtu");
            List<Pics> shijings = new ArrayList<Pics>();
            for (int i = 0; i < arr3.length(); i++) {
                JSONObject o = arr3.getJSONObject(i);
                Pics p = new Pics();

                p.setAlt(o.getString("alt"));
                p.setUrl("http://www.taoshenfang.com" + o.getString("url"));

                shijings.add(p);
            }
            n.setShijingtu(shijings);
        } else {
            n.setShijingtu(null);
        }


        if (j.get("xiaoqutu") != "") {
            JSONArray arr4 = j.getJSONArray("xiaoqutu");
            List<Pics> xiaoqus = new ArrayList<Pics>();
            for (int i = 0; i < arr4.length(); i++) {
                JSONObject o = arr4.getJSONObject(i);
                Pics p = new Pics();

                p.setAlt(o.getString("alt"));
                p.setUrl("http://www.taoshenfang.com" + o.getString("url"));

                xiaoqus.add(p);
            }
            n.setXiaoqutu(xiaoqus);
        } else {
            n.setXiaoqutu(null);
        }


        return n;

    }

}

package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.UrlFactory;

public class ATershousubmitModel implements IATershousubmitModel {
    public void ATershousubmit(final ATerShouSubmit at, final AsycnCallBack back) {
        String url = UrlFactory.PostATershouSubmit();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onSuccess(info);
                    } else {
                        back.onError("请求失败，请稍后重试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    back.onError("请求失败，请稍后重试");
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("请求失败，请稍后重试");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                // 必传
                map.put("username", at.getUsername());
                map.put("modelid", at.getModelid());
                map.put("userid", at.getUserid());
                map.put("province", at.getProvince());
                map.put("city", at.getCity());
                map.put("area", at.getArea());
                map.put("xiaoquname", at.getXiaoquname());
                map.put("jingweidu", at.getJingweidu());
                map.put("zongjia", at.getZongjia());
                map.put("title", at.getTitle());
                map.put("desc", at.getDesc());
                map.put("fangling", at.getFangling());
                map.put("jianzhumianji", at.getJianzhumianji());
                // 必传
                map.put("danyuan", at.getDanyuan());
                map.put("loudong", at.getLongdong());
                map.put("menpai", at.getMenpai());
                map.put("taoneimianji", at.getTaoneimianji());
                map.put("louceng", at.getLouceng());
                map.put("ceng", at.getLoucengshuxing());
                map.put("jiaoyiquanshu", at.getWuyetype());
                map.put("diyaxinxi", at.getDiyaxinxi());
                map.put("huxing", at.getHuxing());
                map.put("shuxing", at.getHouseshuxing());
                map.put("jianzhutype", at.getJianzhutype());
                map.put("jianzhujiegou", at.getJianzhujiegou());
                map.put("tihubili", at.getTihubili());
                map.put("fangwuyongtu", at.getHouseuse());
                map.put("chanquansuoshu", at.getChanquansuoshu());
                map.put("dianti", at.getShifoudianti());
                map.put("isweiyi", at.getWeiyizhuzhai());
                map.put("guapaidate", at.getGuapaishijian());
                map.put("shangcijiaoyi", at.getShangcijiaoyi());
                map.put("biaoqian", at.getBianqian());
                map.put("ditiexian", at.getDitieline());
                map.put("touzifenxi", at.getTouzifenxi());
                map.put("huxingintro", at.getHuxingjieshao());
                map.put("xiaoquintro", at.getXiaoqujieshao());
                map.put("shuifeijiexi", at.getShuifeijiexi());
                map.put("zxdesc", at.getZhuangxiumiaoshu());
                map.put("shenghuopeitao", at.getZhoubianpeitao());
                map.put("xuexiaomingcheng", at.getJiaoyupeitao());
                map.put("jiaotong", at.getJiaotongchuxing());
                map.put("hexinmaidian", at.getHexinmaidian());
                map.put("xiaoquyoushi", at.getXiaoquyoushi());
                map.put("quanshudiya", at.getQuanshudiya());
                map.put("yezhushuo", at.getYezhushuo());
                //
                map.put("jiegou", at.getJiegou());
                map.put("zhuangxiu", at.getZhuangxiu());
                map.put("chaoxiang", at.getChaoxiang());
                map.put("zongceng", at.getZongceng());
                map.put("curceng", at.getCurceng());
                map.put("shi", at.getShi());
                map.put("ting", at.getTing());
                map.put("wei", at.getWei());

                //list集合转json
                JSONArray json = new JSONArray();
                try {
                    for (Pics a : at.getPic()) {
                        JSONObject jo = new JSONObject();
                        jo.put("url", a.getUrl());
                        jo.put("alt", a.getAlt());
                        json.put(jo);
                    }
                } catch (Exception e2) {
                }

                map.put("pics", json.toString());
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

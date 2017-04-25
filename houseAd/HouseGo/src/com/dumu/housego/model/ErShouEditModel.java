package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class ErShouEditModel implements IErShouEditModel {
    public void ershouedit(final ATerShouSubmit at, final AsycnCallBack back) {
        String url = UrlFactory.PostErShouEdit();
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
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                // 必传

                map.put("id", at.getId());
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
                map.put("loudong", at.getLongdong());
                map.put("danyuan", at.getDanyuan());
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
                map.put("danyuan", at.getDanyuan());
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

    public void Rentingedit(final RentingDetail r, final AsycnCallBack back) {
        String url = UrlFactory.PostChuzuEdit();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject J = new JSONObject(response);
                        String info = J.getString("info");
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
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", r.getId());
                m.put("username", r.getUsername());
                m.put("modelid", r.getModelid());
                m.put("userid", r.getUserid());
                m.put("province", r.getProvince());
                m.put("city", r.getCity());
                m.put("area", r.getArea());
                m.put("xiaoquname", r.getXiaoquname());
                m.put("jingweidu", r.getJingweidu());
                m.put("shi", r.getShi());
                m.put("ting", r.getTing());
                m.put("wei", r.getWei());
                m.put("mianji", r.getMianji());
                m.put("zujin", r.getZujin());
                m.put("title", r.getTitle());
                m.put("desc", r.getDesc());
                m.put("address", r.getAddress());
                m.put("ceng", r.getCeng());
                m.put("zongceng", r.getZongceng());
                m.put("fangling", r.getFangling());
                //可传
                m.put("zhuangxiu", r.getZhuangxiu());
                m.put("chaoxiang", r.getChaoxiang());
                m.put("leixing", r.getLeixing());
                m.put("jianzhutype", r.getJianzhutype());
                m.put("zulin", r.getZulin());
                m.put("fukuan", r.getFukuan());
                m.put("fangwupeitao", r.getFangwupeitao());
                m.put("ditiexian", r.getDitiexian());
                m.put("biaoqian", r.getBiaoqian());
                m.put("huxingjieshao", r.getHuxingjieshao());
                m.put("liangdian", r.getLiangdian());
                m.put("czreason", r.getCzreason());
                m.put("xiaoquintro", r.getXiaoquintro());
                m.put("zxdesc", r.getZxdesc());
                m.put("shenghuopeitao", r.getShenghuopeitao());
                m.put("jiaotong", r.getJiaotong());
                m.put("yezhushuo", r.getYezhushuo());
                m.put("wuyetype", r.getWuyetype());
                m.put("xiaoqutype", r.getXiaoqutype());

                //list集合转json
                JSONArray json = new JSONArray();
                try {
                    for (Pics a : r.getPics()) {
                        JSONObject jo = new JSONObject();
                        jo.put("url", a.getUrl());
                        jo.put("alt", a.getAlt());
                        json.put(jo);
                    }
                } catch (Exception e2) {
                }

                m.put("pics", json.toString());

                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ptershouedit(final String username, final String userid, final String modelid, final ErShouFangDetails e, final AsycnCallBack back) {
        String url = UrlFactory.PostErShouEdit();
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
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();

                m.put("id", e.getId());
                m.put("username", username);
                m.put("userid", userid);
                m.put("modelid", modelid);

                m.put("province", e.getProvince());
                m.put("city", e.getCity());
                m.put("area", e.getArea());
                m.put("xiaoquname", e.getXiaoquname());
                m.put("chenghu", e.getChenghu());
                m.put("zongjia", e.getZongjia());
                m.put("loudong", e.getLoudong());
                m.put("menpai", e.getMenpai());

                m.put("jingweidu", e.getJingweidu());
                m.put("curceng", e.getCurceng());
                m.put("zongceng", e.getZongceng());
                m.put("ceng", e.getCeng());
                m.put("pub_type", e.getPub_type());
                m.put("hidetel", e.getHidetel());
                m.put("title", e.getTitle());

                //list集合转json
                JSONArray json = new JSONArray();
                try {
                    for (Pics a : e.getPics()) {
                        JSONObject jo = new JSONObject();
                        jo.put("url", a.getUrl());
                        jo.put("alt", a.getAlt());
                        json.put(jo);
                    }
                } catch (Exception e2) {
                }


                m.put("pics", json.toString());
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ptrentingedit(final RentingDetail r, final AsycnCallBack back) {
        String url = UrlFactory.PostChuzuEdit();
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
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", r.getId());
                m.put("username", r.getUsername());
                m.put("userid", r.getUserid());
                m.put("modelid", r.getModelid());

                m.put("province", r.getProvince());
                m.put("city", r.getCity());
                m.put("area", r.getArea());
                m.put("xiaoquname", r.getXiaoquname());
                m.put("jingweidu", r.getJingweidu());
                m.put("shi", r.getShi());
                m.put("ting", r.getTing());
                m.put("wei", r.getWei());
                m.put("mianji", r.getMianji());
                m.put("zujin", r.getZujin());
                m.put("pub_type", r.getPub_type());
                m.put("hidetel", r.getHidetel());
                m.put("title", r.getTitle());

                //list集合转json
                JSONArray json = new JSONArray();
                try {
                    for (Pics a : r.getPics()) {
                        JSONObject jo = new JSONObject();
                        jo.put("url", a.getUrl());
                        jo.put("alt", a.getAlt());
                        json.put(jo);
                    }
                } catch (Exception e2) {
                }
                m.put("pics", json.toString());

                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

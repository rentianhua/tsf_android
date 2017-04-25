package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.JsonArray;

import android.util.Log;

public class PTershouSubmitModel implements IPTershouSubmitModel {
    public void PTershouSubmit(final String username, final String userid, final String modelid, final ErShouFangDetails e,
                               final AsycnCallBack back) {
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
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
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
                m.put("danyuan", e.getDanyuan());
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
}

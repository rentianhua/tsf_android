package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class PTrentingSubmitModel implements IPTrentingSubmitModel {
    public void PTrentingSubmit(final RentingDetail r, final AsycnCallBack back) {
        String url = UrlFactory.PostATrentingSubmit();
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

package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class YuYueHouseModel implements IYuYueHouseModel {
    public void Loadyuyue(final String formid, final String fromtable, final String username, final String fromuserid,
                          final String type, final String yuyuedate, final String yuyuetime, final String t,
                          final AsycnCallBack back) {
        String url = UrlFactory.PostYuYueHouseUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        String infomation = obj.getString("info");
                        back.onSuccess(infomation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("fromid", formid);
                map.put("fromtable", fromtable);
                map.put("username", username);
                map.put("fromuserid", fromuserid);
                map.put("type", type);
                map.put("yuyuedate", yuyuedate);
                map.put("yuyuetime", yuyuetime);
                map.put("t", t);

                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.MyYuYueListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class MyYuYueHouseModel implements IMyYuYueHouseModel {
    public void LoadYuYueData(final String username, final AsycnCallBack back) {
        String url = UrlFactory.PostMyYuYueHouseUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<YuYueData> yuyuedatas = MyYuYueListJSONParse.parseSearch(response);
                        back.onSuccess(yuyuedatas);
                    }
                } catch (JSONException e) {
                    try {
                        JSONObject J = new JSONObject(response);
                        String info = J.getString("info");
                        back.onError(info);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("t", "2");
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void DeleteYuYue(final String id, final String userid, final String username, final AsycnCallBack back) {
        String url = UrlFactory.PostDeleteMyYuYueHouseUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        String infomation = obj.getString("info").toString();
                        back.onSuccess(infomation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("id", id);
                param.put("userid", userid);
                param.put("username", username);
                return param;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

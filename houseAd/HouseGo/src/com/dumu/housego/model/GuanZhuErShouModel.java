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
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GuanZhuErShouJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class GuanZhuErShouModel implements IGuanZhuErShouModel {
    public void loadGuanZhuErShou(final String username, final String table, final AsycnCallBack back) {
        String url = UrlFactory.PostGuanZhuErShouUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<ErShouFangDetails> ershoufangdetails = GuanZhuErShouJSONParse.parseSearch(response);
                        back.onSuccess(ershoufangdetails);
                    } else {
                        back.onError("无关注房源");
                    }
                } catch (JSONException e) {
                    back.onError("无关注房源");
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("table", table);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

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

public class GuanZhuHouseModel implements IGuanZhuHouseModel {
    public void LoadGuanZhuHouse(final String fromid, final String fromtable, final String userid,
                                 final String username, final String type, final String t, final AsycnCallBack back) {
        String url = "http://www.taoshenfang.com/index.php?g=api&m=house&a=guanzhu_add";
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getInt("success") == 90) {
                            String infomation = obj.getString("info").toString();
                            back.onSuccess(infomation);
                        } else {
                            back.onError(obj.getString("info"));
                        }
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
                Map<String, String> param = new HashMap<String, String>();
                param.put("fromid", fromid);
                param.put("fromtable", fromtable);
                param.put("userid", userid);
                param.put("username", username);
                param.put("type", type);
                param.put("t", t);
                return param;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

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

public class GuanZhuDeleteModel implements IGuanZhuDeleteModel {
    public void deleteGuanZhu(final String id, final String userid, final String username, final AsycnCallBack back) {
        String url = UrlFactory.PostGuanZhuDeleteUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getInt("success") == 108) {
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", id);
                map.put("userid", userid);
                map.put("username", username);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

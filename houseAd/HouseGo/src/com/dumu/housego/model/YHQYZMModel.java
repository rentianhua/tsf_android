package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class YHQYZMModel implements IYHQYZMModel {

    public void yhqyzm(final String mob, final AsycnCallBack back) {
        String url = UrlFactory.PostYHQDelete();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onSuccess(info);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
                m.put("mob", mob);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

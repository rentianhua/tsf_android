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
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class AddLiuYanModel implements IAddLiuYanModel {
    public void addLiuyan(final String from_uid, final String to_uid, final String content, final AsycnCallBack back) {
        String url = UrlFactory.PostAddLiuYan();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onSuccess(info);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("请求失败");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
                m.put("from_uid", from_uid);
                m.put("to_uid", to_uid);
                m.put("content", content);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

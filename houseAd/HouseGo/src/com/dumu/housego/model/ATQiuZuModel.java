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
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.ATQiuZuListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class ATQiuZuModel implements IATQiuZuModel {
    public void qiuzu(final String jjrid, final String table, final AsycnCallBack back) {
        String url = UrlFactory.PostQiuZuQiuGou();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<QiuzuANDQiuGou> qiuzus = ATQiuZuListJSONParse.parseSearch(response);
                        back.onSuccess(qiuzus);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    JSONObject j;
                    try {
                        j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onError(info);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("请求失败");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<String, String>();
                p.put("jjrid", jjrid);
                p.put("table", table);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

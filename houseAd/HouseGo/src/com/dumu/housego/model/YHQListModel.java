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
import com.dumu.housego.entity.YHQ;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GetYHQlistJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class YHQListModel implements IYhqListModel {

    @Override
    public void yhqList(String userid, final AsycnCallBack back) {
        String url = UrlFactory.GetYHQList(userid);
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<YHQ> yhqs = GetYHQlistJSONParse.parseSearch(response);
                        back.onSuccess(yhqs);
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
            }
        });
        HouseGoApp.getQueue().add(request);
    }

    public void yhqDelete(final String userid, final String id, final String username, final String yzm, final AsycnCallBack back) {
        String url = UrlFactory.PostYHQListDelete();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onSuccess(info);
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
                Map<String, String> m = new HashMap<String, String>();
                m.put("userid", userid);
                m.put("id", id);
                m.put("username", username);
                m.put("yzm", yzm);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

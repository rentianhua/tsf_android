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

public class HasGouDiModel implements IHasGouDiModel {
    public void Hasgoudi(final String house_id, final String userid, final AsycnCallBack back) {
        String url = UrlFactory.PostHasGoudi();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject J = new JSONObject(response);
                        String info = J.getString("info");
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
                Map<String, String> p = new HashMap<String, String>();
                p.put("house_id", house_id);
                p.put("userid", userid);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

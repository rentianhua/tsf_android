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

import android.util.Log;

public class GouDiAddModel implements IGouDiAddModel {

    @Override
    public void AddGoudi(final String house_id, final String userid, final String title, final String jine, final AsycnCallBack back) {
        String url = UrlFactory.PostGouDiAdd();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        if (j.getInt("success") == 82) {
                            String info = j.getString("info");
                            JSONObject b = j.getJSONObject("result");
                            String order_no = b.getString("order_no");
                            back.onSuccess(order_no);
                        } else {
                            back.onError(j.getString("info"));
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
                map.put("house_id", house_id);
                map.put("userid", userid);
                map.put("title", title);
                map.put("jine", jine);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

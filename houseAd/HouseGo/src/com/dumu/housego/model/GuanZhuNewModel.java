package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GuanZhuErShouJSONParse;
import com.dumu.housego.util.GuanZhuNewJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class GuanZhuNewModel implements IGuanZhuNewModel {
    public void loadGuanZhuNew(final String username, final String table, final AsycnCallBack back) {
        String url = UrlFactory.PostGuanZhuErShouUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<NewHouseDetail> newhousedetails = GuanZhuNewJSONParse.parseSearch(response);
                        back.onSuccess(newhousedetails);
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
                map.put("username", username);
                map.put("table", table);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

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
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.SubMitErShouListJSONParse;
import com.dumu.housego.util.SubMitErShouListJSONParse2;
import com.dumu.housego.util.SubMitErShouListJSONParse3;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class ChengJiaoESModel implements IChengJiaoESModel {
    public void ChengJiaoES(final String username, final String userid, final String table, final AsycnCallBack back) {
        String url = UrlFactory.PostChengJiaoHouseList();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        List<ErShouFangDetails> ershoudetails = SubMitErShouListJSONParse3.parseSearch(response);
                        back.onSuccess(ershoudetails);
                    } catch (JSONException e) {
                        back.onError("无成交房源");
                        e.printStackTrace();
                    }
                } else {
                    back.onError("无数据");
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("无数据");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("table", table);
                map.put("userid", userid);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

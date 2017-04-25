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
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class YHQGetYzmModel implements IYHQGetYzmModel {

    public void YzmInfo(final String mob, final AsycnCallBack back) {
        String url = UrlFactory.PostYZMforYHQ();
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
                Map<String, String> p = new HashMap<String, String>();
                p.put("mob", mob);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void AddYHQ(final String house_id, final String coupon_id, final String userid, final String buyname, final String buytel, final String username,
                       final String yzm, final AsycnCallBack back) {
        String url = UrlFactory.PostAddYHQ();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject j = new JSONObject(response);
                        int success = j.getInt("success");
                        if (success == 67) {
                            JSONObject o = j.getJSONObject("result");
                            YHQ y = new YHQ();
                            y.setBuyname(o.getString("buyname"));
                            y.setBuytel(o.getString("buytel"));
                            y.setCoupon_id(o.getString("coupon_id"));
                            y.setCoupon_name(o.getString("coupon_name"));
                            y.setDifu(o.getString("difu"));
                            y.setHouse_id(o.getString("house_id"));
                            y.setId(o.getString("id"));
                            y.setInputtime(o.getString("inputtime"));
                            y.setOrder_no(o.getString("order_no"));
                            y.setShifu(o.getString("shifu"));
                            y.setUserid(o.getString("userid"));

                            back.onSuccess(y);
                        } else {
                            String info = j.getString("info");
                            back.onError(info);
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
                Map<String, String> p = new HashMap<String, String>();
                p.put("house_id", house_id);
                p.put("coupon_id", coupon_id);
                p.put("userid", userid);
                p.put("buyname", buyname);
                p.put("buytel", buytel);
                p.put("username", username);
                p.put("yzm", yzm);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

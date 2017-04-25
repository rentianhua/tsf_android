package com.dumu.housego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.JsonObject;

public class GouDiListModel implements IGouDiListModel {
    public void GoudiList(String userid, final AsycnCallBack back) {
        String url = UrlFactory.GetGouDiList(userid);
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONArray arr = new JSONArray(response);
                        List<GouDiInfo> goudis = new ArrayList<GouDiInfo>();
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject j = arr.getJSONObject(i);
                            GouDiInfo g = new GouDiInfo();
                            g.setAddtime(j.getLong("addtime"));
                            g.setHouse_id(j.getString("house_id"));
                            g.setId(j.getString("id"));
                            g.setUserid(j.getString("userid"));
                            g.setJine(j.getString("jine"));
                            g.setOrder_no(j.getString("order_no"));
                            g.setTitle(j.getString("title"));
                            g.setPay_status(j.getString("pay_status"));
                            g.setTrade_no(j.getString("trade_no"));
                            g.setPaytime(j.getString("paytime"));
                            g.setBuyer_email(j.getString("buyer_email"));

                            goudis.add(g);
                        }
                        back.onSuccess(goudis);
                    }
                } catch (JSONException e) {
                    try {
                        JSONObject j = new JSONObject(response);
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

    public void GouDiDelete(final String id, final String userid, final AsycnCallBack back) {
        String url = UrlFactory.PostGouDiDelete();
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", id);
                map.put("userid", userid);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

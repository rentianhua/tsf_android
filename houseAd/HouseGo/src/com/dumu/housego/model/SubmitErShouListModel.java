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
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.SubMitErShouListJSONParse;
import com.dumu.housego.util.SubMitErShouListJSONParse2;
import com.dumu.housego.util.SubMitRentingListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class SubmitErShouListModel implements ISubmitErShouListModel {
    // 二手房列表
    public void submitershoulist(final String username, final String userid, final String table,
                                 final AsycnCallBack back) {
        String url = UrlFactory.PostSubmitESorCZList();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<ErShouFangDetails> submitershous = SubMitErShouListJSONParse2.parseSearch(response);
                        back.onSuccess(submitershous);
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
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("userid", userid);
                map.put("table", table);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void submitrentinglist(final String username, final String userid, final String table,
                                  final AsycnCallBack back) {
        String url = UrlFactory.PostSubmitESorCZList();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<RentingDetail> rentingdetails = SubMitRentingListJSONParse.parseSearch(response);
                        back.onSuccess(rentingdetails);
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
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("userid", userid);
                map.put("table", table);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

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
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.LiuYanListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class LiuYanModel implements ILiuYanModel {
    public void liuyan(final String userid, final AsycnCallBack back) {
        final String url = UrlFactory.PostLiuYanList();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<LiuYanList> liuyans = LiuYanListJSONParse.parseSearch(response);
                        back.onSuccess(liuyans);
                    } else {
                        back.onError("无留言");
                    }
                } catch (JSONException e) {
                    back.onError("无留言");
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
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

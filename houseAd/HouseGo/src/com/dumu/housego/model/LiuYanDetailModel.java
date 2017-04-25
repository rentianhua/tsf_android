package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class LiuYanDetailModel implements ILiuYanDetailModel {
    public void liuyandetail(final String userid, final String towho, final AsycnCallBack back) {
        String url = UrlFactory.PostLiuYanDetail();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONArray array = new JSONArray(response);
                        List<LiuYanList> qiuzulists = new ArrayList<LiuYanList>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            LiuYanList n = new LiuYanList();
                            n.setContent(j.getString("content"));
                            n.setId(j.getString("id"));
                            n.setInputtime(j.getString("inputtime"));
//					n.setRealname(j.getString("realname"));
                            n.setUserpic(j.getString("userpic"));
                            n.setFrom_status(j.getString("from_status"));
                            n.setFrom_uid(j.getString("from_uid"));
                            n.setTo_uid(j.getString("to_uid"));
                            n.setTo_status(j.getString("to_status"));
                            qiuzulists.add(n);
                        }
                        back.onSuccess(qiuzulists);
                    }
                } catch (Exception e) {
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<String, String>();
                m.put("userid", userid);
                m.put("towho", towho);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

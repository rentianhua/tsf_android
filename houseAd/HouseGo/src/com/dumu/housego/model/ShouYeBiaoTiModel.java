package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.ShouYeBiaoTi;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class ShouYeBiaoTiModel implements IShouYeBiaotiModel {
    public void shouye(final AsycnCallBack back) {
        String url = UrlFactory.GetShouYeBiaoTi();
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONArray array = new JSONArray(response);
                        List<ShouYeBiaoTi> qiuzulists = new ArrayList<ShouYeBiaoTi>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            ShouYeBiaoTi n = new ShouYeBiaoTi();
                            n.setValue(j.getString("value"));
                            n.setVarname(j.getString("varname"));
                            qiuzulists.add(n);
                        }
                        back.onSuccess(qiuzulists);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        });
        HouseGoApp.getQueue().add(request);
    }
}

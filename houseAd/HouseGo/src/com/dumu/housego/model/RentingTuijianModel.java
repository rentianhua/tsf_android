package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RentingTuijian;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class RentingTuijianModel implements IRentingTuijianModel {
    public void tuijian(final AsycnCallBack back) {
        String url = UrlFactory.GetRentingTuijianUrl();
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONArray array = new JSONArray(response);
                        List<RentingTuijian> newrecommends = new ArrayList<RentingTuijian>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            RentingTuijian n = new RentingTuijian();
                            JSONObject obj2 = obj.getJSONObject("data");
                            n.setTitle(obj2.getString("title"));
                            n.setArea_name(obj2.getString("area_name"));
                            n.setThumb(obj2.getString("thumb"));
                            n.setCity_name(obj2.getString("city_name"));
                            n.setProvince_name(obj2.getString("province_name"));
                            n.setThumb(obj2.getString("thumb"));
                            n.setId(obj.getString("id"));
                            n.setZujin(obj2.getString("zujin"));
                            n.setCatid(obj.getString("catid"));
                            newrecommends.add(n);
                        }
                        back.onSuccess(newrecommends);
                    }
                } catch (Exception e) {
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

package com.dumu.housego.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.FangJiaXiangQing;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class FangJiaXiangqingModel implements IFangJiaXiangqingModel {
    public void fangjia(final AsycnCallBack back) {
        String url = UrlFactory.GetFangJiaXingQing();
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<FangJiaXiangQing> fangs = new ArrayList<FangJiaXiangQing>();
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            FangJiaXiangQing n = new FangJiaXiangQing();
                            n.setAvg_percent(j.getString("avg_percent"));
                            n.setAvg_percent_o(j.getString("avg_percent_o"));
                            n.setAvg_price(j.getString("avg_price"));
                            n.setAvg_price_o(j.getString("avg_price_o"));
                            n.setComp_count(j.getString("comp_count"));
                            n.setComp_count_o(j.getString("comp_count_o"));
                            n.setHouse_percent(j.getString("house_percent"));
                            n.setHouse_percent_o(j.getString("house_percent_o"));
                            n.setId(j.getString("id"));
                            n.setMonth(j.getString("month"));
                            n.setView_count(j.getString("view_count"));
                            n.setView_count_o(j.getString("view_count_o"));
                            fangs.add(n);
                        }
                        back.onSuccess(fangs);
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

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
import com.dumu.housego.entity.SearchXiaoQu;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.SearchXiaoQuJSONParse;
import com.dumu.housego.util.UrlFactory;

public class SearchXiaoQuModel implements ISearchXiaoQuModel {
    public void SearchXiaoqu(final String area, final String key, final AsycnCallBack back) {
        String url = UrlFactory.PostSearchXiaoQu();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<SearchXiaoQu> xiaoqus = SearchXiaoQuJSONParse.parseSearch(response);
                        back.onSuccess(xiaoqus);
                    }
                } catch (JSONException e) {
                    try {
                        JSONObject j = new JSONObject(response);
                        String info = j.getString("info");
                        back.onError(info);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        back.onError("请求失败，请稍后重试");
                    }
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("请求失败，请检查网络连接");
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("area", area);
                map.put("key", key);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

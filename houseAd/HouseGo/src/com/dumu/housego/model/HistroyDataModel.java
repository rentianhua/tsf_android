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
import com.dumu.housego.entity.HistroyData;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HistoryDataJSONParse;
import com.dumu.housego.util.UrlFactory;

public class HistroyDataModel implements IHistroyDataModel {
    public void histroydata(final String username, final AsycnCallBack back) {
        String url = UrlFactory.PostHistroyDate();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<HistroyData> historys = HistoryDataJSONParse.parseSearch(response);
                        back.onSuccess(historys);
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
                Map<String, String> m = new HashMap<String, String>();
                m.put("username", username);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

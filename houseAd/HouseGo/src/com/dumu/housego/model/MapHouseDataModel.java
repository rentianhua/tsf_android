package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.Street;
import com.dumu.housego.entity.XiaoquMapHouse;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.MapHouseDataParse;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.util.XiaoQuMapHouseJSONParse;

import android.util.Log;

public class MapHouseDataModel implements IMapHouseDataModel {
    public void LoadMapHouseData(final String fromtable, final AsycnCallBack back) {
        String url = UrlFactory.PostXingzhengHouseUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<AreaHouse> areahouses = MapHouseDataParse.parseSearch(response);
                        back.onSuccess(areahouses);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
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
                map.put("fromtable", fromtable);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void LoadAreaMapHouseData(final String city, final String fromtable, final AsycnCallBack back) {
        String url = UrlFactory.PostAreaMapHouse();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<AreaHouse> streets = MapHouseDataParse.parseSearch(response);
                        back.onSuccess(streets);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
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
                map.put("fromtable", fromtable);
                map.put("city", city);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

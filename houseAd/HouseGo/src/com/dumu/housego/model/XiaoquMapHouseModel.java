package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.XiaoquMapHouse;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangMapHouseJSONParse;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.RentingMapHouseJSONParse;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.util.XiaoQuMapHouseJSONParse;

import android.util.Log;

public class XiaoquMapHouseModel implements IxiaoquMapHouseModel {
    public void xiaoqu(final String area, final String fromtable, final AsycnCallBack back) {
        String url = UrlFactory.PostXiaoQuMapHouse();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<XiaoquMapHouse> xiaoqus = XiaoQuMapHouseJSONParse.parseSearch(response);
                        back.onSuccess(xiaoqus);
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
                map.put("area", area);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void Allxiaoqu(final String xiaoqu, final String fromtable, final AsycnCallBack back) {
        String url = UrlFactory.PostXiaoQuAllHosue();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                if (response != null) {
                    if (fromtable.equals("ershou")) {
                        try {
                            List<ErShouFangRecommendData> ershous = ErShouFangMapHouseJSONParse.parseSearch(response);
                            back.onSuccess(ershous);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            List<RentingRecommendData> rentings = RentingMapHouseJSONParse.parseSearch(response);
                            back.onSuccess(rentings);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
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
                map.put("xiaoqu", xiaoqu);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

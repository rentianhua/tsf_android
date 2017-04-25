package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.SubMitErShouListJSONParse2;
import com.dumu.housego.util.SubMitRentingListJSONParse;
import com.dumu.housego.util.UrlFactory;

public class RentingWeiTuoModel implements IRentingWeiTuoModel {

    public void weituo(final String userid, final String table, final AsycnCallBack back) {
        String url = UrlFactory.PostWeiTuoGuanLi();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<RentingDetail> submitershous = SubMitRentingListJSONParse.parseSearch(response);
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
                Map<String, String> m = new HashMap<String, String>();
                m.put("userid", userid);
                m.put("table", table);
                return m;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

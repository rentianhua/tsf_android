package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class PaySuccessModel implements IPaySuccessModel {

    public void PayInfo(final String resultStatus, final String jine, final String order_no, final String trade_no, AsycnCallBack back) {
        String url = UrlFactory.PostPayStatusSuccess();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<String, String>();
                p.put("resultStatus", resultStatus);
                p.put("total_amount", jine);
                p.put("out_trade_no", order_no);
                p.put("trade_no", trade_no);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

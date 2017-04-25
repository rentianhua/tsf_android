package com.dumu.housego.model;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RegistPhoneCode;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.Gson;

import android.util.Log;

public class PhoneCodeModel implements IPhoneCodeModel {
    private RequestQueue queue;

    public PhoneCodeModel() {
        super();
        this.queue = Volley.newRequestQueue(HouseGoApp.getContext());
    }

    public void GetPhoneCode(String mob, String verify, final AsycnCallBack back) {
        String url = UrlFactory.GetPhoneCodeUrl(mob, verify);
        StringRequest request = new StringRequest(url, new Listener<String>() {
            public void onResponse(String response) {
                if (response != null) {
                    Gson gson = new Gson();
                    RegistPhoneCode phone = gson.fromJson(response, RegistPhoneCode.class);
                    String info = phone.getInfo();
                    if (phone.getSuccess() == 4) {
                        back.onSuccess("发送成功");
                    } else {
                        if (info != null && info.length() > 0)
                            back.onError(info);
                        else
                            back.onError("发送失败");
                    }
                } else {
                    back.onError("发送失败");
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError("网络不行了");
            }
        });
        queue.add(request);
    }
}

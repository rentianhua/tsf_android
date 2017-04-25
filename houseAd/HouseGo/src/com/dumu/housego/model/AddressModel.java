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
import com.dumu.housego.entity.Address;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.AddressListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class AddressModel implements IAddressModel {

    public AddressModel() {
        super();
    }

    public void address(final String pid, final AsycnCallBack back) {
        final String url = UrlFactory.PostAddressList();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<Address> addresses = AddressListJSONParse.parseSearch(response);
                        back.onSuccess(addresses);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.toString());

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("pid", pid);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

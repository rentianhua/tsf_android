package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.NewHouseListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class NewHouseListModel implements IFourDataProgramaModel {
    private List<NewHouseList> newhouselists;

    public NewHouseListModel() {
        super();
    }

    public void GetRecommedHouse(final FourDataPrograma fourdata, final AsycnCallBack back) {
        String url = UrlFactory.PostFourDataProgramaUrl();
        CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<NewHouseList> newhouselists;
                        newhouselists = NewHouseListJSONParse.parseSearch(response);
                        back.onSuccess(newhouselists);
                    } else {
                        if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                            back.onError("无对应房源数据！");
                        else
                            back.onError("已无更多房源数据");
                    }
                } catch (JSONException e) {
                    if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                        back.onError("无对应房源数据！");
                    else
                        back.onError("已无更多房源数据");
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("catid", fourdata.getCatid());
                params.put("page", fourdata.getPage());
                params.put("ct", fourdata.getCt());
                params.put("ar", fourdata.getAr());
                params.put("zx", fourdata.getZx());
                params.put("zj", fourdata.getZj());
                params.put("shi", fourdata.getShi());
                params.put("yt", fourdata.getYt());
                params.put("wy", fourdata.getWy());
                params.put("xq", fourdata.getXq());
                params.put("kwds", fourdata.getKwds());
                return params;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

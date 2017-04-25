package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class ErShouFangProgramaModel implements IFourDataProgramaModel {

    public ErShouFangProgramaModel() {
        super();
    }

    public void GetRecommedHouse(final FourDataPrograma fourdata, final AsycnCallBack back) {
        String url = UrlFactory.PostFourDataProgramaUrl();
        CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        List<ErShouFangRecommendData> ershoufangrecommends;
                        ershoufangrecommends = ErShouFangReconmendJSONParse.parseSearch(response);
                        back.onSuccess(ershoufangrecommends);
                    } catch (JSONException e) {
                        if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                            back.onError("无对应房源数据！");
                        else
                            back.onError("已无更多房源数据");
                        e.printStackTrace();
                    }
                } else {
                    if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                        back.onError("无对应房源数据！");
                    else
                        back.onError("已无更多房源数据");
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
                params.put("zj", fourdata.getZj());
                params.put("shi", fourdata.getShi());
                params.put("mj", fourdata.getMj());
                params.put("qs", fourdata.getQs());
                params.put("ly", fourdata.getLy());
                params.put("dt", fourdata.getDt());
                params.put("yt", fourdata.getYt());
                params.put("cx", fourdata.getCx());
                params.put("lc", fourdata.getLc());
                params.put("zx", fourdata.getZx());
                params.put("wy", fourdata.getWy());
                params.put("kwds", fourdata.getKwds());
                return params;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

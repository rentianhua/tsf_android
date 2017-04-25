package com.dumu.housego.model;

import java.util.List;

import org.json.JSONException;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.NewHouseHotJSONParse;
import com.dumu.housego.util.UrlFactory;

public class NewTuiJianModel implements INewTuiJianModel {

    public void Tuijian(String posid, final AsycnCallBack back) {
        String url = UrlFactory.GetTuiJianWeiList(posid);
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<NewHouseHotRecommend> tuijians = NewHouseHotJSONParse.parseSearch(response);
                        back.onSuccess(tuijians);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        });
        HouseGoApp.getQueue().add(request);
    }
}

package com.dumu.housego.model;

import java.io.IOException;
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
import com.android.volley.toolbox.StringRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.YHQinfo;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangDetialJSONParse;
import com.dumu.housego.util.GetYHQInfoJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.NewHouseDetialJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class NewHouseDetailModel implements INewHouseDetailaModel {
    private NewHouseDetail nhb;

    public NewHouseDetailModel() {
        super();
    }

    public void FindNewHouseDetail(final String catid, final String id, final AsycnCallBack back) {
        new AsyncTask<String, String, NewHouseDetail>() {
            protected NewHouseDetail doInBackground(String... params) {
                try {
                    String url = UrlFactory.PostRecommendListToDetailUrl();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("catid", catid);
                    map.put("id", id);
                    String json = HttpUtils.isToString(HttpUtils.post(url, map));
                    if (json != null && json.length() > 0) {
                        NewHouseDetail newhousedetail = NewHouseDetialJSONParse.parseSearch(json);
                        return newhousedetail;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(NewHouseDetail newhousedetail) {
                back.onSuccess(newhousedetail);
            }
        }.execute();

    }

    @Override
    public void GetYHQinfo(final String new_id, final String new_catid, final AsycnCallBack back) {
        String url = UrlFactory.PostNewHouseYHQ();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<YHQinfo> yhqs = GetYHQInfoJSONParse.parseSearch(response);
                        back.onSuccess(yhqs);
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
                Map<String, String> p = new HashMap<String, String>();
                p.put("new_id", new_id);
                p.put("new_catid", new_catid);
                return p;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}

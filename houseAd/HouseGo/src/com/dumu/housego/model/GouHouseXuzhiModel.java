package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GouHouseXuzhiJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class GouHouseXuzhiModel implements IGouHouseXuzhiModel {
    public GouHouseXuzhiModel() {
        super();
    }

    public void GouHouseXuzhi(final AsycnCallBack back) {
        new AsyncTask<String, String, List<RecommendNews>>() {
            protected List<RecommendNews> doInBackground(String... params) {
                try {
                    String url = "http://www.taoshenfang.com/index.php?g=api&m=house&a=house_xuzhi";
                    InputStream is;
                    is = HttpUtils.get(url);
                    String json = HttpUtils.isToString(is);
                    if (json != null && json.length() > 0) {
                        List<RecommendNews> recommends;
                        recommends = GouHouseXuzhiJSONParse.parseSearch(json);
                        return recommends;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(java.util.List<RecommendNews> recommends) {
                back.onSuccess(recommends);
            }
        }.execute();
    }
}

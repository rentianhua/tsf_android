package com.dumu.housego.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.util.ErShouFangDetialJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class ErShouFangDetailModel implements IErShouFangDetailaModel {

    public void FindErShouFangDetail(final String catid, final String id, final AsycnCallBack back) {
        new AsyncTask<String, String, ErShouFangDetails>() {
            protected ErShouFangDetails doInBackground(String... params) {
                try {
                    // String
                    // url=UrlFactory.PostRecommendListToDetailUrl().replace("g=Wap&a=shows",
                    // "a=api_shows");
                    String url = UrlFactory.PostRecommendListToDetailUrl();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("catid", catid);
                    map.put("id", id);
                    String json = HttpUtils.isToString(HttpUtils.post(url, map));
                    if (json != null && json.length() > 0) {
                        ErShouFangDetails ershoufangdetails = ErShouFangDetialJSONParse.parseSearch(json);
                        return ershoufangdetails;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(ErShouFangDetails ershoufangdetail) {
                back.onSuccess(ershoufangdetail);
            }
        }.execute();
    }
}

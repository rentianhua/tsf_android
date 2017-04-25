package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.util.BlockTradeDetialJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.RemoveBOM;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class BlockTradeDetailModel implements IBlockTradeDetailaModel {
    private BlockTradeDetail b;

    public BlockTradeDetailModel() {
        super();
    }

    public void FindBlockTradeDetail(final String catid, final String id, final AsycnCallBack back) {
        new AsyncTask<String, String, BlockTradeDetail>() {
            protected BlockTradeDetail doInBackground(String... params) {
                try {
                    String url = UrlFactory.GetRecommendListToDetailUrlString(catid, id);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("catid", catid);
                    map.put("id", id);
                    String json = HttpUtils.isToString(HttpUtils.post(url, map));
                    if (json != null && json.length() > 0) {
                        BlockTradeDetail e = BlockTradeDetialJSONParse.parseSearch(json);
                        return e;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(BlockTradeDetail blocktradedetail) {
                back.onSuccess(blocktradedetail);
            }
        }.execute();
    }
}

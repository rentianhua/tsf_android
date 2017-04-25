package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class RecommendHouseModel implements IRecommendHouseModel {

	public RecommendHouseModel() {
		super();
	}

	public void GetRecommedHouse(final AsycnCallBack back) {

		new AsyncTask<String, String, List<RecommendNews>>() {
			protected List<RecommendNews> doInBackground(String... params) {
				try {
					String url = UrlFactory.GetRecommendHouseUrl();
					InputStream is;
					is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					if(json != null && json.length()>0) {
						List<RecommendNews> recommends;
						recommends = JSONParse.parseSearch(json);
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
package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.ErShoutuijianJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.NewHouseReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class ErShouFangRecommendModel implements IRecommendHouseModel {

	public ErShouFangRecommendModel() {
		super();
	}

	@Override
	public void GetRecommedHouse(final AsycnCallBack back) {
		new AsyncTask<String, String, List<ErShouFangRecommendData>>() {
			protected List<ErShouFangRecommendData> doInBackground(String... params) {
				try {
					String url = UrlFactory.GetErShouFangRecommendUrl();
					InputStream is;
					is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					if(json != null && json.length()>0){
						List<ErShouFangRecommendData> ershoufangrecommends;
						ershoufangrecommends = ErShoutuijianJSONParse.parseSearch(json);
						return ershoufangrecommends;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(java.util.List<ErShouFangRecommendData> ershoufangrecommends) {
				back.onSuccess(ershoufangrecommends);
			}
		}.execute();
	}
}

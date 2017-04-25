package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.NewHouseHotJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class NewHouseHotModel implements INewHouseHotModel {

	public NewHouseHotModel() {
		super();
	}

	@Override
	public void GetNewHouseHot(final AsycnCallBack back) {
		new AsyncTask<String, String, List<NewHouseHotRecommend>>() {
			protected List<NewHouseHotRecommend> doInBackground(String... params) {
				try {
					String url = UrlFactory.GetWapNewHouseHotUrl();
					InputStream is;
					is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					if(json != null && json.length()>0){
						List<NewHouseHotRecommend> newhousehots;
						newhousehots = NewHouseHotJSONParse.parseSearch(json);
						return newhousehots;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			protected void onPostExecute(java.util.List<NewHouseHotRecommend> newhousehots) {
				back.onSuccess(newhousehots);
			}
		}.execute();
	}
}
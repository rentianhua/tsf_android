package com.dumu.housego.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.RentingDetialJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class RentingDetailModel implements IRentingDetailModel {
	public void LoadRentingData(final String catid, final String id, final AsycnCallBack back) {
		new AsyncTask<String, String, RentingDetail>() {
			protected RentingDetail doInBackground(String... params) {

				try {
					String url = UrlFactory.PostRecommendListToDetailUrl();
					Map<String, String> map = new HashMap<String, String>();

					map.put("catid", catid);
					map.put("id", id);
					String json = HttpUtils.isToString(HttpUtils.post(url, map));
					if(json != null && json.length()>0){
						RentingDetail rentingdetail = RentingDetialJSONParse.parseSearch(json);
						return rentingdetail;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(RentingDetail rentingdetail) {
				back.onSuccess(rentingdetail);
			}
		}.execute();
	}
}

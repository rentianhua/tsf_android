package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.NewHouseRecommendData;

public class NewHouseReconmendJSONParse {
	public static List<NewHouseRecommendData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		// JSONArray ary = array.get;
		List<NewHouseRecommendData> newrecommends = new ArrayList<NewHouseRecommendData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			NewHouseRecommendData n = new NewHouseRecommendData();
			JSONObject obj2 = obj.getJSONObject("data");
			n.setTitle(obj2.getString("title"));
			n.setDescription(obj2.getString("description"));
			n.setThumb(obj2.getString("thumb"));
			n.setUrl(obj2.getString("url"));
			newrecommends.add(n);
		}
		return newrecommends;

	}

}

package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GouHouseXuzhiJSONParse {
	public static List<RecommendNews> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		// JSONArray ary = array.get;
		List<RecommendNews> recommends = new ArrayList<RecommendNews>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			RecommendNews n = new RecommendNews();
			n.setTitle(j.getString("title"));
			n.setDescription(j.getString("description"));
			n.setThumb(j.getString("thumb"));
			n.setUrl(j.getString("url"));
			recommends.add(n);
		}
		return recommends;

	}

}

package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ErShoutuijianJSONParse {
	public static List<ErShouFangRecommendData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<ErShouFangRecommendData> ershoufangrecommends = new ArrayList<ErShouFangRecommendData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			ErShouFangRecommendData n = new ErShouFangRecommendData();

			JSONObject j=obj.getJSONObject("data");
			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			
			n.setTitle(j.getString("title"));
			n.setZongjia(j.getString("zongjia"));
			n.setJianzhumianji(j.getString("jianzhumianji"));
			n.setTing(j.getString("ting"));
			n.setShi(j.getString("shi"));
			n.setThumb(j.getString("thumb"));
			n.setCity_name(j.getString("city_name"));
			n.setArea_name(j.getString("area_name"));

			ershoufangrecommends.add(n);
		}
		return ershoufangrecommends;

	}

}

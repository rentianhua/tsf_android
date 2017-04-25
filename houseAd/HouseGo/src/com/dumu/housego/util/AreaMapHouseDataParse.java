package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AreaMapHouseDataParse {
	public static List<AreaHouse> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<AreaHouse> areahouses = new ArrayList<AreaHouse>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			AreaHouse n = new AreaHouse();

			n.setCid(obj.getString("cid"));
			n.setHouse_count(obj.getString("house_count"));
			n.setId(obj.getString("id"));
			n.setName(obj.getString("name"));
			n.setPid(obj.getString("pid"));

			areahouses.add(n);
		}

		return areahouses;

	}

}

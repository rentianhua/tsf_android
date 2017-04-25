package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.NewHouseHotRecommend;

public class NewHouseHotJSONParse {
	public static List<NewHouseHotRecommend> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<NewHouseHotRecommend> newhousehots = new ArrayList<NewHouseHotRecommend>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			NewHouseHotRecommend n = new NewHouseHotRecommend();
			JSONObject obj2 = obj.getJSONObject("data");
			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setTitle(obj2.getString("title"));
			n.setJunjia(obj2.getString("junjia"));
			n.setThumb(obj2.getString("thumb"));
			n.setLoupandizhi(obj2.getString("loupandizhi"));
			n.setUrl(obj2.getString("url"));
			n.setMianjiarea(obj2.getString("mianjiarea"));
			newhousehots.add(n);
		}
		return newhousehots;

	}

}

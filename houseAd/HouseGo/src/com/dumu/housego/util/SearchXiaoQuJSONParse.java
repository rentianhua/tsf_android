package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.SearchXiaoQu;

public class SearchXiaoQuJSONParse {
	public static List<SearchXiaoQu> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<SearchXiaoQu> xiaoqus = new ArrayList<SearchXiaoQu>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			SearchXiaoQu n = new SearchXiaoQu();

			n.setId(obj.getString("id"));
			n.setTitle(obj.getString("title"));


			xiaoqus.add(n);
		}
		return xiaoqus;

	}

}

package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.HistroyData;
import com.dumu.housego.entity.NewHouseDetail;

public class HistoryDataJSONParse {
	public static List<HistroyData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<HistroyData> histroys = new ArrayList<HistroyData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			HistroyData n = new HistroyData();
			
			n.setAction(j.getString("action"));
			n.setInputtime(j.getString("inputtime"));
			n.setTitle(j.getString("title"));
			n.setType(j.getString("type"));
			n.setUsername(j.getString("username"));
		

				histroys.add(n);
			}

		
		return histroys;

	}

}

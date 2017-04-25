package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.NewHouseList;

public class NewHouseListJSONParse {
	public static List<NewHouseList> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<NewHouseList> newhouselists = new ArrayList<NewHouseList>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			NewHouseList n = new NewHouseList();

			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setTitle(obj.getString("title"));
			n.setThumb(obj.getString("thumb"));
			n.setAreaname(obj.getString("areaname"));
			n.setCityname(obj.getString("cityname"));
			n.setJunjia(obj.getString("junjia"));
			n.setLoupandizhi(obj.getString("loupandizhi"));
			n.setTypeid(obj.getString("typeid"));
			n.setZonghe(obj.getString("zonghe"));
			newhouselists.add(n);
		}
		return newhouselists;

	}

}

package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.XiaoquMapHouse;

public class XiaoQuMapHouseJSONParse {
	public static List<XiaoquMapHouse> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<XiaoquMapHouse> agentdatas = new ArrayList<XiaoquMapHouse>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			XiaoquMapHouse n = new XiaoquMapHouse();
			
			n.setCatid(obj.getString("catid"));
			n.setHouse_count(obj.getString("house_count"));
			n.setId(obj.getString("id"));
			n.setTitle(obj.getString("title"));
			n.setJingweidu(obj.getString("jingweidu"));

			agentdatas.add(n);
		}
		return agentdatas;

	}

}

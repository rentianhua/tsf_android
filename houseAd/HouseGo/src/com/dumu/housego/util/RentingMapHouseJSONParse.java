package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.RentingRecommendData;

public class RentingMapHouseJSONParse {
	public static List<RentingRecommendData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<RentingRecommendData> ershoufangrecommends = new ArrayList<RentingRecommendData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			RentingRecommendData n = new RentingRecommendData();

			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setPosid(obj.getString("posid"));
			n.setTitle(obj.getString("title"));
			n.setZujin(obj.getString("zujin"));
			n.setMianji(obj.getString("mianji"));
			n.setTing(obj.getString("ting"));
			n.setShi(obj.getString("shi"));
			n.setChaoxiang(obj.getString("chaoxiang"));
			n.setThumb(obj.getString("thumb"));
			n.setBiaoqian(obj.getString("biaoqian"));
			
			ershoufangrecommends.add(n);
		}
		return ershoufangrecommends;

	}

}

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

public class ErShouFangReconmendJSONParse {
	public static List<ErShouFangRecommendData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<ErShouFangRecommendData> ershoufangrecommends = new ArrayList<ErShouFangRecommendData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			ErShouFangRecommendData n = new ErShouFangRecommendData();

			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setPosid(obj.getString("posid"));
			n.setTitle(obj.getString("title"));
			n.setZongjia(obj.getString("zongjia"));
			n.setJianzhumianji(obj.getString("jianzhumianji"));
			n.setTing(obj.getString("ting"));
			n.setShi(obj.getString("shi"));
			n.setChaoxiang(obj.getString("chaoxiang"));
			n.setThumb(obj.getString("thumb"));
			n.setBiaoqian(obj.getString("biaoqian"));
			n.setCityname(obj.getString("cityname"));
			n.setAreaname(obj.getString("areaname"));
			n.setZonghe(obj.getString("zonghe"));
			
			ershoufangrecommends.add(n);
		}
		return ershoufangrecommends;

	}

}

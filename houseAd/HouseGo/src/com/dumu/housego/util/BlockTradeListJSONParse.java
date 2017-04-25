package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BlockTradeListJSONParse {
	public static List<BlockTradeList> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<BlockTradeList> blocktrades = new ArrayList<BlockTradeList>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			BlockTradeList n = new BlockTradeList();

			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setPosid(obj.getString("posid"));
			n.setTitle(obj.getString("title"));
			 n.setZongjia(obj.getString("zongjia"));
			 n.setZhandimianji(obj.getString("zhandimianji"));
			n.setThumb(obj.getString("thumb"));
			n.setCityname(obj.getString("cityname"));
			n.setAreaname(obj.getString("areaname"));
			n.setWuyetype(obj.getString("wuyetype"));
			n.setHezuofangshi(obj.getString("hezuofangshi"));
			n.setZonghe(obj.getString("zonghe"));
			n.setShiyongnianxian(obj.getString("shiyongnianxian"));
			
			blocktrades.add(n);
		}
		return blocktrades;

	}

}

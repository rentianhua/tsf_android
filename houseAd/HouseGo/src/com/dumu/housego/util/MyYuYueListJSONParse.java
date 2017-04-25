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
import com.dumu.housego.entity.YuYueData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MyYuYueListJSONParse {
	public static List<YuYueData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<YuYueData> yuyuedatas = new ArrayList<YuYueData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			YuYueData n = new YuYueData();
			n.setCatid(obj.getString("catid"));
			n.setFromid(obj.getString("fromid"));
			n.setFromtable(obj.getString("fromtable"));
			n.setFromuser(obj.getString("fromuser"));
			n.setId(obj.getString("id"));
			n.setInputtime(obj.getString("inputtime"));
			n.setListorder(obj.getString("listorder"));
			n.setLock(obj.getString("lock"));
			n.setType(obj.getString("type"));
			n.setUsername(obj.getString("username"));
			n.setYuyuedate(obj.getString("yuyuedate"));
			n.setYuyuetime(obj.getString("yuyuetime"));
			n.setZhuangtai(obj.getString("zhuangtai"));
			if(obj.has("house") && !obj.isNull("house"))
			{
				JSONObject obj2 = obj.getJSONObject("house");
				n.setHouseid(obj2.getString("id"));
				n.setTitle(obj2.getString("title"));
			}
			else
			{
				n.setHouseid("");
				n.setTitle("");
				continue;
			}
			yuyuedatas.add(n);
		}
		return yuyuedatas;
	}
}

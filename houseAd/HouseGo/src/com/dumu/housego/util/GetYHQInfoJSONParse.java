package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.YHQinfo;

public class GetYHQInfoJSONParse {
	public static List<YHQinfo> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<YHQinfo> yhqs = new ArrayList<YHQinfo>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			YHQinfo n = new YHQinfo();

			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setTitle(obj.getString("title"));
			n.setDescription(obj.getString("description"));
			n.setMaxnum(obj.getString("maxnum"));
			n.setNew_catid(obj.getString("new_catid"));
			n.setNew_id(obj.getString("new_id"));
			n.setPay(obj.getString("pay"));
			n.setVmoney(obj.getString("vmoney"));
			n.setYigou(obj.getString("yigou"));
			n.setZhuangtai(obj.getString("zhuangtai"));

			yhqs.add(n);
		}
		return yhqs;

	}

}

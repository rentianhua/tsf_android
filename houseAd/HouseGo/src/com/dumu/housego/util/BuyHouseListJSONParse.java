package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BuyHouseListJSONParse {
	public static List<QiuZuBuyHouseList> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<QiuZuBuyHouseList> qiuzulists = new ArrayList<QiuZuBuyHouseList>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			QiuZuBuyHouseList n = new QiuZuBuyHouseList();

			n.setArea(j.getString("area"));
			n.setCatid(j.getString("catid"));
			n.setChenghu(j.getString("chenghu"));
			n.setCity(j.getString("city"));
			n.setId(j.getString("id"));
			n.setInputtime(j.getString("inputtime"));
			n.setIslink(j.getString("islink"));
			n.setKeywords(j.getString("keywords"));
			n.setListorder(j.getString("listorder"));
			n.setLock(j.getString("lock"));
			n.setPosid(j.getString("posid"));
			n.setProvince(j.getString("province"));
			n.setShi(j.getString("shi"));
			n.setStatus(j.getString("status"));
			n.setSysadd(j.getString("sysadd"));
			n.setThumb(j.getString("thumb"));
			n.setTitle(j.getString("title"));
			n.setTypeid(j.getString("typeid"));
			n.setUpdatetime(j.getString("updatetime"));
			n.setUsername(j.getString("username"));
			n.setViewsupdatetime(j.getString("viewsupdatetime"));
			n.setZongjiarange(j.getString("zongjiarange"));

			qiuzulists.add(n);

		}
		return qiuzulists;

	}

}

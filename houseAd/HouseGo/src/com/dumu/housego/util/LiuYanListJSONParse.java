package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LiuYanListJSONParse {
	public static List<LiuYanList> parseSearch(String json) throws JSONException {
		
		
//		JSONArray array = new JSONArray(json);
		JSONObject obj=new JSONObject(json);
		List<LiuYanList> qiuzulists = new ArrayList<LiuYanList>();
		
		
		
		for (int i = 0; i <1000; i++) {
			if(!obj.isNull(String.valueOf(i))){
				JSONObject j = obj.getJSONObject(String.valueOf(i));
				
				LiuYanList n = new LiuYanList();
				n.setContent(j.getString("content"));
				n.setId(j.getString("id"));
				n.setInputtime(j.getString("inputtime"));
				n.setRealname(j.getString("realname"));
				n.setUserpic(j.getString("userpic"));
				n.setFrom_status(j.getString("from_status"));
				n.setFrom_uid(j.getString("from_uid"));
				n.setTo_uid(j.getString("to_uid"));
				n.setTo_status(j.getString("to_status"));
				n.setIds(j.getInt("ids"));
				n.setYidu(j.getInt("yidu"));
				n.setWeidu_sum(j.getString("weidu_sum"));
				qiuzulists.add(n);
			}

		}
		return qiuzulists;

	}

}

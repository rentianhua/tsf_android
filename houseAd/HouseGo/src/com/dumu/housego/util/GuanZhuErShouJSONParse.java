package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;

public class GuanZhuErShouJSONParse {
	public static List<ErShouFangDetails> parseSearch(String json)
			throws JSONException {
		JSONArray array = new JSONArray(json);
		List<ErShouFangDetails> ershoufangdetails = new ArrayList<ErShouFangDetails>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			ErShouFangDetails n = new ErShouFangDetails();
			n.setId(obj.getString("id"));
			if (obj.has("house") && !obj.isNull("house")) {
				JSONObject j = obj.getJSONObject("house");
				n.setPosid(j.getString("id"));
				n.setCatid(j.getString("catid"));
				n.setTitle(j.getString("title"));
				n.setXiaoquname(j.getString("xiaoquname"));
				n.setShi(j.getString("shi"));
				n.setTing(j.getString("ting"));
				n.setJianzhumianji(j.getString("jianzhumianji"));
				n.setChaoxiang(j.getString("chaoxiang"));
				n.setZongjia(j.getString("zongjia"));
				n.setCeng(j.getString("ceng"));
				n.setZongceng(j.getString("zongceng"));
				n.setFangling(j.getString("fangling"));
				n.setJianzhutype(j.getString("jianzhutype"));
				n.setDitiexian(j.getString("ditiexian"));
				n.setThumb(j.getString("thumb"));
			} else {
				n.setPosid("");
				n.setCatid("");
				n.setTitle("");
				n.setXiaoquname("");
				n.setShi("");
				n.setTing("");
				n.setJianzhumianji("");
				n.setChaoxiang("");
				n.setZongjia("");
				n.setCeng("");
				n.setZongceng("");
				n.setFangling("");
				n.setJianzhutype("");
				n.setDitiexian("");
				n.setThumb("");
			}
			ershoufangdetails.add(n);
		}
		return ershoufangdetails;
	}

}

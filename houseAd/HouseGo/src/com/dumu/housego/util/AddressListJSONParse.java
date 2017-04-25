package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AddressListJSONParse {
	public static List<Address> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			Address n = new Address();
			n.setCid(obj.getString("cid"));
			n.setId(obj.getString("id"));
			n.setName(obj.getString("name"));
			n.setPid(obj.getString("pid"));

			addresses.add(n);

		}
		return addresses;

	}

}

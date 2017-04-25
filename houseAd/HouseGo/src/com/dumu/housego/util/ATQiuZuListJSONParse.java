package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ATQiuZuListJSONParse {
    public static List<QiuzuANDQiuGou> parseSearch(String json) throws JSONException {
        JSONArray array = new JSONArray(json);
        List<QiuzuANDQiuGou> ershoufangdetails = new ArrayList<QiuzuANDQiuGou>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject j = array.getJSONObject(i);
            QiuzuANDQiuGou n = new QiuzuANDQiuGou();

            n.setArea_name(j.getString("area_name"));
            n.setChenghu(j.getString("chenghu"));
            n.setCity_name(j.getString("city_name"));
            n.setInputtime(j.getString("updatetime"));
            n.setLock(j.getString("lock"));
            n.setUsername(j.getString("username"));
            n.setZujinrange(j.getString("zujinrange"));
            n.setId(j.getString("id"));
            ershoufangdetails.add(n);
        }
        return ershoufangdetails;
    }
}

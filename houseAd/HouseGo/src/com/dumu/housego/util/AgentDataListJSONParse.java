package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AgentDataListJSONParse {
    public static List<AgentData> parseSearch(String json) throws JSONException {
        JSONArray array = new JSONArray(json);
        List<AgentData> agentdatas = new ArrayList<AgentData>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            AgentData n = new AgentData();

            JSONObject obj2 = obj.getJSONObject("info");

            n.setUsername(obj2.getString("username"));
            n.setCtel(obj2.getString("vtel"));
            n.setUserpic(obj2.getString("userpic"));
            n.setUserid(obj2.getString("userid"));

            n.setRealname(obj.getString("realname"));
            n.setComm_count(obj.getString("comm_count"));
            n.setMainarea(obj.getString("mainarea"));
            n.setDengji(obj.getString("dengji"));
            n.setBiaoqian(obj.getString("biaoqian"));

            if (obj.has("fb_rate") && !obj.isNull("fb_rate"))
                n.fb_rate = obj.getInt("fb_rate");

            if (obj.has("chengjiao_count") && !obj.isNull("chengjiao_count"))
                n.chengjiao_count = obj.getInt("chengjiao_count");

            if (obj.has("weituo_count") && !obj.isNull("weituo_count"))
                n.weituo_count = obj.getInt("weituo_count");

            if (obj.has("daikan_count") && !obj.isNull("daikan_count"))
                n.daikan_count = obj.getInt("daikan_count");

            agentdatas.add(n);
        }
        return agentdatas;

    }

}

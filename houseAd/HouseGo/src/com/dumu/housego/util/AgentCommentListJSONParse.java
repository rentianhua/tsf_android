package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AgentCommentListJSONParse {
	public static List<AgentCommentList> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<AgentCommentList> comments = new ArrayList<AgentCommentList>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			AgentCommentList n = new AgentCommentList();
				n.setId(j.getString("id"));
				n.setAgent(j.getString("agent"));
				n.setApproved(j.getString("approved"));
				n.setAuthor(j.getString("author"));
				n.setAuthor_ip(j.getString("author_ip"));
				n.setComment_id(j.getString("comment_id"));
				n.setContent(j.getString("content"));
				n.setDate(j.getString("date"));
				n.setParent(j.getString("parent"));
				n.setStb(j.getString("stb"));
				n.setUser_id(j.getString("user_id"));
				n.setUserpic(j.getString("userpic"));
				comments.add(n);
		}
		return comments;

	}

}

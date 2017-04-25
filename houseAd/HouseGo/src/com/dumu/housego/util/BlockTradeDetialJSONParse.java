package com.dumu.housego.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;

public class BlockTradeDetialJSONParse {

	public static BlockTradeDetail parseSearch(String json) throws JSONException {
		JSONObject j = new JSONObject(json);
		BlockTradeDetail n = new BlockTradeDetail();
		n.setAddress(j.getString("address"));
		JSONObject b=j.getJSONObject("fubiao");
		 n.setAllow_comment(b.getString("allow_comment"));
		n.setArea(j.getString("area"));
		n.setAreaname(j.getString("areaname"));
		n.setBianhao(j.getString("bianhao"));
		n.setCatid(j.getString("catid"));
		n.setCity(j.getString("city"));
		n.setCityname(j.getString("cityname"));
		n.setContactname(j.getString("contactname"));
		 n.setContent(b.getString("content"));
		n.setDayviews(j.getString("dayviews"));
		n.setDescription(j.getString("description"));
		n.setDitiexian(j.getString("ditiexian"));
		n.setDituzuobiao(j.getString("dituzuobiao"));
		n.setGdinfo(j.getString("gdinfo"));
		n.setGoudijine(j.getString("goudijine"));
		n.setHasgd(j.getString("hasgd"));
		n.setHezuofangshi(j.getString("hezuofangshi"));
		n.setId(j.getString("id"));
		n.setInputtime(j.getString("inputtime"));
		n.setIslink(j.getString("islink"));
		n.setJingweidu(j.getString("jingweidu"));
		n.setKeywords(j.getString("keywords"));
		n.setListorder(j.getString("listorder"));
		 n.setMaxcharperpage(b.getString("maxcharperpage"));
		n.setMonthviews(j.getString("monthviews"));
		 n.setPaytype(b.getString("paytype"));
		n.setPosid(j.getString("posid"));
		n.setProvince(j.getString("province"));
		 n.setRelation(b.getString("relation"));
		 n.setRenzheng(b.getString("renzheng"));
		n.setShiyongnianxian(j.getString("shiyongnianxian"));
		n.setStatus(j.getString("status"));
		n.setStyle(j.getString("style"));
		n.setSysadd(j.getString("sysadd"));
		n.setTags(j.getString("tags"));
		n.setTel(j.getString("tel"));
		 n.setTemplate(b.getString("template"));
		n.setThumb(j.getString("thumb"));
		n.setTitle(j.getString("title"));
		n.setTudishuxing(j.getString("tudishuxing"));
		n.setTypeid(j.getString("typeid"));
		n.setUpdatetime(j.getString("updatetime"));
		n.setUrl(j.getString("url"));
		n.setUsername(j.getString("username"));
		n.setViews(j.getString("views"));
		n.setViewsupdatetime(j.getString("viewsupdatetime"));
		n.setWeekviews(j.getString("weekviews"));
		n.setWuyetype(j.getString("wuyetype"));
		 n.setXuzhi(b.getString("xuzhi"));
		n.setYesterdayviews(j.getString("yesterdayviews"));
		n.setZhandimianji(j.getString("zhandimianji"));
		n.setZongjia(j.getString("zongjia"));
		n.setZuobiaodizhi(j.getString("zuobiaodizhi"));

		return n;

	}

}

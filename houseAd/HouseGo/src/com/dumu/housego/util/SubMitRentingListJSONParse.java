package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;

public class SubMitRentingListJSONParse {
	public static List<RentingDetail> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<RentingDetail> rentingdetails = new ArrayList<RentingDetail>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			RentingDetail n = new RentingDetail();

			n.setAddress(j.getString("address"));
			n.setArea(j.getString("area"));
			n.setAreaname(j.getString("areaname"));
			n.setBianhao(j.getString("bianhao"));
			n.setBiaoqian(j.getString("biaoqian"));
			n.setCatid(j.getString("catid"));
			n.setCeng(j.getString("ceng"));
			n.setChaoxiang(j.getString("chaoxiang"));
			n.setChu(j.getString("chu"));
			n.setCity(j.getString("city"));
			n.setCityname(j.getString("cityname"));
			n.setCzreason(j.getString("czreason"));
			n.setDayviews(j.getString("dayviews"));
			n.setDescription(j.getString("description"));
			n.setDesc(j.getString("desc"));
			n.setDitiexian(j.getString("ditiexian"));
			n.setDituzuobiao(j.getString("dituzuobiao"));
			n.setFangling(j.getString("fangling"));
			n.setFangwupeitao(j.getString("fangwupeitao"));
			n.setFenpei_time(j.getString("fenpei_time"));
			n.setFukuan(j.getString("fukuan"));
			n.setHidetel(j.getString("hidetel"));
			n.setHuxing(j.getString("huxing"));
			n.setHuxingjieshao(j.getString("huxingjieshao"));
			n.setId(j.getString("id"));
			n.setInputtime(j.getString("inputtime"));
			n.setIslink(j.getString("islink"));
			n.setJianzhutype(j.getString("jianzhutype"));
			n.setJiaotong(j.getString("jiaotong"));
			n.setJingweidu(j.getString("jingweidu"));
			n.setJjr_id(j.getString("jjr_id"));
			n.setKeywords(j.getString("keywords"));
			n.setLeixing(j.getString("leixing"));
			n.setLiangdian(j.getString("liangdian"));
			n.setListorder(j.getString("listorder"));
			n.setLock(j.getString("lock"));
			n.setLouceng(j.getString("louceng"));
			n.setMianji(j.getString("mianji"));
			n.setMonthviews(j.getString("monthviews"));
			
			
			n.setPosid(j.getString("posid"));
			n.setProvince(j.getString("province"));
			n.setPub_type(j.getString("pub_type"));
			n.setShenghuopeitao(j.getString("shenghuopeitao"));
			n.setShi(j.getString("shi"));
			n.setShuxing(j.getString("shuxing"));

			n.setStatus(j.getString("status"));
			n.setStyle(j.getString("style"));
			n.setSysadd(j.getString("sysadd"));
			n.setTags(j.getString("tags"));
			n.setThumb(j.getString("thumb"));
			n.setTing(j.getString("ting"));
			n.setTitle(j.getString("title"));
			n.setTypeid(j.getString("typeid"));
			n.setUpdatetime(j.getString("updatetime"));
			n.setUrl(j.getString("url"));
			n.setUsername(j.getString("username"));
			n.setViews(j.getString("views"));
			n.setViewsupdatetime(j.getString("viewsupdatetime"));
			n.setWeekviews(j.getString("weekviews"));
			n.setWei(j.getString("wei"));
			n.setWuyetype(j.getString("wuyetype"));
			n.setXiaoqu(j.getString("xiaoqu"));
			n.setXiaoquname(j.getString("xiaoquname"));
			n.setXiaoquintro(j.getString("xiaoquintro"));
			n.setXiaoqutype(j.getString("xiaoqutype"));
			n.setYangtai(j.getString("yangtai"));
			n.setYesterdayviews(j.getString("yesterdayviews"));
			n.setYezhushuo(j.getString("yezhushuo"));
			n.setZaizu(j.getString("zaizu"));
			n.setZhuangxiu(j.getString("zhuangxiu"));
			n.setZongceng(j.getString("zongceng"));
			n.setZujin(j.getString("zujin"));
			n.setZulin(j.getString("zulin"));
			n.setZxdesc(j.getString("zxdesc"));
			n.setZuobiaodizhi(j.getString("zuobiaodizhi"));
			n.setApply_state(j.getString("apply_state"));

			
			if(!j.getString("pics").equals("")&& !j.getString("pics").equals("a:0:{}")){
			
				JSONArray arr=j.getJSONArray("pics");
				List<Pics> pics = new ArrayList<Pics>();
				for (int x= 0; x < arr.length(); x++) {
					JSONObject o = arr.getJSONObject(x);
					Pics p= new Pics();

					p.setAlt(o.getString("alt"));
					p.setUrl(o.getString("url"));
				
					pics.add(p);
				}
				
				n.setPics(pics);
				
			}else{
				n.setPics(null);
			}
	

			
			rentingdetails.add(n);
		}

		return rentingdetails;

	}

}

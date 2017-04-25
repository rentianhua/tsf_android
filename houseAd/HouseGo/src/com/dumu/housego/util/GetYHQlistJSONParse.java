package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.YHQ;

public class GetYHQlistJSONParse {
    public static List<YHQ> parseSearch(String json) throws JSONException {
        JSONArray array = new JSONArray(json);
        List<YHQ> yhqs = new ArrayList<YHQ>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject j = array.getJSONObject(i);
            YHQ n = new YHQ();

            n.setId(j.getString("id"));
            n.setBuyname(j.getString("buyname"));
            n.setBuytel(j.getString("buytel"));
            n.setCoupon_id(j.getString("coupon_id"));
            n.setCoupon_name(j.getString("coupon_name"));
            n.setDifu(j.getString("difu"));
            n.setHouse_id(j.getString("house_id"));
            n.setHouse_title(j.getString("house_title"));
            n.setInputtime(j.getString("inputtime"));
            n.setOrder_no(j.getString("order_no"));
            n.setShifu(j.getString("shifu"));
            n.setTrade_status(j.getString("trade_status"));
            n.setUserid(j.getString("userid"));
            n.setPay_status(j.getString("pay_status"));
            n.setBuyer_email(j.getString("buyer_email"));
            n.setTrade_no(j.getString("trade_no"));
            n.setPaytime(j.getString("paytime"));
            n.setDescription(j.getString("description"));
            n.setIsused(j.getString("isused"));
            yhqs.add(n);
        }
        return yhqs;

    }

}

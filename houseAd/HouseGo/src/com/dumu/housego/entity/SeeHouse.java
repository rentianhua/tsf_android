package com.dumu.housego.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeeHouse implements Serializable {
    public String id;
    public String catid;
    public String username;
    public String type;
    public String lock;
    public String realname;
    public String yuyuedate;
    public String yuyuetime;

    public static List<SeeHouse> filterWeekSee(List<SeeHouse> sees) {
        List<SeeHouse> rts = new ArrayList<>();
        if (sees != null && sees.size() > 0) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            for (int i = 0; i < sees.size(); i++) {
                try {
                    SeeHouse see = sees.get(i);
                    if (see.yuyuedate != null && see.yuyuedate.length() > 0) {
                        Date seeDate = df.parse(see.yuyuedate);
                        long diff = now.getTime() - seeDate.getTime();
                        if (diff < 1000 * 60 * 60 * 24 * 7)
                            rts.add(see);
                    }
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return rts;
    }

    public static List<SeeHouse> parseSeeHouseList(JSONArray list) {
        try {
            if (list != null && list.length() > 0) {
                List<SeeHouse> rts = new ArrayList<>();
                for (int i = 0; i < list.length(); i++) {
                    JSONObject item = list.getJSONObject(i);
                    SeeHouse see = parseSeeHouse(item);
                    if (see != null) {
                        rts.add(see);
                    }
                }
                return rts;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SeeHouse parseSeeHouse(JSONObject item) {
        try {
            SeeHouse see = new SeeHouse();

            if (item.has("id") && !item.isNull("id"))
                see.id = item.getString("id");

            if (item.has("catid") && !item.isNull("catid"))
                see.catid = item.getString("catid");

            if (item.has("username") && !item.isNull("username"))
                see.username = item.getString("username");

            if (item.has("type") && !item.isNull("type"))
                see.type = item.getString("type");

            if (item.has("lock") && !item.isNull("lock"))
                see.lock = item.getString("lock");

            if (item.has("realname") && !item.isNull("realname"))
                see.realname = item.getString("realname");

            if (item.has("yuyuedate") && !item.isNull("yuyuedate"))
                see.yuyuedate = item.getString("yuyuedate");

            if (item.has("yuyuetime") && !item.isNull("yuyuetime"))
                see.yuyuetime = item.getString("yuyuetime");

            return see;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

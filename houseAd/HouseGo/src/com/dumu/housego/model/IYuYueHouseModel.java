package com.dumu.housego.model;

public interface IYuYueHouseModel extends IModel {
	void Loadyuyue(String formid, String fromtable, String username, String fromuserid, String type, String yuyuedate,
			String yuyuetime, String t, AsycnCallBack back);
}

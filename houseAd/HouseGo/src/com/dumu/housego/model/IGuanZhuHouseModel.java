package com.dumu.housego.model;

public interface IGuanZhuHouseModel extends IModel {
	void LoadGuanZhuHouse(String fromid, String fromtable, String userid, String username, String type, String t,
			AsycnCallBack back);
}

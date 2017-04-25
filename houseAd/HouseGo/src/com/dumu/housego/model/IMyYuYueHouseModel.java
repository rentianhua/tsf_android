package com.dumu.housego.model;

public interface IMyYuYueHouseModel extends IModel {
	void LoadYuYueData(String username, AsycnCallBack back);

	void DeleteYuYue(String id, String userid, String username, AsycnCallBack back);
}

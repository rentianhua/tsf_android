package com.dumu.housego.model;

public interface IBuyHouseModel extends IModel {
	void buyhouselist(String username, String table, AsycnCallBack back);

	void buyhousedelete(String id, String userid, String username, AsycnCallBack back);
}

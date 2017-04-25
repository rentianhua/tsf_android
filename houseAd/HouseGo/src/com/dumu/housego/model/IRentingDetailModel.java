package com.dumu.housego.model;

public interface IRentingDetailModel extends IModel {
	void LoadRentingData(String catid, String id, AsycnCallBack back);
}

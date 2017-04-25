package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IMapHouseDataModel {
	void LoadMapHouseData(String fromtable, AsycnCallBack back);
	void LoadAreaMapHouseData(String city,String fromtable, AsycnCallBack back);
}

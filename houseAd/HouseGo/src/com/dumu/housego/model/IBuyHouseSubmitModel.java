package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IBuyHouseSubmitModel {
	void buyhousesubmit(String username, String province, String city, String area, String shi, String zongjiarange,
			String chenghu, String title, AsycnCallBack back);
}

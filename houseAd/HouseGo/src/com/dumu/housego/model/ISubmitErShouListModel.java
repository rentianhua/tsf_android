package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface ISubmitErShouListModel {
	void submitershoulist(String username, String userid, String table, AsycnCallBack back);

	void submitrentinglist(String username, String userid, String table, AsycnCallBack back);
}

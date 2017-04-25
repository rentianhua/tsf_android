package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IQiuZuListModel {
	void QiuZuList(String username, String table, AsycnCallBack back);

	void DeleteQiuZu(String id, String userid, String username, AsycnCallBack back);
}

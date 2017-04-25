package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IChengJiaoESModel {
	void ChengJiaoES(String username,String userid, String table, AsycnCallBack back);
}

package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IMyDaiKanModel {
	void LoadDaiKanData(String username, String t,AsycnCallBack back);
}

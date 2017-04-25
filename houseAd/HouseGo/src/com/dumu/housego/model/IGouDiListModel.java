package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IGouDiListModel {
	void GoudiList(String userid,AsycnCallBack back);
	void GouDiDelete(String id,String userid,AsycnCallBack back);
}

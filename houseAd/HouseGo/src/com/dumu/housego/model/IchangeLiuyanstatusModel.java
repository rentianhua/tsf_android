package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IchangeLiuyanstatusModel {
	void changeStatus(String id,String userid,AsycnCallBack back);
}

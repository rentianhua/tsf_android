package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IPhoneCodeModel {
	void GetPhoneCode(String mob, String verify, AsycnCallBack back);
}

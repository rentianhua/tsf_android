package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IAddLiuYanModel {
	void addLiuyan(String from_uid,String to_uid,String content,AsycnCallBack back);
}
